package com.forezp.banya.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.forezp.banya.viewimpl.music.MusicContentFragment;

/**
 * Created by forezp on 16/10/1.
 */
public class MusicViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    //private List<Fragment> mFragments;

    public MusicViewpagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
        // this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override public Fragment getItem(int position) {

        return MusicContentFragment.newInstance(position,mTitles[position]);
    }

    @Override public int getCount() {
        return mTitles.length;
    }
}
