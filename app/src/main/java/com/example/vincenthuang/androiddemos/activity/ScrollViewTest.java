package com.example.vincenthuang.androiddemos.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个测试ScrollView的演示实例", createTime = "2017/2/9")
public class ScrollViewTest extends AppCompatActivity {

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_test);

        TextView textView = (TextView) findViewById(R.id.scrolled_text);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);

        //这里是为textView赋值，内容在R.string.text中，测试时最好内容长一些
        textView.setText(getResources().getString(R.string.scroll_text));
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //手指抬起
                    case MotionEvent.ACTION_UP:
                        break;
                    //手指落下
                    case MotionEvent.ACTION_DOWN:
                        break;
                    //手指滑动
                    case MotionEvent.ACTION_MOVE:
                        /**
                         * 1、getScorollY()——滚动条滑动的距离
                         * 2、getMeasuredHeight()——内容的整体高度，包括隐藏部分
                         * 3、getHeight()——显示高度。内容未布满ScrollView时，2=3；内容大于ScrollView时，3=Scro高度，2>3。
                         */
                        //顶部状态
                        if (scrollView.getScrollY() <= 0) {
                            Toast.makeText(ScrollViewTest.this, "达到顶部", Toast.LENGTH_SHORT).show();
                        }

                        //底部状态
                        //TextView的总高度 <= ScrollView的高度+滚动距离(getChildAt(0):第0个子控件)
                        if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getScrollY() + scrollView.getHeight()) {
                            Toast.makeText(ScrollViewTest.this, "到达底部", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });
    }
}

