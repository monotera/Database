/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Intefaces.IGestionLibro;
import Intefaces.IGestionPrestamo;
import Interfaces.IFacadeLibreria;
import entities.DtoResumen;
import entities.Libro;
import entities.Prestamo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author USER
 */
public class FacadeLibreria implements IFacadeLibreria {

    private ArrayList<Libro> catalogo = new ArrayList<>();
    private IGestionLibro gestionLibro = new RepositorioLibro();
    private IGestionPrestamo gestionPrestamo = new RepositorioPrestamo();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private Prestamo prestamoActual;

    public FacadeLibreria() {
        this.catalogo = gestionLibro.CargarLibros();
    }

    public ArrayList<Libro> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Libro> catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public ArrayList<Libro> consultarLibros() {
        return this.catalogo;
    }

    @Override
    public void agregarLibro(Libro libro) {
        this.catalogo.add(libro);
        gestionLibro.agregarLibro(libro);

    }

    @Override
    public void cargarLibros() {
        this.catalogo = gestionLibro.CargarLibros();
    }

    @Override
    public void PersistirPrestamo() {
        gestionPrestamo.PersistirPrestamo(this.prestamoActual);
    }

    @Override
    public IGestionLibro getGestionLibro() {
        return gestionLibro;
    }

    @Override
    public void setGestionLibro(IGestionLibro gestionLibro) {
        this.gestionLibro = gestionLibro;
    }

    @Override
    public IGestionPrestamo getGestionPrestamo() {
        return gestionPrestamo;
    }

    @Override
    public void setGestionPrestamo(IGestionPrestamo gestionPrestamo) {
        this.gestionPrestamo = gestionPrestamo;
    }

    @Override
    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    @Override
    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public Prestamo getPrestamoActual() {
        return prestamoActual;
    }

    @Override
    public void setPrestamoActual(Prestamo prestamoActual) {
        this.prestamoActual = prestamoActual;
    }

    @Override
    public boolean crearNuevoPrestamo() {
        prestamoActual = new Prestamo();
        LocalDateTime t = LocalDateTime.now();
        this.prestamoActual.setFecha(t);
        if (this.catalogo.isEmpty()) {
            return false;
        }
        for (Libro l : catalogo) {
            if (l.getUnidadDisponibles() != 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public DtoResumen agregarLinea(Libro libro, int cantidad) {
       
        return  prestamoActual.agregarLinea(libro, cantidad);
        
    }

}
