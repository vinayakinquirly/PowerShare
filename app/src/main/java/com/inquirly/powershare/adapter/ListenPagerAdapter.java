package com.inquirly.powershare.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.inquirly.powershare.fragment.ListenFeedbacksFragment;
import com.inquirly.powershare.fragment.ListenHomeFragment;
import com.inquirly.powershare.fragment.ListenMentionsFragment;
import com.inquirly.powershare.fragment.PowerApprovalFragment;
import com.inquirly.powershare.fragment.PowerHistoryFragment;
import com.inquirly.powershare.fragment.PowerPostFragment;
import com.inquirly.powershare.fragment.PowerQueueFragment;

import java.util.ArrayList;

/**
 * Created by Vinayak on 8/29/2016.
 */
public class ListenPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> titles = new ArrayList<>();

    public ListenPagerAdapter(FragmentManager fm,ArrayList<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ListenHomeFragment();
                break;
            case 1:
                fragment = new ListenMentionsFragment();
                break;
            case 2:
                fragment = new ListenFeedbacksFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
