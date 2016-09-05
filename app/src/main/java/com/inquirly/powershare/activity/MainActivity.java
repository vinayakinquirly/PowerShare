package com.inquirly.powershare.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.view.MenuItem;
import android.content.Intent;
import com.inquirly.powershare.R;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import com.inquirly.powershare.constants.Constants;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import com.inquirly.powershare.fragment.MainPowerShareFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private DrawerLayout drawer;
    private Boolean exit = false;
    public static Toolbar toolbar;
    private static Activity activity;
    private static ActionBar actionBar;
    public static FrameLayout frameLayout;
    public static LinearLayout linearLayoutMain;
    private static final String TAG = "MainActivity";
    private static FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.powerToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        configureToolBar("PowerShare","basic",false);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_close, R.string.drawer_open);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment = null;
        MainPowerShareFragment mainPowerShareFragment = (MainPowerShareFragment)
                (fragment = new MainPowerShareFragment());

        if(fragment!= null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, fragment).commit();
        }

        linearLayoutMain = (LinearLayout)findViewById(R.id.linear_mainMain);
        frameLayout = (FrameLayout)findViewById(R.id.frame_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void configureToolBar(String title,String mode,boolean backNeeded) {
        Log.i(TAG,"item received--" + title + "--" + mode + "--" + backNeeded);
        actionBar.setTitle(title);

        if(backNeeded){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_back_btn);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateTo(new MainPowerShareFragment(),null,null);
                }
            });
            Log.i(TAG,"seems no back needed");
        }

        if(!mode.equals(Constants.APP_BAR_MODE_BASIC)){
            Log.i(TAG,"do nothing, basic mode selected");
        }else {
            Log.i(TAG,"do nothing, basic mode selected");
        }
    }

    public void navigateTo(Fragment fragment, String backStackLabel, Bundle bundle) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment previousInstance = getSupportFragmentManager().findFragmentByTag(backStackLabel);

        if (previousInstance != null)
            fragmentTransaction.remove(previousInstance);
        fragmentTransaction.replace(R.id.frame_main, fragment);

        if(bundle != null){
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
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

        }else if (id == R.id.action_chat) {
            return true;

        }else if(id==android.R.id.home){
            onBackPressed();
            navigateTo(new MainPowerShareFragment(),"MainPowerShareFragment",null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.powerRow:
                intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.listenRow:
                intent = new Intent(MainActivity.this,ListenActivity.class);
                startActivity(intent);
                break;

            case R.id.pipeRow:
                break;

            case R.id.logoutRow:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }
}
