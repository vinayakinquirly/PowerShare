package com.inquirly.powershare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.inquirly.powershare.R;
import com.inquirly.powershare.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVideoFragment extends Fragment {

    private View view;
    private ImageView youtubeThumb;
    private static final String TAG = "AddVideoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_video, container, false);
        ((MainActivity)getActivity()).configureToolBar("Add Video","basic",false);
        youtubeThumb = (ImageView)view.findViewById(R.id.ytd_thumb);
        ((EditText)this.view.findViewById(R.id.video_link)).addTextChangedListener(new CheckEnteredText());
        return view;
    }

    class CheckEnteredText implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG, "onTextChanged()> text: " + s);
            if (AddVideoFragment.this.isLinkValid(s.toString())) {
                String videoID = AddVideoFragment.this.extractYTVideoId(s.toString());
                Log.d(AddVideoFragment.TAG, "onTextChanged()> videoID: " + videoID);
                if (videoID != null) {
                    Picasso.with(AddVideoFragment.this.getActivity()).load(AddVideoFragment.
                            this.getYoutubeThumbnailUrl(videoID)).into(youtubeThumb);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }

    private boolean isLinkValid(String link) {
        EditText videoLink = (EditText) this.view.findViewById(R.id.video_link);
        Log.d(TAG, " isLinkValid()> link size after split: " + link.split(" ").length);
        if (link.contains("youtube.") && link.split(" ").length <= 1) {
            return true;
        }
        videoLink.setError("Invalid Youtube Url,please check & try again");
        videoLink.requestFocus();
        return false;
    }

    private String extractYTVideoId(String youTubeUrl) {
        Matcher matcher = Pattern.compile("(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*").matcher(youTubeUrl);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private String getYoutubeThumbnailUrl(String videoID) {
        return "http://img.youtube.com/vi/" + videoID + "/0.jpg";
    }
}