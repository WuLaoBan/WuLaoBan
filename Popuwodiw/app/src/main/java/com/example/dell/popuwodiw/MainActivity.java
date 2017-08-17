package com.example.dell.popuwodiw;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
popuwodiw下拉链表,属于组合试
1添加 Buttonife的依赖,取消掉ActionBar,使用ToolBar代替\
2完成整体的布局,初始化控件,设置点击事件
3初始化popuwodiw所显示的数据
4初始化popuwodiw控件的设置
5popuwodiw与listview相关联
6三个popuwodiw所依附的linearlayout,更加点击事件,做对应逻辑处理(改变textview颜色)

 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.supplier_list_product_tv)
    TextView mProductTv;  // 可以修改名称
    @Bind(R.id.supplier_list_product)
    LinearLayout mProduct;
    @Bind(R.id.supplier_list_sort_tv)
    TextView mSortTv;     // 可以修改名称
    @Bind(R.id.supplier_list_sort)
    LinearLayout mSort;
    @Bind(R.id.supplier_list_activity_tv)
    TextView mActivityTv;   // 可以修改名称
    @Bind(R.id.supplier_list_activity)
    LinearLayout mActivity;
    @Bind(R.id.supplier_list_lv)
    ListView mSupplierListLv;
    private ArrayList<Map<String,String>> mList,mList2,mList3;
    private LinearLayout mView;
    private PopupWindow mPopupWindow;
    //private ListView  lv;
    //private SimpleAdapter mSimpleAdapter;
    private SimpleAdapter mSimpleAdapter1;
    private SimpleAdapter mSimpleAdapter2;
    private SimpleAdapter mSimpleAdapter3;
    private PopupWindow mMPopupWindow;
    private ListView mView1;
    //定义int类型变量用来做标记
    private int Count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
  //初始化popuwodiw所显示的数据
        initData();
        //初始化popuwodiw
        initpopuwodiw();
    }


    //初始化popuwodiw
    private void initpopuwodiw() {
        //把包裹Listview布局的XML文件转换为View对象
        View popup =  LayoutInflater.from(this).inflate(R.layout.popwin, null);
        mMPopupWindow = new PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//设置popuwodiw外不可以点击
        mMPopupWindow.setOutsideTouchable(true);
        //设置mPopupWindow里面的Listview焦点
        mMPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //设置结束时点计世界
        mMPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置TextView的颜色,把所有LinearLayout的文本颜色该为灰色
                mProductTv.setTextColor(Color.parseColor("#5a5959"));
                mSortTv.setTextColor(Color.parseColor("#5a5959"));
                mActivityTv.setTextColor(Color.parseColor("#5a5959"));

            }
        });
        //设置点击popuwodiw以外的地方,是初始化popuwodiw消失
        mView = (LinearLayout) popup.findViewById(R.id.popwin_supplier_list_bottom);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMPopupWindow.dismiss();
            }
        });
       //获取Listview

        mView1 = (ListView)popup.findViewById(R.id.popwin_supplier_list_lv);
        mSimpleAdapter1 = new SimpleAdapter(this, mList, R.layout.item2,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mSimpleAdapter2 = new SimpleAdapter(this, mList2, R.layout.item2,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mSimpleAdapter3 = new SimpleAdapter(this, mList3, R.layout.item2,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        //listView条目点击事件
         mView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mMPopupWindow.dismiss();
                 switch (Count){
                     case 0:
                         String Product = mList.get(position).get("name");
                         mProductTv.setText(Product);
                         break;
                     case 1:
                         String Sort = mList2.get(position).get("name");
                         mSortTv.setText(Sort);
                         break;
                     case 2:
                         String activity = mList3.get(position).get("name");
                         mActivityTv.setText(activity);
                         break;
                 }
             }
         });

    }




    //初始化数据,popuwodiw所需,一共有三个,所以我要封装好三个数据,这是假数据,真实数据从网获取
    private void initData() {
    //创建一个popuwodiw加载数据的大合子,Mop集合(jian,值)
        mList = new ArrayList<>();
        //存放String的字符创数组
        String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品",
                "酒水饮料", "水果"};
        //创建一个小盒子
        Map<String,String> map1;
        for (int x=0; x<menuStr1.length;x++){
            map1=new HashMap<String, String>();
            map1.put("name",menuStr1[x]);
            mList.add(map1);

        }
        //创建一个存放popupwindow加载数据的大盒子2,Map集合(键,值)
        mList2 = new ArrayList<>();
        String[] menuStr2 = new String[]{"综合排序", "配送费最低"};
        Map<String, String> map2;
        for (int i = 0; i < menuStr2.length; ++i) {
            map2 = new HashMap<String, String>();
            map2.put("name", menuStr2[i]);
            mList2.add(map2);
        }
        //创建一个存放popupwindow加载数据的大盒子3,Map集合(键,值)
        mList3 = new ArrayList<>();
        String[] menuStr3 = new String[]{"优惠活动", "特价活动", "免配送费",
                "可在线支付"};
        Map<String, String> map3;
        for (int i = 0 ; i < menuStr3.length; ++i) {
            map3 = new HashMap<String, String>();
            map3.put("name", menuStr3[i]);
            mList3.add(map3);
        }


    }

    @OnClick({R.id.supplier_list_product, R.id.supplier_list_sort, R.id.supplier_list_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supplier_list_product:
                //设置琪Textview点击为绿色


                //设置popuwindow里的listview适配器
                mView1.setAdapter(mSimpleAdapter1);

                //让popwindow显示出来,,参数1.view对象,决定了popwinview在那个控件,参数2.3决定坐标

                mMPopupWindow.showAsDropDown(mProduct,0,2);

                Count = 0;

                break;
            case R.id.supplier_list_sort:

                //设置popuwindow里的listview适配器
                mView1.setAdapter(mSimpleAdapter2);

                //让popwindow显示出来,,参数1.view对象,决定了popwinview在那个控件,参数2.3决定坐标

                mMPopupWindow.showAsDropDown(mSort,0,2);
                Count = 1;
                break;
            case R.id.supplier_list_activity:

                //设置popuwindow里的listview适配器
                mView1.setAdapter(mSimpleAdapter3);

                //让popwindow显示出来,,参数1.view对象,决定了popwinview在那个控件,参数2.3决定坐标

                mMPopupWindow.showAsDropDown(mActivity,0,2);
                Count = 2;
                break;
        }
    }
}
