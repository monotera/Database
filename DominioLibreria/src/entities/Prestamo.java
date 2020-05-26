/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import Const.contantes;
import Enums.Denominacion;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Prestamo {

    private LocalDateTime fecha;
    private int numero;
    private double total;
    private ArrayList<Linea> lineas = new ArrayList<>();
    private ArrayList<Moneda> pagoMonedas = new ArrayList<>();

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public ArrayList<Moneda> getPagoMonedas() {
        return pagoMonedas;
    }

    public void setPagoMonedas(ArrayList<Moneda> pagoMonedas) {
        this.pagoMonedas = pagoMonedas;
    }

    public Prestamo(LocalDateTime fecha, int numero, ArrayList<Linea> lineas, ArrayList<Moneda> pagoMoedas) {
        this.fecha = fecha;
        this.numero = numero;
        this.lineas = lineas;
        this.pagoMonedas = pagoMoedas;
    }

    public Prestamo() {
        this.total = 0;
    }

    public DtoResumen agregarLinea(Libro libro, int cantidad) {
        DtoResumen dto = new DtoResumen();
        Linea nuevaLienea = new Linea();
        nuevaLienea.setLibroEnPrestamo(libro);
        nuevaLienea.setCantidad(cantidad);
        nuevaLienea.setSubTotal((libro.getPrecioBase() + (libro.getNumeroImagenes() * contantes.VALOR_IMAGEN) + (libro.getNumeroVideos() * contantes.VALOR_VIDEO)) * cantidad);//se da el subtotal
        this.lineas.add(nuevaLienea);
        calcularTotal();
        dto.setTotal(this.total);
        dto.setTama(lineas.size());
        return dto;
    }

    private void calcularTotal() {
        double tot = 0;
        for (Linea l : lineas) {
            tot += l.getSubTotal();
        }
        this.total = tot;
    }

    public DtoResumen eliminarLinea(Linea l) {
        DtoResumen dto = new DtoResumen();
        boolean remove = this.lineas.remove(l);
        if (remove) {
            dto.setAgregar(true);
            l.getLibroEnPrestamo().aumentarCantidad(l.getCantidad());
            dto.setMensaje("Se elimino de forma correacta la linea");
            calcularTotal();
            dto.setTotal(this.total);
            dto.setTama(this.lineas.size());
            return dto;
        }
        dto.setAgregar(false);
        dto.setMensaje("No se pudo eliminar");
        return dto;
    }

    public DtoResumen agregarMoneda(Denominacion denomincion, int cantidad) {
        DtoResumen dto = new DtoResumen();

        for (int i = 0; i < cantidad; i++) {
            Moneda monedaNueva = new Moneda();
            monedaNueva.setDenominacion(denomincion);
            if (this.pagoMonedas.add(monedaNueva)) {
                dto.setAgregar(true);
                dto.setMensaje("Se pudo agregar la moneda");

                
            } else {
                dto.setAgregar(false);
                dto.setMensaje("No se pudo agregar la moneda");

            }
        }
        dto.setSaldo(calcularSaldo());
        return dto;
    }

    private double calcularSaldo() {
        double saldo = 0;
        for (Moneda m : this.pagoMonedas) {
            saldo += m.getValor();
        }
        return saldo;

    }
    
    public DtoResumen terminarPrestamo()
    {
        DtoResumen dto = new DtoResumen();
        if(verificarSaldo())
        {
            dto.setAgregar(true);
            dto.setDevuelta(calcularDevuelta());
            dto.setMensaje("Se realizo el prestamo");
        }else
        {
            dto.setAgregar(false);
            dto.setMensaje("No hay saldo suficiente");
        }
        return dto;
    }
    private boolean verificarSaldo()
    {
        double saldo  = calcularSaldo();
        return saldo >= this.total;
    }
    private double calcularDevuelta()
    {
        return calcularSaldo() - this.total;
    }
}
