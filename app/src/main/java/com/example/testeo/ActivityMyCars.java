package com.example.testeo;

import static com.example.testeo.R.id.carContainer;
import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.mycars_btn_back;
import static com.example.testeo.R.id.noCars;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
            eachCar(cars);
        }


    }
    private void eachCar(List<Coche> cars){
        LinearLayout carContainer = findViewById(R.id.carContainer);

        for (Coche coche : cars) {
            String codigoXML = generarCodigoXML(coche);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cocheView = inflater.inflate(R.layout.car_layout, null);


            carContainer.removeAllViews(); // Eliminar vistas anteriores
            carContainer.addView(XmlParser.parse(codigoXML)); // Agregar vista generada

            layout.addView(cocheView); // Agregar vista de coche al layout principal
        }


    }
    public String generarCodigoXML(Coche coche) {
        StringBuilder sb = new StringBuilder();

        // Inicio del View
        sb.append("<View\n");
        sb.append("    android:id=\"@+id/divider_car\"\n");
        sb.append("    android:layout_width=\"match_parent\"\n");
        sb.append("    android:layout_height=\"1dp\"\n");
        sb.append("    android:background=\"?android:attr/listDivider\"\n");
        sb.append("    tools:ignore=\"DuplicateIds\" />\n");

        // Inicio del LinearLayout padre
        sb.append("<LinearLayout\n");
        sb.append("    android:layout_width=\"match_parent\"\n");
        sb.append("    android:layout_height=\"wrap_content\"\n");
        sb.append("    android:orientation=\"horizontal\">\n");

        // Inicio del primer LinearLayout hijo
        sb.append("    <LinearLayout\n");
        sb.append("        android:layout_width=\"match_parent\"\n");
        sb.append("        android:layout_height=\"wrap_content\"\n");
        sb.append("        android:layout_weight=\"1\"\n");
        sb.append("        android:orientation=\"vertical\">\n");

        // Primer TextView
        sb.append("        <LinearLayout\n");
        sb.append("            android:layout_width=\"match_parent\"\n");
        sb.append("            android:layout_height=\"wrap_content\"\n");
        sb.append("            android:orientation=\"horizontal\">\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"100dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:layout_weight=\"1\"\n");
        sb.append("                android:text=\"Coche: \"\n");
        sb.append("                android:textSize=\"15dp\"\n");
        sb.append("                android:textStyle=\"bold\" />\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"80dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"" + coche.getMarca() + "\" />\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"80dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"" + coche.getModelo() + "\" />\n");
        sb.append("        </LinearLayout>\n");

        // Segundo TextView
        sb.append("        <LinearLayout\n");
        sb.append("            android:layout_width=\"match_parent\"\n");
        sb.append("            android:layout_height=\"wrap_content\"\n");
        sb.append("            android:orientation=\"horizontal\">\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"100dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:textSize=\"15dp\"\n");
        sb.append("                android:textStyle=\"bold\"\n");
        sb.append("                android:text=\"Matricula: \" />\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"80dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"" + coche.getMatricula() + "\" />\n");
        sb.append("        </LinearLayout>\n");
        //Tercer TextView
        sb.append("        <LinearLayout\n");
        sb.append("            android:layout_width=\"match_parent\"\n");
        sb.append("            android:layout_height=\"wrap_content\"\n");
        sb.append("            android:orientation=\"horizontal\">\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"100dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:textSize=\"15dp\"\n");
        sb.append("                android:textStyle=\"bold\"\n");
        sb.append("                android:text=\"Km Actual: \" />\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"80dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"" + coche.getKm() + " Km\" />\n");
        sb.append("        </LinearLayout>\n");
        //Cuarto TextView
        sb.append("        <LinearLayout\n");
        sb.append("            android:layout_width=\"match_parent\"\n");
        sb.append("            android:layout_height=\"wrap_content\"\n");
        sb.append("            android:orientation=\"horizontal\">\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"100dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:textSize=\"15dp\"\n");
        sb.append("                android:textStyle=\"bold\"\n");
        sb.append("                android:text=\"Proxima ITV \" />\n");
        sb.append("            <TextView\n");
        sb.append("                android:layout_width=\"80dp\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"Fecha ITV \" />\n");
        sb.append("        </LinearLayout>\n");
        sb.append("    </LinearLayout>\n");
        //Boton
        sb.append("            <Button\n");
        sb.append("                android:layout_width=\"wrap_content\"\n");
        sb.append("                android:layout_height=\"wrap_content\"\n");
        sb.append("                android:text=\"Editar\"\n");
        sb.append("                android:backgroundTint=\"@color/yellow_500\"\n");
        sb.append("                android:layout_gravity=\"center\" />\n");
        sb.append("        </LinearLayout>\n");

        return sb.toString();

    }
    }
