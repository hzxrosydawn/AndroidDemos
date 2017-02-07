package com.example.vincenthuang.androiddemos.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vincenthuang.androiddemos.R;

public class BaseAdapterTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        ListView baseAdapaterListView = (ListView) findViewById(R.id.base_adapter_list);
        BaseAdapter baseAdapter = new BaseAdapter() {

            //该方法返回列表中列表项的数目
            @Override
            public int getCount() {
                return 30;
            }

            //该方法返回数据集中指定位置列表项的数据内容
            @Override
            public Object getItem(int position) {
                return null;
            }

            //该方法返回数据集中指定位置的条目在列表中的行Id
            @Override
            public long getItemId(int position) {
                return position;
            }

            //该方法返回在列表中用来显示数据集中指定位置的条目的View
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //创建一个LinearLayout，并向其中添加组件
                LinearLayout linearLayout = new LinearLayout(BaseAdapterTest.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(BaseAdapterTest.this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                TextView textView = new TextView(BaseAdapterTest.this);
                textView.setText("第" + String.valueOf(position + 1) + "个列表项");
                textView.setTextSize(20);
                textView.setTextColor(Color.CYAN);

                linearLayout.addView(imageView);
                linearLayout.addView(textView);

                return linearLayout;
            }
        };

        baseAdapaterListView.setAdapter(baseAdapter);
    }
}
