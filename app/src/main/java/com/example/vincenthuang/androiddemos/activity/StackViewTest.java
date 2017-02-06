package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entry(desc = "一个StackView演示界面", createTime = "2017/2/6")
public class StackViewTest extends AppCompatActivity {

    StackView stackView;
    int[] imageIds = new int[]
            {
                    R.drawable.alistar, R.drawable.annie, R.drawable.blitzcrank,
                    R.drawable.garen, R.drawable.jax, R.drawable.kayle,
                    R.drawable.malphite, R.drawable.maokai, R.drawable.miss_fortune,
                    R.drawable.xin_zhao, R.drawable.warwick, R.drawable.urgot
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view_test);
        stackView = (StackView) findViewById(R.id.mStackView);
        // 创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems
                // 使用/layout/cell.xml文件作为界面布局
                , R.layout.stack_view_cell, new String[]{"image"},
                new int[]{R.id.stack_cell_img});
        stackView.setAdapter(simpleAdapter);
    }

    public void prev(View view) {
        // 显示上一个组件
        stackView.showPrevious();
    }

    public void next(View view) {
        // 显示下一个组件
        stackView.showNext();
    }
}
