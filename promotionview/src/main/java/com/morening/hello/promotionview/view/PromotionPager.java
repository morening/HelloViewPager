package com.morening.hello.promotionview.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by morening on 2017/12/9.
 */

class PromotionPager extends ViewPager{

    private ValueAnimator mFullAnimator = null;
    private ValueAnimator mPartAnimator = null;
    private long mAutoScrollInterval = 3000L;
    private int mSlideNumber = 0;

    private int mTitleSize = 0;
    private int mTitleColor = Color.BLACK;
    private boolean mEnableTitleColorBalance = false;
    private RelativeLayout.LayoutParams mLayoutParams = null;

    protected PromotionPager(Context context) {
        this(context, null);
    }

    protected PromotionPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /*
     * set Auto Scroll Interval
     *
     * @param interval default by 3000L
     * @throws IllegalArgumentException when @param interval less than zero
     */
    protected void setAutoScrollInterval(long interval){
        if (interval < 0){
            throw new IllegalArgumentException("Interval can't be negative: "+interval);
        }
        mAutoScrollInterval = interval;
    }


    /*
     * set slide number
     *
     * @param number default by 0
     * @throws IllegalArgumentException when @param number less than zero
     */
    protected void setSlideNumber(int number){
        if (number < 0){
            throw new IllegalArgumentException("Number can't be negative: "+number);
        }
        mSlideNumber = number;
    }


    /*
         * start auto scroll
         *
         * @param position the initial position
         * @throws IllegalArgumentException when @param position less than zero
         */
    protected void startAutoScroll(final int position){
        if (position < 0){
            throw new IllegalArgumentException("Position can't be negative: "+position);
        }

        mFullAnimator = ValueAnimator.ofInt(0, mSlideNumber)
                .setDuration(mAutoScrollInterval *(mSlideNumber-1));
        mFullAnimator.setRepeatMode(ValueAnimator.RESTART);
        mFullAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mFullAnimator.setInterpolator(new LinearInterpolator());
        mFullAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        setCurrentItem((Integer) animation.getAnimatedValue(), true);
                    }
                });

        mPartAnimator = ValueAnimator.ofInt(position, mSlideNumber)
                .setDuration(mAutoScrollInterval * Math.abs(mSlideNumber-position-1));
        mPartAnimator.setInterpolator(new LinearInterpolator());
        mPartAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        setCurrentItem((Integer) animation.getAnimatedValue(), true);
                    }
                });

        if (position < mSlideNumber-1){
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(mPartAnimator, mFullAnimator);
            animatorSet.start();
        } else {
            mFullAnimator.start();
        }
    }


    /*
     * cancel auto scroll
     *
     */
    protected void cancelAutoScroll(){
        if (mFullAnimator != null){
            mFullAnimator.cancel();
            mFullAnimator = null;
        }
        if (mPartAnimator != null){
            mPartAnimator.cancel();
            mPartAnimator = null;
        }
    }

    protected void setTitleSize(int size){
        mTitleSize = size;
    }

    protected void setTitleColor(int color){
        mTitleColor = color;
    }

    protected void enableTitleColorBalance(boolean enable){
        mEnableTitleColorBalance = enable;
    }

    protected void setTitlePosition(RelativeLayout.LayoutParams lp){
        mLayoutParams = lp;
    }

    protected RelativeLayout.LayoutParams getTitlePosition(){
        return mLayoutParams;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter instanceof PromotionPagerAdapter){
            ((PromotionPagerAdapter) adapter).setTitleSize(mTitleSize);
            ((PromotionPagerAdapter) adapter).setTitleColor(mTitleColor);
            ((PromotionPagerAdapter) adapter).enableTitleColorBalance(mEnableTitleColorBalance);
            ((PromotionPagerAdapter) adapter).setTitlePosition(mLayoutParams);
        }
        super.setAdapter(adapter);
    }
}
