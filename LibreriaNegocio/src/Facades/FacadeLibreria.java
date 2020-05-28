/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Const.contantes;
import Enums.Denominacion;
import Intefaces.IGestionLibro;
import Intefaces.IGestionPrestamo;
import Interfaces.IFacadeLibreria;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Moneda;
import entities.Prestamo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author USER
 */
public class FacadeLibreria implements IFacadeLibreria {

    private ArrayList<Libro> catalogo = new ArrayList<>();
    private IGestionLibro gestionLibro = new RepositorioLibro();
    private IGestionPrestamo gestionPrestamo = new RepositorioPrestamo();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private Prestamo prestamoActual = new Prestamo();

    public FacadeLibreria() {
        this.catalogo = gestionLibro.CargarLibros();
        this.prestamos = gestionPrestamo.cargarPrestamos();
    }

    public ArrayList<Libro> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Libro> catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public ArrayList<Libro> consultarLibros() {
        return this.catalogo;
    }

    @Override
    public void agregarLibro(Libro libro) {
        this.catalogo.add(libro);
        gestionLibro.agregarLibro(libro);

    }

    @Override
    public void cargarLibros() {
        this.catalogo = gestionLibro.CargarLibros();
    }

    @Override
    public void PersistirPrestamo() {
        gestionPrestamo.PersistirPrestamo(this.prestamoActual);
    }

    @Override
    public IGestionLibro getGestionLibro() {
        return gestionLibro;
    }

    @Override
    public void setGestionLibro(IGestionLibro gestionLibro) {
        this.gestionLibro = gestionLibro;
    }

    @Override
    public IGestionPrestamo getGestionPrestamo() {
        return gestionPrestamo;
    }

    @Override
    public void setGestionPrestamo(IGestionPrestamo gestionPrestamo) {
        this.gestionPrestamo = gestionPrestamo;
    }

    @Override
    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    @Override
    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public Prestamo getPrestamoActual() {
        return prestamoActual;
    }

    @Override
    public void setPrestamoActual(Prestamo prestamoActual) {
        this.prestamoActual = prestamoActual;
    }

    @Override
    public boolean crearNuevoPrestamo() {
        prestamoActual = new Prestamo();
        prestamoActual.setNumero(prestamos.size() + 1);
        LocalDateTime t = LocalDateTime.now();
        this.prestamoActual.setFecha(t);
        if (this.catalogo.isEmpty()) {
            return false;
        }
        for (Libro l : catalogo) {
            if (l.getUnidadDisponibles() != 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public DtoResumen agregarLinea(Libro libro, int cantidad) {
        //libro que mandamos exista y la cantidad sea igual o mayor, suma de los subtotales
        DtoResumen res = new DtoResumen();
        int bandera = cantiLibros(libro, cantidad);
        if (bandera == 1) {
            double subtotal = calcularSubTotal(libro, cantidad);
            res = prestamoActual.agregarLinea(libro, cantidad, subtotal);

            res.setAgregar(true);
            return res;
        } else {
            res.setAgregar(false);
            if (bandera == 0) {
                res.setMensaje("ERROR: no hay la cantidad suficiente de libros en el catalogo");
            } else {
                res.setMensaje("ERROR: El libro no existe");
            }
        }
        return res;
    }

    private int existeLibro(Libro libro) {
        return catalogo.indexOf(libro);
    }

    private int cantiLibros(Libro libro, int canti) {
        int respuesta = -1;
        int indice = existeLibro(libro);
        if (indice != -1) {
            int unidades = catalogo.get(indice).getUnidadDisponibles();
            respuesta = 0;
            if (unidades >= canti) {
                catalogo.get(indice).disminuirCantidad(canti);
                respuesta = 1;
            }
        }
        return respuesta;
    }

    @Override
    public DtoResumen eliminarLinea(Linea linea) {
        DtoResumen dto = new DtoResumen();
        if (VerificarLinea(linea)) {
            dto.setMensaje("El valor es NULO");
            dto.setAgregar(false);
            return dto;
        }

        int index = this.prestamoActual.getLineas().indexOf(linea);
        if (index == -1) {
            dto.setMensaje("El libro no esta en la linea ");
            dto.setAgregar(false);
            return dto;
        }

        return this.prestamoActual.eliminarLinea(linea);
    }

    private boolean VerificarLinea(Linea l) {

        return (l == null);
    }

    @Override
    public DtoResumen agregarMoneda(Denominacion denomincion, int cantidad) {
        DtoResumen dto = new DtoResumen();
        if (denomincion == null) {
            dto.setAgregar(false);
            dto.setMensaje("El campo esta vacio");
            return dto;
        }

        return this.prestamoActual.agregarMoneda(denomincion, cantidad);
    }

    @Override
    public DtoResumen terminarPrestamo() {
        DtoResumen dto = new DtoResumen();
        dto = this.prestamoActual.terminarPrestamo();
        double devuelta = dto.getDevuelta();
        if (dto.isAgregar()) {
            if (gestionPrestamo.PersistirPrestamo(prestamoActual)) {
                this.prestamos.add(prestamoActual);
                dto = actualizarExistencias();
                int contMil = 0, contQui = 0 ;
                ArrayList<Moneda> listaM = prestamoActual.getPagoMonedas();
                
                for (Moneda m : listaM) {
                   
                    if(m.getDenominacion() ==Denominacion.MIL)
                        contMil ++;
                    if(m.getDenominacion() == Denominacion.QUIENTOS)
                        contQui++;
                }
               if( !gestionPrestamo.persistirMonedas(Denominacion.QUIENTOS, contQui, prestamoActual.getNumero()))
               {
                   dto.setAgregar(false);
                   dto.setMensaje("No se pudo persistir monedas de quinientos");
               }
               if(!gestionPrestamo.persistirMonedas(Denominacion.MIL, contMil, prestamoActual.getNumero()))
               {
                   dto.setAgregar(false);
                   dto.setMensaje("No se pudo persistir monedas de quinientos");
               }
               gestionPrestamo.commit();
            } else {
                dto.setAgregar(false);
                dto.setMensaje("No se pude insertar el prestamo en la BD");
            }
            
        }
        dto.setDevuelta(devuelta);
        return dto;
    }

    private DtoResumen actualizarExistencias() {
        int cantidad, numeroPrestamo;
        numeroPrestamo = this.prestamoActual.getNumero();
        DtoResumen dto = new DtoResumen();
        for (Linea l : this.prestamoActual.getLineas()) {
            Libro lib = new Libro();

            lib = l.getLibroEnPrestamo();
            cantidad = l.getCantidad();

            if (gestionPrestamo.actualizarExistencias(lib, cantidad)) {
                if (gestionPrestamo.insertarLineas(l, numeroPrestamo)) {
                    dto.setAgregar(true);
                } else {
                    dto.setAgregar(false);
                    dto.setMensaje("No se pude insertar la linea en la BD");
                }
            } else {
                dto.setAgregar(false);
                dto.setMensaje("No se pude actualizar exitencias en BD");
            }

        }
        return dto;
    }

    @Override
    public DtoResumen consultarPrestamo(int numero) {
        DtoResumen dto = gestionPrestamo.consultarPrestamo(numero);
        dto.getPrestamo().setLineas(gestionPrestamo.buscarLineasPorUnPrestamo(numero));
        for (Linea l : dto.getPrestamo().getLineas()) {
            l.setLibroEnPrestamo(gestionLibro.buscarLibro(l.getLibroEnPrestamo().getIsbn()));
            double subTotal = calcularSubTotal(l.getLibroEnPrestamo(), l.getCantidad());
            l.setSubTotal(subTotal);
        }
        dto.setCantiQuini(gestionPrestamo.buscarMonedas(Denominacion.QUIENTOS, numero));
        dto.setCantiMil(gestionPrestamo.buscarMonedas(Denominacion.MIL, numero));
        return dto;
    }

    private double calcularSubTotal(Libro libro, int cantidad) {
        return ((libro.getPrecioBase() + (libro.getNumeroImagenes() * contantes.VALOR_IMAGEN) + (libro.getNumeroVideos() * contantes.VALOR_VIDEO)) * cantidad);
    }

}
