package com.lv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hotspot {
    private double x;
    private double y;
    private int number;
    private int times;
    private Map<String, Integer> visitInfo;

    public Map<String, Integer> getVisitInfo() {
        return visitInfo;
    }

    public void setVisitInfo(Map<String, Integer> visitInfo) {
        this.visitInfo = visitInfo;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Hotspot(double x, double y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.times = 0;
        this.visitInfo = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Hotspot{" +
                "x=" + x +
                ", y=" + y +
                ", number=" + number +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
