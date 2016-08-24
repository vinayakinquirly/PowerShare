package com.inquirly.powershare.fragment;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.inquirly.powershare.R;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;
import com.inquirly.powershare.activity.LoginActivity;
import com.inquirly.powershare.activity.OnboardActivity;

public class OnboardFragment extends Fragment {

    private int position;
    private View view;
    private RelativeLayout base1;
    private LinearLayout base2;
    private TextView main_head,sub_head,message,prog_1,prog_2,prog_3,powerBtn;
    private ImageView image;
    private static final String TAG ="OnboardScreenOne";

    // newInstance constructor for creating fragment with arguments
    public static OnboardFragment newInstance(int page) {
        OnboardFragment fragmentFirst = new OnboardFragment();
        Bundle args = new Bundle();
        args.putInt("position", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position", 0);
        Log.i(TAG,"onCreate position--" +position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_onboard, container, false);
        main_head = (TextView)view.findViewById(R.id.txt_walkthrough_title);
        image = (ImageView)view.findViewById(R.id.img_walkthrough);
        sub_head = (TextView)view.findViewById(R.id.txt_walkthrough_description);
        message = (TextView)view.findViewById(R.id.txt_walkthrough_dexcription_sub);
        base1 = (RelativeLayout)view.findViewById(R.id.relative_walkthrough_base_one);
        base2 = (LinearLayout)view.findViewById(R.id.relative_walkthrough_base_two);
        powerBtn = (TextView)view.findViewById(R.id.txt_walkthrough_go_btn);

        OnboardActivity.colorPill(position);

        switch(position){
            case 0:
                main_head.setText(getResources().getString(R.string.label_walkthrough_title_one));
                image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_one));
                sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_one));
                message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_one));
                break;

            case 1:
                main_head.setText(getResources().getString(R.string.label_walkthrough_title_two));
                image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_two));
                sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_two));
                message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_two));
                break;

            case 2:
                main_head.setText(getResources().getString(R.string.label_walkthrough_title_three));
                image.setImageDrawable(getResources().getDrawable(R.drawable.img_walkthrough_three));
                sub_head.setText(getResources().getString(R.string.label_walkthrough_desc_three));
                message.setText(getResources().getString(R.string.label_walkthrough_sub_desc_three));
                break;

            case 3:
                base1.setVisibility(View.GONE);
                base2.setVisibility(View.VISIBLE);
                powerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                break;
        }
        return view;
    }
}
