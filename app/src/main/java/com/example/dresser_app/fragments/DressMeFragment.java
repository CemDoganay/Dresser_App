package com.example.dresser_app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dresser_app.GeneratedCombination;
import com.example.dresser_app.R;

import java.io.File;

public class DressMeFragment extends Fragment{

    public static final String CURRENT_PHOTO_NAME = "com.example.android.Ideas.extra.CURRENT_PHOTO_NAME";
    public static final String CURRENT_PHOTO_URI = "com.example.android.Ideas.extra.CURRENT_PHOTO_URI";

    private Button next, gallery;
    private String nameOfPic, picURI;
    private ImageView photoFromGallery;
    static final int REQUEST_GALLERY = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_ideas,
                container,
                false);

        gallery = (Button) rootView.findViewById(R.id.button_dressMe);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = new File(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

        next = (Button) rootView.findViewById(R.id.button_next_ideas);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GeneratedCombination.class);
                startActivity(intent);
            }
        });



        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case REQUEST_GALLERY:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData(); //This includes the path and the name of the picture
                    photoFromGallery.setImageURI(selectedImage); //Can remove this
                    picURI = selectedImage.toString();

                    String temp = selectedImage.getPath();
                    nameOfPic = temp.substring(temp.indexOf("P") + 9);

                    Intent intent = new Intent(getActivity(), GeneratedCombination.class);
                    intent.putExtra(CURRENT_PHOTO_NAME, nameOfPic);
                    intent.putExtra(CURRENT_PHOTO_URI, picURI);
                    startActivity(intent);
                    break;
            }
    }
}
