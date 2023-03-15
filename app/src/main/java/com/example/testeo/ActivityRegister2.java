package com.example.testeo;

import static com.example.testeo.R.id.register_2_btn_UI;

import static com.example.testeo.R.id.register_btn_next;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);


        Button addCarButton = findViewById(R.id.register_2_btn_add_car);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister2.this, ActivityAddCar.class);
                startActivity(intent);
            }
        });

        Button nextButton = findViewById(R.id.register_2_btn_UI);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister2.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

    }
}
