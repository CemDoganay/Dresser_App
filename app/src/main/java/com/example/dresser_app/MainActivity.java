package com.example.dresser_app;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.dresser_app.fragments.DressMeFragment;
import com.example.dresser_app.fragments.WardrobeFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(new DressMeFragment());
        list.add(new WardrobeFragment());

        mPager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        mPager.setAdapter(pagerAdapter);

    }
}
