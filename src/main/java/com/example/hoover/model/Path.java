package com.example.hoover.model;

import java.util.LinkedList;
import java.util.List;
/* List of points that determine the trajectory of the vacuum cleaner*/
public class Path  {
        final List pointList = new LinkedList<Point>() ;

    public List getPointList() {
        return pointList;
    }
    public void addPoint(Point point){
        pointList.add(point);
    }
}
