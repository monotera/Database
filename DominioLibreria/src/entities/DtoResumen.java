/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class DtoResumen {
    private String mensaje;
    private ArrayList<Linea> coleccion = new ArrayList<>();
    private boolean agregar;
    private double total;
    private double saldo;
    private double vueltos;

    public String getMensaje() {
        return mensaje;
    }

    public DtoResumen() {
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Linea> getColeccion() {
        return coleccion;
    }

    public void setColeccion(ArrayList<Linea> coleccion) {
        this.coleccion = coleccion;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getVueltos() {
        return vueltos;
    }

    public void setVueltos(double vueltos) {
        this.vueltos = vueltos;
    }
    
   
    
    
}
