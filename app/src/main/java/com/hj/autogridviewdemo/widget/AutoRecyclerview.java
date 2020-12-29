package com.hj.autogridviewdemo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.hj.autogridviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.TRANSPARENT;

/**
 * 可分页的RecyclerView
 */
public class AutoRecyclerview extends FrameLayout {
    private Context mContext;
    private TextView tvTitle;
    private AutoFitViewPager mViewPager;
    private LinearLayout mLlDotLayout;

    private List<ModelBean> mModelList;

    private int pageCount = 0; //总的页数

    private int pageSize = 6; // 每页的个数

    private ImageView[] dotImgs;//底部小圆点图片
    private List<View> mPagerList;
    private LayoutInflater inflater;


    public AutoRecyclerview(@NonNull Context context) {
        this(context, null);
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
        inflater = LayoutInflater.from(mContext);
        View parentView = inflater.inflate(R.layout.item_recycler_layout, this);
        tvTitle = parentView.findViewById(R.id.tv_title);
        mViewPager = parentView.findViewById(R.id.view_pager);
        mLlDotLayout = parentView.findViewById(R.id.ll_dots_layout);
    }

    public void setData(List<ModelBean> modelList) {
        this.mModelList = modelList;
        //总的页数 = 总数/每页书来奶 ，并取整
        pageCount = (int) Math.ceil(mModelList.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate 一给新的实例
            GridView gridView = (GridView) inflater.inflate(R.layout.item_gridview_layout, mViewPager, false);
            gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));//这一行是GridView 去掉水波纹效果
            gridView.setAdapter(new GridViewAdapter(mContext, mModelList, i, pageSize));
            mPagerList.add(gridView);
            gridView.setOnItemClickListener((parent, view, position, id) -> {
                if (itemCallBack != null) {
                    itemCallBack.onItemClickListener(id);
                }
            });
        }

        //设置适配器
        mViewPager.setAdapter(new AutoViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();

        //如果是由一页，则不显示下方小圆点
        mLlDotLayout.setVisibility((pageCount > 1 ? VISIBLE : GONE));

    }

    /**
     * 设置小圆点
     */
    private void setOvalLayout() {
        mLlDotLayout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 5, 0);
        params.weight = DensityUtil.dp2px(12);
        params.height = DensityUtil.dp2px(4);
        params.gravity = Gravity.CENTER;
        dotImgs = new ImageView[pageCount];

        for (int i = 0; i < pageCount; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.passport_select_drawable);
            imageView.setSelected(i == 0);//默认启动时，选中第一个
            dotImgs[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            mLlDotLayout.addView(imageView);//添加到布局里面显示
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotImgs.length; i++) {
                    dotImgs[i].setSelected(position == i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    /**
     * 是否显示小圆点
     */
    public void setDotVisible(boolean visible){
        mLlDotLayout.setVisibility(visible ? VISIBLE :GONE);

    }

    public interface onItemCallBack {
        void onItemClickListener(long id);
    }

    public onItemCallBack itemCallBack;

    public void setOnItemCallBack(onItemCallBack itemCallBack) {
        this.itemCallBack = itemCallBack;
    }


}
