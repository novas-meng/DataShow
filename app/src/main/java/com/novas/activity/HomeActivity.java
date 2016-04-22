package com.novas.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.novas.controller.HomeController;
import com.novas.datashow.R;
import com.novas.model.DataModel;

/**
 * Created by novas on 16/3/19.
 */
public class HomeActivity extends AppCompatActivity
{
    private HomeController homeController;
    private Activity mContext;
    private String mJarPath;
    private String mClassName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println("on create");
        DataModel dataModel=DataModel.getInstance();
        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase("/data/data/com.novas.datashow/yinger.db", null);
        // sqLiteDatabase.compileStatement("")
       // Cursor cursor=sqLiteDatabase.query("yinng", null, null, null, null, null, null);
       // System.out.println("count="+cursor.getCount());
        try
        {
          //  dataModel.init();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        dataModel.getData();
        homeController=HomeController.getHomeControllerInstance(this);
        homeController.addFragment(R.id.home_tab_container,"showfragment");
        homeController.addFragment(R.id.home_tab_container,"alarmfragment");
        homeController.addFragment(R.id.home_tab_container, "settingfragment");
        homeController.showFragment("showfragment");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("on destroy");
        homeController.destroy();
    }
}