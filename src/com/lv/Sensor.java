package com.lv;

public class Sensor {
    private String sensorNum;
    private Integer times;

    public Sensor(String sensorNum, Integer times) {
        this.sensorNum = sensorNum;
        this.times = times;
    }

    public String getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(String sensorNum) {
        this.sensorNum = sensorNum;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
