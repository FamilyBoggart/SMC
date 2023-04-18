package com.example.testeo.Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Componente implements Serializable {
    private int km;
    private int km_revision;
    private String nombre;

    public Componente(String nombre, int km_revision, int km) {
        this.km = km;
        this.km_revision = km_revision;
        this.nombre = nombre;
    }

    public double getKm() {
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
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


