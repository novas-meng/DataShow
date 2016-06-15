package com.novas.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.novas.activity.SearchDeviceActivity;
import com.novas.activity.datastoreActivity;
import com.novas.controller.HomeController;
import com.novas.datashow.R;


/**
 * Created by novas on 16/4/8.
 */
public class settingfragment extends Fragment
{
    LinearLayout bluetoothlinearLayout;
    LinearLayout datastorelinearlayout;
    HomeController homeController;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_setting,container,false);
        homeController=HomeController.getHomeControllerInstance(null);
        context=homeController.getContext();
        bluetoothlinearLayout=(LinearLayout)view.findViewById(R.id.bluetooth);
        datastorelinearlayout=(LinearLayout)view.findViewById(R.id.datastore);
        bluetoothlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SearchDeviceActivity.class);
                startActivity(intent);
            }
        });
        datastorelinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,datastoreActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
