package com.inquirly.powershare.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableRow;
import android.widget.TextView;

import com.inquirly.powershare.R;
import com.inquirly.powershare.adapter.PowerPagerAdapter;
import com.inquirly.powershare.constants.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,TabLayout.OnTabSelectedListener,
        NavigationView.OnNavigationItemSelectedListener{

    private Intent intent;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager powerPager;
    private PagerTitleStrip powerTitleStrip;
    private PowerPagerAdapter powerPagerAdapter;
    private static final String TAG = "MainActivity";
    private ArrayList<String> titles = new ArrayList<>();
    private TableRow powerRow,listenRow,pipeRow,logoutRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.powerToolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titles.add("POST");
        titles.add("APPROVAL");
        titles.add("QUEUE");
        titles.add("HISTORY");

        powerPager = (ViewPager)findViewById(R.id.powerPager);
        powerPagerAdapter = new PowerPagerAdapter(getSupportFragmentManager(),titles);
        powerPager.setAdapter(powerPagerAdapter);
        setTabPagerStrip();
        setNavDrawerLayout();
    }

    private void setTabPagerStrip() {
        mTabLayout = (TabLayout)findViewById(R.id.powerTabLayout);
        powerPager.setOnPageChangeListener(this);
        mTabLayout.setTabsFromPagerAdapter(powerPagerAdapter);
        mTabLayout.setupWithViewPager(powerPager);
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
                }
            }
        }
    }

    public void setNavDrawerLayout(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.drawer_close,R.string.drawer_open);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_alert) {
            return true;
        }
        if (id == R.id.action_chat) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.powerRow:
                intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                    //powerRow.setBackgroundColor(getResources().getColor(R.color.background_tab_selected_indicator));
                break;

            case R.id.listenRow:
                listenRow.setBackgroundColor(getResources().getColor(R.color.background_tab_selected_indicator));
                break;

            case R.id.pipeRow:
                pipeRow.setBackgroundColor(getResources().getColor(R.color.background_tab_selected_indicator));
                break;

            case R.id.logoutRow:
                logoutRow.setBackgroundColor(getResources().getColor(R.color.background_tab_selected_indicator));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
