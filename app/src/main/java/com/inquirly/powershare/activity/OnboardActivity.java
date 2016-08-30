package com.inquirly.powershare.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inquirly.powershare.R;
import android.view.WindowManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.inquirly.powershare.adapter.OnboardPagerAdapter;
import com.inquirly.powershare.constants.Constants;

import java.util.ArrayList;

public class OnboardActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static int screenNum;
    public static Activity activity;
    public static  TextView pill_1,pill_2,pill_3;
    private static final String TAG = "OnBoardingActivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_ONE_TIME_BOARDING,Context.MODE_PRIVATE);
        boolean isFirstDownload = sharedPreferences.getBoolean(Constants.SHARED_IS_FIRST_DOWNLOAD,false);
        Log.i(TAG,"check download count--" + isFirstDownload);
        if(isFirstDownload){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        ViewPager onBoardPager = (ViewPager) findViewById(R.id.onBoardPager);
        activity = OnboardActivity.this;
        OnboardPagerAdapter pagerAdapter = new OnboardPagerAdapter(getSupportFragmentManager());
        if (onBoardPager != null) {
            onBoardPager.setAdapter(pagerAdapter);
        }
    }

    public static void colorPill(int position){
        pill_1 = (TextView)activity.findViewById(R.id.prog_1);
        pill_2 = (TextView)activity.findViewById(R.id.prog_2);
        pill_3 = (TextView)activity.findViewById(R.id.prog_3);
        LinearLayout onboardLinearMain = (LinearLayout)activity.findViewById(R.id.onboardLinearMain);
        Log.i(TAG,"check position received---" + position);
        screenNum= position-1;
        switch(screenNum){
            case 0:
                onboardLinearMain.setBackgroundColor(activity.getResources().getColor(R.color.background_action_bar));
                pill_1.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                pill_2.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                pill_3.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                break;

            case 1:
                onboardLinearMain.setBackgroundColor(activity.getResources().getColor(R.color.background_action_bar));
                pill_1.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                pill_2.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                pill_3.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                break;

            case 2:
                onboardLinearMain.setBackgroundColor(activity.getResources().getColor(R.color.background_action_bar));
                pill_1.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                pill_2.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_white_outline));
                pill_3.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                break;

            case 3:
                onboardLinearMain.setBackgroundColor(activity.getResources().getColor(R.color.white));
                pill_1.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                pill_2.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                pill_3.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.background_button_white));
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG,"check position-scroll-" +position);
    }

    @Override
    public void onPageSelected(int position) {
        screenNum=  position;
        Log.i(TAG,"check position--" + screenNum);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
