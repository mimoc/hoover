package com.example.hoover.model;

public class Spot {
    private Point point;

    public Spot(Integer[] point) {
        this.point = new Point(point);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
