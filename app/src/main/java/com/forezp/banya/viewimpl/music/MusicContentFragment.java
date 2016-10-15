package com.forezp.banya.viewimpl.music;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forezp.banya.Presenter.DoubanMusicPresenter;
import com.forezp.banya.R;
import com.forezp.banya.adapter.MusicAdapter;
import com.forezp.banya.api.BookApiUtils;
import com.forezp.banya.api.MusicApiUtils;
import com.forezp.banya.base.BaseFragment;
import com.forezp.banya.bean.music.MusicRoot;
import com.forezp.banya.bean.music.Musics;
import com.forezp.banya.utils.ThemeUtils;
import com.forezp.banya.viewinterface.music.IGetMusicByTagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by forezp on 16/10/1.
 */
public class MusicContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,IGetMusicByTagView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;
    private int position;
    private MusicAdapter adapter;
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    private DoubanMusicPresenter doubanMusicPresenter;
    private List<String> listTag;
    private List<Musics> musicList;
    public static MusicContentFragment newInstance(int position, String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("position", position);
        MusicContentFragment fragment = new MusicContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_content, container, false);
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
        musicList=new ArrayList<>();
        String[] strTags= MusicApiUtils.getApiTag(position);
        listTag= Arrays.asList(strTags);
        scrollRecycleView();
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);
        doubanMusicPresenter=new DoubanMusicPresenter(getActivity());
        String tag=BookApiUtils.getRandomTAG(listTag);
        doubanMusicPresenter.searchMusicByTag(this,tag,false);
        adapter=new MusicAdapter(getActivity());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(adapter);
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
                                doubanMusicPresenter.searchMusicByTag(MusicContentFragment.this,tag,true);
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
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }

    @Override
    public void getMusicByTagSuccess(MusicRoot musicRoot, boolean isLoadMore) {
        if(isLoadMore){
            musicList.addAll(musicRoot.getMusics());

        }else {
            musicList.clear();
            musicList.addAll(musicRoot.getMusics());
        }
        adapter.setList(musicList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRefresh() {
        String tag=BookApiUtils.getRandomTAG(listTag);
        doubanMusicPresenter.searchMusicByTag(this,tag,false);
        idSwiperefreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idSwiperefreshlayout != null) {
                    idSwiperefreshlayout.setRefreshing(false);
                }
            }
        },1500);
    }
}
