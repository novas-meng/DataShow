package com.novas.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.novas.model.DataModel;
import com.novas.model.Element;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by novas on 16/4/21.
 */
public class datastoreActivity extends Activity
{
    DataModel datamodel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase("/data/data/com.novas.datashow/yinger.db",null);
        try
        {
        //    new  DataModel().init();
            DataModel model=new DataModel();
            model.getData();
            for(Map.Entry<String,ArrayList<Element>> entry:model.showdataMap.entrySet())
            {
                String key=entry.getKey();
                ArrayList<Element> elementArrayList=entry.getValue();
                for(int j=0;j<elementArrayList.size();j++)
                {
                    System.out.println("插入");
                    Element element=elementArrayList.get(j);
                    System.out.println(element+"date="+key);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        */
        datamodel=DataModel.getInstance();
    }
}
