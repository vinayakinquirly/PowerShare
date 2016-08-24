package com.inquirly.powershare.activity;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import org.json.JSONObject;
import java.util.ArrayList;
import android.content.Intent;
import org.json.JSONException;
import android.widget.EditText;
import android.widget.TextView;
import com.inquirly.powershare.R;
import com.android.volley.VolleyError;
import android.support.v7.app.AppCompatActivity;
import com.inquirly.powershare.others.MakeApiRequest;
import com.inquirly.powershare.others.IRequestCallBack;
import com.inquirly.powershare.application.MainApplicationClass;

public class LoginActivity extends AppCompatActivity {

    private EditText email,pass;
    private JSONObject jsonObject = new JSONObject();
    private static final String TAG = "LoginActivity";
    private ArrayList<String> details = new ArrayList<>();
    private MainApplicationClass appInstance = MainApplicationClass.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.edit_login_email);
        pass = (EditText) findViewById(R.id.edit_login_password);
        TextView txt_login_btn = (TextView) findViewById(R.id.txt_login_btn);
        details.add(email.getText().toString());
        details.add(pass.getText().toString());

        assert txt_login_btn != null;

        txt_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    jsonObject.put("emailID",email.getText().toString());
                    jsonObject.put("password",pass.getText().toString());
                    jsonObject.put("deviceID",appInstance.deviceId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                makeLoginRequest(jsonObject);
            }
        });
    }

    public void makeLoginRequest(JSONObject jsonObject){
        Log.i(TAG,"enterd in makeLoginRequest");
        MakeApiRequest.loginRequest(
                jsonObject,
                new IRequestCallBack() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Log.i(TAG,"response Received---" + response.toString());
                        try {
                            int resCode =response.getJSONObject("status").getInt("resCode");
                            if(resCode==3001){
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(VolleyError error) {
                        Log.i(TAG,"error--" + error);
                    }
                }
        );
    }
}
