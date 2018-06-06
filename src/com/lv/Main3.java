package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main3 {
//    就算最大等待时间
    public static void main(String[] args) throws Exception{
        int time = 21;
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-29\\hotspot\\"+ time+"时间段");
        File[] files = fileFolder.listFiles();

        File outFile = new File("C:\\E\\dataSet\\2018-05-29\\hotspot最大等待时间\\" +time + "时间段"  + ".txt");
        FileWriter fileWriter = new FileWriter(outFile, true);

        for (File file : files) {
            String hotspotNum = file.getName().substring(0, file.getName().lastIndexOf("."));

            ArrayList<Sensor> arrivedSensor = new ArrayList<>();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String sensorNum = data[0];
                Integer times = Integer.parseInt(data[1]);
                Sensor s = new Sensor(sensorNum, times);
                if (s.getTimes() > 0) {
                    arrivedSensor.add(s);
                }
            }

            int totalSensor = arrivedSensor.size();
            int numOfTimes = 1;
            if (totalSensor > 0) {
                double total = Math.pow(0.8, totalSensor);
                double calcu = 0;

                double t = (double)10 / 60;

                while (calcu < total) {
                    calcu = 1;

                    for (Sensor sensor : arrivedSensor) {
                        calcu = calcu * maxStayingTime(numOfTimes * t, sensor.getTimes());
                    }
                    numOfTimes++;
                }
                numOfTimes--;
                String out = hotspotNum + "," + numOfTimes + "\n";
                fileWriter.write(out);

            }
            else {
                System.out.println("......");
            }

        }
        fileWriter.close();
    }

    public static double maxStayingTime(double t, int times) {
        return 1- Math.pow((times * t), 0) * Math.exp(-times * t) / 1;
    }

    public static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
}
