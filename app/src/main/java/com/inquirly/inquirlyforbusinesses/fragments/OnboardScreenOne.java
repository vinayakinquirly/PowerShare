package com.inquirly.inquirlyforbusinesses.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;
import com.inquirly.inquirlyforbusinesses.R;

public class OnboardScreenOne extends Fragment {

    private View view;
    private RelativeLayout base1;
    private LinearLayout base2;
    private TextView main_head,sub_head,message;
    private ImageView image;
    private static final String TAG ="OnboardScreenOne";

    public OnboardScreenOne(){
        Log.i(TAG,"ONE FRAGMENT");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_onboard_screen_one, container, false);
        Log.i(TAG,"main fragment entered");
        main_head = (TextView)view.findViewById(R.id.txt_walkthrough_title);
        image = (ImageView)view.findViewById(R.id.img_walkthrough);
        sub_head = (TextView)view.findViewById(R.id.txt_walkthrough_description);
        message = (TextView)view.findViewById(R.id.txt_walkthrough_dexcription_sub);
        base1 = (RelativeLayout)view.findViewById(R.id.relative_walkthrough_base_one);
        base2 = (LinearLayout)view.findViewById(R.id.relative_walkthrough_base_two);

        Bundle bundle = new Bundle();

        if(bundle!=null){
            int screenNum = bundle.getInt("screenNum");
            Log.i(TAG,"check num received--" + screenNum);
            switch(screenNum){
                case 1:
                    main_head.setText(getResources().getString(R.string.label_walkthrough_title_one));
                    image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_one));
                    sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_one));
                    message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_one));
                    break;

                case 2:
                    main_head.setText(getResources().getString(R.string.label_walkthrough_title_two));
                    image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_two));
                    sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_two));
                    message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_two));
                    break;

                case 3:
                    main_head.setText(getResources().getString(R.string.label_walkthrough_title_three));
                    image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_three));
                    sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_three));
                    message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_three));
                    break;
            }
        }
        return view;
    }
}
