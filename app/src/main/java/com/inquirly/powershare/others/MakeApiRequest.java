package com.inquirly.powershare.others;

import java.util.Map;
import android.util.Log;
import java.util.HashMap;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.inquirly.powershare.constants.Constants;
import com.android.volley.toolbox.JsonObjectRequest;
import com.inquirly.powershare.application.MainApplicationClass;

/**
 * Created by Vinayak on 8/23/2016.
 */
public class MakeApiRequest {

    private static final String TAG = "MakeApiRequest";

    public static void loginRequest(JSONObject details, final IRequestCallBack callBack) {
        Log.i(TAG, "check json Object---" + details + "\n" + Constants.API_LOGIN);
        JsonObjectRequest jsonObjectRequest = null;
        try {
            jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    Constants.API_LOGIN,
                    details,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callBack.onSuccess(response);
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callBack.onError(error);
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
                    headers.put(Constants.ACCEPT, Constants.APPLICATION_JSON);
                    headers.put(Constants.APP_KEY, Constants.APP_KEY_VALUE);
                    return headers;
                }
            };
        }catch(Exception e){
            Log.i(TAG,"check exception---" + e.getMessage());
        }
        MainApplicationClass.getInstance().addToRequestQueue(jsonObjectRequest);
        if (jsonObjectRequest != null) {
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(180000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }
}
