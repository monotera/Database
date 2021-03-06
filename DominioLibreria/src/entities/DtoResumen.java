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
    private Prestamo prestamo = new Prestamo();
    private boolean agregar;
    private double total;
    private double saldo;
    private double vueltos;

    public int getCantiMil() {
        return cantiMil;
    }

    public void setCantiMil(int cantiMil) {
        this.cantiMil = cantiMil;
    }

    public int getCantiQuini() {
        return cantiQuini;
    }

    public void setCantiQuini(int cantiQuini) {
        this.cantiQuini = cantiQuini;
    }
    private int tama = 0;
    private  double  devuelta;
    private int cantiMil = 0;
    private int cantiQuini = 0;

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(double devuelta) {
        this.devuelta = devuelta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getTama() {
        return tama;
    }

    public void setTama(int tama) {
        this.tama = tama;
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
