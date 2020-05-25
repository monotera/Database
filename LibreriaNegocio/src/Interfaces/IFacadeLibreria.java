/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Intefaces.IGestionLibro;
import Intefaces.IGestionPrestamo;
import entities.*;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IFacadeLibreria {

    ArrayList<Libro> consultarLibros();

    void cargarLibros();

    void agregarLibro(Libro libro);

    void PersistirPrestamo();

    boolean crearNuevoPrestamo();

    IGestionLibro getGestionLibro();

    void setGestionLibro(IGestionLibro gestionLibro);

    IGestionPrestamo getGestionPrestamo();

    void setGestionPrestamo(IGestionPrestamo gestionPrestamo);

    ArrayList<Prestamo> getPrestamos();

    void setPrestamos(ArrayList<Prestamo> prestamos);

    Prestamo getPrestamoActual();

    void setPrestamoActual(Prestamo prestamoActual);

    DtoResumen agregarLinea(Libro libro, int cantidad);

    DtoResumen eliminarLinea(Linea linea);
}
