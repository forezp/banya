package com.forezp.banya.viewinterface.film;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.filmlive.FilmLive;

/**
 * Created by forezp on 16/9/23.
 */
public interface IGetFilmLiveView extends IBaseView {

    void getFilmLiveSuccess(FilmLive filmLive);

    /**
     * 网络请求失败
     */
    void getDataFail();
}
