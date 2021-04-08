package com.example.hoover.model;

public class Point {
    private Integer x,y;

    public Point(Integer[] coordinates) {
        this.x = coordinates[0];
        this.y = coordinates[1];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
