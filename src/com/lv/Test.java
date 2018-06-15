package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception{
        ArrayList<Hotspot> hotspotArrayList = Utils.getAllHotspot();
        Hotspot hotspot_10 = null;
        for (Hotspot hotspot : hotspotArrayList) {
            if (hotspot.getNumber() == 10) {
                hotspot_10 = hotspot;
            }
        }
        int count = 0;
        File file = new File("C:\\E\\dataSet\\2018-05-29\\五秒\\时间段\\8时间段\\12.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            Point point = new Point(x, y);
            if (Utils.getDistanceBetweenPointAndHotSpot(point, hotspot_10) < 60) {
                count ++;
            }
        }
        System.out.println(count);

    }
}
