package com.example.testeo;


import static com.example.testeo.R.id.ui_btn_my_cars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityUI extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_ui);

        //Establecer nombre
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        TextView nombre = findViewById(R.id.txt_name_yours);
        nombre.setText(user.getNombre());

        //Button a mis coches
        Button loginButton = findViewById(ui_btn_my_cars);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUI.this, ActivityMyCars.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });


    }






}
