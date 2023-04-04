package com.example.testeo;

import static com.example.testeo.R.id.register_btn_login;
import static com.example.testeo.R.id.register_btn_next;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityRegister extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button loginButton = findViewById(register_btn_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        Button nextButton = findViewById(register_btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityRegister2.class);
                try{
                    registrar();
                }
                catch (Exception e){
                    Log.e("Error en la BD", "Hemos tenido un error "+e.getMessage());
                }

                startActivity(intent);
            }

            public void registrar(){
                Context context = getApplicationContext();
                DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();

                TextView view = null;
                if(comprobacionPassword()) {
                    Usuario user = new Usuario();

                    view = findViewById(R.id.txt_register_name);
                    registro.put("nombre", view.getText().toString());

                    view = findViewById(R.id.txt_register_mail);
                    registro.put("email", view.getText().toString());

                    view = findViewById(R.id.txt_register_password);
                    registro.put("password", view.getText().toString());

                    bd.insert("usuarios",null,registro);
                    System.out.println("Registro creado exitosamente");
                }
                else System.err.println("Las password no coinciden");
                bd.close();
            }

            public boolean comprobacionPassword(){
                TextView view;
                view=findViewById(R.id.txt_register_password);
                String password = view.getText().toString();
                view=findViewById(R.id.txt_register_confirm_password);
                String confirmPassword=view.getText().toString();
                return password.equals(confirmPassword);
            }

        });


    }
}
