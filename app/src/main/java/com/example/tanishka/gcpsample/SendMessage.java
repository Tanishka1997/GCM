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

    public SendMessage(String mobile) throws IOException{
    connect("Hello Tanishka",mobile);
    }

    public void connect(String message,String mobile) throws IOException {

        String gcm_url ="http://192.168.1.5/gcm_sample/connect.php";
        URL url = new URL(gcm_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            Log.w("Send","abcd");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream outputStream=connection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data=URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(message,"UTF-8")+"&"+
            URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            outputStream.close();
            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line="";

            while ((line = bufferedReader.readLine())!=null);
            response+=line;
            bufferedReader.close();
            inputStream.close();
            Log.w("SendMessage",response);

        }finally {
            connection.disconnect();
            Log.w("SendMessage","12345");
        }
    }
}
