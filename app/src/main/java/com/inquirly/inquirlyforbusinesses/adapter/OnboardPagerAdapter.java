package com.inquirly.inquirlyforbusinesses.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Vinayak on 8/22/2016.
 */
public class OnboardPagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public OnboardPagerAdapter(Context context, FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super( fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }
}
