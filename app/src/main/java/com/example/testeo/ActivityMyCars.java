package com.example.testeo;

import static com.example.testeo.R.id.carContainer;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.mycars_btn_back;
import static com.example.testeo.R.id.noCars;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        showCar(user);


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
    protected void showCar(Usuario user){



        List<Coche> cars = user.getCoches();

        if(cars.isEmpty()){
            TextView noCar = findViewById(noCars);
            noCar.setVisibility(View.VISIBLE);
            LinearLayout carContainer = findViewById(R.id.carContainer);
            carContainer.setVisibility(View.GONE);

        }
        else{
            TextView noCar = findViewById(noCars);
            noCar.setVisibility(View.GONE);
            LinearLayout carContainer = findViewById(R.id.carContainer);
            carContainer.setVisibility(View.VISIBLE);
        }


    }
    protected void securitymethod(Coche cars){
        /*
        for (Coche coche : cars) {
            // Inflar el diseño XML de un coche en una nueva vista
            View carView = getLayoutInflater().inflate(R.layout.layout_car, null);

            // Obtener las referencias de los elementos del diseño XML
            TextView brandTextView = carView.findViewById(R.id.brandCar);
            TextView modelTextView = carView.findViewById(R.id.modelCar);
            TextView plateTextView = carView.findViewById(R.id.plateCar);
            TextView kmTextView = carView.findViewById(R.id.kmCar);
            TextView nextItvTextView = carView.findViewById(R.id.ITVCar);
            Button editButton = carView.findViewById(R.id.editButton);

            // Establecer los valores de los elementos del diseño XML con los atributos del objeto Coche
            brandTextView.setText(coche.getMarca());
            modelTextView.setText(coche.getModelo());
            plateTextView.setText(coche.getMatricula());
            kmTextView.setText(String.valueOf(coche.getKm()));
            nextItvTextView.setText(coche.getITV());

            // Agregar el diseño XML de un coche al LinearLayout
            carContainer.addView(carView);
        }   */
    }

    private void addCar(Usuario user){

    }
}
