package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morening.hello.promotionview.util.Utils;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionItem extends RelativeLayout {

    private ImageView mBgImage = null;
    private TextView mTitleView = null;

    private String mTitle = null;
    private Drawable mBgDrawable = null;
    private int mTitleColor = Color.BLACK;
    private float mTitleSize = 0;
    private LayoutParams mLayoutParams = null;

    protected PromotionItem(Context context) {
        this(context, null);
    }

    protected PromotionItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected PromotionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    protected PromotionItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mBgImage = new ImageView(context);
        mTitleView = new TextView(context);
        mTitleSize = Utils.sp2px(context, 48);
    }

    protected void show() {
        removeAllViews();

        if (mBgDrawable != null){
            LayoutParams image_lp =
                    new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mBgImage.setImageDrawable(mBgDrawable);
            addView(mBgImage, image_lp);
        }

        if (!TextUtils.isEmpty(mTitle)){
            mTitleView.setText(mTitle);
            mTitleView.setTextColor(mTitleColor);
            mTitleView.setTextSize(mTitleSize);
            if (mLayoutParams == null){
                mLayoutParams = getTitlePositionDefault();
            }
            addView(mTitleView, mLayoutParams);
        }
    }

    private LayoutParams getTitlePositionDefault(){
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_IN_PARENT);

        return lp;
    }

    protected void setBgDrawable(Drawable drawable){
        if (drawable != null){
            mBgDrawable = drawable;
        }
    }

    protected void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTitle = title;
        }
    }

    protected void setTitleColor(int color){
        mTitleColor = color;
    }

    protected void setTitleSize(float size){
        if (size > 0){
            mTitleSize = size;
        }
    }

    protected void setTitlePosition(LayoutParams lp){
        if (lp != null){
            mLayoutParams = lp;
        }
    }
}
