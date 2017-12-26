package com.morening.hello.helloviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.morening.hello.promotionview.view.PromotionView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PromotionView promotionView = new PromotionView(this);
        promotionView.setCenterHorizontalVerticalInParent(-1)
                .setAlignParentStartEnd(RelativeLayout.ALIGN_PARENT_END)
                .setAlignParentTopBottom(RelativeLayout.ALIGN_PARENT_BOTTOM)
                .setMarginEnd(126)
                .setMarginBottom(126);
        promotionView.show();
        setContentView(promotionView);
    }
}
