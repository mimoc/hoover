package com.example.hoover.range;

import com.example.hoover.model.Path;
import com.example.hoover.model.Point;
import com.example.hoover.model.Spot;

import java.util.List;
/* determines if a Patch is in Path range and removes the Patch*/
public class Range {

    public static void isInRange(Point start,Point end, List<Spot> spotList){
        List<Spot> spotCopyList = List.copyOf(spotList);
        for ( Spot spot : spotCopyList ){
            int spotX = spot.getPoint().getX();
            int spotY = spot.getPoint().getY();
            if ( (spotX >= Math.min(start.getX(), end.getX()) && spotX <= Math.max(start.getX(),end.getX()))
            && (spotY >= Math.min(start.getY(), end.getY()) && spotY <= Math.max(start.getY(),end.getY())))
            {
                spotList.remove(spot);
            }
        }

    }
}
