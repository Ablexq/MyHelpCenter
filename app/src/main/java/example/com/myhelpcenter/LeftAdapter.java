package example.com.myhelpcenter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * .
 */

class LeftAdapter extends BaseAdapter {

    private Context context;
    private List<String> lists;
    private int mSelect = 0;   //选中项

    public LeftAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    public void changeSelected(int position) { //刷新方法
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.left_item, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.leftTv = (TextView) convertView.findViewById(R.id.leftTv);
            mViewHolder.indicator = convertView.findViewById(R.id.indicator);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.leftTv.setText(lists.get(position));

        if (mSelect == position) {
            mViewHolder.indicator.setVisibility(View.VISIBLE);
            mViewHolder.leftTv.setTextColor(Color.parseColor("#ff8041"));
        } else {
            mViewHolder.indicator.setVisibility(View.GONE);
            mViewHolder.leftTv.setTextColor(Color.parseColor("#050505"));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView leftTv;
        View indicator;
    }

}
