package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个Toast演示界面", createTime = "2017/2/7")
public class ToastTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);

        final Button mTexToast = (Button) findViewById(R.id.text_toast_btn);
        Button mViewToast = (Button) findViewById(R.id.view_toast_btn);

        mTexToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToastTest.this, "显示简单文本的Toast", Toast.LENGTH_SHORT).show();
            }
        });

        mViewToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast mToast = new Toast(ToastTest.this);
                LayoutInflater inflater = LayoutInflater.from(ToastTest.this);
                View toastView = inflater.inflate(R.layout.toast_test_view, null);
                mToast.setView(toastView);
                mToast.setDuration(Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }
}
