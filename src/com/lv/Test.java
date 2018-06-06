package com.lv;

public class Test {
    public static void main(String[] args) {
        double t = (double)10 / 60;
        double a = maxStayingTime(2*t, 18);
        double b = maxStayingTime(2*t, 866);
        double c = maxStayingTime(2*t, 2817);
        double d = maxStayingTime(2*t, 2);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        double res = a * b * c * d;
        System.out.println(res);

        double tal = Math.pow(0.8, 4);
        System.out.println("tal + "  +tal);


    }

    public static double maxStayingTime(double t, int times) {
        return 1- Math.pow((times * t), 0) * Math.exp(-times * t) / 1;
    }
}
