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
public class Libro {
    private String  isbn;
    private double precioBase;
    private int unidadDisponibles;
    private int numeroImagenes;
    private int numeroVideos;
    private String titulo;

    public Libro(String isbn, double precioBase, int unidadDisponible, int numeroImagenes, int numeroVideos, String titulo) {
        this.isbn = isbn;
        this.precioBase = precioBase;
        this.unidadDisponibles = unidadDisponible;
        this.numeroImagenes = numeroImagenes;
        this.numeroVideos = numeroVideos;
        this.titulo = titulo;
    }

    public Libro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getUnidadDisponibles() {
        return unidadDisponibles;
    }

    public void setUnidadDisponibles(int unidadDisponibles) {
        this.unidadDisponibles = unidadDisponibles;
    }

    public int getNumeroImagenes() {
        return numeroImagenes;
    }

    public void setNumeroImagenes(int numeroImagenes) {
        this.numeroImagenes = numeroImagenes;
    }

    public int getNumeroVideos() {
        return numeroVideos;
    }

    public void setNumeroVideos(int numeroVideos) {
        this.numeroVideos = numeroVideos;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void disminuirCantidad(int cantidad)
    {
        this.unidadDisponibles -= cantidad;
    }
    public void aumentarCantidad(int cantidad)
    {
        this.unidadDisponibles += cantidad;
    }
    @Override
    public String toString(){
        return "Titulo :"+this.titulo+"\nIsbn:"+this.isbn+" \nPrecio base: "+this.precioBase + "\nUnidades Disponibles: "+
                this.unidadDisponibles + "\nNumero de Imagenes: "+this.numeroImagenes + "\nNumero de Videos: "+ this.numeroVideos;
    }

    
}

    