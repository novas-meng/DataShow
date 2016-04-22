package com.novas.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.novas.datashow.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by novas on 16/4/20.
 */
public class bluetoothActivity extends Activity
{
    BluetoothAdapter bluetoothAdapter=null;
    private Set<BluetoothDevice> pairedDevices;
    private ListView listView;
  //  String args="<P>　　小儿<a href=\"http://jbk.familydoctor.com.cn/info491/\" target=\"blank\">先天性心脏病</a>（简称先心病）是胎儿时期心脏血管发育异常而形成的先天畸形，是小儿最常见的<a href=\"http://jbk.familydoctor.com.cn/info6785/\" target=\"blank\">心脏病</a>。其发生的原因多数是由于孕妇在怀孕的最初三个月内受病毒<a href=\"http://zzk.familydoctor.com.cn/857/\" target=\"blank\">感染</a>、放射性辐射、某些药物的影响、缺乏营养以及某些遗传因素，使胎儿的心脏发育异常，从而造成小儿先天性心脏病。常见的先天性心脏病主要有<a href=\"http://jbk.familydoctor.com.cn/info2720/\" target=\"blank\">房间隔缺损</a>、<a href=\"http://zzk.familydoctor.com.cn/9331/\" target=\"blank\">室间隔缺损</a>、法乐氏四联症、<a href=\"http://jbk.familydoctor.com.cn/info2678/\" target=\"blank\">动脉导管未闭</a>、<a href=\"http://jbk.familydoctor.com.cn/info4284/\" target=\"blank\">肺动脉瓣狭窄</a>等。</P>";
 String args="\t\t<div id=\"viewContent\" class=\"viewContent\"> \n" +
          "\t\t\t\t<P>　　小儿<a href=\"http://jbk.familydoctor.com.cn/info491/\" target=\"blank\">先天性心脏病</a>（简称先心病）是胎儿时期心脏血管发育异常而形成的先天畸形，是小儿最常见的<a href=\"http://jbk.familydoctor.com.cn/info6785/\" target=\"blank\">心脏病</a>。其发生的原因多数是由于孕妇在怀孕的最初三个月内受病毒<a href=\"http://zzk.familydoctor.com.cn/857/\" target=\"blank\">感染</a>、放射性辐射、某些药物的影响、缺乏营养以及某些遗传因素，使胎儿的心脏发育异常，从而造成小儿先天性心脏病。常见的先天性心脏病主要有<a href=\"http://jbk.familydoctor.com.cn/info2720/\" target=\"blank\">房间隔缺损</a>、<a href=\"http://zzk.familydoctor.com.cn/9331/\" target=\"blank\">室间隔缺损</a>、法乐氏四联症、<a href=\"http://jbk.familydoctor.com.cn/info2678/\" target=\"blank\">动脉导管未闭</a>、<a href=\"http://jbk.familydoctor.com.cn/info4284/\" target=\"blank\">肺动脉瓣狭窄</a>等。</P>\n" +
          "<P>　　随着医学科学的发展，许多先心病可以早期手术治疗。因此及早发现可疑症状是关键，以便及时检查，明确诊断，采取必要的措施。对以下几种症状要引起注意：</P>\n" +
          "<P>　　1、婴儿哭声低微，<a href=\"http://zzk.familydoctor.com.cn/286/\" target=\"blank\">声音嘶哑</a>。</P>\n" +
          "<P>　　2、<a href=\"http://zzk.familydoctor.com.cn/9/\" target=\"blank\">呼吸</a>急促，吃奶无力。</P>\n" +
          "<P>　　3、胃口小，生长发育不良。</P>\n" +
          "<P>　　4、面色苍白，烦躁不安，多汗，剧烈活动或哭吵后唇周发紫。</P>\n" +
          "<P>　　5、抵抗力弱，容易患感冒、支气管炎和肺炎等病，且患病后不易恢复。</P>\n" +
          "<P>　　近些年来，国内先心病诊断技术和外科技术发展很快，大多数小儿先天性心脏病通过手术纠治的成功率已达95％以上。但是有些患儿因家长疏忽，延迟求医，使病情已达晚期，或失去手术机会，或并发心肺功能不全，增加了手术危险性。为此，我们提醒家长：</P>\n" +
          "<P>　　（1）尽早带孩子去医院检查，明确心脏畸形性质、程度，决定手术合适的年龄，以免抱着“等大一点再治”的想法，延误病情。</P>\n" +
          "<P>　　（2）对有心脏杂音但无症状的儿童，也不能疏忽大意，应定期进行体格检查，包括拍胸片，做心电图，以了解杂音的变化和心肺负荷的情况。必要时考虑手术以预防心内膜炎等并发症。</P>\n" +
          "<P>　　（3）对于因年龄或其他因素需要等待手术的患儿，要注意防止感冒，培养刷牙习惯，保持口腔卫生。发现蛀牙、扁桃体炎要积极治疗。如有长期发热不退，要及早请医师诊治，避免剧烈体育活动，以免加重心肺负担。</P>\n" +
          "<P>　　在配合医生积极治疗的同时，家长的悉心护理也很重要，先天性心脏病患儿在家庭中的护理应注意如下几方面：</P>\n" +
          "<P>　　1、尽量让孩子保持安静，尽量不使患儿哭闹，避免患儿情绪激动，减少不必要的刺激。大些的孩子生活要有规律，动静结合，既不能在外边到处乱跑（严格禁止跑跳和剧烈运动），以免加重心脏负担。同时要保证充足的睡眠。</P>\n" +
          "<P>　　2、<a href=\"http://zzk.familydoctor.com.cn/7860/\" target=\"blank\">心功能不全</a>的孩子往往出汗较多，需保持皮肤清洁，夏天勤洗澡，冬天用热毛巾擦身（注意保暖），勤换衣裤。多喂水，以保证足够的水分。</P>\n" +
          "<P>　　3、患儿宜少食多餐，需保证足够的蛋白质和维生素的摄入，给予的饮食尽可能多样化，易消化。有先天性心脏病的婴儿，喂养比较困难，吸奶时往往易气促乏力而停止吮吸，且易呕吐和大量出汗，故喂奶时可用滴管滴入，以减轻患儿体力消耗。</P>\n" +
          "<P>　　4、保持大便能畅，若大便干燥、排便困难时，过分用力会增加腹压，加重心脏的负担，甚至会产生严重后果。如2～3天无大便，可用开塞露通便。</P>\n" +
          "<P>　　5、居室内保持空气流通，患儿尽量避免到人多拥挤的公共场所逗留，以减少呼吸道感染的机会。应随天气冷暖及时增减衣服，密切注意预防感冒。</P>\n" +
          "<P>　　6、先天性心脏病的患儿体质弱，易感染疾病，尤以呼吸道疾病为多见，故应仔细护理，随着季节的变换，及进增减衣服。如家庭成员中有上呼吸道感染时，应采取隔离措施，平时应尽量少带患儿去公共场所。一旦患儿出现感染时，应积极控制感染。</P>\n" +
          "<P>　　7、定期去医院门诊随访，严格遵照医嘱服药，尤其是强心、利尿药，由于其药理特性，必须绝对控制剂量，按时、按疗程服用，以确保疗效。</P>\n" +
          "<P>　　8、经手术治疗的先心病患儿，术后3个月内要加强护理。注意饮食营养；保暖，防止着凉。安慰和鼓励患儿，以防背上思想包袱；同时注意患儿的睡眠、休息，使其顺利度过术后的恢复期。</P>\n" +
          "<DIV id=bjtj class=bjtj>\n" +
          "<P>&nbsp;&nbsp;&nbsp; <A href=\"http://baby.familydoctor.com.cn/a/201208/7263238141812.html\" target=_blank>孕期B超检查可排查99%以上的先心病</A></P>\n" +
          "<P>&nbsp;&nbsp;&nbsp; <A href=\"http://baby.familydoctor.com.cn/a/201207/688527153839.html\" target=_blank>超过35岁生育孩子易患先心病</A></P>\n" +
          "<P>&nbsp;&nbsp;&nbsp; <A href=\"http://baby.familydoctor.com.cn/baby/201203/8744223225411.html\" target=_blank>爱呛奶的宝宝提防先心病</A></P></DIV>\n" +
          "\t\t\t\t";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
       // listView=(ListView)this.findViewById(R.id.listview);
        TextView textView=(TextView)findViewById(R.id.html);
        textView.setText(Html.fromHtml(args));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        /*
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("您的设备不支持蓝牙");
            builder.create().show();
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("您的设备支持蓝牙");
            builder.create().show();
        }
        //打开蓝牙
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);
        pairedDevices = bluetoothAdapter.getBondedDevices();
        System.out.println("蓝牙设备");
        for(BluetoothDevice device:pairedDevices)
        {
            System.out.println(device.getName());
        }
        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(),"Showing Paired Devices",
                Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        */
    }
}
