package com.example.dresser_app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dresser_app.fragments.PageFragment1;
import com.example.dresser_app.fragments.PageFragment2;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private Button mAdd,mCreate,mIdeas;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mAdd = findViewById(R.id.Add_button);
       mCreate = findViewById(R.id.Create_button);
       mIdeas = findViewById(R.id.ideas_button);
       mydb = new DatabaseHelper(this);

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

           }
       });

       mIdeas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, Ideas.class);
               startActivity(intent);


           }
       });

       List<Fragment> list = new ArrayList<>();
       list.add(new PageFragment1());
       list.add(new PageFragment2());

       pager = findViewById(R.id.pager);
       pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

       pager.setAdapter(pagerAdapter);
    }
}
