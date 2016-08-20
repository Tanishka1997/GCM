package com.example.tanishka.gcpsample;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Tanishka on 19-08-2016.
 */
public class RegisterPreferences {
    private static final String MOBILE="MOBILE";

    public  static String getStoredMobile(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(MOBILE,null);
    }

    public static void setMobile(Context context,String mobile){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(MOBILE,mobile).apply();
    }
}
