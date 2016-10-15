package com.forezp.banya.viewimpl.book;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forezp.banya.Presenter.DoubanBookPresenter;
import com.forezp.banya.R;
import com.forezp.banya.adapter.BookReadingAdapter;
import com.forezp.banya.api.BookApiUtils;
import com.forezp.banya.base.BaseFragment;
import com.forezp.banya.bean.book.BookRoot;
import com.forezp.banya.bean.book.Books;
import com.forezp.banya.utils.ThemeUtils;
import com.forezp.banya.viewinterface.book.IGetBookView;
import com.forezp.banya.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;

/**
 * Created by forezp on 16/9/26.
 */
public class BookReadingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,IGetBookView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;
    private int position;
    private BookReadingAdapter adapter;
    private int lastVisibleItem;
    private LinearLayoutManager  mLayoutManager;
    private DoubanBookPresenter doubanBookPresenter;
    private List<String> listTag;
    private List<Books> booksList;
    public static BookReadingFragment newInstance(int position, String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("position", position);
        BookReadingFragment fragment = new BookReadingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_reading, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args= getArguments();
        if(args!=null){
            position=args.getInt("position");
        }
        booksList=new ArrayList<>();
        String[] strTags= BookApiUtils.getApiTag(position);
        listTag= Arrays.asList(strTags);
        scrollRecycleView();
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);
        doubanBookPresenter=new DoubanBookPresenter(getActivity());
        String tag=BookApiUtils.getRandomTAG(listTag);


        doubanBookPresenter.searchBookByTag(this,tag,false);
        adapter=new BookReadingAdapter(getActivity());
        mLayoutManager= new GridLayoutManager(getActivity(), 3);
        recyclerview.setLayoutManager(mLayoutManager);
        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),0);
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }

    @Override
    public void onRefresh() {
        listTag=Arrays.asList(BookApiUtils.getApiTag(position));
        String tag=BookApiUtils.getRandomTAG(listTag);
        doubanBookPresenter.searchBookByTag(BookReadingFragment.this,tag,false);
        idSwiperefreshlayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (idSwiperefreshlayout != null) {
                    idSwiperefreshlayout.setRefreshing(false);


                }
            }
        },2000);
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     */
    public void scrollRecycleView() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                    if (mLayoutManager.getItemCount() == 1) {
                        if(adapter!=null) {
                            adapter.updateLoadStatus(adapter.LOAD_NONE);
                        }
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()) {
                        if(adapter!=null) {
                            adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                            // isLoadMore = true;
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }
                        //new Handler().postDelayed(() -> getBeforeNews(time), 1000);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String tag=BookApiUtils.getRandomTAG(listTag);

                                doubanBookPresenter.searchBookByTag(BookReadingFragment.this,tag,true);
                            }
                        },1000) ;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void getBookSuccess(BookRoot bookRoot,boolean isLoadMore) {

        if(isLoadMore){
            booksList.addAll(bookRoot.getBooks());
        }else{
            booksList.clear();
            booksList.addAll(bookRoot.getBooks());
        }

        adapter.setList(booksList);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void getBookFail() {

    }
}
