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
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class FacadeLibreria implements IFacadeLibreria{
    private ArrayList<Libro> libros = new ArrayList<>();
    private final IGestionLibro gestionLibro = new RepositorioLibro();
    private final IGestionPrestamo gestionPrestamo = new RepositorioPrestamo();

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    
    @Override
    public ArrayList<Libro> consultarLibros() {
        return  this.libros;
    }

   

    @Override
    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
        gestionLibro.agregarLibro(libro);
        
    }
    
    
}
