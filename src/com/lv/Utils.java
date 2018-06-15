package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Utils {
    //从hotspot 文件获得所有的hotpot
    public static ArrayList<Hotspot> getAllHotspot() throws Exception {
        ArrayList<Hotspot> hotspotArrayList = new ArrayList<>();

        File file = new File("C:\\E\\dataSet\\2018-05-29\\五秒\\阈值300\\result.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            int num = Integer.parseInt(data[2]);
            Hotspot hotspot = new Hotspot(x, y, num);
            hotspotArrayList.add(hotspot);

        }
        return hotspotArrayList;
    }

    //判断 Point 和 HotSpot 点之间的距离
    public static double getDistanceBetweenPointAndHotSpot(Point p1, Hotspot p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX())* (p1.getX() - p2.getX())+(p1.getY() - p2.getY())* (p1.getY() - p2.getY())));
    }
}
