package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


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

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }


    /*
     * Show Indicator View with the specified dot view
     *
     * @param views the list of views, the type is T which extends from BaseView
     */
    public void show(int size) {
        if (mCustomViews == null){
            mCustomViews = new ArrayList<>();
        }
        if (mCustomViews.size() == 0){
            for (int k=0; k<size; k++){
                mCustomViews.add((T) new DotView(mContext));
            }
        }

        setOrientation(HORIZONTAL);
        for (int k = 0; k< mCustomViews.size(); k++){
            LayoutParams lp =
                    new LayoutParams(56, 56);
            if (k == 0){
                lp.setMarginStart(0);  // todo hardcoding
            } else {
                lp.setMarginStart(56);
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
    public void setSelectedItem(int position){
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

    public void setCustomViews(List<T> customViews){
        mCustomViews = customViews;
    }

    public void setSelectedColor(int color){
        mSelectedColor = color;
    }

    public void setUnselectedColor(int color){
        mUnselectedColor = color;
    }

    public void enableColorBalance(boolean enable){
        mEnableColorBalance = enable;
    }

    public boolean isColorBalance(){

        return mEnableColorBalance;
    }
}
