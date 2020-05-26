/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applibreria;

import Enums.Denominacion;
import Facades.FacadeLibreria;
import Interfaces.IFacadeLibreria;
import entities.Libro;
import entities.Linea;
import entities.DtoResumen;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PantallaLibreriaController implements Initializable {

    IFacadeLibreria facadeLibreria = new FacadeLibreria();
    private final ObservableList<Libro> ListaLibrosObservable = FXCollections.observableArrayList();
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
    private TableView<Libro> tablaAgregar;
    @FXML
    private TableColumn<Libro, String> tableIsbnAgregar = new TableColumn<>("Isbn");
    @FXML
    private TableColumn<Libro, String> tableTituloAgregar = new TableColumn<>("Titulo");
    @FXML
    private AnchorPane BotonValorDenominacion;
    @FXML
    private Text texto1KL;
    @FXML
    private Button BotonNuevoPrestamo;
    @FXML
    private Text TextoLocalDate;
    @FXML
    private Text TextoNumeroPrestamo;
    @FXML
    private ComboBox<String> ComboboxSeleccionLibros;
    @FXML
    private TextField TextCant;
    @FXML
    private Button BotonAgregarLinea;
    @FXML
    private TableView<Linea> TablaLineasDelPrestamo;
    @FXML
    private TableColumn<Linea, String> ColumnaLibro = new TableColumn<>("titulo");
    @FXML
    private TableColumn<Linea, Integer> ColumnaCantidad = new TableColumn<>("cantidad");
    @FXML
    private TableColumn<Linea, Double> ColumnaPrecioLibro = new TableColumn<>("precioBase");
    @FXML
    private TableColumn<Linea, Double> ColumnaSubTotal = new TableColumn<>("subTotal");
    @FXML
    private Text TextoTotalPrestamo;
    @FXML
    private TextField TextCantMonedas;
    @FXML
    private ComboBox<Denominacion> ComboboxDenominacion;
    @FXML
    private Button BotonAgregarMonedas;
    @FXML
    private Text TextoSaldoDispMonedas;
    @FXML
    private Text TextoVueltos;
    @FXML
    private Button BotonTerminarPrestamo;
    @FXML
    private Button BotonGenerarReporte;
    @FXML
    private Button botonEliminar;
    @FXML
    private Text textoCantiLineas;
    @FXML
    private Text textoExito;

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
            JOptionPane.showMessageDialog(null, "Valores incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        txtIsbn.clear();
        txtNumeroImagenes.clear();
        txtNumeroVideos.clear();
        txtPrecio.clear();
        txtUnidadesDisponibles.clear();
        txtTitulo.clear();

        llenarCampos();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //facadeLibreria.cargarLibros();
        llenarCampos();
        resetAll();

    }

    private void llenarCampos() {
        tablaAgregar.getItems().clear();

        for (Libro l : facadeLibreria.consultarLibros()) {
            tablaAgregar.getItems().add(l);
            ComboboxSeleccionLibros.getItems().add(l.getTitulo());
        }
        ComboboxDenominacion.getItems().addAll(Denominacion.MIL, Denominacion.QUIENTOS);
    }

    @FXML
    private void ManejadorBotonNuevoPrestamo(ActionEvent event) {
        BotonAgregarLinea.setDisable(false);
        BotonAgregarMonedas.setDisable(false);
        BotonGenerarReporte.setDisable(false);
        botonEliminar.setDisable(false);
        if (facadeLibreria.crearNuevoPrestamo()) {
            TextoLocalDate.setText(facadeLibreria.getPrestamoActual().getFecha().toString());
            String numero = Integer.toString(facadeLibreria.getPrestamoActual().getNumero());
            TextoNumeroPrestamo.setText(numero);

        } else {
            JOptionPane.showMessageDialog(null, "no se puede iniciar nuevo prestamo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void llenarCamposPrestamo() {
        TablaLineasDelPrestamo.getItems().clear();

        for (Linea l : facadeLibreria.getPrestamoActual().getLineas()) {
            TablaLineasDelPrestamo.getItems().add(l);

        }

    }

    @FXML
    private void ManejadorBotonAgregarLinea(ActionEvent event) {

        DtoResumen res = new DtoResumen();
        try {
            String titulo = ComboboxSeleccionLibros.getSelectionModel().getSelectedItem().toString();
            if (!TextCant.getText().isEmpty() && titulo != null) {
                int catidad = Integer.parseInt(TextCant.getText());
                for (Libro l : facadeLibreria.consultarLibros()) {
                    if (l.getTitulo() == titulo) {
                        res = facadeLibreria.agregarLinea(l, catidad);
                        TextoTotalPrestamo.setText(Double.toString(res.getTotal()));
                        textoCantiLineas.setText(Integer.toString(res.getTama()));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cantidad incompleta", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (!res.isAgregar()) {
                JOptionPane.showMessageDialog(null, res.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Caracter invalido y/o lirbro no seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        llenarCamposPrestamo();
        if (facadeLibreria.getPrestamoActual().getLineas().size() != 0) {
            BotonTerminarPrestamo.setDisable(false);
        }
        reset();
    }

    @FXML
    private void ManejadorBotonAgregarMonedas(ActionEvent event) {
        Denominacion d = ComboboxDenominacion.getSelectionModel().getSelectedItem();
        int cantidad;
        DtoResumen dto = new DtoResumen();
        try {
            cantidad = Integer.parseInt(TextCantMonedas.getText());
            System.err.println(cantidad);
            dto = facadeLibreria.agregarMoneda(d, cantidad);
            if (!dto.isAgregar()) {
                JOptionPane.showMessageDialog(null, dto.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                textoExito.setFill(Paint.valueOf("#c10909"));
                textoExito.setText("Error");
            } else {
                textoExito.setFill(Paint.valueOf("#00b524"));
                textoExito.setText("Exito");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Solo se aceptan enteros", "Error", JOptionPane.ERROR_MESSAGE);
            textoExito.setFill(Paint.valueOf("#c10909"));
            textoExito.setText("Error");
        }
        TextoSaldoDispMonedas.setText("$" + Double.toString(dto.getSaldo()));
    }

    @FXML
    private void ManejadorBotonTerminarPrestamo(ActionEvent event) {
        DtoResumen dto = facadeLibreria.terminarPrestamo();
        if (dto.isAgregar()) {
            resetAll();
            JOptionPane.showMessageDialog(null, "Su devuelta es de " + dto.getDevuelta());
        } else {
            JOptionPane.showMessageDialog(null, dto.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void ManejadorBotonGenerarReporte(ActionEvent event) {

    }

    @FXML
    private void ManejadorBotonEliminar(ActionEvent event) {
        Linea l = TablaLineasDelPrestamo.getSelectionModel().getSelectedItem();
        DtoResumen dto = new DtoResumen();
        dto = facadeLibreria.eliminarLinea(l);
        if (dto.isAgregar()) {

            textoCantiLineas.setText(Integer.toString(dto.getTama()));
            TextoTotalPrestamo.setText(Double.toString(dto.getTotal()));
            llenarCamposPrestamo();
        } else {
            JOptionPane.showMessageDialog(null, dto.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void resetAll() {
        textoExito.setText(" ");
        ComboboxDenominacion.setValue(null);
        TextCantMonedas.setText("0");
        ComboboxSeleccionLibros.setValue(null);
        TextCant.setText(null);
        TextoLocalDate.setText("2020-XX-XXTXX:XX:XX.XXX");
        textoCantiLineas.setText("0");
        TextoTotalPrestamo.setText("0.0");
        TablaLineasDelPrestamo.getItems().clear();
        TextoSaldoDispMonedas.setText("$0");

    }

    private void reset() {
        textoExito.setText(" ");
        ComboboxDenominacion.setValue(null);
        TextCantMonedas.setText("0");
        ComboboxSeleccionLibros.setValue(null);
        TextCant.setText(null);

    }
}
