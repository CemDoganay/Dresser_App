package com.example.dresser_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dresser_app.TakePicture;

import java.io.File;

import static com.example.dresser_app.Ideas.REQUEST_GALLERY;

public class Wardrobe extends AppCompatActivity {

    private Button mOpen, mAdd, mRemove;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

        mydb = new DatabaseHelper(this);

        mOpen = findViewById(R.id.open_button);
        mAdd = findViewById(R.id.add_button);
        mRemove = findViewById(R.id.remove_button);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Wardrobe.this, TakePicture.class);
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
                            Toast.makeText(Wardrobe.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                            else Toast.makeText(Wardrobe.this, "Data Deleted from database but not from gallery", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(Wardrobe.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }


                    break;
            }
    }
}
