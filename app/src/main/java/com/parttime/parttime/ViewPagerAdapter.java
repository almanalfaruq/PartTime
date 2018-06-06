package com.parttime.parttime;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by danasw on 19/04/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] pageTitle = {"Kategori", "Hot"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new KategoriFragment();
        } else {
            return new HotFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return pageTitle[0];
        } else {
            return pageTitle[1];
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}