package com.example.testeo;

import static com.example.testeo.R.id.login_txt_email;
import static com.example.testeo.R.id.login_txt_password;
import static com.example.testeo.R.id.register_btn_login;
import static com.example.testeo.R.id.register_btn_next;
import static com.example.testeo.R.id.txt_register_confirm_password;
import static com.example.testeo.R.id.txt_register_mail;
import static com.example.testeo.R.id.txt_register_password;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

                try{
                    Usuario user = new Usuario();
                    if(registrar(user)){
                        Intent intent = new Intent(ActivityRegister.this, ActivityRegister2.class);
                        String nombre= user.getNombre();
                        System.out.println(nombre);
                        intent.putExtra("objUser",user);
                        startActivity(intent);
                    }
                }
                catch (Exception e){
                    Log.e("Error en la BD", "Hemos tenido un error "+e.getMessage());
                }
            }

            private boolean registrar(Usuario user){
                Context context = getApplicationContext();

                TextView view = null;
                if(comprobacionPassword()) {
                    if(comprobacionMail()){
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
                        return true;
                    }
                    else errorRegister("Mail escrito incorrectamente");
                    return false;

                }
                else errorRegister("Las password no coinciden");
                return false;

            }

            private boolean comprobacionPassword(){
                TextView view;
                view=findViewById(R.id.txt_register_password);
                String password = view.getText().toString();
                view=findViewById(R.id.txt_register_confirm_password);
                String confirmPassword=view.getText().toString();
                return password.equals(confirmPassword);
            }

            private boolean comprobacionMail(){
                TextView view= findViewById(R.id.txt_register_mail);
                String mail =view.getText().toString();
                return mail.contains("@");
            }

            private void errorRegister(String text){
                TextView txt_error = findViewById(R.id.txt_wrong_register);
                txt_error.setVisibility(View.VISIBLE);
                txt_error.setText(text);
                if(text.equals("Mail escrito incorrectamente")){
                    EditText removeEmail = findViewById(txt_register_mail);
                    removeEmail.setText("");
                }
                else if(text.equals("Las password no coinciden"))
                {
                    EditText removePassword = findViewById(txt_register_password);
                    removePassword.setText("");

                    EditText removeConfirmPassword = findViewById(txt_register_confirm_password);
                    removeConfirmPassword.setText("");
                }
            }
        });


    }
}
