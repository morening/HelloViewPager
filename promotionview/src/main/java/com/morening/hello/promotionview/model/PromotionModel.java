package com.morening.hello.promotionview.model;

import com.morening.hello.promotionview.contract.Contract;
import com.morening.hello.promotionview.repository.IRepository;
import com.morening.hello.promotionview.view.LoadCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morening on 2017/12/9.
 */

public class PromotionModel<T extends DataBean> implements Contract.Model {

    private List<IRepository> mDataRepos = null;

    public PromotionModel(){
        mDataRepos = new ArrayList<>();
    }

    @Override
    public void load(LoadCallback callback) {
        List<T> datas = new ArrayList();
        boolean isSuccess = false;
        for (IRepository repo: mDataRepos){
            if (repo.get(datas)){
                callback.onSuccess(datas);
                isSuccess = true;
                break;
            }
        }
        if (!isSuccess){
            callback.onFail(-1, null);
        }
    }

    @Override
    public void addDataRepos(List dataRepos) {
        if (mDataRepos == null){
            mDataRepos = new ArrayList<>();
        }
        mDataRepos.clear();
        mDataRepos.addAll(dataRepos);
    }
}
