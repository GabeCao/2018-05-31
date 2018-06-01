package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    //获得每个时间段的sensor 对hotspot 的访问情况
    public static void main(String[] args) throws Exception{
        int num = 21;
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-29\\"+num+"点时间段数据");
        File[] files = fileFolder.listFiles();
        for (File file : files) {
            ArrayList<Hotspot> hotspotArrayList = Utils.getAllHotspot();

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                Point point = new Point(x, y);
                for (Hotspot hotspot : hotspotArrayList) {
                    if (Utils.getDistanceBetweenPointAndHotSpot(point, hotspot) < 60) {
                        hotspot.setTimes(hotspot.getTimes() + 1);
                    }
                }
            }

            File outFile = new File("C:\\E\\dataSet\\2018-05-29\\"+num+"点时间段访问hotspot\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile, true);
            for (Hotspot hotspot : hotspotArrayList) {
                String outString = hotspot.getX() + "," + hotspot.getY() + "," + hotspot.getNumber() +"," + hotspot.getTimes();
                fileWriter.write(outString + "\n");
            }
            fileWriter.close();
        }


    }
}
