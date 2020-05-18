/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Intefaces.IGestionLibro;
import Intefaces.IGestionPrestamo;
import Interfaces.IFacadeLibreria;
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
    private Prestamo prestamoActual = new Prestamo();

    public boolean crearNuevoPrestamo() {

        LocalDateTime t = LocalDateTime.now();
        this.prestamoActual.setFecha(t);
        if (this.catalogo.isEmpty()) {
            return false;
        }
        for (Libro l : catalogo) {
            if (l.getUnidadDisponibles() != 0) {
                return false;
            }

        }
        return true;

    }

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

}
