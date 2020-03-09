package com.example.dresser_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Tagging extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button add;
    private ImageView image;
    private Spinner spinner_color;
    private Spinner spinner_type;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagging);

        add = findViewById(R.id.button_add);
        image = findViewById(R.id.imageView_cloth);

        mydb = new DatabaseHelper(this);

        final Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(TakePicture.CURRENT_PHOTO_PATH));
        image.setImageBitmap(bitmap);

        spinner_color =  findViewById(R.id.spinner_color);
        spinner_type =  findViewById(R.id.spinner_type);

        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);

        adapter_color.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_color.setAdapter(adapter_color);
        spinner_type.setAdapter(adapter_type);

        spinner_color.setOnItemSelectedListener(this);
        spinner_type.setOnItemSelectedListener(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = getIntent().getStringExtra(TakePicture.CURRENT_PHOTO_URI);

                String address = temp.substring(temp.indexOf("P") + 9);
                String type = spinner_type.getSelectedItem().toString();
                String color = spinner_color.getSelectedItem().toString();

                boolean bool = mydb.insertData(address, type, color);

                if(bool == false)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Failed",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }

                if(bool == true)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Success",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }



                Intent intent = new Intent(Tagging.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

