package com.example.vincenthuang.androiddemos.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个Menu测试界面", createTime = "2017/2/11")
public class MenuTest extends AppCompatActivity {

    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
        testTextView = (TextView) findViewById(R.id.menu_test_text_view);

        //为View注册上下文菜单，然后长按该View就可以显示上下文菜单了
        this.registerForContextMenu(testTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu_test_items, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //也可以使用如下方式来处理选项菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        /*if (item.isCheckable()) {
            item.setChecked(true);
        }*/

        switch (item.getItemId()) {
            case R.id.red_item:
                testTextView.setTextColor(Color.RED);
                break;
            case R.id.green_item:
                testTextView.setTextColor(Color.GREEN);
                break;
            case R.id.blue_item:
                testTextView.setTextColor(Color.BLUE);
                break;
            case R.id.common_item:
                Toast.makeText(MenuTest.this, "您点击了一个普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.font_10:
                testTextView.setTextSize(10.0F);
                break;
            case R.id.font_12:
                testTextView.setTextSize(12.0F);
                break;
            case R.id.font_14:
                testTextView.setTextSize(14.0F);
                break;
            case R.id.font_16:
                testTextView.setTextSize(16.0F);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_test_items, menu);
        menu.setHeaderIcon(R.drawable.tools);
        menu.setHeaderTitle("更改文本");

    }

    // 处理上下文菜单的点击事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 获取注册了上下文菜单的AdapterView的信息
        /*AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();*/
        switch (item.getItemId()) {
            case R.id.cyan_item:
                testTextView.setTextColor(Color.BLACK);
                break;
            case R.id.font_18:
                testTextView.setTextSize(18.0F);
                break;
            case R.id.all_caps_item:
                testTextView.setAllCaps(true);
                break;
            case R.id.cancel_all_caps_item:
                testTextView.setAllCaps(false);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    // 弹出PopupMenu
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.context_menu_test_items, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cyan_item:
                        testTextView.setTextColor(Color.BLACK);
                        break;
                    case R.id.font_18:
                        testTextView.setTextSize(18.0F);
                        break;
                    case R.id.all_caps_item:
                        testTextView.setAllCaps(true);
                        break;
                    case R.id.cancel_all_caps_item:
                        testTextView.setAllCaps(false);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
