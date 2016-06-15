package com.novas.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.LocalSocket;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.novas.controller.HomeController;
import com.novas.datashow.IBookManager;
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
    private static final java.lang.String DESCRIPTOR = "com.novas.datashow.Inovas";
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("进程连接上了");
            System.out.println("ibinder="+iBinder);

            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                iBinder.transact(IManager.Stub.TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
           // IManager bookManager=IManager.Stub.asInterface(iBinder);
           // try {
           //     bookManager.addBook();
           // } catch (RemoteException e) {
           //     e.printStackTrace();
           // }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println("on create");
       // Loc
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
       // Intent intent=new Intent(this,MYservice.class);
      //  bindService(intent,connection, Context.BIND_AUTO_CREATE);
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