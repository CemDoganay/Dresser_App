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
import android.widget.Toast;

import com.example.dresser_app.DatabaseHelper;
import com.example.dresser_app.R;
import com.example.dresser_app.TakePicture;

import java.io.File;



public class WardrobeFragment extends Fragment {

    private static final int DELETE_PICTURE = 1;
    private Button mOpen, mAdd, mRemove;
    private DatabaseHelper theDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_wardrobe, container,false);

        theDB = new DatabaseHelper(getActivity());

        mOpen = rootView.findViewById(R.id.open_button);
        mAdd = rootView.findViewById(R.id.add_button);
        mRemove = rootView.findViewById(R.id.remove_button);

        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivity(intent);
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            /**
             * This activity is used to add a new clothing to your wardrobe. It opens the Camera app
             * to take a picture of your clothing. Once taken you can either take another picture or
             * use the current photo. Then you will be able to edit the information about the
             * picture.
             * @param add
             */
            @Override
            public void onClick(View add) {
                Intent intent = new Intent(getActivity(), TakePicture.class);
                startActivity(intent);
            }
        });

        mRemove.setOnClickListener(new View.OnClickListener() {
            /**
             * This activity is used to remove clothing from your wardrobe. It opens the Gallery app
             * to go through your photos. Once an image is selected it will be removed from the
             * database and phones storage.
             * @param remove
             */
            @Override
            public void onClick(View remove) {
                File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivityForResult(intent, DELETE_PICTURE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case DELETE_PICTURE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData(); //This includes the path and the name of the picture
                    String temp = selectedImage.getPath();
                    String nameOfPic = temp.substring(temp.indexOf("P") + 9);

                    int result = theDB.deleteData(nameOfPic);

                    if(result > 0) {
                        //Acquiring file to delete
                        File delFile = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), nameOfPic);

                        if (delFile.exists()) {
                            delFile.delete();
                            Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else {
                            //Toast.makeText(getActivity(), "Data Deleted from database but not from gallery", Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), delFile.getPath(), Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(getActivity(), "Data not Deleted", Toast.LENGTH_LONG).show();
                    }


                    break;
            }
    }
}
