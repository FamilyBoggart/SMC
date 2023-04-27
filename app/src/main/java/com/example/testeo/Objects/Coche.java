package com.example.testeo.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.testeo.DB_SQLite;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Coche implements Serializable {
        private String marca;
        private String modelo;
        private String matricula;
        private int year_matriculacion;
        private int km;
        public List<Componente> componentes;
    /*
       Constructor para AddCar
    */
    public Coche(String matricula,int km){
        this.matricula=matricula;
        this.km=km;
        this.componentes = new ArrayList<Componente>();
        }

    /*
        Constructor para la BBDD
    */
    public Coche(String marca, String modelo, String matricula, int year_matriculacion, int km) {
            this.marca = marca;
            this.modelo = modelo;
            this.matricula = matricula;
            this.year_matriculacion = year_matriculacion;
            this.km = km;
            this.componentes = new ArrayList<Componente>();
        }


    // Getters & Setters
        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public int getYear_matriculacion() {
            return year_matriculacion;
        }

        public void setYear_matriculacion(int year_matriculacion) {
            this.year_matriculacion = year_matriculacion;
        }

        public int getKm() {
            return this.km;
        }

        public void setKm(int km) {
            this.km = km;
        }


        public List<Componente> getComponentes(Context context) {

            DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();

            this.componentes=admin.getComponentes(this.matricula);

            db.close();
            admin.close();



            return this.componentes;
        }

        public void setComponentes(Context context) {

            Componente[] componentesArray = {
                new Componente("Aceite de motor", 10000, this.km,this.matricula),
                new Componente("Filtro de aire", 20000, this.km,this.matricula),
                new Componente("Liquido de frenos", 40000, this.km,this.matricula),
                new Componente("Filtro de aceite", 15000, this.km,this.matricula),
                new Componente("Bujias", 30000, this.km,this.matricula),
                new Componente("Filtro de combustible", 25000, this.km,this.matricula),
                new Componente("Neumaticos de alante", 50000, this.km,this.matricula),
                new Componente("Neumaticos de atras", 50000, this.km,this.matricula),
                new Componente("Bateria", 60000, this.km,this.matricula),
                new Componente("Correa de distribucion", 100000, this.km,this.matricula),
                new Componente("Suspension", 80000, this.km,this.matricula)
            };

            for(int i = 0; i < componentesArray.length; i++) {
                this.componentes.add(componentesArray[i]);

            }
        }

        public void editComponente(){}


    //Metodos para la ITV
    public int getITV(){
        /**
         * 1º: 4 años
         * 4-10 años: cada 2 años
         * >10 años: cada año
         */
        Calendar cal = Calendar.getInstance();
        int yearNow= cal.get(Calendar.YEAR);
        int diff = yearNow-year_matriculacion;

        if(diff<4){
            return year_matriculacion+4;
        }
        else if(diff==4||diff>=10){return  yearNow;}
        else {
            int itv1 =year_matriculacion+4;
            while(yearNow>itv1){
                itv1+=2;
            }
            return(Math.max(itv1,yearNow));
        }
    }
        //Metodos CRUD de componentes
    public void editarComponente(Componente componente) {
            for (int i = 0; i < this.componentes.size(); i++) {
                if (this.componentes.get(i).getNombre().equals(componente.getNombre())) {
                    this.componentes.set(i, componente);
                    break;
                }
            }
        }
    public void registrarComponente(Context context,Componente componente){
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();


        registro.put("componente",componente.getNombre());
        registro.put("km_revision",componente.getKmRevision());
        registro.put("km",componente.getKm());
        registro.put("matricula",this.matricula);

        db.insert("componentes",null,registro);
        db.close();
        admin.close();
    }

    public void actualizarComponente(Context context,Componente componente){
        ContentValues registro = new ContentValues();
        DB_SQLite admin = new DB_SQLite(context,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();




        registro.put("km",componente.getKm());

        String condicion="matricula=? AND componente=?";
        String[] argumentos={this.matricula,componente.getNombre()};

        db.update("componentes",registro,condicion,argumentos);
        db.close();
        admin.close();

    }
    }


