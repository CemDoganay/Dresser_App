package com.example.dresser_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_combination);

        niceButton = findViewById(R.id.button_nice);
        tryAgain = findViewById(R.id.button_tryAgain);



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
