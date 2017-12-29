package com.morening.hello.helloviewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.model.DataBean;
import com.morening.hello.promotionview.repository.IRepository;
import com.morening.hello.promotionview.util.Utils;
import com.morening.hello.promotionview.view.PromotionView;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);

                LinearLayout container = new LinearLayout(MainActivity.this);
                container.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (getWindow().getDecorView().getMeasuredHeight()-3*2)/4);

                initPromotionView0(container, lp);
                addDivider(container);
                initPromotionView1(container, lp);
                addDivider(container);
                initPromotionView2(container, lp);
                addDivider(container);
                initPromotionView3(container, lp);

                setContentView(container);
            }
        });
    }

    private void addDivider(LinearLayout container){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        View divider = new View(MainActivity.this);
        divider.setBackgroundColor(Color.GRAY);
        container.addView(divider, lp);
    }

    private void initPromotionView0(LinearLayout container, LinearLayout.LayoutParams lp) {
        PromotionView promotionView0 = new PromotionView(MainActivity.this);
        RelativeLayout.LayoutParams title_lp0 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_lp0.addRule(RelativeLayout.CENTER_IN_PARENT);
        title_lp0.bottomMargin = Utils.dp2px(MainActivity.this, 10);
        title_lp0.setMarginEnd(Utils.dp2px(MainActivity.this, 10));
        promotionView0.setDefaultPageTitlePosition(title_lp0);
        promotionView0.setDefaultPageTitleColor(Color.BLUE);
        promotionView0.setDefaultPageTitleSize(Utils.sp2px(MainActivity.this, 12));
        promotionView0.show();
        container.addView(promotionView0, lp);
    }

    private void initPromotionView1(LinearLayout container, LinearLayout.LayoutParams lp) {
        PromotionView promotionView1 = new PromotionView(MainActivity.this);
        RelativeLayout.LayoutParams title_lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        title_lp1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        title_lp1.topMargin = Utils.dp2px(MainActivity.this, 10);
        promotionView1.setTitlePosition(title_lp1);
        promotionView1.setPromotionTitleSize(Utils.sp2px(MainActivity.this, 16));
        promotionView1.setIndicatorCustomViewPosition(Utils.dp2px(MainActivity.this, 16), Utils.dp2px(MainActivity.this, 16), Utils.dp2px(MainActivity.this, 16), 0);
        RelativeLayout.LayoutParams indicator_lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        indicator_lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicator_lp1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        indicator_lp1.bottomMargin = Utils.dp2px(MainActivity.this, 10);
        promotionView1.setIndicatorPosition(indicator_lp1);
        promotionView1.setAutoScrollInterval(9000);
        promotionView1.addDataRepo(new IRepository() {
            @Override
            public boolean get(List datas) {
                datas.add(new DataBean("7", null));
                datas.add(new DataBean("8", null));
                datas.add(new DataBean("9", null));
                return true;
            }
        });
        promotionView1.show();
        container.addView(promotionView1, lp);
    }

    private void initPromotionView2(LinearLayout container, LinearLayout.LayoutParams lp) {
        PromotionView promotionView2 = new PromotionView(MainActivity.this);
        RelativeLayout.LayoutParams title_lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        title_lp2.addRule(RelativeLayout.ALIGN_PARENT_START);
        title_lp2.topMargin = Utils.dp2px(MainActivity.this, 10);
        title_lp2.setMarginStart(Utils.dp2px(MainActivity.this, 10));
        promotionView2.setTitlePosition(title_lp2);
        promotionView2.setPromotionTitleSize(Utils.sp2px(MainActivity.this, 22));
        promotionView2.setIndicatorCustomViewPosition(Utils.dp2px(MainActivity.this, 22), Utils.dp2px(MainActivity.this, 22), Utils.dp2px(MainActivity.this, 22), 0);
        RelativeLayout.LayoutParams indicator_lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        indicator_lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicator_lp2.addRule(RelativeLayout.ALIGN_PARENT_END);
        indicator_lp2.bottomMargin = Utils.dp2px(MainActivity.this, 10);
        indicator_lp2.setMarginEnd(Utils.dp2px(MainActivity.this, 10));
        promotionView2.setIndicatorPosition(indicator_lp2);
        promotionView2.setAutoScrollInterval(3000);
        promotionView2.addDataRepo(new IRepository() {
            @Override
            public boolean get(List datas) {
                datas.add(new DataBean("4", null));
                datas.add(new DataBean("5", null));
                datas.add(new DataBean("6", null));
                return true;
            }
        });
        promotionView2.show();
        container.addView(promotionView2, lp);
    }

    private void initPromotionView3(LinearLayout container, LinearLayout.LayoutParams lp) {
        PromotionView promotionView3 = new PromotionView(MainActivity.this);
        RelativeLayout.LayoutParams title_lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        title_lp3.addRule(RelativeLayout.ALIGN_PARENT_END);
        title_lp3.bottomMargin = Utils.dp2px(MainActivity.this, 10);
        title_lp3.setMarginEnd(Utils.dp2px(MainActivity.this, 10));
        promotionView3.setTitlePosition(title_lp3);
        promotionView3.setPromotionTitleSize(Utils.sp2px(MainActivity.this, 28));
        promotionView3.setIndicatorCustomViewPosition(Utils.dp2px(MainActivity.this, 28), Utils.dp2px(MainActivity.this, 28), Utils.dp2px(MainActivity.this, 28), 0);
        RelativeLayout.LayoutParams indicator_lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        indicator_lp3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        indicator_lp3.addRule(RelativeLayout.ALIGN_PARENT_START);
        indicator_lp3.topMargin = Utils.dp2px(MainActivity.this, 10);
        indicator_lp3.setMarginStart(Utils.dp2px(MainActivity.this, 10));
        promotionView3.setIndicatorPosition(indicator_lp3);
        promotionView3.setAutoScrollInterval(1000);
        promotionView3.addDataRepo(new IRepository() {
            @Override
            public boolean get(List datas) {
                datas.add(new DataBean("1", null));
                datas.add(new DataBean("2", null));
                datas.add(new DataBean("3", null));
                return true;
            }
        });
        promotionView3.show();
        container.addView(promotionView3, lp);
    }
}
