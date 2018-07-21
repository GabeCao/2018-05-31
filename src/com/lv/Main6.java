package com.lv;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main6 {
//将数据分成14时间段
    public static void main(String[] args) throws Exception{
        String sensorsDataPath = "C:\\Users\\lv\\Desktop\\数据\\2009-03-15\\2009-03-15_5_每五秒取一次数据";
        File fileFolder = new File(sensorsDataPath);
        File[] files = fileFolder.listFiles();
        for (File file: files) {

            for (int i = 1; i <= 14; i++) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;

                File outFile1 = new File("C:\\Users\\lv\\Desktop\\数据\\2009-03-15\\2009-03-15_6_将数据分成14个时间段\\" + i + "时间段\\" + file.getName());

                if (!outFile1.exists()) {
                    outFile1.getParentFile().mkdirs();
                }

                FileWriter fileWriter1 = new FileWriter(outFile1, true);
                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split(",");
                    int data_sec = strToSeconds(data[2]);
                    int sec1 = (i - 1) * 3600;
                    int sec2 = i * 3600;
                    if (data_sec >= sec1 && data_sec < sec2) {
                        fileWriter1.write(line + "\n");
                    }
                }
                fileWriter1.close();

            }
        }
    }

    public static int strToSeconds(String str) {
        String[] data = str.split(":");
        int hour = Integer.parseInt(data[0]) - 8;
        int minute = Integer.parseInt(data[1]);
        int seconds = Integer.parseInt(data[2]);
        return hour * 3600 + minute * 60 + seconds;
    }
}
