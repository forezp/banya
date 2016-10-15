package com.forezp.banya.viewinterface.film;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.filmusbox.FilmUsBox;

/**
 * Created by forezp on 16/9/25.
 */
public interface IGetUsBoxView extends IBaseView{

    void getFilmUsBoxSuccess(FilmUsBox filmUsBox);

    /**
     * 网络请求失败
     */
    void getDataFail();
}
