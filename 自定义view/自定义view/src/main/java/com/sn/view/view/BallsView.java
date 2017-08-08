package com.sn.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * date:2017/8/7
 * author:易宸锋(dell)
 * function:自绘式自定义控件,牢记的三个方法
 * onMeasure(int,int)测量:该方法来检查View组件及他所包含的所有子组件的大小
 * onLayout(boolean,int,int,int,int)位置:当该组件要分配器子组件的位置,大小时,调用(使用频率较少)
 * onDraw(canves)绘制:当该组件将要绘制他的内容是,回调该方法(使用频率最高,不要再此方法里做耗时操作和对象的建立)
 * <p>
 * 创建一个可以进行拖动的小球
 * <p>
 * 自绘式自定义控件实现步骤:
 * 1.类继承VIew(从一个普通的类,变成一个控件了)
 * 2.覆写他必须要覆写的三个方法
 * 3.在OnMeasure和OnDrawer方法,写对应的业务逻辑
 */

public class BallsView extends View {

    private int mHeight;
    private int mWidth;
    private int X;
    private int Y;
    private boolean mOnBall;

    //这三个方法是让你做初始化的业务逻辑
    //代码中使用自定义控件(new BallsView),自动回调此方法.
    public BallsView(Context context) {
        this(context, null);
    }

    //XML布局使用此自定义控件,自动回调此方法
    public BallsView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    //XML布局中使用此自定义控件,且带有样式时,自动回调
    public BallsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiView();
    }

    //设置圆半径的距离
    private int mRadius =50;

    //自定义控件做一些初始化的操作
    private void intiView() {
        System.out.println("我是控件初始化的逻辑");
    }

    //测试
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        获取当前控件的宽和高
        mHeight = this.getHeight();
        mWidth = this.getWidth();
        //获取屏幕的正中心点
        X = mWidth / 2;
        Y = mHeight / 2;
    }

    //此方法进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //画圆,根据他需要什么参数(ctr+Q),我就参加什么参数
        canvas.drawCircle(X,Y,mRadius,paint);
    }

    //触摸事件的监听
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下时,回调
                int downX = (int) event.getX();
                int downy = (int) event.getY();
                //二者的区别就是,前者获取的坐标是自定义左上角为中心点,后者是屏幕的左上角为中心点(注意屏幕)
/*                event.getRawX();
                event.getRawX();*/

                //进行判断,用户手上是否点在了圆上
                mOnBall = isOnBall(downX, downy);
                Toast.makeText(getContext(), "用户的手点击到圆了吗?"+ mOnBall, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE://移动时,回调
                //进行判断,用户手上是否点在了圆上,在圆上的话,我就让代码时时移动
                if (mOnBall){
                    X = (int) event.getX();
                    Y = (int) event.getY();
                    //时时回调OnDrawer方法
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP://松开时,回调
                break;
        }
        return true;
    }

    //java,对象,方法,参数,结果.   当我遇到一个我不知道的方法,我只需要知道,传的参数是什么,返回结果是什么,其他就不用管了.
    //作为一个程序员必须要有的能力:1.解决问题的能力    2.自学能力   3.表达能力.
    private boolean isOnBall(int downX, int downy) {
        //勾股定理,得到按下的半径
        double sqrt = Math.sqrt((downX - X) * (downX - X) + (downy - Y) * (downy - Y));
        //对应圆的半径和按下半径进行判断,看用户的手是否点在圆上
        if(sqrt <=  mRadius){
            return true;
        }
        return false;
    }


}
