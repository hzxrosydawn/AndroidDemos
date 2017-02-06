package com.example.vincenthuang.androiddemos.activity.AdapterViewActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.vincenthuang.androiddemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewTest extends AppCompatActivity {

    private String[] summoner_names = new String[]{
            "Alistar", "Annie", "Ashe", "Blitzcrank",
            "Brand", "Garen", "Jax", "Kayle",
            "Malphite", "Maokai", "Miss Fortune", "Morganna",
            "Rammus", "Sion", "Sivir", "Swain"};
    private int[] Icons = new int[]{
            R.drawable.alistar, R.drawable.annie, R.drawable.ashe, R.drawable.blitzcrank,
            R.drawable.brand, R.drawable.garen, R.drawable.jax, R.drawable.kayle,
            R.drawable.malphite, R.drawable.maokai, R.drawable.miss_fortune, R.drawable.morganna,
            R.drawable.rammus, R.drawable.sion, R.drawable.sivir, R.drawable.swain};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_test);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        final ImageView previewView = (ImageView) findViewById(R.id.preview_image_view);

        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < summoner_names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("header", Icons[i]);
            item.put("name", summoner_names[i]);
            items.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, items, R.layout.grid_view_cell,
                new String[]{"header", "name"}, new int[]{R.id.grid_item_view, R.id.summoner_name_text});
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                previewView.setImageResource(Icons[position]);
            }
        });
    }
}
