/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import entities.Libro;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IGestionLibro {

    void agregarLibro(Libro libro);

    ArrayList<Libro> CargarLibros();

    Libro buscarLibro(String Isbn);

}
