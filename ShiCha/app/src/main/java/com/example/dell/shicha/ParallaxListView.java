package com.example.dell.shicha;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 作者:武锦
 * 时间:2017/8/16
 * 工程名:ShiCha
 * 继承试控件
 * 1继承listview,覆写构造方法
 * 2覆写overScrollBy方法,重点关组deltay,isTouchEvent方法
 * 3暴露一个目标,去得到外界的imageview,并侧莱昂imageview的高度
 * 4覆写OnTOUcheEvent方法
 */

public class ParallaxListView extends ListView{

    private ImageView mParallaximage;
    private int mInt;
    private int mDrawableHeight;
    private int orignalHeight;
    private ValueAnimator mAnimator;

    public ParallaxListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ParallaxListView(Context context) {
        this(context,null);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



    }


    public void setparallaximage(ImageView parallaximage) {
        mParallaximage = parallaximage;
        //B.获取图片的原始高度
        mDrawableHeight = parallaximage.getDrawable().getIntrinsicHeight();
//获取imagelive控件的原因高度,一边回弹时,回到原始高度
        orignalHeight=parallaximage.getHeight();

    }

    //滑动listview俩端是,才会被调用
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        //通过LOG来验证参数的作用
         System.out.print("deltay"+deltaY+"");
        //顶不下啦,用户触摸的炒作才执行视查效果
        if (deltaY<0 && isTouchEvent){
            //deltaY是负值,但我们要改为绝对值iv高度
            mInt = mParallaximage.getHeight() + Math.abs(deltaY);
            //把新的高度值负给控件,改变控件的高度
            //B.避免图片的无限放大,是图片最大不能够超过图片本身的高度
            if(mInt <= mDrawableHeight){
                //把新的高度值负值给控件,改变控件的高度
                mParallaximage.getLayoutParams().height=mInt;
                mParallaximage.requestLayout();
            }
            }


        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

    }
    //覆写触摸事件,让滑动图片重新恢复原有的样子
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP://送开时,  回调
//把当前的头布局的高度恢复初四高度
                int height = mParallaximage.getHeight();

                //属性动画,改变高度的值,把我们当前头布局的高度,改为原始时的高度
               mAnimator = ValueAnimator.ofInt(height, orignalHeight);
                //动画更新监听
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    private  float animatedFraction;
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //获取动画执行过程中的分度值
        animatedFraction = mAnimator.getAnimatedFraction();
        //获取中间的值，并赋给控件的新高度，可以使控件平稳回弹的效果
        Integer animatedValue = (Integer) mAnimator.getAnimatedValue();
        //让新的高度值生效
                        mParallaximage.getLayoutParams().height=animatedValue;
                        mParallaximage.requestLayout();
                    }
                });
                // 动画的回弹效果，值越大，回弹越厉害
                mAnimator.setInterpolator(new OvershootInterpolator(2));
        //设置动画设置的时间
                mAnimator.setDuration(1000);
        //动画执行
                mAnimator.start();
                break;
        }
        return super.onTouchEvent(ev);
    }
}

