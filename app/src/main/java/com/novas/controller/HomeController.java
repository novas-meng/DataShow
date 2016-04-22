package com.novas.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import com.novas.datashow.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by novas on 16/3/21.
 */
public class HomeController implements control
{
    private static HomeController homeController;
    private Activity context;
    private FragmentTransaction ft;
    private Hashtable<String, Fragment> fragmentHashtable=new Hashtable<>();
    int[] ids=null;
    View[] linearLayouts=null;
    View[] textviews=null;
    List<String> namelist=new ArrayList<>();
    int currentindex=1;
    private HomeController(Activity context)
    {
        this.context=context;
        AppCompatActivity activity=(AppCompatActivity)context;
        ft=activity.getSupportFragmentManager().beginTransaction();
    }
    public Context getContext()
    {
        return context;
    }
    public static HomeController getHomeControllerInstance(Activity context)
    {
        if(homeController==null)
        {
            homeController=new HomeController(context);
        }
        return homeController;
    }
    public void addFragment(int layoutid,String frname)
    {
        Fragment fr=null;
        try {
            Class c=Class.forName("com.novas.fragment."+frname);
            fr=(Fragment)c.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        fragmentHashtable.put(frname,fr);
        namelist.add(frname);
        ft.add(layoutid,fr);
        ft.hide(fr);
    }

    public void showFragment(String fragment)
    {
        linearLayouts=new View[fragmentHashtable.size()];
        textviews=new View[linearLayouts.length];
        ids=new int[fragmentHashtable.size()];
        linearLayouts[0]=context.findViewById(R.id.tab1);
        linearLayouts[1]=context.findViewById(R.id.tab2);
        linearLayouts[2]=context.findViewById(R.id.tab3);
        textviews[0]=context.findViewById(R.id.tab1text);
        textviews[1]=context.findViewById(R.id.tab2text);
        textviews[2]=context.findViewById(R.id.tab3text);
        ids[0]=R.id.tab1;
        ids[1]=R.id.tab2;
        ids[2]=R.id.tab3;
        tabClickListener tabClickListener=new tabClickListener();
       // AppCompatActivity activity=(AppCompatActivity)context;
        //ft=activity.getSupportFragmentManager().beginTransaction();
        for(int i=0;i<linearLayouts.length;i++)
        {
            linearLayouts[i].setOnClickListener(tabClickListener);
        }
        ft.show(fragmentHashtable.get(fragment));
        TextView textView=(TextView)context.findViewById(R.id.tab1text);
        textView.setTextColor(Color.GREEN);
        currentindex=namelist.indexOf(fragment);
       // if(ft.)
        ft.commit();
    }

    @Override
    public void destroy() {
        if(context!=null)
        {
            context=null;
            ft=null;
            homeController=null;
        }
    }
    public void showtab(int id)
    {
        int index=0;
        for(int i=0;i<ids.length;i++)
        {
            if(ids[i]==id)
            {
                index=i;
            }
        }
        System.out.println("currentindex="+currentindex+"index="+index);
        ((TextView)textviews[index]).setTextColor(Color.GREEN);
        if(index!=currentindex)
        {
            AppCompatActivity activity=(AppCompatActivity)context;
            ft=activity.getSupportFragmentManager().beginTransaction();
            ((TextView)textviews[index]).setTextColor(Color.GREEN);
            ((TextView)textviews[currentindex]).setTextColor(Color.WHITE);
            Fragment oldfragment=fragmentHashtable.get(namelist.get(currentindex));
            Fragment newfragment=fragmentHashtable.get(namelist.get(index));
            ft.hide(oldfragment);
            ft.show(newfragment);
            ft.commit();
            currentindex=index;
        }

    }

    class tabClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            showtab(v.getId());
        }
    }
}
