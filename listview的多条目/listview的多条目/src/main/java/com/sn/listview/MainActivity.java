package com.sn.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //设置三种类型,对应我三种不同类型的Item,数字随意生成,主要是为了区分
    private static final int FristType = 774;

    private static final int TwoType = 362;

    private static final int ThreeType = 52;


    String[] texts = {"玉皇", "王母", "长蛾", "八戒", "如来", "易宸锋", "守星者", "部长", "大师", "收藏家"};

    private int[] images = {R.drawable.jx_left_listitem_1, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_2, R.drawable.jx_left_listitem_3,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_6, R.drawable.jx_left_listitem_6,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5};

    private List<DataBean> list;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initData();
        //查找控件
        mListView = (ListView) findViewById(R.id.lv);
        //设置适配器
        mListView.setAdapter(new MyApdapter());

    }

    /**
     * 这里是因为没有多条目的类型,手动我分一下,分为三种类型
     */
    private void initData() {
        list=new ArrayList<DataBean>();

        for(int x=0; x<10; x++){
            DataBean data = new DataBean();
            //第一种类型的数据,也就是2等等,去展示一个类型
            if ( x%2 == 0){
                data.setType(FristType);
                data.setText(texts[x]);
                System.out.println(texts[x]);

            }//第二种类型的数据
            else if ( x %3 ==0){
                data.setType(TwoType);
                data.setText(texts[x]);
                data.setImages(images[x]);

            }//第三种类型的数据
            else {
                data.setType(ThreeType);
                data.setText(texts[x]);
                data.setImages(images[x]);
            }
            list.add(data);
        }

    }

    private class MyApdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}
