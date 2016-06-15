package com.novas.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.*;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.novas.UI.LineChartView;
import com.novas.controller.HomeController;
import com.novas.datashow.R;
import com.novas.model.DataModel;
import com.novas.model.LineChartModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by novas on 16/4/8.
 */
public class showfragment extends Fragment
{
    HomeController homeController=null;
    Context context;
    View lineChartView;
    private LineChart mLineChart;
    DataModel dataModel=null;
    int type;
    String date;
    Spinner datespinner;
    Spinner typespinner;
    Spinner realspinner;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           // super.handleMessage(msg);
            dataModel.onChange();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeController=HomeController.getHomeControllerInstance(null);
        context=homeController.getContext();
        dataModel=DataModel.getInstance();
        dataModel.register(this);
        View view=inflater.inflate(R.layout.fragment_show,container,false);

        typespinner=(Spinner)view.findViewById(R.id.spinner1);
        datespinner=(Spinner)view.findViewById(R.id.spinner2);
        realspinner=(Spinner)view.findViewById(R.id.spinner3);
        realspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("及时显示");
                //dataModel.onChange();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // dataModel.onChange();
                        for (int i = 0; i < 10; i++) {
                            handler.obtainMessage().sendToTarget();
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        typespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("select");
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        datespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mLineChart = (LineChart) view.findViewById(R.id.linechart);
        LineChartModel model=dataModel.getLineChartModel("2016-4-1",1);
       // LineData mLineData = getLineData(24, 5);
        LineData lineData=getLineData(model);
        showChart(mLineChart, lineData, Color.rgb(114, 188, 223));
     //   new Thread(new Runnable() {
      //      @Override
      //      public void run() {
      //          dataModel.onChange();
        //    }
      //  }).start();
        return view;
    }
    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // no description text
      //  lineChart.setDescription("发顺丰");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

    /**
     * 生成一个数据
   //  * @param count 表示图表中有多少个坐标点
   //  * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(LineChartModel model) {
     //   ArrayList<String> xValues = new ArrayList<String>();
     //   for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
      //      xValues.add("" + i*2);
     //   }

        // y轴的数据
     //   ArrayList<Entry> yValues = new ArrayList<Entry>();
      //  for (int i = 0; i < count; i++) {
      //      float value = (float) (Math.random() * range+35) + 3;
        //    yValues.add(new Entry(value, i));
       // }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(model.getyValues(), "测试折线图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色
        List<ILineDataSet> l=new ArrayList<ILineDataSet>();
        l.add(lineDataSet);
        // create a data object with the datasets
        LineData lineData = new LineData(model.getxValues(),l);
        return lineData;
    }
    public void realTimeRefresh()
    {
        type=typespinner.getSelectedItemPosition()+1;
        System.out.println("type="+type+" "+date);
        LineChartModel model=dataModel.getRealtimeLineChartModel(type);
        LineData lineData=getLineData(model);
        showChart(mLineChart, lineData, Color.rgb(114, 188, 223));
    }
    //当温度或者湿度超过阈值的时候，进行报警
    public void alarm()
    {
        homeController.alarm();
    }
    public void refresh()
    {
        type=typespinner.getSelectedItemPosition()+1;
        date=datespinner.getSelectedItem().toString();
        System.out.println("type="+type+" "+date);
        LineChartModel model=dataModel.getLineChartModel(date,type);
        LineData lineData=getLineData(model);
        showChart(mLineChart, lineData, Color.rgb(114, 188, 223));
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
