package com.forezp.banya.viewimpl.film;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forezp.banya.R;
import com.forezp.banya.adapter.MyViewpagerAdapter;
import com.forezp.banya.base.BaseFragment;
import com.forezp.banya.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by forezp on 16/8/13.
 */
public class FilmFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    // TabLayout中的tab标题
    private String[] mTitles;
    // 填充到ViewPager中的Fragment
    private List<Fragment> mFragments;
    private FilmLiveFragment filmLiveFragment;
    private FilmTop250Fragment filmTop250Fragment;
    //private FilmGodFragment filmGodFragment;
    private MyViewpagerAdapter mViewPagerAdapter;

    public static FilmFragment newInstance() {

        Bundle args = new Bundle();

        FilmFragment fragment = new FilmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean hasMultiFragment() {
        return true;
    }

    @Override
    protected String setFragmentName() {
        return "首页－Fragment";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }
    private void initViews(){

        mTitles = getResources().getStringArray(R.array.tab_film);
        //初始化填充到ViewPager中的Fragment集合
        mFragments = new ArrayList<>();
        filmLiveFragment=FilmLiveFragment.newInstance();
        filmTop250Fragment=FilmTop250Fragment.newInstance() ;
       // filmGodFragment=FilmGodFragment.newInstance();
        mFragments.add(filmLiveFragment);
       // mFragments.add(filmGodFragment);
        mFragments.add(filmTop250Fragment);


        // 初始化ViewPager的适配器，并设置给它
        mViewPagerAdapter = new MyViewpagerAdapter(getChildFragmentManager(), mTitles, mFragments);
        viewpager.setAdapter(mViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        viewpager.setOffscreenPageLimit(3);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        viewpager.addOnPageChangeListener(this);

        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tablayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tablayout.setupWithViewPager(viewpager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        tablayout.setTabsFromPagerAdapter(mViewPagerAdapter);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
