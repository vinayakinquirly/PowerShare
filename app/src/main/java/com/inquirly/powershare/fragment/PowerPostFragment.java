package com.inquirly.powershare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.inquirly.powershare.R;
import com.inquirly.powershare.activity.InpireMeActivity;

import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.widget.TableRow;

public class PowerPostFragment extends Fragment implements View.OnClickListener{

    View view;
    Intent intent;
    TableRow table_inpireMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_power_post, container, false);
        table_inpireMe = (TableRow)view.findViewById(R.id.table_inpireMe);

        table_inpireMe.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.table_inpireMe:
                intent = new Intent(getActivity(), InpireMeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
