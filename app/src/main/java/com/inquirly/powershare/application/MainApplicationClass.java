package com.inquirly.powershare.application;

import android.util.Log;
import android.app.Application;
import android.provider.Settings;
import com.android.volley.Request;
import com.facebook.stetho.Stetho;
import com.android.volley.RequestQueue;
import android.content.SharedPreferences;
import com.android.volley.toolbox.Volley;

/**
 * Created by Vinayak on 8/23/2016.
 */
public class MainApplicationClass extends Application {

    public static String deviceId;
    private static MainApplicationClass mInstance;
    public static RequestQueue mRequestQueue;
    private SharedPreferences sharedPreferences;
    private static final String TAG ="MainApplicationClass";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("opinify", "called");
        mInstance = this;
        Stetho.initializeWithDefaults(MainApplicationClass.this);
    //  sharedPreferences = getSharedPreferences(Constants.SHARED_USER_DETAILS,MODE_PRIVATE);
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        deviceId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public MainApplicationClass(){}

    public static synchronized MainApplicationClass getInstance() {
        return mInstance;
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
