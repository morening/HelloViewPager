package com.morening.hello.promotionview.view;

import com.morening.hello.promotionview.model.DataBean;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public interface LoadCallback<T extends DataBean> {

    void onSuccess(List<T> datas);

    void onFail(int errCode, String errMsg);
}
