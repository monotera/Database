/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import entities.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IFacadeLibreria {
    ArrayList<Libro> consultarLibros();
    void agregarLibro(Libro libro);
    
    
}
