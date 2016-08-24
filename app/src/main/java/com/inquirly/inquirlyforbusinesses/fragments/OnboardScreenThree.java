package com.inquirly.inquirlyforbusinesses.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inquirly.inquirlyforbusinesses.R;

public class OnboardScreenThree extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_onboard_screen_three, container, false);

        return view;
    }
}
