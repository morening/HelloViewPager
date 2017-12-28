package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


import com.morening.hello.promotionview.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * IndicatorView could include some dot view{@link DotView}
 * to showItems the current page position
 *
 * Created by morening on 2017/12/9.
 */

class IndicatorView<T extends BaseView> extends LinearLayout{

    private Context mContext = null;
    private List<T> mCustomViews = null;
    private int mSelectedColor = Color.BLACK;
    private int mUnselectedColor = Color.RED;
    private boolean mEnableColorBalance = false;
    private int mCustomViewWidth = 0;
    private int mCustomViewHeight = 0;
    private int mCustomViewMarginStart = 0;
    private int mCustomViewMarginEnd = 0;

    private static final int DEFAULT_CUSTOM_VIEW_WIDTH = 28;
    private static final int DEFAULT_CUSTOM_VIEW_HEIGHT = 28;
    private static final int DEFAULT_CUSTOM_VIEW_MARGIN_START = 28;
    private static final int DEFAULT_CUSTOM_VIEW_MARGIN_END = 0;

    protected IndicatorView(Context context) {
        this(context, null);
    }

    protected IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    protected IndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

        mCustomViewWidth = Utils.dp2px(context, DEFAULT_CUSTOM_VIEW_WIDTH);
        mCustomViewHeight = Utils.dp2px(context, DEFAULT_CUSTOM_VIEW_HEIGHT);
        mCustomViewMarginStart = Utils.dp2px(context, DEFAULT_CUSTOM_VIEW_MARGIN_START);
        mCustomViewMarginEnd = Utils.dp2px(context, DEFAULT_CUSTOM_VIEW_MARGIN_END);
    }


    /*
     * Show Indicator View with the specified dot view
     *
     * @param views the list of views, the type is T which extends from BaseView
     */
    protected void show(int size) {
        if (mCustomViews == null){
            mCustomViews = new ArrayList<>();
        }

        /* get default custom view */
        if (mCustomViews.size() == 0){
            for (int k=0; k<size; k++){
                mCustomViews.add((T) new DotView(mContext));
            }
        }
        /* get default custom view */

        setOrientation(HORIZONTAL);
        for (int k = 0; k< size; k++){
            LayoutParams lp =
                    new LayoutParams(mCustomViewWidth, mCustomViewHeight);
            if (k == 0){
                lp.setMarginStart(0);
                lp.setMarginEnd(mCustomViewMarginEnd);
            } else if (k == size-1) {
                lp.setMarginStart(mCustomViewMarginStart);
                lp.setMarginEnd(0);
            } else {
                lp.setMarginStart(mCustomViewMarginStart);
                lp.setMarginEnd(mCustomViewMarginEnd);
            }
            T view = mCustomViews.get(k);
            view.setDotColor(mUnselectedColor);
            addView(view, lp);
        }
    }


    /*
     * set the selected item with the specified position
     *
     * @param position the select position
     */
    protected void setSelectedItem(int position){
        if (mCustomViews == null || mCustomViews.size() == 0){
            return;
        }

        for (int k = 0; k< mCustomViews.size(); k++){
            T view = mCustomViews.get(k);
            if (k == position){
                view.setDotColor(mSelectedColor);
            } else {
                view.setDotColor(mUnselectedColor);
            }
        }
    }

    protected void setCustomViews(List<T> customViews){
        mCustomViews = customViews;
    }

    protected void setSelectedColor(int color){
        mSelectedColor = color;
    }

    protected void setUnselectedColor(int color){
        mUnselectedColor = color;
    }

    protected void enableColorBalance(boolean enable){
        mEnableColorBalance = enable;
    }

    protected boolean isColorBalance(){

        return mEnableColorBalance;
    }

    protected void setCustomViewsPosition(int width, int height, int marginStart, int marginEnd) {
        mCustomViewWidth = width;
        mCustomViewHeight = height;
        mCustomViewMarginStart = marginStart;
        mCustomViewMarginEnd = marginEnd;
    }
}
