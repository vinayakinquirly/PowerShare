package com.inquirly.inquirlyforbusinesses.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import com.inquirly.inquirlyforbusinesses.R;

public class OnboardScreenTwo extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_onboard_screen_two, container, false);

        return view;
    }

}
