package com.example.vincenthuang.androiddemos.activity.AdapterViewActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincenthuang.androiddemos.R;

public class ExpandableListViewTest extends AppCompatActivity {
    private String[] groupNames = {"法师", "坦克", "射手", "战士"};
    private String[][] childStrings = {
            {"复仇焰魂", "策士统领", "黑暗之女", "卡牌大师", "猩红收割者", "末日使者", "堕落天使"},
            {"披甲龙龟", "熔岩巨兽", "牛头酋长", "亡灵战神", "炼金术士", "蒸汽机器人", "扭曲树精"},
            {"首领之傲", "赏金猎人", "战争女神", "迅捷斥候", "麦林炮手", "暗夜猎手", "寒冰射手"},
            {"德玛西亚之力", "德邦总管", "审判天使", "嗜血猎手", "蛮族之王", "武器大师", "祖安狂人"},

    };
    private int[][] child_icons = {
            {R.drawable.brand, R.drawable.swain, R.drawable.annie, R.drawable.twisted_fate,
                    R.drawable.vladimir, R.drawable.fiddlesticks, R.drawable.morganna},
            {R.drawable.rammus, R.drawable.malphite, R.drawable.alistar, R.drawable.sion,
                    R.drawable.singed, R.drawable.blitzcrank, R.drawable.maokai},
            {R.drawable.urgot, R.drawable.miss_fortune, R.drawable.sivir, R.drawable.teemo,
                    R.drawable.tristana, R.drawable.vayne, R.drawable.ashe},
            {R.drawable.garen, R.drawable.xin_zhao, R.drawable.kayle, R.drawable.warwick,
                    R.drawable.tryndamere, R.drawable.jax, R.drawable.dr_mundo}
    };
    private String[] groupStrings = {
            "发生能输出高额的伤害，也具有有效的控制", "坦克具有高额的血量、护甲和魔抗",
            "射手的远程攻击让近战英雄很头疼", "战士是近战的强者"
    };
    private String[][] heroStrings = {
            {"准备好进入火焰世界了吗？嘿嘿嘿嘿", "诺克萨斯人从来不会闲逛", "那个……你看见过我的小熊吗？",
                    "今天是我的幸运日！", "我的杯子已经空了一半", "听候您的吩咐，主人", "我们会叫他们好受的！"},
            {"一起找点儿乐子吧！", "坚如磐石！", "俺很生气，后果很严重！", "交给我吧！", "要来一杯么？",
                    "发动机已启动，随时可以出发", "这股力量……我无法控制"},
            {"他们会知道害怕的", "扬帆！起航！", "战斗吧！", "提莫队长正在待命！", "我好想射点儿什么！",
                    "我会让你死的很痛快", "明智之选"},
            {"恶人终有恶报！", "长枪依在！(菊花拿来！)", "迎接审判吧！", "这局胜负已定",
                    "我的大刀早已饥渴难耐了！", "开打开打！", "蒙多想去哪就去哪"}
    };
    private String groupString;
    private String heroString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_test);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

        ExpandableListAdapter expandableListAdapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return groupStrings.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return childStrings[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return groupStrings[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return childStrings[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                     ViewGroup parent) {
                convertView = LayoutInflater.from(ExpandableListViewTest.this).inflate(
                        R.layout.group_view, parent, false);
                TextView group_name = (TextView) convertView.findViewById(R.id.group_name);
                group_name.setText(groupNames[groupPosition]);
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                     View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(ExpandableListViewTest.this).inflate(
                        R.layout.child_view, parent, false);
                ImageView child_icon = (ImageView) convertView.findViewById(R.id.child_icon);
                child_icon.setImageResource(child_icons[groupPosition][childPosition]);
                TextView child_name = (TextView) convertView.findViewById(R.id.child_name);
                child_name.setText(childStrings[groupPosition][childPosition]);
                return convertView;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        expandableListView.setAdapter(expandableListAdapter);

        //设置列表项组的点击监听事件
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                groupString = groupStrings[groupPosition];
                showToast(groupString);

            }
        });

        //设置子选项点击监听事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                heroString = heroStrings[groupPosition][childPosition];
                showToast(heroString);
                return true;
            }
        });
    }

    public void showToast(String heroString) {
        Toast.makeText(ExpandableListViewTest.this, heroString, Toast.LENGTH_SHORT).show();
    }
}
