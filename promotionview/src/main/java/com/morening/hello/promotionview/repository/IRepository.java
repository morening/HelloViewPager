package com.morening.hello.promotionview.repository;

import com.morening.hello.promotionview.model.DataBean;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public interface IRepository<T extends DataBean> {

    /*
     * get the data to show
     *
     * @param datas a data List
     * @return the loaded result, if true, the data will be shown; otherwise, default page will be displayed.
     */
    boolean get(List<T> datas);
}
