package com.example.testeo;

import static com.example.testeo.R.id.add_car_btn_back;
import static com.example.testeo.R.id.add_car_btn_next;
import static com.example.testeo.R.id.add_car_txt_km;
import static com.example.testeo.R.id.add_car_txt_marca;
import static com.example.testeo.R.id.add_car_txt_matricula;
import static com.example.testeo.R.id.add_car_txt_modelo;
import static com.example.testeo.R.id.add_car_txt_year_mat;
import static com.example.testeo.R.id.btn_removecar;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.switch1;
import static com.example.testeo.R.id.txt_add_car;

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
        Coche carOld = (Coche) intent.getSerializableExtra("objCar");
        carData(carOld);


        Button removeButton = findViewById(btn_removecar);
        removeButton.setVisibility(View.GONE);
        removeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Context context= getApplicationContext();
                user.eliminarCoche(carOld,context);
            }
        });
        //Mostramos el boton borrar
        if(carOld!=null){

            removeButton.setVisibility(View.VISIBLE);
            TextView txt = findViewById(txt_add_car);
            txt.setText("Editar Coche");

        }


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

                    Context context = getApplicationContext();
                    Coche carNew=addCar();
                    //Damos por hecho que si cambia la matricula se trata de un coche distinto
                    if(carOld!=null&&carOld.getMatricula().equals(carNew.getMatricula()))
                    {  user.modificarCoche(carNew,context);}
                    else
                    { user.agregarCoche(carNew,context);  }


                    Switch component = findViewById(switch1);
                    if(component.isChecked()){
                        Intent intent = new Intent(ActivityAddCar.this, ActivityAddComponent.class);
                        intent.putExtra("objUser",user);
                        intent.putExtra("objCar",carNew);
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

        Context context = getApplicationContext();
        EditText view = null;

        view = findViewById(add_car_txt_matricula);
        String matricula =view.getText().toString();

        view = findViewById(add_car_txt_km);
        int km =Integer.parseInt(view.getText().toString());


        Coche car = new Coche(context,matricula,km);


        view = findViewById(add_car_txt_marca);
        String marca =view.getText().toString();
        car.setMarca(marca);

        view = findViewById(add_car_txt_modelo);
        String modelo =view.getText().toString();
        car.setModelo(modelo);

        view = findViewById(add_car_txt_year_mat);
        int year =Integer.parseInt(view.getText().toString());
        car.setYear_matriculacion(year);

        return car;
    }
    public void carData(Coche coche){
        if(coche != null){
            EditText view = null;

            view = findViewById(add_car_txt_marca);
            view.setText(coche.getMarca());

            view = findViewById(add_car_txt_modelo);
            view.setText(coche.getModelo());

            view = findViewById(add_car_txt_matricula);
            view.setText(String.valueOf(coche.getMatricula()));

            view = findViewById(add_car_txt_year_mat);
            view.setText(String.valueOf(coche.getYear_matriculacion()));

            view = findViewById(add_car_txt_km);
            view.setText(String.valueOf(coche.getKm()));
        }
    }
}
