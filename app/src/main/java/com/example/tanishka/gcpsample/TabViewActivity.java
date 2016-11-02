package com.example.tanishka.gcpsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TabViewActivity extends AppCompatActivity {
  ActionBar actionBar ;
  ViewPager viewPager;
  FragmentPage fragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        assert actionBar != null;
        actionBar=getSupportActionBar();
        viewPager=(ViewPager) findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });

        fragmentPagerAdapter=new FragmentPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

            actionBar.addTab(
                    actionBar.newTab().setText("Chat")
                            .setTabListener((ActionBar.TabListener) tabListener));
        actionBar.addTab(
                actionBar.newTab().setText("Contacts")
                        .setTabListener((ActionBar.TabListener) tabListener));
        actionBar.addTab(
                actionBar.newTab().setText("Groups")
                        .setTabListener((ActionBar.TabListener) tabListener));


    }



}
