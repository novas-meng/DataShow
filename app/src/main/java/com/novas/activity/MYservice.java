package com.novas.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by novas on 16/5/19.
 */
public class MYservice extends Service
{

    private IManager.Stub binder=new IManager.Stub()
    {

        @Override
        public void addBook() throws RemoteException {
            System.out.println("add book");
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("ibinder="+binder);
        return binder;
    }
}
