package com.example.vincenthuang.androiddemos.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个测试Notification的演示界面", createTime = "2017/2/10")
public class NotificationTest extends AppCompatActivity {

    static final int NOTIFICATION_ID = 0x123;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void showNotification(View view) {
        //创建一个启动其他Activity的Intent
        Intent intent = new Intent(NotificationTest.this, ShowFengJieActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTest.this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                //打开通知后自动消失
                .setAutoCancel(true)
                //设置状态栏的消息
                .setTicker("你有女朋友了！")
                //设置状态栏显示的图标
                .setSmallIcon(R.drawable.small_icon_notification)
                //设置通知内容的标题
                .setContentTitle("点击查看你的女朋友")
                //设置通知内容
                .setContentText("如果不满意还有哟")
                //使用系统默认的声音
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setWhen(System.currentTimeMillis())
                //设置通知将要启动程序的Intent
                .setContentIntent(pendingIntent)
                .build();
        //发出通知
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
