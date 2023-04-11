package com.example.testeo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button btnLogin = findViewById(R.id.start_btn_login);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
            startActivity(intent);
        });

        Button btnRegister = findViewById(R.id.start_btn_register);
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityRegister.class);
            startActivity(intent);});
    }
}



