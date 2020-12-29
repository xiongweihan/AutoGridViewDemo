package com.hj.autogridviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hj.autogridviewdemo.widget.AutoRecyclerview;
import com.hj.autogridviewdemo.widget.ModelBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ModelBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < 8; i++) {
            ModelBean bean = new ModelBean("张三" + i, "2020-12-31", i % 2 == 0 ? 0 : 1);
            list.add(bean);
        }
    }

    private void initView() {
        AutoRecyclerview arList = findViewById(R.id.ar_list);
        arList.setData(list);
    }
}