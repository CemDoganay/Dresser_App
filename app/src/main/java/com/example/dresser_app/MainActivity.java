package com.example.dresser_app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

      /*
       mAdd = findViewById(R.id.Add_button);
       mCreate = findViewById(R.id.Create_button);
       mIdeas = findViewById(R.id.ideas_button);


       mAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, TakePicture.class);
               startActivity(intent);
           }
       });

       mCreate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           */
        List<Fragment> list = new ArrayList<>();
        list.add(new DressMeFragment());
        list.add(new WardrobeFragment());

        mPager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

      /*
       mIdeas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, Ideas.class);
               startActivity(intent);
           }
       });
       */
        mPager.setAdapter(pagerAdapter);
    }
}
