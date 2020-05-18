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

  
    
}
