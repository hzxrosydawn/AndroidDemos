package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entry(desc = "一个ImageSwitcher的演示界面", createTime = "2017/2/7")
public class ImageSwitcherTest extends AppCompatActivity {

    int[] imageIds = new int[]
            {
                    R.drawable.alistar, R.drawable.annie, R.drawable.blitzcrank,
                    R.drawable.garen, R.drawable.jax, R.drawable.kayle,
                    R.drawable.malphite, R.drawable.maokai, R.drawable.miss_fortune,
                    R.drawable.xin_zhao, R.drawable.warwick, R.drawable.urgot
            };
    ImageSwitcher switcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher_test);
        // 创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        // 获取显示图片的ImageSwitcher
        switcher = (ImageSwitcher)
                findViewById(R.id.switcher);
        // 为ImageSwitcher设置图片切换的动画效果
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                // 创建ImageView对象
                ImageView imageView = new ImageView(ImageSwitcherTest.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                // 返回ImageView对象
                return imageView;
            }
        });
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems
                // 使用/layout/imge_switcher_grid_cell.xml文件作为界面布局
                , R.layout.image_switcher_grid_cell, new String[]{"image"},
                new int[]{R.id.image_switcher_grid_img});
        GridView grid = (GridView) findViewById(R.id.grid01);
        // 为GridView设置Adapter
        grid.setAdapter(simpleAdapter);
        // 添加列表项被选中的监听器
        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 显示当前被选中的图片
                switcher.setImageResource(imageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 添加列表项被单击的监听器
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 显示被单击的图片
                switcher.setImageResource(imageIds[position]);
            }
        });
    }
}
