package com.example.testeo;

import static com.example.testeo.R.id.add_car_txt_year_mat;
import static com.example.testeo.R.id.car_brand;
import static com.example.testeo.R.id.car_button;
import static com.example.testeo.R.id.car_itv;
import static com.example.testeo.R.id.car_km;
import static com.example.testeo.R.id.car_model;
import static com.example.testeo.R.id.car_plate;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.mycars_btn_back;
import static com.example.testeo.R.id.noCars;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Usuario;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class ActivityMyCars extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        showCar(user);


        // Botones
        Button backButton = findViewById(mycars_btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyCars.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });


        Button addCarButton = findViewById(mycars_btn_add_car);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyCars.this, ActivityAddCar.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });

    }

    protected void showCar(Usuario user) {
        Context context = getApplicationContext();
        List<Coche> cars = user.getCoches(context);

        if (cars.isEmpty()) {
            TextView noCar = findViewById(noCars);
            noCar.setVisibility(View.VISIBLE);
            LinearLayout carContainer = findViewById(R.id.carContainer);
            carContainer.setVisibility(View.GONE);

        } else {
            TextView noCar = findViewById(noCars);
            noCar.setVisibility(View.GONE);
            eachCar(cars,user);
        }


    }

    private void eachCar(List<Coche> cars,Usuario user) {
        LinearLayout carContainer = findViewById(R.id.carContainer);

        carContainer.setVisibility(View.VISIBLE);
        carContainer.removeAllViews();

        for (Coche coche : cars) {

            View view = getLayoutInflater().inflate(R.layout.car_layout, null);

            TextView carBrand = view.findViewById(car_brand);
            carBrand.setText(coche.getMarca());

            TextView carModel = view.findViewById(car_model);
            carModel.setText(coche.getModelo());

            TextView carPlate = view.findViewById(car_plate);
            carPlate.setText(coche.getMatricula());

            TextView carKm = view.findViewById(car_km);
            carKm.setText(String.valueOf(coche.getKm()));

            TextView carITV = view.findViewById(car_itv);
            carITV.setText(String.valueOf(coche.getITV()));

            coche.setComponentes();

            Button editButton = view.findViewById(car_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ActivityAddCar.class);
                    intent.putExtra("objCar", coche);
                    intent.putExtra("objUser",user);
                    startActivity(intent);
                }
            });

            carContainer.addView(view);


        }
    }
}




