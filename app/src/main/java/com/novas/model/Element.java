package com.novas.model;

/**
 * Created by novas on 16/4/21.
 */
public class Element {
    public double time;
    //温度
    public double tmpr;
    //湿度
    public double moisture;

    @Override
    public String toString() {
        return "time="+time+" tmpr="+tmpr+" "+moisture;
    }
}
