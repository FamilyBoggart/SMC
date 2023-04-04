package com.example.testeo.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.testeo.DB_SQLite;

import java.util.List;

public class Usuario {
    private String nombre;
    private String email;
    private String password;
    private List<Coche> coches;

    //Constructores

    public Usuario(){
        this.nombre = "";
        this.email = "";
        this.password = "";
        this.coches=null;
    }
    public Usuario(String nombre, String email, String password, List<Coche> coches) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.coches = coches;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coche> getCoches() {
        return coches;
    }
    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    // metodos CRUD para Coches (read = getCoches)

    public void agregarCoche(Coche coche) {
        this.coches.add(coche);
    }

    public void modificarCoche(Coche cocheNuevo) {

        // Buscamos el coche que queremos modificar en el ArrayList
        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getMatricula().equals(cocheNuevo.getMatricula())) {
                // Si encontramos el coche, lo reemplazamos por el nuevo objeto coche
                coches.set(i, cocheNuevo);
                break;
            }
        }
    }

    public void eliminarCoche(String matricula) {

        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getMatricula().equals(matricula)) {
                coches.remove(i);
                break;
            }
        }
    }

    //metodos para acceso a la base de Datos
    public SQLiteDatabase dB(Context context){
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        return bd;
    }

    public void agregarUsuario(Context context) {
        ContentValues registro = new ContentValues();
        SQLiteDatabase bd =dB(context);

        registro.put("nombre",this.nombre);
        registro.put("email",this.email);
        registro.put("password",this.password);

        bd.insert("usuarios",null,registro);
        bd.close();
    }
}
