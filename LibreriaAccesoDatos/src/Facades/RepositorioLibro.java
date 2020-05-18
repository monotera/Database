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
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class RepositorioLibro implements IGestionLibro{

    @Override
    public void agregarLibro(Libro libro) {
       String SQl = "INSERT into books (isbn,precioBase,unidadesDisponibles,numeroImagenes,numeroVideos,Titulo) VALUES (?,?,?,?,?,?)";
        System.err.println("Insertadno datos");
       
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)){
            pr.setString(1, libro.getIsbn());
            pr.setDouble(2, libro.getPrecioBase());
            pr.setInt(3, libro.getUnidadDisponibles());
            pr.setInt(4, libro.getNumeroImagenes());
            pr.setInt(5, libro.getNumeroVideos());
            pr.setString(6, libro.getTitulo());
            pr.execute();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
    }

    @Override
    public ArrayList<Libro> consultarLibros() {
        String SQl = "select * from BOOKS";
        System.err.println("Insertadno datos");
       Libro l = new Libro();
       ArrayList<Libro> libros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                l.setIsbn(rs.getString("ISB"));
                l.setNumeroImagenes(rs.getInt("NUMEROIMAGENES"));
                l.setPrecioBase(rs.getDouble("PRECIOBASE"));
                l.setTitulo(rs.getString("Titulo"));
                l.setNumeroVideos(rs.getInt("NumeroImages"));
                l.setUnidadDisponibles(rs.getInt("UNIDADESDISPONIBLES"));
                libros.add(l);
                
            }
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        return libros;
    }
    
    
}
