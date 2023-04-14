package com.example.testeo;

import static com.example.testeo.R.id.carContainer;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.mycars_btn_back;
import static com.example.testeo.R.id.ui_btn_my_cars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Usuario;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ActivityMyCars extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        Intent intent  = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");

        // Contenedor de coches
        LinearLayout container = findViewById(carContainer);
        List<Coche> cars = user.getCoches();
        if (cars.isEmpty()){}
        else{
            container.setVisibility(View.VISIBLE);
            for (Coche coche:cars) {
                
            }
        }
        // Botones
        Button backButton = findViewById(mycars_btn_back);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyCars.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });


        Button addCarButton = findViewById(mycars_btn_add_car);
        addCarButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyCars.this, ActivityAddCar.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });

    }


}
