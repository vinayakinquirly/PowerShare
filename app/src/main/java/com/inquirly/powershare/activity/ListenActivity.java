package com.inquirly.powershare.activity;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.view.MenuItem;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Typeface;
import com.inquirly.powershare.R;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import com.inquirly.powershare.adapter.ListenPagerAdapter;

public class ListenActivity extends AppCompatActivity implements
        TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener {

    private Intent intent;
    private TabLayout mTabLayout;
    private ViewPager listenPager;
    private ListenPagerAdapter powerPagerAdapter;
    private static final String TAG = "MainActivity";
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.listenToolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titles.add("HOME");
        titles.add("MENTIONS");
        titles.add("FEEDBACK");

        listenPager = (ViewPager)findViewById(R.id.listenPager);
        powerPagerAdapter = new ListenPagerAdapter(getSupportFragmentManager(),titles);
        listenPager.setAdapter(powerPagerAdapter);
        setTabPagerStrip();
    }

    private void setTabPagerStrip() {
        mTabLayout = (TabLayout)findViewById(R.id.listenTabLayout);
        listenPager.setOnPageChangeListener(this);
        mTabLayout.setTabsFromPagerAdapter(powerPagerAdapter);
        mTabLayout.setupWithViewPager(listenPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setOnTabSelectedListener(this);
        setFontToTabs(mTabLayout);
    }

    private void setFontToTabs(TabLayout tabLayout) {
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        listenPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
}
