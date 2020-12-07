package com.miapp.WikiAnimal.Modelo;

public class Animal {
    private String Nombre;
    private String NombreC;
    private String Datos;
    private String Ubicacion;

    public Animal(){}
    public Animal(String nombre, String nombreC, String datos, String ubicacion) {
        this.Nombre = nombre;
        this.NombreC = nombreC;
        this.Datos = datos;
        this.Ubicacion = ubicacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) { Nombre = nombre; }

    public String getNombreC() {
        return NombreC;
    }

    public void setNombreC(String nombreC) {
        NombreC = nombreC;
    }

    public String getDatos() {
        return Datos;
    }

    public void setDatos(String datos) { Datos = datos; }

    public String getubicacion() {
        return Ubicacion;
    }

    public void setubicacion(String ubicacion) {
        this.Ubicacion = ubicacion;
    }

}

