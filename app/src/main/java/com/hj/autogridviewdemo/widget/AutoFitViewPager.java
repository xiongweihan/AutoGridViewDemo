package com.hj.autogridviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 自适应高度的 ViewPager
 */
public class AutoFitViewPager extends ViewPager {

    public AutoFitViewPager(@NonNull Context context) {
        this(context,null);
    }

    public AutoFitViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                requestLayout();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //find the current child view
        View view = getChildAt(0);//注意:这里是有bug的,看文末的解决方式,todo 我这里选第一页，是为了后边的如果填充不满一行会变化高度，所以以第一页高度为主

        if(view != null){
            view.measure(widthMeasureSpec,heightMeasureSpec);
        }
        setMeasuredDimension(getMeasuredWidth(),measureHeight(heightMeasureSpec,view));


    }

    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSzie = MeasureSpec.getSize(measureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSzie;
        } else {

            if(view != null){
                result = view.getMeasuredHeight();
            }
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSzie);
            }

        }
        return result;
    }
}
