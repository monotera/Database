/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Intefaces.IGestionLibro;
import entities.Libro;
import java.sql.Connection;
import java.sql.DriverManager;
import constants.constante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class RepositorioLibro implements IGestionLibro {

    
    private void commit() {
        String SQl = "commit";

        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)) {

            pr.execute();
            conn.close();
            System.err.println("Commit ejecutado");

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }

    }
    @Override
    public void agregarLibro(Libro libro) {
        String SQl = "INSERT into books (isbn,precioBase,unidadesDisponibles,numeroImagenes,numeroVideos,Titulo) VALUES (?,?,?,?,?,?)";
        System.err.println("Insertadno datos");

        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)) {
            pr.setString(1, libro.getIsbn());
            pr.setDouble(2, libro.getPrecioBase());
            pr.setInt(3, libro.getUnidadDisponibles());
            pr.setInt(4, libro.getNumeroImagenes());
            pr.setInt(5, libro.getNumeroVideos());
            pr.setString(6, libro.getTitulo());
            pr.execute();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        commit();
    }

    @Override
    public ArrayList<Libro> CargarLibros() {
        String SQl = "select * from BOOKS";
        System.err.println("Insertando datos");

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
                libros.add(l);

            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        return libros;
    }

    @Override
    public Libro buscarLibro(String Isbn) {
        String SQl = "select * from BOOKS where ISBN = ?";
        System.err.println("Buscando Lirbro");
        Libro l = new Libro();
        try (Connection conn = DriverManager.getConnection(constante.THINCONN, constante.USERNAME, constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);) {
            ps.setString(1, Isbn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                l.setIsbn(rs.getString("ISBN"));
                l.setNumeroImagenes(rs.getInt("NUMEROIMAGENES"));
                l.setPrecioBase(rs.getDouble("PRECIOBASE"));
                l.setTitulo(rs.getString("TITULO"));
                l.setNumeroVideos(rs.getInt("NUMEROIMAGENES"));
                l.setUnidadDisponibles(rs.getInt("UNIDADESDISPONIBLES"));
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        return l;
    }

}
