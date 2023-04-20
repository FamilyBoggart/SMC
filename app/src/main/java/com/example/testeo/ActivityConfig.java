package com.example.testeo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityConfig extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");

        Button backButton = findViewById(R.id.config_btn_back_to_ui);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ActivityConfig.this, ActivityUI.class);
                intent.putExtra("objUser",user);
                startActivity(intent);
            }
        });


    }
}
