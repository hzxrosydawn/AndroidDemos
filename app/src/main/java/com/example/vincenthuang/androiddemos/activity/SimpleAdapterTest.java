package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.vincenthuang.androiddemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterTest extends AppCompatActivity {

    private String[] names = new String[]{"杜特尔特", "特朗不靠谱", "普大帝"};
    private String[] description = new String[]{"实干派", "大嘴", "硬汉"};
    private int[] imgID = new int[]{R.drawable.duterte, R.drawable.trump, R.drawable.putin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter_test);

        ListView simpleAdapterListView = (ListView) findViewById(R.id.simple_adapter_list);

        List<Map<String, Object>> itemLists = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("names", names[i]);
            listItem.put("description", description[i]);
            listItem.put("header", imgID[i]);
            itemLists.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, itemLists, R.layout.simple_adapter_list_item,
                new String[]{"names", "description", "header"}, new int[]{R.id.name_view, R.id.desc_view,
                R.id.header_view});
        simpleAdapterListView.setAdapter(simpleAdapter);
    }
}
