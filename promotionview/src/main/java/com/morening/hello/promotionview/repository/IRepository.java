package com.morening.hello.promotionview.repository;

import com.morening.hello.promotionview.model.DataBean;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public interface IRepository<T extends DataBean> {

    boolean get(List<T> datas);
}
