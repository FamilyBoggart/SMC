package com.example.testeo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

import org.w3c.dom.Text;

public class ActivityConfig extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");

        TextView txtError = findViewById(R.id.config_txt_error);
        txtError.setVisibility(View.GONE);

        showData(user);

        Button backButton = findViewById(R.id.config_btn_back_to_ui);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityConfig.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });

        Button changeButton = findViewById(R.id.btn_change_password);
        changeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(modifyData(user)){
                    Intent intent = new Intent(ActivityConfig.this, ActivityUI.class);
                    intent.putExtra("objUser",user);
                    startActivity(intent);
                }



            }
        });


    }

    public void showData(Usuario user){

        TextView userName = findViewById(R.id.config_edit_txt_name);
        userName.setText(user.getNombre());

        TextView userMail = findViewById(R.id.config_edit_txt_mail);
        userMail.setText(user.getEmail());

    }
    public boolean modifyData(Usuario user){
        TextView userName = findViewById(R.id.config_edit_txt_name);
        TextView userMail = findViewById(R.id.config_edit_txt_mail);
        TextView userOldPassword = findViewById(R.id.edit_password_old);
        TextView userNewPassword = findViewById(R.id.edit_new_password);
        TextView userConfirmPassword = findViewById(R.id.edit_confirm_new_password);
        if (userMail.getText().toString().contains("@")){
            if(!userOldPassword.getText().toString().equals("")){
                if(userOldPassword.getText().toString().equals(user.getPassword())){
                    if(userNewPassword.getText().toString().equals(userConfirmPassword.getText().toString())){
                        if(!userNewPassword.getText().toString().equals("")){
                            Context context = getApplicationContext();
                            user.setNombre(userName.getText().toString());
                            user.setEmail(userMail.getText().toString());
                            user.setPassword(userNewPassword.getText().toString());

                            user.modifyUser(context);
                            return true;
                        }
                        else{
                            errorConfig("Debes introducir una nueva contraseña");
                            return false;}


                    }
                    else{
                        errorConfig("Las contraseñas introducidas son distintas");
                        return false;
                    }
                }
                else{
                    errorConfig("La contraseña es incorrecta");
                    return false;
                }
            }
            else{
                errorConfig("Debes introducir la contraseña");
                return false;
            }
        }
        else{
            errorConfig("Debes introducir un mail valido");
            return false;
        }

    }
    public void errorConfig(String text){
        TextView txtError = findViewById(R.id.config_txt_error);
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(text);
    }
}
