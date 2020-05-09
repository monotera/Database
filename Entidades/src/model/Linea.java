/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Linea {

    private int cantidad;
    private Libro libroEnPrestamo;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Libro getLibroEnPrestamo() {
        return libroEnPrestamo;
    }

    public void setLibroEnPrestamo(Libro libroEnPrestamo) {
        this.libroEnPrestamo = libroEnPrestamo;
    }
}
