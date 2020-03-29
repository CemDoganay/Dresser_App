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

    /**
     * This method is used to generate a menu before the user gets a generated outfit by the app.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_ideas,
                container,
                false);

        gallery = (Button) rootView.findViewById(R.id.button_dressMe);
        gallery.setOnClickListener(new View.OnClickListener() {
            /**
             * When the Dress Me button is pressed this button will call the Gallery app and go into
             * the directory of the phone. This is where the user will choose one of the pieces of
             * clothing they would like to match.
             * @param images
             */
            @Override
            public void onClick(View images) {
                File dir = new File(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

        next = (Button) rootView.findViewById(R.id.button_next_ideas);
        next.setOnClickListener(new View.OnClickListener() {
            /**
             * Currently you need to push the next button right after you choose a piece of clothing
             * from the Dress Me button. This will then go to another page to show which clothing
             * matches the selected clothing.
             * @param combination
             */
            @Override
            public void onClick(View combination) {
                Intent intent = new Intent(getActivity(), GeneratedCombination.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    /**
     * This method is to open new activities. This would be used to call the GeneratedCombination
     * class to show the user what matches the current selected clothing.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case REQUEST_GALLERY:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData(); //This includes the path and the name of the picture
                    //photoFromGallery.setImageURI(selectedImage); //Can remove this
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
