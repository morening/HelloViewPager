package com.morening.hello.promotionview.repository;

import com.morening.hello.promotionview.model.DataBean;

import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class CacheRepo implements IRepository {
    @Override
    public boolean get(List datas) {

        datas.add(new DataBean("123", null));
        datas.add(new DataBean("456", null));
        datas.add(new DataBean("789", null));

        return true;
    }
}
