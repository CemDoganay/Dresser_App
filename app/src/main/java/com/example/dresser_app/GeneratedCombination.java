package com.example.dresser_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GeneratedCombination extends AppCompatActivity {
    private Button niceButton, tryAgain;
    private ImageView chosenPhoto, suggestedPhoto;
    private Cursor picCursor;
    private String picName, clothColor, clothType;

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

        clothType = picCursor.getString(picCursor.getColumnIndex("TYPE"));

        //Extra textview to debug cursor values
        TextView tv = findViewById(R.id.URI_VIEW);
        //tv.setText();

        if (clothType.equals("TOP")){
            chosenPhoto = findViewById(R.id.topImage);
            suggestedPhoto = findViewById(R.id.botImage);
        }

        else if (clothType.equals("BOTTOM")){
            chosenPhoto = findViewById(R.id.botImage);
            suggestedPhoto = findViewById(R.id.topImage);
        }

       else
           tv.setText("Nothing found");

        Uri thePic = Uri.parse(getIntent().getStringExtra(Ideas.CURRENT_PHOTO_URI));
        chosenPhoto.setImageURI(thePic);

        picCursor.moveToLast();
        clothColor = picCursor.getString(picCursor.getColumnIndex("COLOR"));

        //Run through algorithm to find matching pictures
        tv.setText(match(clothColor)[0]);

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


    String[] match(String color){
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
}
