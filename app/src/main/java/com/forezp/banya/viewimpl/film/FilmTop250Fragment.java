package com.forezp.banya.viewimpl.film;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forezp.banya.Presenter.DoubanFilmPresenter;
import com.forezp.banya.R;
import com.forezp.banya.adapter.Top250FilmAdapter;
import com.forezp.banya.base.BaseFragment;
import com.forezp.banya.bean.top250.Root;
import com.forezp.banya.utils.ThemeUtils;
import com.forezp.banya.viewinterface.film.IgetTop250View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by forezp on 16/9/22.
 */
public class FilmTop250Fragment extends BaseFragment implements IgetTop250View,SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;

    private DoubanFilmPresenter doubanFilmPresenter;
    private Top250FilmAdapter adapter;

    private LinearLayoutManager mLayoutManager;


    private int lastVisibleItem;
    private int pageCount;
    private final int PAGE_SIZE=10;
    private Root mRoot;

    public static FilmTop250Fragment newInstance() {

        Bundle args = new Bundle();
        FilmTop250Fragment fragment = new FilmTop250Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fim_live, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doubanFilmPresenter=new DoubanFilmPresenter(getActivity());

        doubanFilmPresenter.getTop250(this,pageCount*PAGE_SIZE,PAGE_SIZE,false);
        mLayoutManager = new LinearLayoutManager(getContext());

        recyclerview.setLayoutManager(mLayoutManager);
        scrollRecycleView();
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);
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
    public void getTop250Success(Root root, boolean isLoadMore) {
        if(isLoadMore){
            mRoot.getSubjects().addAll(root.getSubjects());
            adapter.notifyDataSetChanged();
        }else {
            mRoot=root;
            adapter = new Top250FilmAdapter(getActivity(), mRoot);
            recyclerview.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getDataFail() {

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
                                pageCount++;
                                doubanFilmPresenter.getTop250(FilmTop250Fragment.this,pageCount*PAGE_SIZE,PAGE_SIZE,true);
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
    public void onRefresh() {
        doubanFilmPresenter.getTop250(this,pageCount*PAGE_SIZE,PAGE_SIZE,false);
        idSwiperefreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idSwiperefreshlayout != null) {
                    idSwiperefreshlayout.setRefreshing(false);
                }
            }
        },2000);

    }
}
