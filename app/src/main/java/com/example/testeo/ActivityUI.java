package com.example.testeo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityUI extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        Intent intent=getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("objUser");
        System.out.println(user.getNombre());
        System.out.println(user.getEmail());

        setName(user.getNombre());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

    }

    private int userID(){
        Intent intent = getIntent();
        int userId = intent.getIntExtra("id",-1);
        return userId;

    }
    private void setName(String name){

       TextView nombre = findViewById(R.id.txt_name_yours);
    try{
        nombre.setText((CharSequence) name);}
    catch(Exception e){
        Log.e("Error: ",e.getMessage());
    }
    }



}
