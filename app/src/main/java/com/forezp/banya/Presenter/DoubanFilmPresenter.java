package com.forezp.banya.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.forezp.banya.base.BasePresenter;
import com.forezp.banya.bean.filmdetail.FilmDetail;
import com.forezp.banya.bean.filmlive.FilmLive;
import com.forezp.banya.bean.filmusbox.FilmUsBox;
import com.forezp.banya.bean.top250.Root;
import com.forezp.banya.viewinterface.film.IGetFilmDetail;
import com.forezp.banya.viewinterface.film.IGetFilmLiveView;
import com.forezp.banya.viewinterface.film.IGetUsBoxView;
import com.forezp.banya.viewinterface.film.IgetTop250View;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class DoubanFilmPresenter extends BasePresenter{

    public DoubanFilmPresenter(Context context) {
        super(context);

    }

    /**
     * 获取正在热映
     */
    public void getFilmLive(IGetFilmLiveView iGetFilmLiveView){

        doubanApi.getLiveFilm()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmLive -> {
                    disPlayFilmLiveList(iGetFilmLiveView,filmLive, mContext);
                },this::loadError);


    }



    private void disPlayFilmLiveList(IGetFilmLiveView iGetFilmLiveView,FilmLive filmLive, Context context) {
        //Toast.makeText(context,filmLive.toString(),Toast.LENGTH_SHORT).show();
        if(filmLive==null){
            iGetFilmLiveView.getDataFail();
        }else {
            iGetFilmLiveView.getFilmLiveSuccess(filmLive);
            Log.e("test", filmLive.toString());
        }
    }

    /**
     * 获取
     */
    public void getFilmDetail(IGetFilmDetail iGetFilmDetail,String id){
        doubanApi.getFilmDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmDetail -> {
                    disPlayFilmDetail(iGetFilmDetail,filmDetail, mContext);
                },this::loadError);


    }

    private void disPlayFilmDetail(IGetFilmDetail iGetFilmDetail, FilmDetail filmDetail, Context context) {
        //Toast.makeText(context,filmLive.toString(),Toast.LENGTH_SHORT).show();
        if(filmDetail==null){
            iGetFilmDetail.getDataFail();
        }else {
            iGetFilmDetail.getFilmDetailSuccess(filmDetail);
            Log.e("test", filmDetail.toString());
        }
    }

    /**
     * 获取top250
     * @param start
     * @param count
     */

    public void getTop250(IgetTop250View igetTop250View,int start, int count,boolean isLoadMore){
        doubanApi.getTop250(start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(root -> {
                    disPlayDoubanTop250List(igetTop250View,root, isLoadMore);
                },this::loadError);

    }

    private void disPlayDoubanTop250List(IgetTop250View igetTop250View,Root root, boolean isLoadMore) {
        //Toast.makeText(context,root.toString(),Toast.LENGTH_SHORT).show();
        Log.e("test",root.toString());
        igetTop250View.getTop250Success(root, isLoadMore);
    }
    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }


    /**
     * 北美榜单
     * @param iGetUsBoxView
     */

    public void getUsBox(IGetUsBoxView iGetUsBoxView){
        doubanApi.getUsBox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(root -> {
                    displayUsBox(iGetUsBoxView,root);

                },this::loadError);

    }

    private void displayUsBox(IGetUsBoxView iGetUsBoxView,FilmUsBox filmUsBox) {
        if(filmUsBox!=null) {
            iGetUsBoxView.getFilmUsBoxSuccess(filmUsBox);
        }else {
            iGetUsBoxView.getDataFail();
        }
    }


}
