package com.example.testeo.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.testeo.DB_SQLite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nombre;
    private String email;
    private String password;
    private List<Coche> coches;
    private int id;

    //Constructores

    public Usuario(){
        this.nombre = "";
        this.email = "";
        this.password = "";
        this.coches=new ArrayList<Coche>();
    }
    public Usuario(String email, String password) {
        this.nombre = "";
        this.email = email;
        this.password = password;
        this.coches = new ArrayList<Coche>();
    }

    // Getters y Setters

    public String getNombre() {

        return this.nombre;
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

    public List<Coche> getCoches(Context context) {

        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        this.coches=admin.getCoches(this.getId());

        db.close();
        admin.close();

        return this.coches;
    }


    private void setId(int id){this.id=id;}
    public int getId(){
        return this.id;}

    // metodos CRUD para Coches (read = getCoches)

    public void agregarCoche(Coche coche,Context context) {
        //POO
        this.coches.add(coche);

        //BBDD
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("marca",coche.getMarca());
        registro.put("modelo",coche.getModelo());
        registro.put("matricula",coche.getMatricula());
        registro.put("año_matriculacion",coche.getYear_matriculacion());
        registro.put("owner_name",this.getNombre());
        registro.put("owner_id",this.getId());
        registro.put("km",coche.getKm());

        db.insert("coches",null,registro);

        db.close();
        admin.close();
        //Registramos los componentes del coche
        for(Componente componente : coche.componentes){
                coche.registrarComponente(context,componente);
        }




    }

    public void modificarCoche(Coche cocheNuevo,Context context) {

        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("marca",cocheNuevo.getMarca());
        registro.put("modelo",cocheNuevo.getModelo());
        registro.put("matricula",cocheNuevo.getMatricula());
        registro.put("año_matriculacion",cocheNuevo.getYear_matriculacion());
        registro.put("owner_name",this.getNombre());
        registro.put("owner_id",this.getId());
        registro.put("km",cocheNuevo.getKm());


        String condicion = "matricula=?";
        String[] argumentos = {cocheNuevo.getMatricula()};

        db.update("coches",registro,condicion,argumentos);
        db.close();
        admin.close();

        //Modificamos los comonentes del coche
        for(Componente componente : cocheNuevo.componentes){
            cocheNuevo.actualizarComponente(context,componente);
        }



        // POO
        for (int i = 0; i < this.coches.size(); i++) {
            if (this.coches.get(i).getMatricula().equals(cocheNuevo.getMatricula())) {
                // Si encontramos el coche, lo reemplazamos por el nuevo objeto coche
                this.coches.set(i, cocheNuevo);
                break;
            }
        }
    }

    public void eliminarCoche(Coche car,Context context) {

        //BBDD
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String condicion = "matricula=?";
        String[] argumentosCar={car.getMatricula()};

        db.delete("componentes",condicion,argumentosCar);
        db.delete("coches",condicion,argumentosCar);
        db.close();
        admin.close();

        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getMatricula().equals(car.getMatricula())) {
                coches.remove(i);
                break;
            }
        }
    }

    //metodos para acceso a la base de Datos


    public void agregarUsuario(Context context) {
        ContentValues registro = new ContentValues();
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        registro.put("nombre",this.nombre);
        registro.put("email",this.email);
        registro.put("password",this.password);

        db.insert("usuarios",null,registro);
        this.setId(generarID(admin,this.email,this.password));
        db.close();
    }

    private int generarID(DB_SQLite db,String email,String password){

        int id= db.getID(email,password);
        db.close();
        return id;
    }

    public void generateDataById(Context context){

        //Encontramos la id
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        setId(generarID(admin,this.email,this.password));
        this.setNombre(admin.getColumnValue("nombre",this.id));
            //Falta añadir los coches
        admin.close();


    }


}
