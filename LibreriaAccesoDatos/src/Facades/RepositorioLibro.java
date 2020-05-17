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
/**
 *
 * @author USER
 */
public class RepositorioLibro implements IGestionLibro{

    @Override
    public void agregarLibro(Libro libro) {
        String SQl = "INSERT into books (isbn,precioBase,unidadesDisponibles,numeroImagenes,numeroVideos,Titulo) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)){
            pr.setString(1, libro.getIsbn());
            pr.setDouble(2, libro.getPrecioBase());
            pr.setInt(3, libro.getUnidadDisponibles());
            pr.setInt(4, libro.getNumeroImagenes());
            pr.setInt(5, libro.getNumeroVideos());
            pr.setString(6, libro.getTitulo());
        } catch (Exception e) {
        }
    }
    
    
}
