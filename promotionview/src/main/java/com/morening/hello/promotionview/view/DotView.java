package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * DotView extended from BaseView {@link BaseView}
 * This is a type of implementation of the indicator view {@link IndicatorView}
 *
 * Created by morening on 2017/12/10.
 */

public class DotView extends BaseView {

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);

        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(0, 0);
        canvas.drawCircle(radius/2, radius/2, radius/2, mPaint);
        canvas.restore();
    }
}
