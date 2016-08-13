package com.example.tanishka.gcpsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Tanishka on 10-07-2016.
 */
public class GCMPushRecieverService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
    sendNotification(data.getString("message"));
    }
 public void sendNotification(String message){
     Intent i=new Intent(this,MainActivity.class);
     i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     PendingIntent pi=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
     Uri ringtone= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
     Notification notification= new NotificationCompat.Builder(this)
             .setSmallIcon(R.mipmap.ic_launcher)
             .setContentText("New Message")
             .setContentText(message)
             .setSound(ringtone)
             .setAutoCancel(true)
             .setContentIntent(pi).build();
   NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
     notificationManagerCompat.notify(0,notification);
 }
}
