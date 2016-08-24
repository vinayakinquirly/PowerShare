package com.inquirly.powershare.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.inquirly.powershare.fragment.PowerApprovalFragment;
import com.inquirly.powershare.fragment.PowerHistoryFragment;
import com.inquirly.powershare.fragment.PowerPostFragment;
import com.inquirly.powershare.fragment.PowerQueueFragment;

import java.util.ArrayList;

/**
 * Created by Vinayak on 8/24/2016.
 */
public class PowerPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> titles = new ArrayList<>();

    public PowerPagerAdapter(FragmentManager fm,ArrayList<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PowerPostFragment();
                break;
            case 1:
                fragment = new PowerApprovalFragment();
                break;
            case 2:
                fragment = new PowerQueueFragment();
                break;
            case 3:
                fragment = new PowerHistoryFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}