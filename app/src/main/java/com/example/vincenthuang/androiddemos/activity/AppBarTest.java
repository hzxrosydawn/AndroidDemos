package com.example.vincenthuang.androiddemos.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;
import com.example.vincenthuang.androiddemos.Utils.Entry;

@Entry(desc = "一个测试AppBar的演示界面", createTime = "2017/2/13")
public class AppBarTest extends AppCompatActivity {
    private TextView appBarTestTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_test);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_test_Toolbar);
        appBarTestTextView = (TextView) findViewById(R.id.appbar_test_text_view);

        setSupportActionBar(mToolbar);
        ActionBar testActionBar = getSupportActionBar();

        if (testActionBar != null) {
            // 启用向上按钮
            testActionBar.setDisplayHomeAsUpEnabled(true);
            // 设置ActionBar的标题
            testActionBar.setTitle(R.string.test_action_bar_title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu_test_items, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        MenuItem providerItem = menu.findItem(R.id.action_provider_test_item);

        // 监听Action View的扩展和折叠事件
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                if (item.getItemId() == R.id.search_item) {
                    Toast.makeText(AppBarTest.this, "该Action View扩展开了", Toast.LENGTH_SHORT).show();
                }
                // 返回true表明处理了展开事件，如果返回false，就表明没有处理展开事件，该Action View就不会展开
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                if (item.getItemId() == R.id.search_item) {
                    Toast.makeText(AppBarTest.this, "该Action View折叠起来了", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        // 获取Action View并执行与该View相关的操作
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("搜索文本");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(AppBarTest.this, "未找到[" + query + "]", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // 获取ActionBar Provider并执行相关操作
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(providerItem);
        // 创建一个发送的Intent，并附上分享的数据
        Intent mShareIntent = new Intent(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_STREAM, appBarTestTextView.getText());
        mShareActionProvider.setShareIntent(mShareIntent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red_item:
                appBarTestTextView.setTextColor(Color.RED);
                return true;
            case R.id.green_item:
                appBarTestTextView.setTextColor(Color.GREEN);
                return true;
            case R.id.blue_item:
                appBarTestTextView.setTextColor(Color.BLUE);
                return true;
            case R.id.font_10:
                appBarTestTextView.setTextSize(10.0F);
                return true;
            case R.id.font_14:
                appBarTestTextView.setTextSize(14.0F);
                return true;
            case R.id.font_16:
                appBarTestTextView.setTextSize(16.0F);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
