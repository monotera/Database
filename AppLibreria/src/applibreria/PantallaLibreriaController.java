/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applibreria;

import Facades.FacadeLibreria;
import Interfaces.IFacadeLibreria;
import entities.Libro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PantallaLibreriaController implements Initializable {

    IFacadeLibreria facadeLibreria = new FacadeLibreria();
    @FXML
    private Label label;
    @FXML
    private Button buttonAgregarLibro;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtUnidadesDisponibles;
    @FXML
    private TextField txtNumeroImagenes;
    @FXML
    private TextField txtNumeroVideos;
    @FXML
    private TextField txtPrecio;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Libro nuevoLibro = new Libro();
        try {
            nuevoLibro.setIsbn(txtIsbn.getText());
            nuevoLibro.setNumeroImagenes(Integer.parseInt(txtNumeroImagenes.getText()));
            nuevoLibro.setNumeroVideos(Integer.parseInt(txtNumeroVideos.getText()));
            nuevoLibro.setTitulo(txtTitulo.getText());
            nuevoLibro.setUnidadDisponibles(Integer.parseInt(txtUnidadesDisponibles.getText()));
            nuevoLibro.setPrecioBase(Double.parseDouble(txtPrecio.getText()));

            facadeLibreria.agregarLibro(nuevoLibro);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valores incorrectos", "Error", JOptionPane.WARNING_MESSAGE);
        }

        txtIsbn.clear();
        txtNumeroImagenes.clear();
        txtNumeroVideos.clear();
        txtPrecio.clear();
        txtUnidadesDisponibles.clear();
        txtPrecio.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
