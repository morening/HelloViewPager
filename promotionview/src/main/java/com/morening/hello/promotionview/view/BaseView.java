package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * This is a base view which should be extended as you wish
 * eg. DotView is a type of implementation
 *
 * IndicatorView {@link IndicatorView} should include
 * as many Views(extended from BaseView) as Page View
 * in the PromotionPager {@link PromotionPager}
 *
 * Created by morening on 2017/12/14.
 */

class BaseView extends View{

    protected Paint mPaint = new Paint();

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setDotColor(int color){
        mPaint.setColor(color);
        invalidate();
    }

}
