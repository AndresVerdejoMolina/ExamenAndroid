package com.example.usuario.jdachat;

public class Reservas {
    String fecha;
    String comensales;
    String nombre;
    String telefono;
    String comentarios;

    public Reservas(String fecha, String nombre, String telefono, String comensales, String comentarios) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.comensales = comensales;
        this.telefono = telefono;
        this.comentarios = comentarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComensales() {
        return comensales;
    }

    public void setComensales(String comensales) {
        this.comensales = comensales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public Reservas(){

    }

}
