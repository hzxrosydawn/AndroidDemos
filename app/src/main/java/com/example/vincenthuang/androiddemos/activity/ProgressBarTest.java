package com.example.vincenthuang.androiddemos.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个ProgressBar及其子类的演示界面", createTime = "2017/2/6")
public class ProgressBarTest extends AppCompatActivity {

    ProgressBar largeProgressBar, normalProgressBar, smallProgressBar, inverseProgressBar, horProgressBar, horProgressBar2;
    TextView progressText;
    private int progressValue = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //表明消息是该程序发送的
            if (msg.what == 0x111) {
                largeProgressBar.setProgress(progressValue);
                normalProgressBar.setProgress(progressValue);
                smallProgressBar.setProgress(progressValue);
                inverseProgressBar.setProgress(progressValue);
                horProgressBar.setProgress(progressValue);
                horProgressBar2.setProgress(progressValue);
                progressText.setText("当前进度为" + String.valueOf(progressValue));

            }
        }
    };
    private int data[] = new int[100];
    private int dataLength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);

        largeProgressBar = (ProgressBar) findViewById(R.id.large_progress_bar);
        normalProgressBar = (ProgressBar) findViewById(R.id.normal_progress_bar);
        smallProgressBar = (ProgressBar) findViewById(R.id.small_progress_bar);
        inverseProgressBar = (ProgressBar) findViewById(R.id.inverse_progress_bar);
        horProgressBar = (ProgressBar) findViewById(R.id.hor_progress_bar);
        horProgressBar2 = (ProgressBar) findViewById(R.id.hor_progress_bar2);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        progressText = (TextView) findViewById(R.id.progress_value);

        new Thread(new Runnable() {
            public void run() {
                while (progressValue < 100) {
                    progressValue = doWork();
                    // Update the progress bar
                    mHandler.sendEmptyMessage(0x111);
                }
            }
        }).start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                progressText.setText("当前进度为" + String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                progressText.setText("当前星级为" + String.valueOf(rating));
            }
        });
    }

    public int doWork() {
        //为数组元素赋值
        data[dataLength++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dataLength;
    }
}
