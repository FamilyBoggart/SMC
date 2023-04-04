package com.example.testeo;

import static com.example.testeo.R.id.login_txt_email;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeo.Objects.Usuario;

public class ActivityUI extends AppCompatActivity {

    int userID=-1;

    protected void onCreate(Bundle savedInstanceState) {
        Usuario user = new Usuario();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        userID=userID();
        System.out.println(userID);
    }

    private int userID(){
        Intent intent = getIntent();
        int userId = intent.getIntExtra("id",-1);
        return userId;

    }
    protected void setName(){
        Context context = getApplicationContext();
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);

        String name=admin.getColumnValue("nombre",userID);

        EditText setName = findViewById(login_txt_email);

        if(name!=null) setName.setText(name);
        else setName.setText("Tu nombre");
    }



}
