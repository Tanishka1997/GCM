package com.example.tanishka.gcpsample;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tanishka on 10-07-2016.
 */
public class GCMRegisterationIntentService extends IntentService {
    public static final String REGISTERATION_SUCCESS="RegisterationSuccess";
    public static final String REGISTERATION_ERROR="RegisterationError";
    String name;
    public GCMRegisterationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        name=intent.getExtras().getString("message");
        registerToken();

    }
   public void registerToken(){
       Intent intent;
       String token;

       try {
           InstanceID instanceID=InstanceID.getInstance(this);
           token=instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
           Log.w("GCMRegisteration","token:"+token);
           String reg="http://172.32.9.60/gcm_sample/register.php";
           URL url=new URL(reg);
           HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
           try {
               urlConnection.setRequestMethod("POST");
               urlConnection.setDoOutput(true);
               OutputStream outputStream=urlConnection.getOutputStream();
               BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
               String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                       URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");
               bufferedWriter.write(data);
               bufferedWriter.flush();
               outputStream.close();
           }finally {
               urlConnection.disconnect();
           }

           intent = new Intent(REGISTERATION_SUCCESS);
           intent.putExtra("token",token);
       }catch (Exception e){
        Log.w("GCMRegisteration","Error");
           intent=new Intent(REGISTERATION_ERROR);
       }
      sendBroadcast(intent);
   }
}
