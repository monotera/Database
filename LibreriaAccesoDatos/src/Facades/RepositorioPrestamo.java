/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Intefaces.IGestionPrestamo;
import constants.constante;
import entities.DtoResumen;
import entities.Libro;
import entities.Linea;
import entities.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class RepositorioPrestamo implements IGestionPrestamo {

    private void commit() {
        String SQl = "commit";

        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)) {

            pr.execute();
            conn.close();
            System.err.println("Commit ejecutado");

        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }

    }

    @Override
    public boolean PersistirPrestamo(Prestamo prestamo) {
        String SQl = "INSERT into PRESTAMO (NUMERO,FECHA,TOTAL) VALUES (?,?,?)";
        System.err.println("Insertadno prestamo");

        boolean dto;
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)) {
            pr.setInt(1, prestamo.getNumero());
            pr.setDate(2, Date.valueOf(prestamo.getFecha().toLocalDate()));
            pr.setDouble(3, prestamo.getTotal());
            pr.execute();
            conn.close();
            dto = true;
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
            dto = false;
        }
        commit();
        return dto;
    }

    @Override
    public ArrayList<Prestamo> cargarPrestamos() {
        String SQl = "select * from PRESTAMO";
        System.err.println("Cargando prestamos");

        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                Date date = rs.getDate("FECHA");
                Timestamp t = new Timestamp(date.getTime());
                p.setFecha(t.toLocalDateTime());
                p.setNumero(rs.getInt("NUMERO"));
                p.setTotal(rs.getDouble("TOTAL"));
                prestamos.add(p);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        return buscarLineas(prestamos);
    }

    public ArrayList<Prestamo> buscarLineas(ArrayList<Prestamo> listaPrestamos) {
        String SQL2 = "select cantidad,ISBNlibro,NumeroPrestamo from linea, Prestamo where numeroprestamo = ?";
        System.err.println("Buscando Lineas");
        for (Prestamo p : listaPrestamos) {
            ArrayList<Linea> l = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                    PreparedStatement ps = conn.prepareStatement(SQL2);) {
                {
                    int x = p.getNumero();
                    ps.setInt(1, x);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Linea lin = new Linea();
                        lin.setCantidad(rs.getInt("CANTIDAD"));
                        lin.setLibroEnPrestamo(buscarLibro(rs.getString("ISBNlibro")));
                        l.add(lin);
                    }
                    p.setLineas(l);
                    conn.close();

                }
            } catch (Exception ex) {
                System.out.println("Error de conexion:" + ex.toString());
                ex.printStackTrace();

            }
        }
        return listaPrestamos;
    }

    public Libro buscarLibro(String isn) {
        String SQl = "select * from BOOKS";
        System.err.println("Sancando libros");

        ArrayList<Libro> libros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {

                Libro l = new Libro();
                l.setIsbn(rs.getString("ISBN"));
                l.setNumeroImagenes(rs.getInt("NUMEROIMAGENES"));
                l.setPrecioBase(rs.getDouble("PRECIOBASE"));
                l.setTitulo(rs.getString("TITULO"));
                l.setNumeroVideos(rs.getInt("NUMEROIMAGENES"));
                l.setUnidadDisponibles(rs.getInt("UNIDADESDISPONIBLES"));
                if (l.getIsbn() == isn) {
                    return l;
                }
            }

            conn.close();
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
       
        return null;
    }

    @Override
    public boolean actualizarExistencias(Libro libro, int cantidad) {
        String SQL = "UPDATE  books SET unidadesdisponibles = unidadesdisponibles - ? WHERE isbn = ?";
        boolean dto;
        System.err.println("Actualizando exitencias");
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQL)) {
            pr.setInt(1, cantidad);
            pr.setString(2, libro.getIsbn());
            pr.execute();
            conn.close();
            dto = (true);
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
            dto = (false);

        }
        commit();
        return dto;
    }

    @Override
    public boolean insertarLineas(Linea linea, int numeroPrestamo) {
        String SQL = "INSERT INTO linea ( CANTIDAD, ISBLIBRO, NUMEROPRESTAMO) VALUES (?,?,?)";
        boolean dto;
        System.err.println("Insertadno lineas");
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQL)) {
            pr.setInt(1, linea.getCantidad());
            pr.setString(2, linea.getLibroEnPrestamo().getIsbn());
            pr.setInt(3, numeroPrestamo);
            pr.execute();
            conn.close();
            dto = (true);

        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
            dto = false;
        }
        commit();
        return dto;

    }

}
