package com.inquirly.powershare.adapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.inquirly.powershare.fragment.OnboardFragment;

public class OnboardPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "OnboardPagerAdapter";
    public OnboardPagerAdapter(FragmentManager fm) {
        super( fm);
    }

    @Override
    public Fragment getItem(int position) {
//        if(position>=2) {
//            position = position-1;
//        }
        Log.i(TAG,"check position---" + position);
        return OnboardFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}