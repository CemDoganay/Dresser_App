package com.example.dresser_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_combination);
    }
}
