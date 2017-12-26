package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionItem extends RelativeLayout {

    private ImageView mBgImage = null;
    private TextView mTitleView = null;

    private String mTitle = null;
    private Drawable mBgDrawable = null;
    private int mTitleColor = Color.BLACK;
    private float mTitleSize = 64;
    private LayoutParams mLayoutParams = null;

    public PromotionItem(Context context) {
        this(context, null);
    }

    public PromotionItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromotionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PromotionItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mBgImage = new ImageView(context);
        mTitleView = new TextView(context);
    }

    public void show() {
        LayoutParams image_lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mBgImage.setImageDrawable(mBgDrawable);
        addView(mBgImage, image_lp);

        if (!TextUtils.isEmpty(mTitle)){
            mTitleView.setText(mTitle);
            mTitleView.setTextColor(mTitleColor);
            mTitleView.setTextSize(mTitleSize);
            if (mLayoutParams == null){
                mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                mLayoutParams.addRule(CENTER_IN_PARENT);
            }
            addView(mTitleView, mLayoutParams);
        }
    }

    public void setBgDrawable(Drawable drawable){
        mBgDrawable = drawable;
    }

    public void setTitle(String title){
        if (TextUtils.isEmpty(title)){
            mTitleView.setVisibility(GONE);
        } else {
            mTitleView.setText(title);
            mTitleView.setVisibility(VISIBLE);
        }
    }

    public void setTitleColor(int color){
        mTitleView.setTextColor(color);
    }

    public void setTitleSize(float size){
        mTitleView.setTextSize(size);
    }

    public void setTitlePosition(LayoutParams lp){
        mLayoutParams = lp;
    }
}
