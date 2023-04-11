package com.example.testeo;

import static com.example.testeo.R.id.login_btn_login;
import static com.example.testeo.R.id.login_btn_register;
import static com.example.testeo.R.id.login_txt_email;
import static com.example.testeo.R.id.login_txt_password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testeo.Objects.Usuario;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Boton para ir a registro
        Button registerButton = findViewById(login_btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });

        //Boton para logearse
        Button loginButton = findViewById(login_btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Usuario user= login();

                if(user.getId() != -1) {
                    Intent intent = new Intent(ActivityLogin.this, ActivityUI.class);
                    intent.putExtra("objUser",user);
                    startActivity(intent);
                }
                else {
                    errorLogin();
                }
            }
            private Usuario login() {
                EditText emailView = findViewById(R.id.login_txt_email);
                String email = emailView.getText().toString();

                EditText passwordView = findViewById(R.id.login_txt_password);
                String password = passwordView.getText().toString();

                //Obtenemos la id
                Context context = getApplicationContext();
                Usuario user = new Usuario(email,password);


                //Trabajamos con la id
                user.generateDataById(context);

                return user;
            }
            private void errorLogin(){
                View txt_error = findViewById(R.id.login_txt_error);
                txt_error.setVisibility(View.VISIBLE);

                EditText removeEmail = findViewById(login_txt_email);
                removeEmail.setText("");

                EditText removePassword = findViewById(login_txt_password);
                removePassword.setText("");

                Log.e("Error de logueo","Esas credenciales no existen");
            }
        });



    }


}