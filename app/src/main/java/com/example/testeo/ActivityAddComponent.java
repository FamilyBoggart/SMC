package com.example.testeo;

import static com.example.testeo.R.id.btn_edit_component;
import static com.example.testeo.R.id.component_km;
import static com.example.testeo.R.id.component_km_rev;
import static com.example.testeo.R.id.component_name;
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

        Context context = getApplicationContext();
        Intent intent = getIntent();
        Coche car = (Coche) intent.getSerializableExtra("objCar"); //Debe vnir un coche siempre
        List<Componente> carComponents = car.getComponentes(context);
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        int kmDiff = Integer.parseInt(intent.getSerializableExtra("kmDiff").toString());
        showComponent(carComponents);

        System.out.println("diferencia = "+kmDiff);

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
                recogerData(carComponents,car);
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });
    }

    protected void showComponent(List<Componente> carComponents){
        Context context = getApplicationContext();
        LinearLayout componentContainer = findViewById(R.id.component_container);
        componentContainer.removeAllViews();

        for(Componente component : carComponents ) {
            View view = getLayoutInflater().inflate(R.layout.component_layout, null);

            TextView componentName = view.findViewById(component_name);
            componentName.setText(component.getNombre());

            TextView componentKmRev = view.findViewById(component_km_rev);
            componentKmRev.setText(String.valueOf(component.getKmRevision()));

            EditText componentKm = new EditText(context);
            componentKm.setHint(String.valueOf(component.getKm()));
            componentKm.setId(View.generateViewId());
            componentKm.setTag(String.valueOf(component.getNombre()));



            componentContainer.addView(view);
        }
    }


    protected void recogerData(List<Componente> carComponents,Coche car) {
        Context context = getApplicationContext();
        LinearLayout componentContainer = findViewById(R.id.component_container);
        int numComponents = componentContainer.getChildCount();
        for (int i = 0; i < numComponents; i++) {
            View child = componentContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout componentLayout = (LinearLayout) child;
                EditText componentKm = componentLayout.findViewById(R.id.component_km);
                String kmStr = componentKm.getText().toString().trim();
                if (!kmStr.isEmpty()) {
                    String componentName = "";
                    if (componentKm.getTag() != null) {
                        componentName = componentKm.getTag().toString();
                    }

                    int km = Integer.parseInt(kmStr);
                    for (Componente c : carComponents) {
                        if (c.getNombre().equals(componentName)) {
                            editarComponente(c, km, context, car);
                            break;
                        }
                    }
                }
            }
        }
    }
    protected void editarComponente(Componente component,int km,Context context,Coche car){
        //POO
        component.setKm(km);

        //BBDD
        car.actualizarComponente(context,component);}
}
