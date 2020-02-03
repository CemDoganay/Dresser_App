package com.example.dresser_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TakePicture extends AppCompatActivity {

    private Button loadImage;
    private ImageView photoView;
    private TextView msg;

    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepicture);
        loadImage = findViewById(R.id.img_ld_btn);
        photoView = findViewById(R.id.new_photo);
        msg = findViewById(R.id.text_message);

        dispatchTakePictureIntent();

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File imgFile = new File(currentPhotoPath);
                if (imgFile.exists()) {
                    //msg.setText("Image found at:\n" + currentPhotoPath);
                    setPic();
                }
                else {
                    msg.setText("Image could not be located.");
                }
            }
        });
    }

    private File createImageFile() throws IOException{
        //Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }
            catch (IOException ex) {
                //Error occurred while creating the File
                Log.e("TakePicture", "dispatchTakePictureIntent() - photoFile error: " + ex);
            }

            //Continue only if the file was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        photoView.setImageBitmap(bitmap);
    }
}
