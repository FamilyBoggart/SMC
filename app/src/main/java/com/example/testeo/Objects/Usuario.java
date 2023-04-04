package com.example.testeo.Objects;

import java.util.List;

public class Usuario {
    private String nombre;
    private String email;
    private String password;
    private List<Coche> coches;

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

    // metodos CRUD para Coches

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
}
