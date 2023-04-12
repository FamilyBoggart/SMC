package com.example.testeo;

import static com.example.testeo.R.id.add_car_btn_back;
import static com.example.testeo.R.id.add_car_btn_next;
import static com.example.testeo.R.id.mycars_btn_add_car;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityAddCar extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Intent intent  = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");

        Button backButton = findViewById(add_car_btn_back);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddCar.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });

        Button nextButton = findViewById(add_car_btn_next);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddCar.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });



    }
}
