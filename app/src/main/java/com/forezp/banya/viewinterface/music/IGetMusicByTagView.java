package com.forezp.banya.viewinterface.music;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.music.MusicRoot;

/**
 * Created by forezp on 16/9/30.
 */
public interface IGetMusicByTagView extends IBaseView{

    void getMusicByTagSuccess(MusicRoot musicRoot,boolean isLoadMore);
}
