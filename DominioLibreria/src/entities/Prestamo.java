/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Prestamo {
    private LocalDateTime fecha;
    private int numero;
    private ArrayList<Linea> lineas = new ArrayList<>();
    private ArrayList<Moneda> pagoMoedas = new  ArrayList<>();

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas) {
        this.lineas = lineas;
    }

    public ArrayList<Moneda> getPagoMoedas() {
        return pagoMoedas;
    }

    public void setPagoMoedas(ArrayList<Moneda> pagoMoedas) {
        this.pagoMoedas = pagoMoedas;
    }

    public Prestamo(LocalDateTime fecha, int numero, ArrayList<Linea> lineas, ArrayList<Moneda> pagoMoedas) {
        this.fecha = fecha;
        this.numero = numero;
        this.lineas = lineas;
        this.pagoMoedas = pagoMoedas;
    }

    public Prestamo() {
        
    }
    public DtoResumen agregarLinea(Libro libro, int cantidad)
    {
        DtoResumen dto = new DtoResumen();
        Linea nuevaLienea = new Linea();
        nuevaLienea.setLibroEnPrestamo(libro);
        nuevaLienea.setCantidad(cantidad);
        nuevaLienea.setSubTotal(libro.getPrecioBase()* cantidad);//se da el subtotal
        this.lineas.add(nuevaLienea);
        return dto;
    }
    
    
}
