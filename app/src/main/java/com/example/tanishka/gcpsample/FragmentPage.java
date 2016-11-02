package com.example.tanishka.gcpsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tanishka on 2/11/16.
 */
public class FragmentPage extends FragmentPagerAdapter {
    public FragmentPage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SendFragment();
            case 1:
                return new Contacts();
            case 2:
                return new Groups();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
