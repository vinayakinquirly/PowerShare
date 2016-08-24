package com.inquirly.inquirlyforbusinesses.activity;

import android.util.Log;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.inquirly.inquirlyforbusinesses.R;
import android.support.v7.app.AppCompatActivity;
import com.inquirly.inquirlyforbusinesses.fragments.OnboardScreenOne;
import com.inquirly.inquirlyforbusinesses.adapter.OnboardPagerAdapter;

public class OnBoardingActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager onBoardPager;
    private OnboardPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private static final String TAG = "OnBoardingActivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        onBoardPager = (ViewPager)findViewById(R.id.onBoardPager);

        Log.i(TAG,"DiscoverFragmnetNew Called");
        titles.add("POWERSHARE YOUR POSTS");
        titles.add("LISTEN TO YOUR AUDIENCE");
        titles.add("REPLY TO YOUR FEEDBACKS");
        titles.add("GET STARTED");

        for(int i=0;i<titles.size();i++){
            OnboardScreenOne onboardScreenOne = new OnboardScreenOne();
            Bundle bundle = new Bundle();
            bundle.putInt("screenNum",i);
            onboardScreenOne.setArguments(bundle);
            fragments.add(onboardScreenOne);
        }

        pagerAdapter = new OnboardPagerAdapter(this,getSupportFragmentManager(),fragments,titles);
        onBoardPager = (ViewPager)findViewById(R.id.onBoardPager);
        onBoardPager.setAdapter(pagerAdapter);
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
