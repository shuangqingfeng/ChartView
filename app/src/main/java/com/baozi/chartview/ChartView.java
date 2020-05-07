package com.baozi.chartview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.core.content.ContextCompat;

import java.util.List;

/**
 * @author: baoZi
 * @date: 2020/5/6
 * @desc:
 */
public class ChartView extends View {

    private Paint mRectPaint;
    private int mItemWidth;
    private Paint mMinutePaint;
    private int mScreenWidth;

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_bright));
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setStrokeCap(Paint.Cap.ROUND);
        mRectPaint.setStrokeJoin(Paint.Join.ROUND);

        mMinutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mMinutePaint.setColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));

        mMinutePaint.setStyle(Paint.Style.FILL);
        mMinutePaint.setStrokeCap(Paint.Cap.ROUND);
        mMinutePaint.setStrokeJoin(Paint.Join.ROUND);
        mMinutePaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mItemWidth = mScreenWidth / 8;
    }

    private int minute = 5;
    private int mItemHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int rectWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getResources().getDisplayMetrics());
        int heightRatio = mItemHeight / minute;
        for (int i = 0; i < 8; i++) {
            Car car = cars.get(i);
            int itemWidth = i * mItemWidth;
            int left = itemWidth + (mItemWidth - rectWidth) / 2;
            int right = left + rectWidth;
            int top = (int) (mItemHeight - car.getMinute() * heightRatio);
            int bottom = mItemHeight;
            LinearGradient linearGradient = new LinearGradient(left, top, right, bottom, Color.parseColor("#80cbff"),
                    Color.parseColor("#0096ff"),
                    Shader.TileMode.REPEAT);
            mRectPaint.setShader(linearGradient);
            canvas.drawRect(left, top, right, bottom, mRectPaint);
            Rect bounds = new Rect();
            String text = car.getMinuteDesc();
            mMinutePaint.getTextBounds(text, 0, text.length(), bounds);
            float offSetY = (bounds.top + bounds.bottom) / 2;
            float offSetX = (bounds.left + bounds.right) / 2;
            int minuteTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            canvas.drawText(text, itemWidth + (mItemWidth / 2) - offSetX,
                    mItemHeight + minuteTop + bounds.height() + offSetY,
                    mMinutePaint);

            Rect bounds1 = new Rect();
            String text1 = car.getDesc();
            mMinutePaint.getTextBounds(text1, 0, text1.length(), bounds1);
            float offSetY1 = (bounds1.top + bounds1.bottom) / 2;
            float offSetX1 = (bounds1.left + bounds1.right) / 2;
            int minuteTop1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            canvas.drawText(text1, itemWidth + (mItemWidth / 2) - offSetX1,
                    mItemHeight + minuteTop + bounds.height() + minuteTop1 + bounds1.height() + offSetY1,
                    mMinutePaint);
        }
    }

    private List<Car> cars;

    public void setData(List<Car> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        cars = list;
        AnimatorSet set = new AnimatorSet();
        for (int i = 0; i < cars.size(); i++) {
            ValueAnimator animator = ValueAnimator.ofFloat(0, cars.get(i).getMinute());

            final int finalI = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    cars.get(finalI).setMinute((Float) valueAnimator.getAnimatedValue());
                    invalidate();
                }
            });
            set.playTogether(animator);
        }
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(2000);
        set.start();
//        invalidate();
    }
}
