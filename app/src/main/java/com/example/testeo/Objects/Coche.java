package com.example.testeo.Objects;

import java.util.List;

public class Coche {
        private String marca;
        private String modelo;
        private String matricula;
        private int year_matriculacion;
        private int km;
        private List<Componente> componentes;

        public Coche(String marca, String modelo, String matricula, int year_matriculacion, int km, List<Componente> componentes) {
            this.marca = marca;
            this.modelo = modelo;
            this.matricula = matricula;
            this.year_matriculacion = year_matriculacion;
            this.km = km;
            this.componentes = componentes;
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

        //Metodos CRUD de componentes
        public void editarComponente(Componente componente) {
            for (int i = 0; i < componentes.size(); i++) {
                if (componentes.get(i).getNombre().equals(componente.getNombre())) {
                    componentes.set(i, componente);
                    break;
                }
            }
        }
    }


