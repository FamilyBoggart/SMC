package com.example.testeo;

import static com.example.testeo.R.id.btn_edit_component;
import static com.example.testeo.R.id.component_btn_back_ui;

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

import java.util.List;

public class ActivityAddComponent extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        Intent intent = getIntent();
        Coche car = (Coche) intent.getSerializableExtra("objCar"); //Debe vnir un coche siempre
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        int kmDiff = Integer.parseInt(intent.getSerializableExtra("kmDiff").toString());
        showComponents(car,kmDiff);

        Button addCarButton = findViewById(component_btn_back_ui);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });

        Button editComponentButton = findViewById(btn_edit_component);
        editComponentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recogerData(car);
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });
    }

    protected void showComponents(Coche car, int kmDiff) {
        Context context = getApplicationContext();
        List <Componente> componentes = car.getComponentes(context);

        if(componentes.isEmpty()){System.out.println("La lista de componentes esta vacia");}
        else{
           eachComponent(componentes,kmDiff,car);
        }
    }

    public void eachComponent(List <Componente> components,int kmDiff,Coche car){
        LinearLayout componentContainer = findViewById(R.id.component_container);
        Context context = getApplicationContext();
        componentContainer.setVisibility(View.VISIBLE);
        componentContainer.removeAllViews();
        int i=0;

        for(Componente componente:components){
            View view = getLayoutInflater().inflate(R.layout.component_layout, null);

            TextView componentName = view.findViewById(R.id.component_name);
            componentName.setText(componente.getNombre());

            TextView componentKmRev = view.findViewById(R.id.component_km_rev);
            componentKmRev.setText(String.valueOf(componente.getKmRevision()));

            EditText componentKm = view.findViewById(R.id.component_km);
            if(kmDiff!=0){
                editarComponente(componente,componente.getKm()+kmDiff,context,car);
            }
            componentKm.setHint(String.valueOf(componente.getKm()));

            //Asignacion de ID
            String editTextId = "component_" + i + "_km";
            int resId = getResources().getIdentifier(editTextId, "id", getPackageName());
            componentKm.setId(resId);
            i++;

            componentContainer.addView(view);
        }

    }

    protected void editarComponente(Componente component,int km,Context context,Coche car){
        //POO
        component.setKm(km);

        //BBDD
        car.actualizarComponente(context,component);}



    protected void recogerData(Coche car) {
        /*
        Context context = getApplicationContext();
        List<Componente> componentes = car.getComponentes(context);
        for (Componente componente:componentes  ) {
           EditText componentKm = findViewById(getResources().getIdentifier(componente.getIdEditText(), "id", getPackageName());
            String kmStr = componentKm.getText().toString().trim();
            if (!kmStr.isEmpty()) {
                int km = Integer.parseInt(kmStr);
                editarComponente( car.componentes.get(i-1), km, context, car);
            }
        }

         */
    }

}
