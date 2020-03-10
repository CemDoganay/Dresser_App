package com.example.dresser_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Ideas extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/{


    private Button next, gallery;
    private ImageView photoFromGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        next = findViewById(R.id.button_next_ideas);
        gallery = findViewById(R.id.button_dressMe);
        photoFromGallery = findViewById(R.id.gallery_photo);

        //generate = findViewById(R.id.button_dressMe);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ideas.this, GeneratedCombination.class);
                startActivity(intent);
            }
        });

        /*
        Spinner spinner =  findViewById(R.id.spinner_color);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        generate = findViewById(R.id.button_generate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ideas.this, GeneratedCombination.class);
                startActivity(intent);
            }
        });
        */
    }

    /*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

     */

}