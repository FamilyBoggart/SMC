package com.example.testeo;

import static com.example.testeo.R.id.register_btn_login;
import static com.example.testeo.R.id.register_btn_next;


import android.content.Context;
import android.content.Intent;

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
                    Usuario user = new Usuario();
                    registrar(user);
                    String nombre= user.getNombre();
                    System.out.println(nombre);
                    intent.putExtra("nombre",nombre);
                }
                catch (Exception e){
                    Log.e("Error en la BD", "Hemos tenido un error "+e.getMessage());
                }

                startActivity(intent);
            }

            public void registrar(Usuario user){
                Context context = getApplicationContext();


                TextView view = null;
                if(comprobacionPassword()) {


                    view = findViewById(R.id.txt_register_name);
                    String name =view.getText().toString() ;
                    user.setNombre(name);

                    view = findViewById(R.id.txt_register_mail);
                    String mail =view.getText().toString();
                    user.setEmail(mail);

                    view = findViewById(R.id.txt_register_password);
                    String password = view.getText().toString();
                    user.setPassword(password);

                    user.agregarUsuario(context);
                    System.out.println("Registro creado exitosamente");
                }
                else System.err.println("Las password no coinciden");

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
