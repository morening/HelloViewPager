package com.morening.hello.promotionview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.contract.Contract;
import com.morening.hello.promotionview.presenter.PromotionPresenter;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionView<T extends BaseView> extends RelativeLayout implements Contract.View {

    private Context mContext = null;

    private IndicatorView mIndicatorView = null;
    private PromotionPager mPromotionPager = null;
    private PromotionItem mDefaultPage = null;

    private int mCurrPosition = 0;

    private static final long DEFAULT_INTERVAL_AUTO_SCROLL = 3000L;
    private long mAutoScrollInterval = DEFAULT_INTERVAL_AUTO_SCROLL;

    private PromotionPresenter mPresenter = null;
    private boolean mEnableAutoScroll = true;

    public PromotionView(Context context) {
        this(context, null);
    }

    public PromotionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromotionView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PromotionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        mPresenter = new PromotionPresenter(this);

        mIndicatorView = new IndicatorView(mContext);
        mPromotionPager = new PromotionPager(mContext);
        mDefaultPage = new PromotionItem(mContext);
    }

    public void show(){
        mPresenter.start();
    }

    @Override
    public void showItems(final List datas) {

        initIndicatorView(datas);

        initPromotionPager(datas);

        if (mEnableAutoScroll){
            mPromotionPager.startAutoScroll(0);
        }
    }


    /****** Indicator View ******/

    /*
     * set the width of Indicator
     *
     * @param width the width
     */
    public PromotionView setIndicatorWidth(int width){

        if (mIndicatorView != null){
            LayoutParams lp = (LayoutParams) mIndicatorView.getLayoutParams();
            lp.width = width;
            mIndicatorView.setLayoutParams(lp);
        }

        return this;
    }


    /*
     * set the height of Indicator
     *
     * @param height the height
     */
    public PromotionView setIndicatorHeight(int height){

        if (mIndicatorView != null){
            LayoutParams lp = (LayoutParams) mIndicatorView.getLayoutParams();
            lp.height = height;
            mIndicatorView.setLayoutParams(lp);
        }

        return this;
    }


    /*
     * set custom view for the Indicator
     *
     * @param customViews the list of custom view extends from BaseView {@link BaseView}
     */
    public PromotionView setIndicatorCustomView(List<T> customViews){

        if (mIndicatorView != null){
            mIndicatorView.setCustomViews(customViews);
        }

        return this;
    }


    /*
     * set the position the Indicator
     *
     * @param lp the type is RelativeLayout.LayoutParams
     */
    public PromotionView setIndicatorPosition(RelativeLayout.LayoutParams lp){

        if (mIndicatorView != null){
            addView(mIndicatorView, lp);
        }

        return this;
    }


    /*
     * set the color of indicator
     *
     * @param color the type is Color
     */
    public PromotionView setIndicatorColor(int selected, int unselected){
        if (mIndicatorView != null){
            mIndicatorView.setSelectedColor(selected);
            mIndicatorView.setUnselectedColor(unselected);
        }

        return this;
    }


    /*
     * enable Indicator color balance
     * If enable Indicator Color Balance,
     * the settings to set unselected/selected color will not be worked
     * Because it will be determined by the color around the promotion view background
     *
     * @param enable the type is boolean
     */
    public PromotionView enableIndicatorColorBalance(boolean enable){
        if (mIndicatorView != null){
            mIndicatorView.enableColorBalance(enable);
        }
        return this;
    }

    private void initIndicatorView(List datas) {
        if (mIndicatorView != null){
            if (mIndicatorView.isColorBalance()){
                balanceIndicatorColor();
            }
            mIndicatorView.show(datas.size());
            mIndicatorView.setSelectedItem(0);
        }
    }

    private void balanceIndicatorColor(){
        int unselectedColor = Color.BLACK;
        int selectedColor = Color.RED;

        // todo add the implementation here

        mIndicatorView.setUnselectedColor(unselectedColor);
        mIndicatorView.setSelectedItem(selectedColor);
    }

    /****** Indicator View ******/




    /****** Promotion View ******/

    /*
     * set the color of the Promotion title
     *
     * @param color the title color
     */
    public PromotionView setPromotionTitleColor(int color){
        if (mPromotionPager != null){
            mPromotionPager.setTitleColor(color);
        }
        return this;
    }

    /*
     * enable Promotion title color balance
     *
     * If enable Promotion Title Color Balance,
     * the settings to set title color will not be worked
     * Because it will be determined by the color around the promotion view background
     */
    public PromotionView enablePromotionTitleColorBalance(boolean enable){
        if (mPromotionPager != null){
            mPromotionPager.enableTitleColorBalance(enable);
        }
        return this;
    }

    public PromotionView setTitlePosition(LayoutParams lp){
        if (mPromotionPager != null){
            mPromotionPager.setTitlePosition(lp);
        }
        return this;
    }

    private void initPromotionPager(List datas) {
        LayoutParams pager_lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mPromotionPager, pager_lp);

        PromotionPagerAdapter adapter = new PromotionPagerAdapter(mContext);
        mPromotionPager.setAdapter(adapter);
        mPromotionPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                mIndicatorView.setSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPromotionPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:   // 0
                        mPromotionPager.cancelAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:     // 1
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mEnableAutoScroll){
                                    mPromotionPager.startAutoScroll(mCurrPosition);
                                }
                            }
                        }, mAutoScrollInterval);
                        break;
                }
                return false;
            }
        });
        mPromotionPager.setSlideNumber(datas.size());
    }

    /****** Promotion View ******/



    /****** Default Page******/

    public PromotionView setDefaultPageTitle(String title){
        if (mDefaultPage != null){
            mDefaultPage.setTitle(title);
        }
        return this;
    }

    public PromotionView setDefaultPageTitleColor(int color){
        if (mDefaultPage != null){
            mDefaultPage.setTitleColor(color);
        }
        return this;
    }

    public PromotionView setDefaultPageBgImage(Drawable bgDrawable){
        if (mDefaultPage != null){
            mDefaultPage.setBgDrawable(bgDrawable);
        }
        return this;
    }

    /****** Default Page******/



    /****** Auto Scroll ******/

    public PromotionView enableAutoScroll(boolean enable){
        mEnableAutoScroll = enable;
        if (mEnableAutoScroll && mPromotionPager != null){
            mPromotionPager.startAutoScroll(mCurrPosition);
        }
        return this;
    }

    public PromotionView setAutoScrollInterval(long interval){
        if (mPromotionPager != null){
            mPromotionPager.setAutoScrollInterval(interval);
            mAutoScrollInterval = interval;
        }
        return this;
    }

    /****** Auto Scroll ******/


    @Override
    public void showDefault() {
        removeAllViews();
        mIndicatorView = null;
        mPromotionPager = null;

        if (mDefaultPage == null){
            mDefaultPage = new PromotionItem(mContext);
            mDefaultPage.setTitle("广告位招租中...");
            mDefaultPage.setTitleColor(Color.BLACK);
            mDefaultPage.setBgDrawable(new ColorDrawable(Color.YELLOW));
        }
        LayoutParams lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mDefaultPage, lp);
    }

    @Override
    public void hideDefault() {
        removeAllViews();
        mDefaultPage = null;
    }
}
