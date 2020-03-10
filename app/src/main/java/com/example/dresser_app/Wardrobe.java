package com.example.dresser_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.dresser_app.TakePicture;

public class Wardrobe extends AppCompatActivity {

    private Button mOpen, mAdd, mRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

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
    }
}
