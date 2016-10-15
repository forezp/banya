package com.forezp.banya.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.forezp.banya.base.BasePresenter;
import com.forezp.banya.bean.music.MusicRoot;
import com.forezp.banya.bean.music.Musics;
import com.forezp.banya.viewinterface.music.IGetMusicById;
import com.forezp.banya.viewinterface.music.IGetMusicByTagView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by forezp on 16/9/30.
 */
public class DoubanMusicPresenter extends BasePresenter {
    public DoubanMusicPresenter(Context context) {
        super(context);
    }

    /**
     * @param
     */
    public void searchMusicByTag(IGetMusicByTagView iGetMusicByTagView, String TAG , boolean isLoadMore){

        doubanApi.searchMusicByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(musicRoot -> {
                    disPlaySearchedMusic(iGetMusicByTagView,musicRoot,isLoadMore);
                },this::loadError);


    }



    private void disPlaySearchedMusic(IGetMusicByTagView iGetMusicByTagView, MusicRoot musicRoot, boolean isLoadMore) {
        iGetMusicByTagView.getMusicByTagSuccess(musicRoot,isLoadMore);
        //if(filmLive==null){
        //   iGetFilmLiveView.getDataFail();
        // }else {
        //   iGetFilmLiveView.getFilmLiveSuccess(filmLive);
        Log.e("test", musicRoot.toString());
        // }
    }

    private void loadError( Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }


    /**
     * @param
     */
    public void getMusicById(IGetMusicById iGetMusicById, String id ){

        doubanApi.getMusicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(music -> {
                    displayMusic(iGetMusicById,music);
                },this::loadError);


    }

    private void displayMusic(IGetMusicById iGetMusicById , Musics musics) {
        iGetMusicById.getMusicSucess(musics);

        Log.e("test", musics.toString());

    }




}
