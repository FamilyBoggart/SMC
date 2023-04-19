package com.example.testeo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.testeo.Objects.Coche;
import com.example.testeo.Objects.Componente;

import java.sql.SQLException;
import java.util.ArrayList;

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
    public int getID(String email, String password)
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

    public ArrayList<Coche> getCoches(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Coche> coches = new ArrayList<>();
        String[] columnas = {"marca", "modelo", "matricula", "año_matriculacion", "km"};
        String condicion = "owner_id=?";
        String[] argumentos = {String.valueOf(userId)};

        Cursor cursor = db.query("coches", columnas, condicion, argumentos, null, null, null);

        while (cursor.moveToNext()) {
            Coche coche = new Coche(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            coches.add(coche);
        }

        cursor.close();
        db.close();
        return coches;
    }

    public ArrayList<Componente> getComponentes(String matricula) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Componente> componentes = new ArrayList<>();
        String[] columnas = {"componente", "km_revision", "km","matricula"};
        String condicion = "matricula=?";
        String[] argumentos = {String.valueOf(matricula)};

        Cursor cursor = db.query("componentes", columnas, condicion, argumentos, null, null, null);

        while (cursor.moveToNext()) {
            Componente componente = new Componente(cursor.getString(0), cursor.getInt(1), cursor.getInt(2),cursor.getString(3));
            componentes.add(componente);
        }

        cursor.close();
        db.close();
        return componentes;
    }


}
