package com.hj.autogridviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.hj.autogridviewdemo.R;

/**
 * 可分页的RecyclerView
 */
public class AutoRecyclerview extends FrameLayout {
    private Context mContext;
    private ViewPager mViewPager;


    public AutoRecyclerview(@NonNull Context context) {
        this(context,null);
    }

    public AutoRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }

    private void init(Context context) {
        mContext = context;

        View parentView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_layout,this);


    }
}
