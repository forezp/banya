package com.forezp.banya.viewinterface.film;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.filmdetail.FilmDetail;

/**
 * Created by forezp on 16/9/25.
 */
public interface IGetFilmDetail extends IBaseView{

    void getFilmDetailSuccess(FilmDetail filmDetail);

    /**
     * 网络请求失败
     */
    void getDataFail();

}
