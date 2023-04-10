package com.example.testeo.Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Componente implements Serializable {
    private int km;
    private LocalDateTime fechaRevision;
    private String nombre;

    public Componente(int km, LocalDateTime fechaRevision, String nombre) {
        this.km = km;
        this.fechaRevision = fechaRevision;
        this.nombre = nombre;
    }

    public double getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public LocalDateTime getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDateTime fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


