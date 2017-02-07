package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个CalendarView测试的演示界面", createTime = "2017//2/7")
public class CalendarViewTest extends AppCompatActivity {

    CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view_test);
        cv = (CalendarView) findViewById(R.id.calendarView);
        // 为CalendarView组件的日期改变事件添加事件监听器
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                // 使用Toast显示用户选择的日期
                Toast.makeText(CalendarViewTest.this,
                        "你生日是" + String.valueOf(year) + "年" + String.valueOf(month) + "月" +
                                String.valueOf(dayOfMonth) + "日", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
