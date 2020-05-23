/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Intefaces.IGestionPrestamo;
import constants.constante;
import entities.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class RepositorioPrestamo implements IGestionPrestamo{

    @Override
    public void PersistirPrestamo(Prestamo prestamo) {
        String SQl = "insert into PRESTAMO (NUMERO,FECHA) VALUES (sequiencia_Prestamo.NEXTVAL,?);";
        System.err.println("Insertadno datos");
       
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement pr = conn.prepareStatement(SQl)){
            pr.setDate(1, Date.valueOf(prestamo.getFecha().toLocalDate()));
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
    }

    @Override
    public ArrayList<Prestamo> cargarPrestamos() {
        String SQl = "select * from PRESTAMO";
        System.err.println("Insertadno datos");
       
       ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                
                p.setFecha(LocalDateTime.parse(rs.getDate("FECHA").toString()));
                p.setNumero(rs.getInt("NUMERO"));
                prestamos.add(p);
            }
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        String SQL = "select id,cantidad,ISBNlibro,NumeroPrestamo from linea, Prestamo where numeroprestamo = numero";
        try (Connection conn = DriverManager.getConnection(constante.THINCONN,constante.USERNAME,constante.PASSWORD);
                PreparedStatement ps = conn.prepareStatement(SQl);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                
                p.setFecha(LocalDateTime.parse(rs.getDate("FECHA").toString()));
                p.setNumero(rs.getInt("NUMERO"));
                prestamos.add(p);
            }
        } catch (Exception ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();

        }
        
        
        return prestamos;
    }
    
}
