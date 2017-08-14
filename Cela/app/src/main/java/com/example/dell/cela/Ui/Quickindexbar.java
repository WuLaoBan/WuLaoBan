package com.example.dell.cela.Ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.dell.cela.Util.toastutil;

/**
 * 作者:武锦
 * 时间:2017/8/14
 * 工程名:Cela
 */

public class Quickindexbar extends View {


    //A.要绘制的内容
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private String mLetter;
    private int mHeight;
    private float cellWidth;
    private Paint mMPaint;
    private float mCallheight;
    private float eventY;
    private int mCurrentindex;
    private int lastindex = -1;

    public Quickindexbar(Context context) {
        this(context, null);
    }

    public Quickindexbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Quickindexbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化笔
        intipaint();
    }

    private void intipaint() {
        mMPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画笔加粗
        mMPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //颜色
        mMPaint.setColor(Color.BLACK);
        mMPaint.setTextSize(20);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < LETTERS.length; i++) {
            //从数组,根据取出字幕\
            mLetter = LETTERS[i];
            //计算x坐标
            float x = cellWidth * 0.5f - mMPaint.measureText(mLetter) * 0.5f;
            //计算y坐标
            float y = mCallheight * 0.5f + mMPaint.measureText(mLetter) * 0.5f + i * mCallheight;
            canvas.drawText(mLetter,x,y, mMPaint);
        }

    }
    //完成测啦索引的测量

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件的宽高
        mHeight = getMeasuredHeight();
        cellWidth = getMeasuredWidth();
        mCallheight = mHeight * 1.0f / LETTERS.length;
    }
    //记录用户上一次按下的位置,以便进行判断这一次所按住的位置是佛还是上一次的位置,如果的话,不作任何处理

    //重写触摸世界,返回值为true,方起效果

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下时回调
                eventY = event.getY();
                mCurrentindex = (int) (eventY / mCallheight);
                //为了防止字母重复
                if (mCurrentindex!=lastindex){
                    //为了角标越界

                    String mLetter = LETTERS[mCurrentindex];
                    toastutil.showToast(getContext(), mLetter);
                    lastindex=mCurrentindex;
                }



                break;
            case MotionEvent.ACTION_MOVE://移动时,回调
                eventY = event.getY();
                mCurrentindex = (int) (eventY / mCallheight);
                //为了防止字母重复
                if (mCurrentindex!=lastindex){
                    //为了角标越减,不停的恢复调用,进行判断

                    String mLetter2 = LETTERS[mCurrentindex];
                    toastutil.showToast(getContext(), mLetter2);
                    lastindex=mCurrentindex;
                }

                break;
            case MotionEvent.ACTION_UP://送开时,  回调

                break;

        }
            return true;
        }
    }



