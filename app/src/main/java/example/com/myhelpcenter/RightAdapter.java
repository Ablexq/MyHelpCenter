package example.com.myhelpcenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class RightAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList;

    public RightAdapter(Context context, Map<String, List<String>> dataset, String[] parentList) {
        this.context = context;
        this.dataset = dataset;
        this.parentList = parentList;
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return dataset.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return dataset.get(parentList[groupPosition]).size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return dataset.get(parentList[groupPosition]);
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataset.get(parentList[groupPosition]).get(childPosition);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 指定位置相应的组视图
    // 用来判断ExpandableListView内容id是否有效的(返回true or false)，
    // 系统会跟据id来确定当前显示哪条内容，也就是firstVisibleChild的位置。
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group_layout, parent, false);
        }
        convertView.setTag(R.layout.item_group_layout, groupPosition);
        convertView.setTag(R.layout.item_child_layout, -1);
        TextView text = (TextView) convertView.findViewById(R.id.group_title);
        final TextView img_icon = (TextView) convertView.findViewById(R.id.img_icon);
        text.setText(parentList[groupPosition]);

        if (isExpanded) {
            img_icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.group_down, 0);
        } else {
            img_icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.group_up, 0);
        }

        return convertView;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_layout, parent, false);
        }
        convertView.setTag(R.layout.item_group_layout, groupPosition);
        convertView.setTag(R.layout.item_child_layout, childPosition);
        TextView text = (TextView) convertView.findViewById(R.id.child_title);
        TextView hasSolved = (TextView) convertView.findViewById(R.id.hasSolved);
        TextView unSolved = (TextView) convertView.findViewById(R.id.unSolved);
        text.setText(dataset.get(parentList[groupPosition]).get(childPosition));

        hasSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "已解决。。。。。。。。。。。。", Toast.LENGTH_SHORT).show();
            }
        });

        unSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "未解决！！！！！！！！！！！！", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    //  当不加任何条件直接返回false,所有的组的child均不可点击。
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}