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

    void match(int topColour, String bottomColour){
        String[][] topBottomMatch = {
                {"red","blue","white","black","grey"},
                {"red","blue","green","white","black","grey","brown"},
                {"blue","green","white","black","grey","brown"},
                {"blue","orange","white","black"},
                {"purple","black","white","grey"},
                {"red","blue","green","white","black","grey","brown"},
                {"red","blue","green","white","black","grey","brown"},
                {"blue","white","black","grey"}
        };

        int flag = 0;

        for(int i = 0;;i++){
            try{
                if(topBottomMatch[topColour][i] == bottomColour){
                    flag = 1;
                    break;
                }
            }catch(Exception e){
                break;
            }
        }

        matchedOutput(flag);
    }

    void matchedOutput(int flag){
        if(flag == 1){
            //output clothing somehow
        }
        else{
            //output that no clothing were matched
        }
    }


    private Button niceButton, tryAgain;
    private ImageView chosenPhoto;
    private Cursor picCursor;
    private String picName, clothColor, clothType;

    //https://www.journaldev.com/9438/android-sqlite-database-example-tutorial
    DatabaseHelper theDB;

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

        TextView tv = findViewById(R.id.URI_VIEW);
        //tv.setText();

        if (clothType.equals("TOP")){
            chosenPhoto = findViewById(R.id.topImage);
            //Set other ImageView
        }

        else if (clothType.equals("BOTTOM")){
            chosenPhoto = findViewById(R.id.botImage);
            //Set other ImageView
        }

       else
           tv.setText("Nothing found");

        Uri thePic = Uri.parse(getIntent().getStringExtra(Ideas.CURRENT_PHOTO_URI));
        chosenPhoto.setImageURI(thePic);

        picCursor.moveToLast();
        clothColor = picCursor.getString(picCursor.getColumnIndex("COLOR"));
        tv.setText(clothColor);

        //Run through algorithm to find matching pictures


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
                //Intent intent = new Intent(GeneratedCombination.this, something.class);
                //startActivity(intent);
            }
        });
    }
}
