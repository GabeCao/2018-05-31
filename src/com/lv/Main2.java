package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class Main2 {
    //得到每个时间段 访问hotspot 的sensor 的情况
    public static void main(String[] args) throws Exception {
        //int time = 21;
        for (int time = 1; time <= 14; time++) {
            //这里注意修改，获得所有的hotspot
            ArrayList<Hotspot> hotspotArrayList = Utils.getAllHotspot();
            //获得每个时间段的数据
            File fileFolder = new File("C:\\Users\\lv\\Desktop\\数据\\2009-03-15\\2009-03-15_6_将数据分成14个时间段\\" + time + "时间段");
            File[] files = fileFolder.listFiles();

            for (File file : files) {
                String sensorName = file.getName().substring(0, file.getName().lastIndexOf("."));
                for (Hotspot hotspot : hotspotArrayList) {
                    hotspot.getVisitInfo().put(sensorName, 0);
                }

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
                            hotspot.getVisitInfo().put(sensorName, hotspot.getVisitInfo().get(sensorName) + 1);
                        }
                    }

                }
                System.out.println(file.getName());
            }

            for (Hotspot hotspot : hotspotArrayList) {
                File outFile = new File("C:\\Users\\lv\\Desktop\\数据\\2009-03-15\\2009-03-15_7_greedy_hotspot中sensor的访问情况\\" + time + "时间段\\" + hotspot.getNumber() + ".txt");
                if (!outFile.exists()) {
                    outFile.getParentFile().mkdirs();
                }
                FileWriter fileWriter = new FileWriter(outFile);
                for (Map.Entry<String, Integer> entry : hotspot.getVisitInfo().entrySet()) {
//                    if(entry.getValue() >= 8) {
//                        String out = entry.getKey() + "," + entry.getValue() + "\n";
//                        fileWriter.write(out);
//                    }else {
//                        String out = entry.getKey() + "," + '0' + "\n";
//                        fileWriter.write(out);
//                    }

                    String out = entry.getKey() + "," + entry.getValue() + "\n";
                    fileWriter.write(out);
                }
                fileWriter.close();
            }
        }
    }
}
