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

    private static final int REQUEST_GALLERY = 1 ;
    private Button mOpen, mAdd, mRemove;
    private DatabaseHelper mydb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_wardrobe,
                container,
                false);

        mydb = new DatabaseHelper(getActivity());
        mAdd = (Button) rootView.findViewById(R.id.add_button);
        mRemove = (Button) rootView.findViewById(R.id.remove_button);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakePicture.class);
                startActivity(intent);
            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = new File(Environment.DIRECTORY_PICTURES);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.parse(dir.getPath()), "image/*");

                startActivityForResult(intent, REQUEST_GALLERY);

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
                    //photoFromGallery.setImageURI(selectedImage); //Can remove this

                    String picURI = selectedImage.toString();
                    String temp = selectedImage.getPath();
                    String nameOfPic = temp.substring(temp.indexOf("P") + 9);

                    int result = mydb.deleteData(nameOfPic);

                    if(result > 0) {
                        File delFile = new File(picURI);
                        if (delFile.exists()) {
                            delFile.delete();
                            Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(getActivity(), "Data Deleted from database but not from gallery", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getActivity(), "Data not Deleted", Toast.LENGTH_LONG).show();
                    }


                    break;
            }
    }
}
