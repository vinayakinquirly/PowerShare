package com.inquirly.inquirlyforbusinesses.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.inquirly.inquirlyforbusinesses.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginACtivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG,"entered");
        Intent intent = new Intent(this,OnBoardingActivity.class);
        startActivity(intent);
    }
}
