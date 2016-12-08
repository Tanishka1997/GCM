package com.example.tanishka.gcpsample;

import android.content.Intent;
import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tanishka on 10-07-2016.
 */
public class GCMTokenRefresh extends FirebaseInstanceIdService {

public static String t;
    public static String getToken(){

        String refreshedToken=t;
        return refreshedToken;
    }
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            t=refreshedToken;
        Log.d("Refresh", "Refreshed token: " + refreshedToken);
       // Intent intent = new Intent(this,GCMRegisterationIntentService.class);
        //startService(intent);

      //  sendRegistrationToServer(refreshedToken);
    }

}