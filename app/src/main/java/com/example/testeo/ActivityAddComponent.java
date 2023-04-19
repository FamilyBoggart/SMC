package com.example.testeo;

import static com.example.testeo.R.id.edit_component_btn_next;
import static com.example.testeo.R.id.mycars_btn_add_car;

import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Componente;
import com.example.testeo.Objects.Usuario;

import java.util.ArrayList;

public class ActivityAddComponent extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        Intent intent = getIntent();
        Coche car = (Coche) intent.getSerializableExtra("objCar"); //Debe vnir un coche siempre
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        System.out.println(car.getKm());
        showComponent(car);



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
        if(car!=null){
            Context context = getApplicationContext();
            LinearLayout componentContainer = findViewById(R.id.component_container);
            int cont =0;
            for(Componente component : car.getComponentes()) {
                cont++;
            }
            System.out.println("Componentes: "+cont);
        }




    }


}
