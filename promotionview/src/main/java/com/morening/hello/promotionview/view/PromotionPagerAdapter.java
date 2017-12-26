package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.model.DataBean;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionPagerAdapter<T extends DataBean> extends PagerAdapter {

    private Context mContext = null;
    private List<T> mDatas = null;

    private int mTitleColor = Color.BLACK;
    private boolean mEnableTitleColorBalance = false;
    private RelativeLayout.LayoutParams mLayoutParams = null;

    public PromotionPagerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<T> datas){
        mDatas = datas;
    }

    @Override
    public int getCount() {
        if (mDatas == null){
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PromotionItem item = new PromotionItem(mContext);
        T data = mDatas.get(position);
        item.setBgDrawable(data.getImage());
        item.setTitle(data.getTitle());
        if (mEnableTitleColorBalance){
            balanceTitleColor();
        }
        item.setTitleColor(mTitleColor);
        item.setTitlePosition(mLayoutParams);
        item.show();
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((PromotionItem)object);
    }

    public void setTitleColor(int color){
        mTitleColor = color;
    }

    public void enableTitleColorBalance(boolean enable){
        mEnableTitleColorBalance = enable;
    }

    private void balanceTitleColor(){
        int color = Color.BLACK;

        // todo add the implementation here

        mTitleColor = color;
    }

    public void setTitlePosition(RelativeLayout.LayoutParams lp){
        mLayoutParams = lp;
    }
}
