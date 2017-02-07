package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;


public class SpinnerTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        AppCompatSpinner summoner_spinner = (AppCompatSpinner) findViewById(R.id.summoner_spinner);
        final String[] summonerNames = new String[]{
                "Alistar", "Annie", "Ashe", "Blitzcrank",
                "Brand", "Garen", "Jax", "Kayle"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, summonerNames);
        summoner_spinner.setAdapter(arrayAdapter);

        summoner_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast(summonerNames[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showToast(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
