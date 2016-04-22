package com.novas.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.mikephil.charting.data.Entry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by novas on 16/4/21.
 */
public class DataModel {
    private static DataModel dataModel=null;
    public HashMap<String, ArrayList<Element>> showdataMap=new HashMap<>();
    Random random=new Random();
    public static DataModel getInstance()
    {
        if(dataModel==null)
        {
            dataModel=new DataModel();
        }
        return dataModel;
    }
    private DataModel()
    {

    }

    //生成LineChartModel,type=1表示只显示温度，type＝2表示只显示湿度，type＝3表示显示温度和湿度
    public LineChartModel getLineChartModel(String date,int type)
    {
        ArrayList<Element> arrayList=showdataMap.get(date);
        ArrayList<Entry> entryArrayList=new ArrayList<>();
        for(int i=0;i<arrayList.size();i++)
        {
            Element element=arrayList.get(i);
            Entry entry=null;
            if(type==1)
            {
                entry=new Entry((float)element.tmpr,i);
            }
            if(type==2)
            {
                entry=new Entry((float)element.moisture,i);
            }
            entryArrayList.add(entry);
        }
        ArrayList<String> xvalues=new ArrayList<>();
        for(int i=0;i<24;i++)
        {
            xvalues.add(i+"");
        }
        LineChartModel lineChartModel=new LineChartModel();
        lineChartModel.yValues=entryArrayList;
        lineChartModel.xValues=xvalues;
        return lineChartModel;
    }
    public void getData()
    {
        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase("/data/data/com.novas.datashow/yinger.db",null);
        Cursor cursor=sqLiteDatabase.query("yinger", null, null, null, null, null, null);
        System.out.println("count="+cursor.getCount());
        cursor.moveToFirst();
        int count=0;

        while (cursor.moveToNext())
        {
                count++;
                double time=cursor.getDouble(0);
                String date=cursor.getString(1);
                double tmpr=cursor.getDouble(2);
                double moisture=cursor.getDouble(3);
                Element element=new Element();
                element.time=time;
                element.tmpr=tmpr;
                element.moisture=moisture;
                if(showdataMap.containsKey(date))
                {
                    ArrayList<Element> list=showdataMap.get(date);
                    list.add(element);
                }
                else
                {
                    ArrayList<Element> list=new ArrayList<>();
                    showdataMap.put(date,list);
                }
                System.out.println(element+"date="+date+"count="+count);
        }

    }
    public void init()throws Exception
    {
        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase("/data/data/com.novas.datashow/yinger.db",null);
      //  sqLiteDatabase.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        for(int i=1;i<=10;i++)
        {
            String key="2016-4-"+i;
            ArrayList<Element> list=new ArrayList<>();
            for(int j=0;j<24;j++)
            {
                String time=j+":00:00";
                Date date = dateFormat.parse(key+" "+time);
                System.out.println(date.getTime());
                Element element=new Element();
                element.time=date.getTime();
                element.tmpr=37.4+2*random.nextDouble()-1;
                element.moisture=0.45+random.nextDouble()/5;
                System.out.println(element.toString());
                list.add(element);
            }
            showdataMap.put(key,list);
        }
        String sql="create table yinger (time real primary key,date varchar(20),tmpr real,moisture real)";
        sqLiteDatabase.execSQL(sql);
        for(Map.Entry<String,ArrayList<Element>> entry:showdataMap.entrySet())
        {
            String key=entry.getKey();
            ArrayList<Element> elementArrayList=entry.getValue();
            for(int j=0;j<elementArrayList.size();j++)
            {
                System.out.println("插入");
                Element element=elementArrayList.get(j);
                ContentValues contentValues=new ContentValues();
                contentValues.put("time",element.time);
                contentValues.put("date",key);
                contentValues.put("tmpr",element.tmpr);
                contentValues.put("moisture",element.moisture);
                sqLiteDatabase.insert("yinger", null, contentValues);
            }
        }
    }
}
