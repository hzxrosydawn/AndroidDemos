package com.example.vincenthuang.androiddemos.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个TextSwitcher的演示界面", createTime = "2017/2/7")
public class TextSwitcherTest extends AppCompatActivity {

    TextSwitcher textSwitcher;
    String[] strs = new String[]
            {
                    "Head First Java",
                    "Head First Android",
                    "Head First C++",
                    "Head First PHP"
            };
    int curStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher_test);
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView tv = new TextView(TextSwitcherTest.this);
                tv.setTextSize(40);
                tv.setTextColor(Color.MAGENTA);
                return tv;
            }
        });
        // 调用next方法显示下一个字符串
        next(null);
    }

    // 事件处理函数，控制显示下一个字符串
    public void next(View source) {
        textSwitcher.setText(strs[curStr++ % strs.length]);
    }
}
