package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main4 {
//  计算在每个hotspot 等待一定时间碰到的概率
    public static void main(String[] args) throws Exception{
        int interval = 21;
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-29\\四秒\\hotspot中sensor的访问情况\\" + interval + "时间段");
        File[] files = fileFolder.listFiles();
        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<Sensor> arriviedSensor = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Integer times = Integer.parseInt(data[1]);
                Sensor sensor = new Sensor(data[0], times);
                if (sensor.getTimes() > 0) {
                    arriviedSensor.add(sensor);
                }
            }

            File outFile = new File("C:\\E\\dataSet\\2018-05-29\\四秒\\在每个hotspot等待一定时间碰到的概率\\" + interval + "时间段\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile, true);
            double t = (double)5 / 60;
            if (arriviedSensor.size() == 0) {
                fileWriter.write("没有sensor到达");
                fileWriter.close();
            } else {
                int maxStayingTime = getMaxStayingTime(arriviedSensor);
                for (int i = 1; i <= maxStayingTime; i++) {
                    for (Sensor sensor : arriviedSensor) {
                        double probability = stayTime(i * t, sensor.getTimes());
                        String outString = "等待时间: " + i + "," + sensor.getSensorNum() + "," + probability + "\n";
                        fileWriter.write(outString);
                    }
                }
            }

            fileWriter.close();

        }
    }
//    得到最大等待时间
    private static int getMaxStayingTime(ArrayList<Sensor> arriviedSensor) {
        int totalSensor = arriviedSensor.size();
        double total = Math.pow(0.9, totalSensor);
        double calcu = 0;
        double t = (double)5 / 60;
        int numOfTimes = 1;
        while (calcu < total) {
            calcu = 1;
            for (Sensor sensor : arriviedSensor) {
                calcu = calcu * stayTime(numOfTimes * t, sensor.getTimes());
            }
            numOfTimes++;
        }

        numOfTimes--;
        if (numOfTimes > 12) {
            numOfTimes = 12;
        }
        return numOfTimes;
    }
//    等待t时间碰到的概率
    private static double stayTime(double t, int times) {
        return 1- (Math.pow((times * t), 0) * Math.exp(-times * t)) / 1;
    }
}
