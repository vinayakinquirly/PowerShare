package com.inquirly.powershare.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inquirly.powershare.R;
import com.inquirly.powershare.activity.MainActivity;
import com.inquirly.powershare.adapter.PowerPagerAdapter;

import java.util.ArrayList;

public class MainPowerShareFragment extends Fragment implements
        ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener{

    View view;
    private ViewPager powerPager;
    public static TabLayout mTabLayout;
    private PowerPagerAdapter powerPagerAdapter;
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_power_share, container, false);
        ((MainActivity)getActivity()).configureToolBar("POWERSHARE","basic",false);

        titles.add("POST");
        titles.add("APPROVAL");
        titles.add("QUEUE");
        titles.add("HISTORY");

        powerPager = (ViewPager)view.findViewById(R.id.powerPager);
        powerPagerAdapter = new PowerPagerAdapter(getActivity().getSupportFragmentManager(), titles);
        powerPager.setAdapter(powerPagerAdapter);

        setTabPagerStrip();
        return view;
    }

    private void setTabPagerStrip() {
        mTabLayout = (TabLayout)view.findViewById(R.id.powerTabLayout);
        powerPager.setOnPageChangeListener(this);
        mTabLayout.setTabsFromPagerAdapter(powerPagerAdapter);
        mTabLayout.setupWithViewPager(powerPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setOnTabSelectedListener(this);
        setFontToTabs(mTabLayout);
    }

    private void setFontToTabs(TabLayout tabLayout) {
        Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                    ((TextView) tabViewChild).setTextSize(14);
                }
            }
        }
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        powerPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
