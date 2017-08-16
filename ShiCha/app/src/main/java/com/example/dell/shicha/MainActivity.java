package com.example.dell.shicha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/*
视查特性实现思路
    1解析Ontouche,Action_Down,Action_move,Activity_up,业务逻辑过于复杂
    2重写listview的ouverScrollBy方法,继承试控件listview,根据用户下拉的距离,动态修改headview的高度
    a.拷贝文本资源到项目中,自定义控件继承listview
    b.使用自定义控件,并头部天剑布局,设置适配器
    c.使用摄图时,把imageliew创给我们的自定义控件
 */
public class MainActivity extends AppCompatActivity {
    private  ParallaxListView  per;
    private View mInflate;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view
        per= (ParallaxListView) findViewById(R.id.par);
        //B`    listview添加一个布局
        mInflate = View.inflate(this, R.layout.item, null);
        per.addHeaderView(mInflate);

        mImageView= (ImageView) mInflate.findViewById(R.id.iv_header);
        //等view见面全部绘制完毕的时候,其得到已经绘制玩控件的宽和高,查一下这个方法,并做一个笔记
        mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//宽和高溢价测量完毕
                per.setparallaximage(mImageView);
                //释放资源
                mImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
//使用listview的arrayafapter,添加文本的item
        per.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Cheese.NAMES));
    }
}
