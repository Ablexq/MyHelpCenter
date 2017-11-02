package example.com.myhelpcenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private LinearLayout container;
    private String[] arrays = {"热门问题", "登录注册", "人脸识别", "身份认证", "运营商", "补充资料",
            "征信报告", "绑卡", "人工审核", "提现", "还款", "在线客服"};
    private List<String> lists = new ArrayList<>();
    private LeftAdapter leftAdapter;

    private ExpandableListView eLv;
    private String[] arrays1 = {"00人工审核大概需要多长时间？", "11人工审核大概需要多长时间？",
            "22人工审核大概需要多长时间？", "33人工审核大概需要多长时间？",
            "44人工审核大概需要多长时间？", "55人工审核大概需要多长时间？",
            "66人工审核大概需要多长时间？", "77人工审核大概需要多长时间？",
            "88人工审核大概需要多长时间？", "99人工审核大概需要多长时间？"};//一级标题
    private String[] arrays2 = {"请使用收到的身份验证码尝试在人行征信中心是否可以查到个人征信报告，" +
            "如果可以查看但返回APP无法提交验证，请通过意见反馈描述并截图给我们。"};//二级标题
    private Map<String, List<String>> dataset = new HashMap<>();
    private TextView callTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initRightData();

        setLeftData();
        setRightData(0);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("111", "position====" + position);
                leftAdapter.changeSelected(position);

                setRightData(position);
            }
        });
    }

    private void setLeftData() {
        Collections.addAll(lists, arrays);
        leftAdapter = new LeftAdapter(this, lists);
        mLv.setAdapter(leftAdapter);
    }

    private void setRightData(int position) {
        if (position == lists.size() - 1) {//最后一项

            container.setVisibility(View.VISIBLE);
            eLv.setVisibility(View.GONE);
        } else {//其他
            container.setVisibility(View.GONE);
            eLv.setVisibility(View.VISIBLE);

            for (int i = 0; i < arrays1.length; i++) {
                List<String> childList = new ArrayList<>();
                childList.add(arrays[position] + " " + arrays2[0]);//添加子item
                Log.e("111", "arrays1[" + i + "]==" + arrays1[i]);
                dataset.put(arrays1[i], childList);//添加总数据
            }
            RightAdapter adapter = new RightAdapter(MainActivity.this, dataset, arrays1);
            eLv.setAdapter(adapter);

            eLv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    int count = eLv.getExpandableListAdapter().getGroupCount();
                    for (int j = 0; j < count; j++) {
                        if (j != groupPosition) {
                            eLv.collapseGroup(j);
                        }
                    }
                }
            });
        }


    }


    private void initRightData() {
        Log.e("111", "arrays1.length==" + arrays1.length);
        for (int i = 0; i < arrays1.length; i++) {
            List<String> childList = new ArrayList<>();
            childList.add(arrays[0] + " " + arrays2[0]);//添加子item
            Log.e("111", "arrays1[" + i + "]==" + arrays1[i]);
            dataset.put(arrays1[i], childList);//添加总数据
        }
    }

    private void findViews() {
        callTv = ((TextView) this.findViewById(R.id.call));
        callTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "打电话！！！！！！！！！！！！", Toast.LENGTH_SHORT).show();
            }
        });
        mLv = ((ListView) findViewById(R.id.lv));
        container = ((LinearLayout) this.findViewById(R.id.container));
        eLv = ((ExpandableListView) this.findViewById(R.id.eLv));
    }

}
