package com.example.testeo;

import static com.example.testeo.R.id.edit_component_btn_next;
import static com.example.testeo.R.id.mycars_btn_add_car;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Usuario;

public class ActivityAddComponent extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        Intent intent = getIntent();
        Coche car = (Coche) intent.getSerializableExtra("objCar");
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");

        Button addCarButton = findViewById(edit_component_btn_next);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });
    }



}
