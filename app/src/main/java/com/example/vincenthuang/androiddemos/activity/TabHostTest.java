package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个不继承TabActivity的TabHost测试实例", createTime = "201/2/9")
public class TabHostTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host_test);

        TabHost th = (TabHost) findViewById(R.id.tabhost);
        //初始化TabHost容器
        th.setup();

        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th.addTab(th.newTabSpec("tab1").setIndicator("已接电话", getResources().getDrawable(
                R.drawable.call_received, null)).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator("已拨电话", getResources().getDrawable(
                R.drawable.call_made, null)).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator("未接电话", getResources().getDrawable(
                R.drawable.call_missed, null)).setContent(R.id.tab3));
    }
}
