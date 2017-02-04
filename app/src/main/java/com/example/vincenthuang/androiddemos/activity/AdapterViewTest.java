package com.example.vincenthuang.androiddemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.ArrayAdapterTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.BaseAdapterTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.ExpandableListViewTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.GridViewTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.ListActivityTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.SimpleAdapterTest;
import com.example.vincenthuang.androiddemos.activity.AdapterViewActivity.SpinnerTest;

@Entry(desc = "一个测试AdapterView常用子类的演示集合", createTime = "2017/2/4")
public class AdapterViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_test);
    }

    public void startListView(View view) {
        switch (view.getId()) {
            case R.id.array_adapter_btn:
                startActivity(new Intent(AdapterViewTest.this, ArrayAdapterTest.class));
                break;
            case R.id.simple_adapter_btn:
                startActivity(new Intent(AdapterViewTest.this, SimpleAdapterTest.class));
                break;
            case R.id.base_adapter_btn:
                startActivity(new Intent(AdapterViewTest.this, BaseAdapterTest.class));
                break;
            case R.id.expandable_list_view_btn:
                startActivity(new Intent(AdapterViewTest.this, ExpandableListViewTest.class));
                break;
            case R.id.list_activity_btn:
                startActivity(new Intent(AdapterViewTest.this, ListActivityTest.class));
                break;
            case R.id.grid_view_btn:
                startActivity(new Intent(AdapterViewTest.this, GridViewTest.class));
                break;
            case R.id.spinner_btn:
                startActivity(new Intent(AdapterViewTest.this, SpinnerTest.class));
            default:
                break;
        }
    }
}
