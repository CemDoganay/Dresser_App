package com.example.dresser_app;

import android.content.ActivityNotFoundException;
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
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TakePicture extends AppCompatActivity {

    public static final String CURRENT_PHOTO_PATH = "com.example.android.TakePicture.extra.CURRENT_PHOTO_PATH";
    public static final String CURRENT_PHOTO_URI = "com.example.android.TakePicture.extra.CURRENT_PHOTO_URI";

    private Button loadImage;
    private Button takeImage;
    private ImageView photoView;
    private TextView msg;

    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;
    Uri currentPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepicture);
        loadImage = findViewById(R.id.img_ld_btn);
        takeImage = findViewById(R.id.take_pic_btn);
        photoView = findViewById(R.id.new_photo);
        msg = findViewById(R.id.text_message);


        currentPhotoUri = dispatchTakePictureIntent();

        File chkFile = new File(currentPhotoPath);
        if ((Integer.parseInt(String.valueOf(chkFile.length()/1024))) == 0 && chkFile.exists())
            chkFile.delete();

        //Load the taken image
        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loadImage.getText().equals("Load Image")) {
                    File imgFile = new File(currentPhotoPath);
                    if (imgFile.exists()) {
                        //msg.setText("Image found at:\n" + currentPhotoPath);
                        setPic();
                        loadImage.setText("Next");
                    }

                    else {
                        msg.setText("Image could not be located.");
                    }
                }

                else if (loadImage.getText() == "Next") {
                    //Crop intent
                    if (currentPhotoUri != null) {
                        Intent cropIntent = new Intent(TakePicture.this, Tagging.class);
                        cropIntent.putExtra(CURRENT_PHOTO_PATH, currentPhotoPath);
                        cropIntent.putExtra(CURRENT_PHOTO_URI, currentPhotoUri.toString());
                        startActivity(cropIntent);
                    }
                }
            }
        });

        //Take picture again
        takeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File delFile = new File(currentPhotoPath);

                    Toast.makeText(TakePicture.this, delFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                    if (delFile.exists())
                        delFile.delete();

                    recreate();
                }
                catch (Exception ex) {
                    Log.e("TakeAgain", "onClick(View view) - intentRecreate error: " + ex);
                }
            }
        });
    }

    private File createImageFile() throws IOException {
        //Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private Uri dispatchTakePictureIntent() {
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

                return photoURI;
            }
        }

        return null;
    }

    private void setPic() {
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        photoView.setImageBitmap(bitmap);
    }
}
