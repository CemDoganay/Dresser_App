package com.example.dresser_app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class CropPic extends AppCompatActivity {

    private Button next;
    private ImageView photoView;

    static final int PIC_CROP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_picture);
        next = findViewById(R.id.button2);
        photoView = findViewById(R.id.crop_photo);

        Intent takePictureIntent = getIntent();
        Uri cropUri = Uri.parse(takePictureIntent.getStringExtra(TakePicture.CURRENT_PHOTO_URI));

        performCrop(cropUri);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CropPic.this, Tagging.class);
                intent.putExtra(TakePicture.CURRENT_PHOTO_PATH, getIntent().getStringExtra(TakePicture.CURRENT_PHOTO_PATH));
                intent.putExtra(TakePicture.CURRENT_PHOTO_URI, getIntent().getStringExtra(TakePicture.CURRENT_PHOTO_URI));
                startActivity(intent);
            }
        });
    }


    //https://stackoverflow.com/questions/15228812/crop-image-in-android
    //This cropping method does not work; check out the "Android-Image-Cropper" library to see if that works
    private void performCrop (Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");

            //Indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");

            //Set crop properties here
            cropIntent.putExtra("crop", true);

            //Indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);

            //Indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);

            //Retrieve data on return
            cropIntent.putExtra("return-data", true);

            //Start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }

        //Respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            String errorMessage = "Whoops - your device doesn't support the crop sction!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(TakePicture.CURRENT_PHOTO_PATH));
        photoView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIC_CROP) {
            if (data != null) {
                //Get the returned data
                Bundle extras = data.getExtras();

                //Get the cropped bitmap
                Bitmap selectedBitmap = extras.getParcelable("data");

                photoView.setImageBitmap(selectedBitmap);
            }
        }
    }
}
