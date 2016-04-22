package com.novas.model;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by novas on 16/4/20.
 */
public class LineChartModel {
   // public
    String title=null;
    ArrayList<Entry> yValues;
    ArrayList<String> xValues;
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setxValues(ArrayList<String> xValues)
    {
        this.xValues=xValues;
    }
    public void setyValues(ArrayList<Entry> yValues)
    {
        this.yValues=yValues;
    }
    public String getTitle()
    {
        return title;
    }
    public ArrayList<String> getxValues()
    {
        return xValues;
    }
    public ArrayList<Entry> getyValues()
    {
        return yValues;
    }
}
