/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Enums.Denominacion;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Prestamo;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IGestionPrestamo {

    boolean PersistirPrestamo(Prestamo prestamo);

    ArrayList<Prestamo> cargarPrestamos();

    boolean actualizarExistencias(Libro libro, int cantidad);

    boolean insertarLineas(Linea linea, int numeroPrestamo);

    DtoResumen consultarPrestamo(int numero);

    ArrayList<Linea> buscarLineasPorUnPrestamo(int numero);

    boolean persistirMonedas(Denominacion denominaciion, int cantidad, int id);
    
    int buscarMonedas (Denominacion denominacion, int id );

    void commit();

}
