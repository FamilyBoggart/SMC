package com.example.testeo;

import static com.example.testeo.R.id.mycars_btn_add_car;
import static com.example.testeo.R.id.mycars_btn_back;
import static com.example.testeo.R.id.noCars;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Usuario;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

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
            eachCar(cars);
        }


    }

    private void eachCar(List<Coche> cars){
        LinearLayout carContainer = findViewById(R.id.carContainer);

        carContainer.setVisibility(View.VISIBLE);
        carContainer.removeAllViews();

        for (Coche coche : cars) {

            View view =getLayoutInflater().inflate(R.layout.car_layout,null);
            carContainer.addView(view);
        }
    }

    /*


    private View generarCodigoXML(Coche coche) {
        // Crear vista contenedor del coche
        LinearLayout cocheContainer = new LinearLayout(this);
        cocheContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        cocheContainer.setOrientation(LinearLayout.HORIZONTAL);

        // Crear vista del texto del coche
        LinearLayout textoContainer = new LinearLayout(this);
        textoContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        textoContainer.setOrientation(LinearLayout.VERTICAL);

        TextView marcaTextView = new TextView(this);
        marcaTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        marcaTextView.setText("Coche: " + coche.getMarca() + " " + coche.getModelo());
        marcaTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        marcaTextView.setTypeface(null, Typeface.BOLD);

        TextView matriculaTextView = new TextView(this);
        matriculaTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        matriculaTextView.setText("Matrícula: " + coche.getMatricula());

        TextView kmTextView = new TextView(this);
        kmTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        kmTextView.setText("Km Actual: " + coche.getKm());

        TextView itvTextView = new TextView(this);
        itvTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        itvTextView.setText("Próxima ITV: " + coche.getFechaItv().toString());

        textoContainer.addView(marcaTextView);
        textoContainer.addView(matriculaTextView);
        textoContainer.addView(kmTextView);
        textoContainer.addView(itvTextView);

        // Crear botón de edición
        Button editarButton = new Button(this);
        editarButton.setLayoutParams(new LinearLayout.LayoutParams

     */


    public String generateCodigoXML(Coche coche) {
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
