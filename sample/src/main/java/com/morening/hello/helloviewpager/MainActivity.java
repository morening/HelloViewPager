package com.morening.hello.helloviewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.view.PromotionView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PromotionView promotionView = new PromotionView(this);
        RelativeLayout.LayoutParams titlePosition_lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePosition_lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        titlePosition_lp.topMargin = 126;
        promotionView.setTitlePosition(titlePosition_lp);
        promotionView.setPromotionTitleColor(Color.BLACK);
        promotionView.setIndicatorColor(Color.RED, Color.BLUE);
//        promotionView.enableAutoScroll(true);
        promotionView.show();
        setContentView(promotionView);
    }
}
