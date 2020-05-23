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
        this.prestamos = gestionPrestamo.cargarPrestamos();
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
        prestamoActual.setNumero(prestamos.size() + 1);
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
        //libro que mandamos exista y la cantidad sea igual o mayor, suma de los subtotales
        DtoResumen res = new DtoResumen();
        int bandera = cantiLibros(libro, cantidad);
        if (bandera == 1) {
            res = prestamoActual.agregarLinea(libro, cantidad);
            res.setAgregar(true);
            return res;
        } else {
            res.setAgregar(false);
            if (bandera == 0) {
                res.setMensaje("ERROR: no hay la cantidad suficiente de libros en el catalogo");
            } else {
                res.setMensaje("ERROR: El libro no existe");
            }
        }
        return res;
    }

    private int existeLibro(Libro libro) {
        return catalogo.indexOf(libro);
    }

    private int cantiLibros(Libro libro, int canti) {
        int respuesta = -1;
        int indice = existeLibro(libro);
        if (indice != -1) {
            int unidades = catalogo.get(indice).getUnidadDisponibles();
            respuesta = 0;
            if (unidades >= canti) {
                respuesta = 1;
            }
        }
        return respuesta;
    }
}
