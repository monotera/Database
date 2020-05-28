/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Linea {

    private int cantidad;
    private Libro libroEnPrestamo = new Libro();
    private double subTotal;

    public Libro getLibroEnPrestamo() {
        return libroEnPrestamo;
    }

    public String getTitulo() {
        return libroEnPrestamo.getTitulo();
    }

    public double getPrecioBase() {
        return libroEnPrestamo.getPrecioBase();
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setLibroEnPrestamo(Libro libroEnPrestamo) {
        this.libroEnPrestamo = libroEnPrestamo;
    }

    public Linea(int cantidad, Libro libroEnPrestamo) {
        this.cantidad = cantidad;
        this.libroEnPrestamo = libroEnPrestamo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Linea() {
    }

    @Override
    public String toString() {
        return "Cantidad: " + this.cantidad + "\nLibro en Prestamo:" + this.libroEnPrestamo.toString();
    }

}
