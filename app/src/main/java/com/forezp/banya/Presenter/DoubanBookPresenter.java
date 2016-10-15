package com.forezp.banya.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.forezp.banya.base.BasePresenter;
import com.forezp.banya.bean.book.BookRoot;
import com.forezp.banya.bean.book.Books;
import com.forezp.banya.viewinterface.book.IGetBookDetailView;
import com.forezp.banya.viewinterface.book.IGetBookView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by forezp on 16/9/26.
 */
public class DoubanBookPresenter extends BasePresenter {
    public DoubanBookPresenter(Context context) {
        super(context);
    }

    /**
     * @param
     */
    public void searchBookByTag(IGetBookView iGetBookView,String TAG ,boolean isLoadMore){

        doubanApi.searchBookByTag(TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookRoot -> {
                    disPlaySearchedBook(iGetBookView,bookRoot,isLoadMore);
                },this::loadError);


    }


    /**
     * @param
     */
    public void getBookById(IGetBookDetailView iGetBookView, String id ){

        doubanApi.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookRoot -> {
                    disPlayBookDetail(iGetBookView,bookRoot);
                },this::loadError);


    }


    private void disPlayBookDetail(IGetBookDetailView iGetBookView, Books books) {
        iGetBookView.getBookSuccess(books);
        //if(filmLive==null){
        //   iGetFilmLiveView.getDataFail();
        // }else {
        //   iGetFilmLiveView.getFilmLiveSuccess(filmLive);
        Log.e("test", books.toString());
        // }
    }




    private void disPlaySearchedBook(IGetBookView iGetBookView,BookRoot bookRoot,boolean isLoadMore) {
        iGetBookView.getBookSuccess(bookRoot,isLoadMore);
        //if(filmLive==null){
         //   iGetFilmLiveView.getDataFail();
       // }else {
         //   iGetFilmLiveView.getFilmLiveSuccess(filmLive);
            Log.e("test", bookRoot.toString());
       // }
    }

    private void loadError( Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }

}
