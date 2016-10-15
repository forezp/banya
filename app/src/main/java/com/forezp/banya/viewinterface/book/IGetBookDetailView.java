package com.forezp.banya.viewinterface.book;

import com.forezp.banya.bean.book.Books;

/**
 * Created by forezp on 16/10/2.
 */
public interface IGetBookDetailView {

    void getBookSuccess(Books books);
    void getBookFail();
}
