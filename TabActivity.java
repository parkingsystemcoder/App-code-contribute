package com.example.tanchian.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tanchian.myapplication.tabs_refragments.CheckSlot;
import com.example.tanchian.myapplication.tabs_refragments.GetCode;
import com.example.tanchian.myapplication.tabs_refragments.History;
import com.example.tanchian.myapplication.tabs_refragments.Profile;

public class TabActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {android.R.drawable.ic_menu_search, android.R.drawable.ic_menu_myplaces, android.R.drawable.ic_menu_recent_history, android.R.drawable.ic_menu_save};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        //TODO: write an adapter for it
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(CheckSlot.newInstance("this data is for check slot"), "CheckSlot");
        adapter.addFragment(GetCode.newInstance("this data is for check slot"), "GetCode");
        adapter.addFragment(History.newInstance("this data is for check slot"), "History");
        adapter.addFragment(Profile.newInstance("this data is for check slot"), "BankAcc");
        viewPager.setAdapter(adapter);
    }
}
