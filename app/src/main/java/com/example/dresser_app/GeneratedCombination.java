package com.example.dresser_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GeneratedCombination extends AppCompatActivity {
    private Button niceButton, tryAgain;
    private ImageView chosenPhoto, suggestedPhoto;
    private Cursor picCursor, suggestCursor;
    private String picName, m_picName, clothColor, clothType, m_clothType;
    private String[] m_clothColor;

    private static final String[][] matching = {
            {"RED","BLUE","WHITE","BLACK","GREY"},
            {"RED","BLUE","GREEN","WHITE","BLACK","GREY","BROWN"},
            {"BLUE","GREEN","WHITE","BLACK","GREY","BROWN"},
            {"BLUE","ORANGE","WHITE","BLACK"},
            {"PURPLE","BLACK","WHITE","GREY"},
            {"RED","BLUE","GREEN","WHITE","BLACK","GREY","BROWN"},
            {"RED","BLUE","GREEN","WHITE","BLACK","GREY","BROWN"},
            {"BLUE","WHITE","BLACK","GREY"}
    };

    private DatabaseHelper theDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_combination);

        niceButton = findViewById(R.id.button_nice);
        tryAgain = findViewById(R.id.button_tryAgain);
        theDB = new DatabaseHelper(this);

        picName = getIntent().getStringExtra(Ideas.CURRENT_PHOTO_NAME);

        picCursor = theDB.getPicInfo(picName);
        picCursor.moveToFirst();

        clothColor = picCursor.getString(picCursor.getColumnIndex("COLOR"));
        clothType = picCursor.getString(picCursor.getColumnIndex("TYPE"));

        //Extra textview to debug cursor values
        TextView tv = findViewById(R.id.URI_VIEW);


        if (clothType.equals("TOP")){
            chosenPhoto = findViewById(R.id.topImage);
            suggestedPhoto = findViewById(R.id.botImage);
            m_clothType = "BOTTOM";
        }

        else if (clothType.equals("BOTTOM")){
            chosenPhoto = findViewById(R.id.botImage);
            suggestedPhoto = findViewById(R.id.topImage);
            m_clothType = "TOP";
        }

       else
           tv.setText("Cloth type error");


        //Run through algorithm to find matching pictures
        m_clothColor = matchColor(clothColor);

        suggestCursor = theDB.getMatchingCloth(m_clothColor[0], m_clothType);
        suggestCursor.moveToFirst();

        //Need a try catch block
        m_picName = suggestCursor.getString(suggestCursor.getColumnIndex("ADDRESS"));

        //Setting the photo that the user had selected into the imageview
        Uri chosenURI = Uri.parse(getIntent().getStringExtra(Ideas.CURRENT_PHOTO_URI));
        chosenPhoto.setImageURI(chosenURI);



        //Get matching picture's name to find the URI of it
        Uri matchingURI = Uri.parse("/com.android.externalstorage.documents/document/Android%2Fdata%2Fcom.example.dresser_app%2Ffiles%2FPictures%2F" + m_picName);
        File f = new File (getExternalFilesDir(Environment.DIRECTORY_PICTURES), m_picName);

        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            suggestedPhoto.setImageBitmap(b);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //suggestedPhoto.setImageURI(matchingURI);
        //tv.setText(matchingURI.toString());


        niceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(GeneratedCombination.this, something.class);
                //startActivity(intent);
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Run the algorithm again to find the next matching pair
            }
        });
    }


    String[] matchColor(String color){
        int colorInArr;

        switch (color.toUpperCase()) {
            case "RED":
                colorInArr = 0;
                break;

            case "BLUE":
                colorInArr = 1;
                break;

            case "GREEN":
                colorInArr = 2;
                break;

            case "ORANGE":
                colorInArr = 3;
                break;

            case "PURPLE":
                colorInArr = 4;
                break;

            case "WHITE":
                colorInArr = 5;
                break;

            case "BLACK":
                colorInArr = 6;
                break;

            case "GREY":
                colorInArr = 7;
                break;

            default:
                colorInArr = -1;
                break;
        }

        return matching[colorInArr];
    }

    void getMatchingPic(String matchColor, String otherType) {
        //suggestCursor = theDB.getMatchingCloth(matchColor, otherType);
    }
}
