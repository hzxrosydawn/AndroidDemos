package com.example.vincenthuang.androiddemos.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

import java.util.Calendar;

@Entry(desc = "一个对话框测试界面", createTime = "2017/2/10")
public class DialogTest extends AppCompatActivity {

    final static int MAX_PROGRESS = 100;
    private String[] items = new String[]{"坦克", "法师", "射手"};
    private String toastString;
    // 该程序模拟填充长度为100的数组
    private int[] data = new int[50];
    // 记录进度对话框的完成百分比
    private int progressStatus = 0;
    // 定义一个负责更新的进度的Handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 表明消息是由该程序发送的
            if (msg.what == 0x123) {
                pd2.setProgress(progressStatus);
            }
        }
    };
    private int hasData = 0;
    private ProgressDialog pd1, pd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
    }

    public void showDialog(View view) {
        switch (view.getId()) {
            case R.id.simple_dialog_btn:
                AlertDialog.Builder simpleBuilder = new AlertDialog.Builder(this)
                        // 设置对话框标题
                        .setTitle("简单对话框")
                        // 设置图标
                        .setIcon(R.drawable.lol)
                        .setMessage("对话框的测试内容\n第二行内容");
                // 为AlertDialog.Builder添加“确定”按钮
                setPositiveButton(simpleBuilder);
                // 为AlertDialog.Builder添加“取消”按钮
                setNegativeButton(simpleBuilder)
                        .create()
                        .show();
                break;
            case R.id.simple_list_dialog_btn:
                AlertDialog.Builder simpleListBuilder = new AlertDialog.Builder(DialogTest.this)
                        // 设置对话框标题
                        .setTitle("简单列表对话框")
                        // 设置图标
                        .setIcon(R.drawable.lol)
                        // 设置简单的列表项内容
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("你选中了《" + items[which] + "》");
                            }
                        });
                // 为AlertDialog.Builder添加“确定”按钮
                setPositiveButton(simpleListBuilder);
                // 为AlertDialog.Builder添加“取消”按钮
                setNegativeButton(simpleListBuilder)
                        .create()
                        .show();
                break;
            case R.id.single_choice_dialog_btn:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        // 设置对话框标题
                        .setTitle("单选列表项对话框")
                        // 设置图标
                        .setIcon(R.drawable.lol)
                        // 设置单选列表项，默认选中第二项（索引为1）
                        .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("你选中了《" + items[which] + "》");
                            }
                        });
                // 为AlertDialog.Builder添加“确定”按钮
                setPositiveButton(builder);
                // 为AlertDialog.Builder添加“取消”按钮
                setNegativeButton(builder)
                        .create()
                        .show();
                break;
            case R.id.multi_choice_dialog_btn:
                AlertDialog.Builder multiChoiceBuilder = new AlertDialog.Builder(this)
                        // 设置对话框标题
                        .setTitle("多选列表项对话框")
                        // 设置图标
                        .setIcon(R.drawable.lol)
                        // 设置多选列表项，设置勾选第2项、第4项
                        .setMultiChoiceItems(items
                                , new boolean[]{false, true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        showToast("你选中了《" + items[which] + "》");
                                    }
                                });
                // 为AlertDialog.Builder添加“确定”按钮
                setPositiveButton(multiChoiceBuilder);
                // 为AlertDialog.Builder添加“取消”按钮
                setNegativeButton(multiChoiceBuilder)
                        .create()
                        .show();
                break;
            case R.id.custom_dialog_btn:
                // 装载app\src\main\res\layout\login界面布局文件
                LinearLayout loginForm = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.toast_test_view, null);
                new AlertDialog.Builder(this)
                        // 设置对话框的图标
                        .setIcon(R.drawable.lol)
                        // 设置对话框的标题
                        .setTitle("自定义View对话框")
                        // 设置对话框显示的View对象
                        .setView(loginForm)
                        // 为对话框设置一个“确定”按钮
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                showToast("单击了【确定】按钮！");
                            }
                        })
                        // 为对话框设置一个“取消”按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                showToast("单击了【取消】按钮！");
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.custom_list_dialog_btn:
                AlertDialog.Builder customBuilder = new AlertDialog.Builder(this)
                        // 设置对话框标题
                        .setTitle("自定义列表项对话框")
                        // 设置图标
                        .setIcon(R.drawable.lol)
                        // 设置自定义列表项
                        .setAdapter(new ArrayAdapter<>(this
                                , R.layout.custom_list_dialog_item
                                , items), null);
                // 为AlertDialog.Builder添加“确定”按钮
                setPositiveButton(customBuilder);
                // 为AlertDialog.Builder添加“取消”按钮
                setNegativeButton(customBuilder)
                        .create()
                        .show();
                break;
            case R.id.popup_window_btn:
                // 装载R.layout.popup_window_dialog对应的界面布局
                View root = this.getLayoutInflater().inflate(R.layout.popup_window_dialog, null);
                // 创建PopupWindow对象
                final PopupWindow popup = new PopupWindow(root, 560, 720);
                // 以下拉方式显示
                // popup.showAsDropDown(v);
                //将PopupWindow显示在指定位置
                popup.showAtLocation(findViewById(R.id.popup_window_btn),
                        Gravity.CENTER, 20, 20);
                // 获取PopupWindow中的“关闭”按钮
                root.findViewById(R.id.popup_window_img).setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                // 关闭PopupWindow
                                popup.dismiss();
                            }
                        });
                break;
            case R.id.date_picker_dialog_btn:
                Calendar date = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(DialogTest.this,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth) {
                                showToast("您选择了：" + year + "年" + (month + 1)
                                        + "月" + dayOfMonth + "日");
                            }
                        }
                        //设置初始日期
                        , date.get(Calendar.YEAR)
                        , date.get(Calendar.MONTH)
                        , date.get(Calendar.DAY_OF_MONTH)).show();

                break;
            case R.id.time_picker_dialog_btn:
                Calendar time = Calendar.getInstance();
                // 创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(DialogTest.this,
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hourOfDay,
                                                  int minute) {
                                showToast("您选择了：" + hourOfDay + "时"
                                        + minute + "分");
                            }
                        }
                        //设置初始时间
                        , time.get(Calendar.HOUR_OF_DAY)
                        , time.get(Calendar.MINUTE)
                        //true表示采用24小时制
                        , true).show();
                break;
            case R.id.circle_progress_dialog_btn:
                // 调用静态方法显示环形进度条
                ProgressDialog.show(this, "任务执行中", "任务执行中，请等待", false, true);
                break;
            case R.id.horizontal_progress_dialog_btn:
                // 将进度条的完成进度重设为0
                progressStatus = 0;
                // 重新开始填充数组
                hasData = 0;
                pd2 = new ProgressDialog(DialogTest.this);
                pd2.setMax(MAX_PROGRESS);
                // 设置对话框的标题
                pd2.setTitle("任务进度百分比");
                // 设置对话框显示的内容
                pd2.setMessage("耗时任务的完成百分比");
                // 设置对话框不能用“取消”按钮关闭
                pd2.setCancelable(false);
                // 设置对话框的进度条风格
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // 设置对话框的进度条是否显示进度
                pd2.setIndeterminate(false);
                pd2.show();
                new Thread() {
                    public void run() {
                        while (progressStatus < MAX_PROGRESS) {
                            // 获取耗时操作的完成百分比
                            progressStatus = MAX_PROGRESS
                                    * doWork() / data.length;
                            // 发送空消息到Handler
                            handler.sendEmptyMessage(0x123);
                        }
                        // 如果任务已经完成
                        if (progressStatus >= MAX_PROGRESS) {
                            // 关闭对话框
                            pd2.dismiss();
                        }
                    }
                }.start();
                break;
            default:
                break;
        }
    }

    private AlertDialog.Builder setPositiveButton(
            AlertDialog.Builder builder) {
        // 调用setPositiveButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("单击了【确定】按钮！");
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder) {
        // 调用setNegativeButton方法添加“取消”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("单击了【取消】按钮！");
            }
        });
    }

    private void showToast(String toastString) {
        Toast.makeText(DialogTest.this, toastString, Toast.LENGTH_SHORT).show();
    }

    // 模拟一个耗时的操作
    private int doWork() {
        // 为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
