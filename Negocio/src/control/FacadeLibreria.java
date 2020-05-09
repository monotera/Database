/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Libro;
import model.Prestamo;

/**
 *
 * @author user
 */
public class FacadeLibreria implements IFacadeLibreria {

    private List<Libro> catalogo = new ArrayList();
    private List<Prestamo> prestamos = new ArrayList();
    private Prestamo prestamoActual;
    private IGestionLibro gestionLibro;
    private IGestionPrestamo gestionPrestamo;
}
