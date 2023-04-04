package com.example.testeo.Objects;

import java.util.List;

public class Coche {
        private String marca;
        private String modelo;
        private String matricula;
        private int anioMatriculacion;
        private int km;
        private List<Componente> componentes;

        public Coche(String marca, String modelo, String matricula, int anioMatriculacion, int km, List<Componente> componentes) {
            this.marca = marca;
            this.modelo = modelo;
            this.matricula = matricula;
            this.anioMatriculacion = anioMatriculacion;
            this.km = km;
            this.componentes = componentes;
        }

        // Métodos getters y setters para cada atributo
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

        public int getAnioMatriculacion() {
            return anioMatriculacion;
        }

        public void setAnioMatriculacion(int anioMatriculacion) {
            this.anioMatriculacion = anioMatriculacion;
        }

        public double getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public List<Componente> getComponentes() {
            return componentes;
        }

        public void setComponentes(List<Componente> componentes) {
            this.componentes = componentes;
        }
    }


