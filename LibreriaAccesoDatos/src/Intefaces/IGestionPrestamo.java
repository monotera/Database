/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import entities.Prestamo;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IGestionPrestamo {
    void PersistirPrestamo(Prestamo prestamo);
    ArrayList<Prestamo> cargarPrestamos();
}
