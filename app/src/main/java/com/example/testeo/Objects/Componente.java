package com.example.testeo.Objects;


import java.io.Serializable;

public class Componente implements Serializable {
    private int km;
    private int km_revision;
    private String nombre;
    private String matricula;

    public Componente(String nombre, int km_revision, int km,String matricula) {
        //POO
        this.km = km;
        this.km_revision = km_revision;
        this.nombre = nombre;
        this.matricula=matricula;

    }

    public int getKm() {
        return km;
    }
    public void setKm(int km) {
        this.km = km;
    }

    public int getKmRevision() {
        return km_revision;
    }
    public void setKmRevision(int km_revision) {
        this.km_revision = km_revision;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}


