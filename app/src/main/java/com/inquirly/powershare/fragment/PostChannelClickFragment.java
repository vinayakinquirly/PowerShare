package com.inquirly.powershare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inquirly.powershare.R;

public class PostChannelClickFragment extends Fragment implements View.OnClickListener{

    private View view;
    private String bundleRec;
    private ImageView acct_image,acct_selected,beacon;
    private static final String TAG ="PostChannelClick";
    private ImageView fb,twitter,linkdn,qr,message,email;
    private TextView main_heading,btn_done,select_all,deselect_all;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_channel_click, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            bundleRec = bundle.getString("channelType");
            Log.i(TAG, "check bundle value---" + bundleRec);
        }else{
            Log.i(TAG,"check bundle value---" + null);
        }
        Log.i(TAG,"check bundle value---" + bundleRec);

        acct_image = (ImageView)view.findViewById(R.id.account_image);
        acct_selected = (ImageView)view.findViewById(R.id.acct_select);
        main_heading = (TextView)view.findViewById(R.id.head_channel_name);
        btn_done = (TextView)view.findViewById(R.id.btn_done);
        select_all = (TextView)view.findViewById(R.id.select_all);
        deselect_all = (TextView)view.findViewById(R.id.deselct_all);
        fb = (ImageView)view.findViewById(R.id.clicked_fb);
        twitter = (ImageView)view.findViewById(R.id.clicked_twitter);
        email = (ImageView)view.findViewById(R.id.clicked_email);
        linkdn = (ImageView)view.findViewById(R.id.clicked_linkdn);
        beacon = (ImageView)view.findViewById(R.id.clicked_beacon);
        message = (ImageView)view.findViewById(R.id.clicked_message);
        qr = (ImageView)view.findViewById(R.id.clicked_qr);

        makeImageVisible(bundleRec);

        select_all.setOnClickListener(this);
        deselect_all.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        return view;
    }

    private void makeImageVisible(String channel_name) {
        switch (channel_name){

            case "fb":
                fb.setVisibility(View.VISIBLE);
                break;

            case "twitter":
                twitter.setVisibility(View.VISIBLE);
                break;

            case "linkdn":
                linkdn.setVisibility(View.VISIBLE);
                break;

            case "email":
                email.setVisibility(View.VISIBLE);
                break;

            case "message":
                message.setVisibility(View.VISIBLE);
                break;

            case "beacon":
                beacon.setVisibility(View.VISIBLE);
                break;

            case "qr":
                qr.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.select_all:
                acct_selected.setVisibility(View.VISIBLE);
                break;

            case R.id.deselct_all:
                acct_selected.setVisibility(View.GONE);
                break;

            case R.id.btn_done:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
