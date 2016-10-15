package com.forezp.banya.viewimpl.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.forezp.banya.Presenter.DoubanBookPresenter;
import com.forezp.banya.R;
import com.forezp.banya.base.BaseActivity;
import com.forezp.banya.bean.book.Books;
import com.forezp.banya.utils.DisplayImgUtis;
import com.forezp.banya.viewimpl.webview.WebviewActivity;
import com.forezp.banya.viewinterface.book.IGetBookDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by forezp on 16/10/1.
 */
public class BookDetailActivity extends BaseActivity implements IGetBookDetailView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.iv_music)
    ImageView ivMusic;
    @BindView(R.id.tv_book_name)
    TextView tvBookName;
    @BindView(R.id.tv_book_grade)
    TextView tvBookGrade;
    @BindView(R.id.tv_book_art)
    TextView tvBookArt;
    @BindView(R.id.tv_book_publishtime)
    TextView tvBookPublishtime;
    @BindView(R.id.tv_book_publish_address)
    TextView tvBookPublishAddress;
    @BindView(R.id.tv_book_grade_num)
    TextView tvBookGradeNum;
    @BindView(R.id.tv_want_read)
    TextView tvWantRead;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_author_description)
    TextView tvAuthorDescription;
    @BindView(R.id.rl_author)
    RelativeLayout rlAuthor;
    @BindView(R.id.tv_chapters)
    TextView tvChapters;
    private Books books;
    private DoubanBookPresenter doubanBookPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        initData();

    }
    private void  initData(){

        doubanBookPresenter=new DoubanBookPresenter(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });
        toolbar.setTitle("－。－看书");

        Intent intent=getIntent();
        if(intent!=null){
            String id= intent.getStringExtra("id");
            if(!TextUtils.isEmpty(id)){
                doubanBookPresenter.getBookById(this,id);
            }
        }
    }
    private void initViews(){

        if(books==null){
            return;
        }
        if(books.getImages()!=null) {
            DisplayImgUtis.getInstance().display(this, books.getImages().getLarge(), ivMusic);
        }
        if(!TextUtils.isEmpty(books.getTitle())){
            tvBookName.setText(books.getTitle());
        }
        if(books.getAuthor()!=null&&books.getAuthor().size()>0){
            tvBookArt.setText(books.getAuthor().get(0));
        }
        if(!TextUtils.isEmpty(books.getPublisher())){
            tvBookPublishAddress.setText(books.getPublisher());
        }
        if(!TextUtils.isEmpty(books.getPubdate())){
            tvBookPublishtime.setText("出版时间"+books.getPubdate());
        }
        if(!TextUtils.isEmpty(books.getPublisher())){
            tvBookPublishAddress.setText(books.getPublisher());
        }

        if(!TextUtils.isEmpty(books.getSummary())){
            tvDescription.setText(books.getSummary());
        }
        if(!TextUtils.isEmpty(books.getAuthor_intro())){
            tvAuthorDescription.setText(books.getAuthor_intro());
        }
        if(!TextUtils.isEmpty(books.getCatalog())){
            tvChapters.setText(books.getCatalog());
        }
        if(!TextUtils.isEmpty(books.getRating().getAverage())){
            tvBookGrade.setText(books.getRating().getAverage()+"分");
        }
        if(!TextUtils.isEmpty(""+books.getRating().getNumRaters())){
            tvBookGradeNum.setText(books.getRating().getNumRaters()+"人评");
        }

    }



    @Override
    public String setActName() {
        return null;
    }

    @OnClick({R.id.tv_want_read, R.id.tv_more_info, R.id.rl_author})
    public void onClick(View view) {
        Intent  intent;
        switch (view.getId()) {
            case R.id.tv_want_read:
            case R.id.tv_more_info:

                intent=new Intent(this, WebviewActivity.class);
                intent.putExtra(WebviewActivity.EXTRA_URL,books.getAlt());
                startThActivityByIntent(intent);
                break;
            case R.id.rl_author:
                intent=new Intent(this, WebviewActivity.class);
                intent.putExtra(WebviewActivity.EXTRA_URL,books.getAlt());
                startThActivityByIntent(intent);
                break;
        }
    }

    @Override
    public void getBookSuccess(Books books) {
        if(books!=null){
            this.books=books;
            initViews();
        }
    }

    @Override
    public void getBookFail() {

    }
}
