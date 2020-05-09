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
public class Libro {

    private String ISBN;
    private double precioBace;
    private int unidadesDisponibles;
    private int numeroDeImagenes;
    private int numeroVideos;

    public Libro(String ISBN, double precioBace, int unidadesDisponibles, int numeroDeImagenes, int numeroVideos) {
        this.ISBN = ISBN;
        this.precioBace = precioBace;
        this.unidadesDisponibles = unidadesDisponibles;
        this.numeroDeImagenes = numeroDeImagenes;
        this.numeroVideos = numeroVideos;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrecioBace() {
        return precioBace;
    }

    public void setPrecioBace(double precioBace) {
        this.precioBace = precioBace;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public int getNumeroDeImagenes() {
        return numeroDeImagenes;
    }

    public void setNumeroDeImagenes(int numeroDeImagenes) {
        this.numeroDeImagenes = numeroDeImagenes;
    }

    public int getNumeroVideos() {
        return numeroVideos;
    }

    public void setNumeroVideos(int numeroVideos) {
        this.numeroVideos = numeroVideos;
    }
}
