package com.example.hoover.service;

import com.example.hoover.model.Path;
import com.example.hoover.model.Point;
import com.example.hoover.model.Spot;
import com.example.hoover.range.Range;
import com.example.hoover.request.HooverRequest;
import com.example.hoover.response.HooverResponse;
import com.example.hoover.validation.Validate;
import com.example.hoover.validation.ValidateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* Service builds the trajectory of the vacuum by calculating each time the start and endpoint for the vacuum until it changes direction again
* For each path , like NNN or SS or E or WW, it is verified if any spots were cleaned; if cleaned they are not taken into account an more*/
@Service
public class HooverService {
    private Path path;
    private List spotList;

    @Autowired
    ValidateRequest validateRequest;
    @Autowired
    HooverRepositoryService hooverRepositoryService;

    public HooverService() {
        this.path = new Path();
        this.spotList = new ArrayList();
    }

    public  synchronized HooverResponse countCleanedSpots(final HooverRequest hooverRequest) throws JsonProcessingException {
        HooverResponse hooverResponse;
        validateRequest(hooverRequest);
        if (!Validate.validationErrorList.isEmpty()) {
            hooverResponse = new HooverResponse();
            hooverResponse.setErrorList(Validate.validationErrorList);
            return hooverResponse;
        }
        constructModel(hooverRequest);
        hooverResponse = constructResponse(getCleanedSpots());
        saveRequestResponseToDataBase(hooverRequest,hooverResponse);

        return hooverResponse;
    }

    private void validateRequest(final HooverRequest hooverRequest){
        validateRequest.validate(hooverRequest);
    }
    /*Creates the objects Path - defines the start and end for each continuous trajectory like NNN*/
    /*Creates the List of Patches */
    public void constructModel(final HooverRequest hooverRequest){
        int roomX = hooverRequest.getRoomSize()[0];
        int roomY = hooverRequest.getRoomSize()[1];
        path = buildHooverPath(hooverRequest.getVacuumCoordinates(),hooverRequest.getInstructions(),roomX,roomY);
        spotList = buildSpotsPoints(hooverRequest.getSpotsCoordinates());
    }

    private HooverResponse constructResponse(int cleanedSpots){
        Integer[] lastVacuumCoordinates = new Integer[2];
        Point lastVacuumPoint = ((Point)(((LinkedList)path.getPointList()).getLast()));
        lastVacuumCoordinates[0] = lastVacuumPoint.getX();
        lastVacuumCoordinates[1] = lastVacuumPoint.getY();

        return new HooverResponse(lastVacuumCoordinates,cleanedSpots);

    }

    private void saveRequestResponseToDataBase(final HooverRequest hooverRequest, final HooverResponse hooverResponse) throws JsonProcessingException {
        hooverRepositoryService.save(hooverRequest, hooverResponse);
    }

    /*
    * each Patch is verified into each Path ; if found the Patch is removed from the list so that will not be counted again*/
    private int getCleanedSpots(){
        int spotsNr = spotList.size();
        List<Point> pathPoints = path.getPointList();
        for ( int i = 0;i<pathPoints.size()-1;i++){
            if (spotList.isEmpty()) return 0;
            Range.isInRange(pathPoints.get(i),pathPoints.get(i+1),spotList);
        }

        return spotsNr - spotList.size();
    }

    private List<Spot> buildSpotsPoints(Integer[][] spotsCoordinates){
        List<Spot> pointList = new ArrayList<>();
        for (int i = 0;i< spotsCoordinates.length;i++){
            pointList.add(new Spot(spotsCoordinates[i]));
        }
        return pointList;
    }

    /* generates the points for the hoover Path
    * NNNWWEEES will mean 4 paths: NNN WW EEE S which will result into 5 points*/
    private Path buildHooverPath(Integer[] startPoint,String directions,int maxX,int maxY){
        Path path = new Path();
        path.addPoint(new Point(startPoint));
        int count;
        int i = 0;
        while ( i < directions.length() -1){
            char direction = directions.charAt(i);
            char nextDirection = directions.charAt(++i);
            count = 1;
            while ((nextDirection == direction)){
                count++;
                if ((i == directions.length() -1 )) break;
                nextDirection = directions.charAt(++i);
            }
            startPoint = changeCoordinates(direction,count,startPoint,maxX,maxY);
            path.addPoint(new Point(startPoint));
        }
        /*take into account the last direction in case it is different from the previous one*/
        if (  directions.charAt(i) != directions.charAt(i-1)) {
            char direction = directions.charAt(i);
            startPoint = changeCoordinates(direction,1,startPoint,maxX,maxY);
            path.addPoint(new Point(startPoint));
        };
        return path;
    }

    private Integer[] changeCoordinates(char direction,int squares,Integer[] coordinates,int maxX,int maxY){
        switch (direction){
            case 'N':{
                coordinates[1]+=squares;
                break;
            }
            case 'S':{
                coordinates[1]-=squares;
                break;
            }
            case 'W':{
                coordinates[0]-=squares;
                break;
            }
            case 'E':{
                coordinates[0]+=squares;
                break;
            } default:
                break;
        }
        if (coordinates[0]>maxX) coordinates[0] = maxX;
        if (coordinates[1]>maxY) coordinates[1] = maxY;

        return coordinates;
    }

}
