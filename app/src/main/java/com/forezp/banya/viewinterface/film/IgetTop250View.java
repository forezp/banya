package com.forezp.banya.viewinterface.film;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.top250.Root;

/**
 * Created by forezp on 16/9/21.
 */
public interface IgetTop250View extends IBaseView{
    void getTop250Success(Root root,boolean isLoadMore);

    /**
     * 网络请求失败
     */
    void getDataFail();

}
