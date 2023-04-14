package com.example.testeo;

import static com.example.testeo.R.id.add_car_btn_back;
import static com.example.testeo.R.id.add_car_btn_next;
import static com.example.testeo.R.id.add_car_txt_km;
import static com.example.testeo.R.id.add_car_txt_marca;
import static com.example.testeo.R.id.add_car_txt_matricula;
import static com.example.testeo.R.id.add_car_txt_modelo;
import static com.example.testeo.R.id.add_car_txt_year_mat;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.switch1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
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

               //Agregamos un coche al usuario asignado
                Coche car=addCar();
                user.agregarCoche(car);







                // Division por el switch
                Switch component = findViewById(switch1);
                if(component.isChecked()){
                    Intent intent = new Intent(ActivityAddCar.this, ActivityAddComponent.class);
                    intent.putExtra("objUser",user);
                    //intent.putExtra("objCar",car);
                    startActivity(intent);}
                else{
                    Intent intent = new Intent(ActivityAddCar.this, ActivityUI.class);
                    intent.putExtra("objUser",user);
                    startActivity(intent);
                }
            }
        });
    }

    public Coche addCar(){
        Coche car = new Coche();
        Context context = getApplicationContext();
        EditText view = null;

        view = findViewById(add_car_txt_marca);
        String marca =view.getText().toString();
        car.setMarca(marca);

        view = findViewById(add_car_txt_modelo);
        String modelo =view.getText().toString();
        car.setModelo(modelo);

        view = findViewById(add_car_txt_matricula);
        String matricula =view.getText().toString();
        car.setMatricula(matricula);

        view = findViewById(add_car_txt_year_mat);
        String year =view.getText().toString();
        car.setYear_matriculacion(Integer.parseInt(year));

        view = findViewById(add_car_txt_km);
        String km =view.getText().toString();
        car.setKm(Integer.parseInt(km));
        return car;
    }
}
