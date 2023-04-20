package com.example.testeo;

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

        showData(user);

        Button backButton = findViewById(R.id.config_btn_back_to_ui);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityConfig.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });


    }

    public void showData(Usuario user){

        TextView userName = findViewById(R.id.config_edit_txt_name);
        userName.setText(user.getNombre());

        TextView userMail = findViewById(R.id.config_edit_txt_mail);
        userMail.setText(user.getEmail());

    }
}
