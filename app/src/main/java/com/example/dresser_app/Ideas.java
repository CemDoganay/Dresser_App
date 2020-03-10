package com.example.dresser_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class Ideas extends AppCompatActivity{

    public static final String CURRENT_PHOTO_NAME = "com.example.android.Ideas.extra.CURRENT_PHOTO_NAME";
    public static final String CURRENT_PHOTO_URI = "com.example.android.Ideas.extra.CURRENT_PHOTO_URI";

    private Button next, gallery;
    private ImageView photoFromGallery;
    private String nameOfPic, picURI;

    static final int REQUEST_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        next = findViewById(R.id.button_next_ideas);
        gallery = findViewById(R.id.button_dressMe);
        photoFromGallery = findViewById(R.id.gallery_photo);


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = new File(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ideas.this, GeneratedCombination.class);
                intent.putExtra(CURRENT_PHOTO_NAME, nameOfPic);
                intent.putExtra(CURRENT_PHOTO_URI, picURI);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case REQUEST_GALLERY:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData(); //This includes the path and the name of the picture
                    photoFromGallery.setImageURI(selectedImage);
                    picURI = selectedImage.toString();

                    String temp = selectedImage.getPath();
                    nameOfPic = temp.substring(temp.indexOf("P") + 9);
                    break;
            }
    }
}