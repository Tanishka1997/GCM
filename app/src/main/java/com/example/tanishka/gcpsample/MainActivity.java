package com.example.tanishka.gcpsample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;




import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (RegisterPreferences.getStoredMobile(this)==null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Home fragment = (Home) fragmentManager.findFragmentById(R.id.frame);
            if (fragment == null) {
                fragment = Home.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
            }

        } else {

            Intent intent = new Intent(this, TabViewActivity.class);
            startActivity(intent);
            this.finish();

        }
    }

}
