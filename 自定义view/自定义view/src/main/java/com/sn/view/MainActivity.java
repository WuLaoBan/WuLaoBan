package com.sn.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 1.自绘控件(完全自定义控件):继承的是View
 * 2.组合式自定义控件:继承的是ViewGroup
 * 3.继承式自定义控件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
