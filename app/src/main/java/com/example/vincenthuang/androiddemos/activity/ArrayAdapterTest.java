package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vincenthuang.androiddemos.R;

public class ArrayAdapterTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter_test);

        ListView arrayAdapterListView = (ListView) findViewById(R.id.array_adapter_list_view);

        String[] names = {"李小璐", "贾乃亮了", "高圆圆", "赵又廷了"};

        //将数组包装成ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.array_adapter_test_view, R.id.array_adapter_text_view, names);

        arrayAdapterListView.setAdapter(arrayAdapter);
    }
}
