package com.example.vincenthuang.androiddemos.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListActivityTest extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] names = {"杜特尔特", "特朗不靠谱", "普大帝"};

        //将数组包装成ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, names);

        setListAdapter(arrayAdapter);
    }

}
