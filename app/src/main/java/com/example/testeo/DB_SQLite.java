package com.example.testeo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLException;

public class DB_SQLite extends SQLiteOpenHelper {



    public DB_SQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super((Context) context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE revisiones(marca VARCHAR(30),modelo VARCHAR(30),componente VARCHAR(50),tiempo int,km int,inspeccion VARCHAR(50) DEFAULT 'Reemplazo')");
        db.execSQL("CREATE TABLE usuarios(id INTEGER PRIMARY KEY ,email VARCHAR(40) NOT NULL,password varchar(20) NOT NULL,nombre VARCHAR(60) NOT NULL)");
        db.execSQL("CREATE TABLE coches (marca VARCHAR(30),modelo VARCHAR(30),matricula varchar(10) PRIMARY KEY NOT NULL,año_matriculacion YEAR,owner_name varchar(60),owner_id int,km INT DEFAULT 0,CONSTRAINT fk_id FOREIGN KEY (owner_id) REFERENCES usuarios(id))");
        db.execSQL("CREATE TABLE componentes(matricula VARCHAR(10),componente VARCHAR(60),tiempo int,tiempo_revsion int,km int,km_revision int,CONSTRAINT fk_matricula FOREIGN KEY (matricula) REFERENCES coches(matricula));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    @SuppressLint("Range")
    int getID(String email, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int id=-1;
        String[] columnas = {"id"};
        String condicion="email = ? AND password = ?";
        String[] argumentos={email,password};
        Cursor cursor = db.query("usuarios",columnas,condicion,argumentos,null,null,null);

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
            }
        cursor.close();
        db.close();

        return id;
    }


    @SuppressLint("Range")
    public String getColumnValue(String columnName, int userID){
        SQLiteDatabase db = this.getReadableDatabase();
        String columnValue = null;

        String[] columnas = {columnName};
        String condicion="id= ?";
        String [] argumentos = {String.valueOf(userID)};

        Cursor cursor = db.query("usuarios", columnas, condicion, argumentos, null, null, null);
        if(cursor.moveToFirst()) columnValue = cursor.getString(cursor.getColumnIndex(columnName));
        cursor.close();
        db.close();
        return columnValue;
    }

}
