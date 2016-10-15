package com.forezp.banya.viewinterface.music;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.music.Musics;

/**
 * Created by forezp on 16/10/1.
 */
public interface IGetMusicById extends IBaseView{
    void getMusicSucess(Musics music);
}
