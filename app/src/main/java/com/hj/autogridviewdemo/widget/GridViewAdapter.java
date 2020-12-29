package com.hj.autogridviewdemo.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hj.autogridviewdemo.R;

import java.util.List;


/**
 * 电子护照--横向滑动的list对应的 adapter
 */
public class GridViewAdapter extends BaseAdapter {
    private List<ModelBean> mDatas;
    private LayoutInflater inflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public GridViewAdapter(Context context, List<ModelBean> mDatas, int curIndex, int pageSize) {
        inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页,如果够，则直接返回每一页显示的最大条目个数pageSize,如果不够，则有几项就返回几,(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);

    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_passport_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.tv_title_item_passport);
            viewHolder.iv = convertView.findViewById(R.id.iv_item_passport);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_success_time);
            viewHolder.tvSubTitle = convertView.findViewById(R.id.tv_subtitle_item_passport);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize
         */
        int pos = position + curIndex * pageSize;
        viewHolder.tv.setText(mDatas.get(pos).getName());
        if(!TextUtils.isEmpty(mDatas.get(pos).getName())){
            viewHolder.tvSubTitle.setVisibility(View.VISIBLE);
            viewHolder.tvSubTitle.setText(mDatas.get(pos).getName());
        }
        viewHolder.iv.setImageResource(R.drawable.icon_complete_sign);
        if (1 == mDatas.get(pos).getStatus()) {
            viewHolder.iv.setImageResource(R.drawable.icon_complete_sign);
            viewHolder.tvTime.setText(mDatas.get(pos).getFinishTime());
            viewHolder.tvTime.setRotation(-25);
            viewHolder.tvTime.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv.setImageResource(R.drawable.icon_uncomplete_sign);
        }
        return convertView;
    }


    class ViewHolder {
        public TextView tv;
        public ImageView iv;
        private TextView tvSubTitle;
        private TextView tvTime;
    }
}