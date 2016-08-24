package com.inquirly.powershare.others;

import org.json.JSONObject;
import com.android.volley.VolleyError;

/**
 * Created by Vinayak on 8/23/2016.
 */
public interface IRequestCallBack {

    void onSuccess(JSONObject response);
    void onError(VolleyError error);
}
