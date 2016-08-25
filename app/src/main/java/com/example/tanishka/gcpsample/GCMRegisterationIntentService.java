package com.example.tanishka.gcpsample;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

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
public class GCMRegisterationIntentService extends IntentService {
    public static final String REGISTERATION_SUCCESS="RegisterationSuccess";
    public static final String REGISTERATION_ERROR="RegisterationError";
    String mobile;
    public GCMRegisterationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mobile=intent.getSerializableExtra("mobile").toString();
        registerToken();

    }
   public void registerToken(){
       Intent intent;
       String token;

       try {
           InstanceID instanceID=InstanceID.getInstance(this);
           token=instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
           Log.w("GCMRegisteration","token:"+token);
           String reg="http://gcpdata.net16.net/register.php";
           URL url=new URL(reg);
           HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
           try {
               urlConnection.setRequestMethod("POST");
               urlConnection.setDoOutput(true);
               OutputStream outputStream=urlConnection.getOutputStream();
               BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
               String data= URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"+
                       URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");
               bufferedWriter.write(data);
               bufferedWriter.flush();
               outputStream.close();
               InputStream inputStream = urlConnection.getInputStream();

               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
               String response = "";
               String line="";

               while ((line = bufferedReader.readLine())!=null);
               response+=line;
               bufferedReader.close();
               inputStream.close();

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
