package com.hj.autogridviewdemo.widget;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AutoViewPagerAdapter extends PagerAdapter {
    private List<View> images = new ArrayList<>();

    public AutoViewPagerAdapter(List<View> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        if(images != null){
            return images.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //当前要显示的对象
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(images.get(position));
        return images.get(position);
    }

    //从viewGroup中删除当前对象
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(images.get(position));
    }
}
