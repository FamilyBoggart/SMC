package com.example.testeo;

import static com.example.testeo.R.id.component_button;
import static com.example.testeo.R.id.component_edit;
import static com.example.testeo.R.id.component_km;
import static com.example.testeo.R.id.component_km_rev;
import static com.example.testeo.R.id.component_name;
import static com.example.testeo.R.id.edit_component_btn_next;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Componente;
import com.example.testeo.Objects.Usuario;

public class ActivityAddComponent extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        Intent intent = getIntent();
        Coche car = (Coche) intent.getSerializableExtra("objCar"); //Debe vnir un coche siempre
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        int kmDiff = Integer.parseInt(intent.getSerializableExtra("kmDiff").toString());
        showComponent(car);

        System.out.println("diferencia = "+kmDiff);

        Button addCarButton = findViewById(edit_component_btn_next);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });
    }

    protected void showComponent(Coche car){

            Context context = getApplicationContext();
            LinearLayout componentContainer = findViewById(R.id.component_container);
            componentContainer.removeAllViews();


        for(Componente component : car.getComponentes(context)) {
            View view = getLayoutInflater().inflate(R.layout.component_layout, null);

            TextView componentName = view.findViewById(component_name);
            componentName.setText(component.getNombre());

            TextView componentKmRev = view.findViewById(component_km_rev);
            componentKmRev.setText(String.valueOf(component.getKmRevision()));

            EditText componentKm = view.findViewById(component_km);
            componentKm.setText(String.valueOf(component.getKm()));

            Button editButton = view.findViewById(component_button);
            editButton.setTag(component);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Componente c= (Componente)v.getTag();
                    //Aplicamos los cambios visuales
                    String km = componentKm.getText().toString();
                    componentKm.setHint(km);
                    componentKm.setText(null);

                    //Conservamos los cambios en la BBDD
                    if(editarComponente(c,Integer.parseInt(km),context,car)){System.out.println("Componente editado correctamente");}




                }
            });

            componentContainer.addView(view);
        }

    }
    protected boolean editarComponente(Componente component,int km,Context context,Coche car){
        //POO
        component.setKm(km);

        //BBDD

        car.actualizarComponente(context,component);

        return true;}


}
