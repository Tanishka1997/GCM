package com.example.tanishka.gcpsample;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tanishka on 13-07-2016.
 */
public class SendMessage {

    public SendMessage(String token) throws IOException{
    connect("Hello Tanishka",token);
    }

    public void connect(String message,String token) throws IOException {

        String gcm_url ="http://172.32.1.161/gcm_sample/connect.php";
        URL url = new URL(gcm_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            Log.w("Send","abcd");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream=connection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data=URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(message,"UTF-8")+"&"+
            URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            outputStream.close();
            Log.w("SendMessage","1234");

        }finally {
            connection.disconnect();
            Log.w("SendMessage","12345");
        }
    }
}
