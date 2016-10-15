package com.forezp.banya.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.forezp.banya.viewimpl.book.BookReadingFragment;

/**
 * Created by forezp on 16/9/26.
 */
public class BookViewpagerAdapter  extends FragmentStatePagerAdapter {

    private String[] mTitles;
    //private List<Fragment> mFragments;

    public BookViewpagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
       // this.mFragments = mFragments;
    }

    @Override public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override public Fragment getItem(int position) {

        return BookReadingFragment.newInstance(position,mTitles[position]);
    }

    @Override public int getCount() {
        return mTitles.length;
    }
}
