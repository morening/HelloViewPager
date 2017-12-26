package com.morening.hello.promotionview.model;

import com.morening.hello.promotionview.contract.Contract;
import com.morening.hello.promotionview.repository.CacheRepo;
import com.morening.hello.promotionview.repository.DatabaseRepo;
import com.morening.hello.promotionview.repository.IRepository;
import com.morening.hello.promotionview.repository.RemoteRepo;
import com.morening.hello.promotionview.view.LoadCallback;
import com.morening.hello.promotionview.view.PromotionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionModel<T extends DataBean> implements Contract.Model {

    private IRepository mCacheRepo = null;
    private IRepository mDatabaseRepo = null;
    private IRepository mRemoteRepo = null;

    public PromotionModel(){
        mCacheRepo = new CacheRepo();
        mDatabaseRepo = new DatabaseRepo();
        mRemoteRepo = new RemoteRepo();
    }

    @Override
    public void load(LoadCallback callback) {
        List<T> datas = new ArrayList();
        if (mCacheRepo.get(datas)){
            callback.onSuccess(datas);
        } else if (mDatabaseRepo.get(datas)) {
            callback.onSuccess(datas);
        } else if (mRemoteRepo.get(datas)){
            callback.onSuccess(datas);
        } else {
            callback.onFail(-1, null);
        }
    }
}
