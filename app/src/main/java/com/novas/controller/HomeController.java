package com.novas.controller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
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
    //发出报警声
    public void alarm()
    {
        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify2 = new Notification.Builder(context)
                .setSmallIcon(R.drawable.bluetooth) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                        // icon)
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")// 设置在status
                        // bar上显示的提示文字
                .setContentTitle("Notification Title")// 设置在下拉status
                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText("This is the notification message")// TextView中显示的详细内容
                .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .getNotification(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
        notify2.defaults = Notification.DEFAULT_ALL;
        nm.notify(1, notify2);

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
