package com.example.dresser_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    private Cursor picCurosr;

    //https://www.journaldev.com/9438/android-sqlite-database-example-tutorial
    //SQLiteDatabase theDB = this.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_combination);

        niceButton = findViewById(R.id.button_nice);
        tryAgain = findViewById(R.id.button_tryAgain);

        String picName = getIntent().getStringExtra(Ideas.CURRENT_PHOTO_NAME);

        //picCurosr = .query(DatabaseHelper.TABLE_NAME, );

        TextView tv = findViewById(R.id.URI_VIEW);
        tv.setText(picCurosr.toString());

        niceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Ideas.this, GeneratedCombination.class);
                //startActivity(intent);
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Ideas.this, GeneratedCombination.class);
                //startActivity(intent);
            }
        });
    }
}
