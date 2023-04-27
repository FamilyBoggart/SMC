package com.example.testeo;

import static com.example.testeo.R.id.btn_edit_component;
import static com.example.testeo.R.id.component_btn_back_ui;
import static com.example.testeo.R.id.component_name;

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
        int []ids =showComponents(car,kmDiff);

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
                recogerData(ids,car);
                Intent intent = new Intent(ActivityAddComponent.this, ActivityUI.class);
                intent.putExtra("objUser", user);
                startActivity(intent);
            }
        });
    }

    protected int[] showComponents(Coche car, int kmDiff) {
        Context context = getApplicationContext();
        List <Componente> componentes = car.getComponentes(context);
        int [] ids = new int[componentes.size()];
        if(componentes.isEmpty()){System.out.println("La lista de componentes esta vacia");}
        else{
          ids= eachComponent(componentes,kmDiff,car);
        }
        return ids;
    }

    public int[] eachComponent(List <Componente> components,int kmDiff,Coche car){
        LinearLayout componentContainer = findViewById(R.id.component_container);
        Context context = getApplicationContext();
        componentContainer.setVisibility(View.VISIBLE);
        componentContainer.removeAllViews();
        int []ids=new int[components.size()];
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
            int editTextId = View.generateViewId();
            componentKm.setId(editTextId);
            ids[i]=editTextId;
            i++;

            componentContainer.addView(view);
            System.out.println(ids[i-1]);
        }

        return ids;
    }

    protected void editarComponente(Componente component,int km,Context context,Coche car){
        //POO
        component.setKm(km);

        //BBDD
        car.actualizarComponente(context,component);}



    public void recogerData(int[] ids,Coche car) {
        Context context = getApplicationContext();
        List <Componente> componentes =car.getComponentes(context);
        for (int i = 0; i < ids.length; i++) {
            EditText editText = findViewById(ids[i]);
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                Componente componente = componentes.get(i);
                int km = Integer.parseInt(text);
                editarComponente(componente, km, getApplicationContext(), car);
            }
        }
    }



}
