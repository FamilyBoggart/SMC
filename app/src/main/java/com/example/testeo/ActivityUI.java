package com.example.testeo;


import static com.example.testeo.R.id.component_car_brand;
import static com.example.testeo.R.id.component_car_model;
import static com.example.testeo.R.id.component_car_plate;
import static com.example.testeo.R.id.show_component_km;
import static com.example.testeo.R.id.show_component_km_rev;
import static com.example.testeo.R.id.show_component_name;
import static com.example.testeo.R.id.ui_btn_my_cars;
import static com.example.testeo.R.id.ui_container;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Componente;
import com.example.testeo.Objects.Usuario;

import java.util.List;

public class ActivityUI extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_ui);

        //Establecer nombre
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        TextView nombre = findViewById(R.id.txt_name_yours);
        nombre.setText(user.getNombre());
        showComponents(user);

        //Button a mis coches
        Button loginButton = findViewById(ui_btn_my_cars);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUI.this, ActivityMyCars.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });


    }

    public void showComponents(Usuario user){
        Context context = getApplicationContext();
        List<Coche> cars = user.getCoches(context);

        LinearLayout componentContainer = findViewById(ui_container);
        componentContainer.removeAllViews();

        for (Coche coche:cars){
            for(Componente componente:coche.getComponentes(context)){
                if(criterioShow(componente,70)){
                    View view = getLayoutInflater().inflate(R.layout.component_show_layout, null);

                    TextView carBrand = view.findViewById(component_car_brand);
                    carBrand.setText(coche.getMarca());

                    TextView carModel = view.findViewById(component_car_model);
                    carModel.setText(coche.getModelo());

                    TextView carPlate = view.findViewById(component_car_plate);
                    carPlate.setText(coche.getMatricula());

                    TextView compName = view.findViewById(show_component_name);
                    compName.setText(componente.getNombre());

                    TextView compKmRev = view.findViewById(show_component_km_rev);
                    compKmRev.setText(String.valueOf(componente.getKmRevision()));

                    TextView compKm = view.findViewById(show_component_km);
                    compKm.setText(String.valueOf(componente.getKm()));


                    componentContainer.addView(view);
                }



            }
        }
    }

    /**
     * Este metodo calculara si un componente esta cercano a tu km de vencimiento en base al porcentaje de uso que lleve, y si este es superior al parametro
     * Que introducimos, entronces devolvera true
     * @param componente: El componente que querremos analizar
     * @param percentage: El porcentaje que consideraremos limite para enseñar
     * @return
     */
    private boolean criterioShow(Componente componente,int percentage){
        double kmRev = (double) componente.getKmRevision();
        int km=componente.getKm();
        double uso = km/kmRev;
        if(uso*100>=percentage){return true;}
        else {return false;}

    }


}
