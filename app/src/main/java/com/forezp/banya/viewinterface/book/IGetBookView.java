package com.forezp.banya.viewinterface.book;

import com.forezp.banya.base.IBaseView;
import com.forezp.banya.bean.book.BookRoot;

/**
 * Created by forezp on 16/9/29.
 */
public interface IGetBookView extends IBaseView {
    void getBookSuccess(BookRoot bookRoot,boolean isLoadMore);
    void getBookFail();
}
