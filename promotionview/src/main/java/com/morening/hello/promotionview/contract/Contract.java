package com.morening.hello.promotionview.contract;

import com.morening.hello.promotionview.model.DataBean;
import com.morening.hello.promotionview.view.LoadCallback;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public interface Contract<T extends DataBean> {

    interface View<T> {
        void showItems(List<T> datas);
        void showDefault();
        void hideDefault();
    }

    interface Presenter<T> {
        void start();
    }

    interface Model<T> {
        void load(LoadCallback callback);
    }

}
