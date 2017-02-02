package com.example.vincenthuang.androiddemos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.vincenthuang.androiddemos.Utils.Entry;

/*@Entry(
        desc = "A MainActivity, which contains the  entries of all activities. " +
                "And all of these activities have Annotation @Entry.",
        createTime = "2017-02-2: 20:04")*/
public class MainActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ProgressDialog mProgressDialog;

    private List<ActivityInfoEntity> mActivityInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MAdapter();
        mRecyclerView.setAdapter(mAdapter);
        loadActivityInfo();
    }

    private void loadActivityInfo() {
        new AsyncTask<Void, Void, List<ActivityInfoEntity>>() {

            @Override
            protected void onPreExecute() {
                mProgressDialog = new ProgressDialog(MainActivity.this, ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setTitle("Loading ...");
            }

            @Override
            protected List<ActivityInfoEntity> doInBackground(Void... params) {
                List<ActivityInfoEntity> result = new ArrayList<>();
                PackageManager packageManager = getPackageManager();
                String path = getPackageCodePath();
                PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
                for (ActivityInfo activityInfo : packageInfo.activities) {
                    try {
                        ActivityInfoEntity entity = new ActivityInfoEntity();
                        entity.className = activityInfo.name;
                        entity.launchMode = getLaunchModeString(activityInfo.launchMode);
                        Class<Activity> activityClass = (Class<Activity>) Class.forName(activityInfo.name);
                        entity.classDesc = getActivityEntry(activityClass, 0);
                        if (entity.classDesc == null) continue;
                        entity.classCreateTime = getActivityEntry(activityClass, 1);
                        result.add(entity);
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(List<ActivityInfoEntity> activityInfoEntities) {
                mActivityInfoList = activityInfoEntities;
                mAdapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    private String getLaunchModeString(int lunchMode) {
        String result = null;
        switch (lunchMode) {
            case ActivityInfo.LAUNCH_SINGLE_INSTANCE:
                result = "SingleInstance";
                break;
            case ActivityInfo.LAUNCH_SINGLE_TOP:
                result = "SingleTop";
                break;
            case ActivityInfo.LAUNCH_SINGLE_TASK:
                result = "SingleTask";
                break;
            default:
                result = "Standard";
        }
        return result;
    }

    /**
     * 根据class文件获取此类的描述
     *
     * @param activityClass 对应class文件
     * @return 相应的描述, null -- 如果此类没有使用Entry修饰
     * @see com.example.vincenthuang.androiddemos.Utils.Entry
     */
    private String getActivityEntry(@NonNull Class<Activity> activityClass, int type) {
        boolean isEntry = activityClass.isAnnotationPresent(Entry.class);
        if (!isEntry) return null;
        Entry entry = activityClass.getAnnotation(Entry.class);
        if (type == 0) {
            return entry.desc();
        } else {
            return entry.createTime();
        }
    }

    private class MAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity_entry, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ActivityInfoEntity activityInfo = mActivityInfoList.get(position);
            String className = activityInfo.className;
            if (activityInfo.className.endsWith("_")) {
                className = activityInfo.className.substring(0, className.length() - 1);
            }
            viewHolder.entryName.setText(className);
            if (!TextUtils.isEmpty(activityInfo.classCreateTime)) {
                viewHolder.createTime.setText(activityInfo.classCreateTime);
            }
            viewHolder.lunchMode.setText(activityInfo.launchMode);
            viewHolder.rightPoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(MainActivity.this, mActivityInfoList.get(position).classDesc, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String className = mActivityInfoList.get(position).className;
                    try {
                        Log.v(TAG, "try startActivity, and className is " + className);
                        startActivity(new Intent(MainActivity.this, Class.forName(className)));
                        Log.v(TAG, "startActivity. complete");
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mActivityInfoList == null ? 0 : mActivityInfoList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private TextView entryName;
            private TextView lunchMode, createTime;
            private ImageView rightPoint;

            public ViewHolder(View itemView) {
                super(itemView);
                entryName = (TextView) itemView.findViewById(R.id.tv_activity_name);
                lunchMode = (TextView) itemView.findViewById(R.id.tv_activity_lunch_mode);
                createTime = (TextView) itemView.findViewById(R.id.tv_activity_create_time);
                rightPoint = (ImageView) itemView.findViewById(R.id.iv_right_point);
            }
        }
    }

    private class ActivityInfoEntity {
        private String className;
        private String classCreateTime;
        private String classDesc;
        private String launchMode;
    }
}
