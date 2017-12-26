package com.morening.hello.promotionview.presenter;

import com.morening.hello.promotionview.contract.Contract;
import com.morening.hello.promotionview.model.DataBean;
import com.morening.hello.promotionview.model.PromotionModel;
import com.morening.hello.promotionview.view.LoadCallback;
import com.morening.hello.promotionview.view.PromotionView;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionPresenter<T extends DataBean> implements Contract.Presenter {

    private Contract.View mView = null;
    private Contract.Model mModel = null;

    public PromotionPresenter(PromotionView view) {
        this.mView = view;
    }

    @Override
    public void start() {
        if (mView != null){
            mView.showDefault();
        }

        if (mModel == null){
            mModel = new PromotionModel();
        }
        mModel.load(new LoadCallback() {
            @Override
            public void onSuccess(List datas) {
                mView.hideDefault();
                mView.showItems(datas);
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                mView.showDefault();
            }
        });
    }
}
