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
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.contract.Contract;
import com.morening.hello.promotionview.presenter.PromotionPresenter;
import com.morening.hello.promotionview.repository.CacheRepo;
import com.morening.hello.promotionview.repository.DatabaseRepo;
import com.morening.hello.promotionview.repository.IRepository;
import com.morening.hello.promotionview.repository.RemoteRepo;
import com.morening.hello.promotionview.util.Utils;

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

    private static final int DEFAULT_INDICATOR_MARGIN_BOTTOM = 126;
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
        mDefaultPage.setTitle("广告位招租中...");
        mDefaultPage.setTitleSize(Utils.sp2px(mContext, 30));
        mDefaultPage.setTitleColor(Color.BLACK);
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

    public PromotionView setIndicatorCustomViewPosition(int width, int height, int marginStart, int marginEnd){
        if (mIndicatorView != null){
            mIndicatorView.setCustomViewsPosition(width, height, marginStart, marginEnd);
        }

        return this;

    }

    /*
     * set the position of the Indicator
     *
     * @param lp the type is RelativeLayout.LayoutParams
     */
    public PromotionView setIndicatorPosition(LayoutParams lp){
        mIndicatorPosition = lp;

        return this;
    }

    /*
     * get the position of the Indicator
     *
     * @return RelativeLayout.LayoutParams
     */
    public LayoutParams getIndicatorPosition(){
        return mIndicatorPosition;
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

    private LayoutParams mIndicatorPosition = null;
    private void initIndicatorView(List datas) {
        if (mIndicatorView != null){
            if (mIndicatorView.isColorBalance()){
                balanceIndicatorColor();
            }
            mIndicatorView.show(datas.size());
            mIndicatorView.setSelectedItem(0);
            if (mIndicatorPosition == null){
                mIndicatorPosition = getIndicatorPositionDefault();
            }
            addView(mIndicatorView, mIndicatorPosition);
        }
    }

    private LayoutParams getIndicatorPositionDefault(){
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL);
        lp.addRule(ALIGN_PARENT_BOTTOM);
        lp.bottomMargin = Utils.dp2px(mContext, DEFAULT_INDICATOR_MARGIN_BOTTOM);

        return lp;
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

    public PromotionView setPromotionTitleSize(int size){
        if (mPromotionPager != null){
            mPromotionPager.setTitleSize(size);
        }
        return this;
    }

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

    /*
     * set title position for every item
     *
     * @param lp RelativeLayout.LayoutParams
     */
    public PromotionView setTitlePosition(LayoutParams lp){
        if (mPromotionPager != null){
            mPromotionPager.setTitlePosition(lp);
        }
        return this;
    }

    /*
     * get the title position
     *
     * @return RelativeLayout.LayoutParams
     */
    public LayoutParams getTitlePosition(){
        if (mPromotionPager != null){
            return mPromotionPager.getTitlePosition();
        }

        return null;
    }

    private void initPromotionPager(List<T> datas) {
        LayoutParams lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mPromotionPager, lp);

        PromotionPagerAdapter adapter = new PromotionPagerAdapter(mContext);
        adapter.setData(datas);
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

    public PromotionView setDefaultPageTitleSize(int size){
        if (mDefaultPage != null){
            if (size != 0){
                mDefaultPage.setTitleSize(size);
            }
        }
        return this;
    }

    public PromotionView setDefaultPageTitleColor(int color){
        if (mDefaultPage != null){
            mDefaultPage.setTitleColor(color);
        }
        return this;
    }

    public PromotionView setDefaultPageTitlePosition(LayoutParams lp){
        if (mDefaultPage != null){
            mDefaultPage.setTitlePosition(lp);
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



    /****** MODEL I/F ******/

    /*
     * add data repository
     * The data will be loaded as added sequence. If the first repo could provide the promotion data, the left repo will not be loaded.
     * So it is important that adding repo as data loaded sequence.
     * eg, if you have add CacheRepo to load the data saved in cache memory, add DatabaseRepo to load the data from DB and add RemoteRepo to get the data from remote server,
     * you have to CacheRepo{@link CacheRepo} added firstly, add DatabaseRepo{@link DatabaseRepo} and RemoteRepo{@link RemoteRepo} added at last.
     * If CacheRepo can't provide data(means return false), then DatabaseRepo will be loaded, and DatabaseRepo could provide data(means return true),
     * it is no neccessary to load RemoteRepo(mean RemoteRepo will not be loaded any more)
     *
     * @param repo IRepository
     */
    public PromotionView addDataRepo(IRepository repo){
        mPresenter.addDataRepo(repo);

        return this;
    }

    /****** MODEL I/F ******/




    @Override
    public void showDefault() {
        removeAllViews();

        LayoutParams lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mDefaultPage.show();
        addView(mDefaultPage, lp);
    }

    @Override
    public void hideDefault() {
        removeAllViews();
    }
}
