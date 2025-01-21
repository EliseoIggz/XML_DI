package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import com.example.practica3.practicafxml.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nombreJugadorField;

    @FXML
    private Button botonFacil;

    //private Stage primaryStage;

//    public void setStage(Stage stage) {
//        this.primaryStage = stage;
//    }

    @FXML
    public void onBotonFacilClick() throws IOException {
        iniciarJuego(4, 3); // Configuración para modo Fácil
    }

    @FXML
    public void onBotonDificilClick() throws IOException {
        iniciarJuego(5, 4); // Configuración para modo Difícil
    }

    /**
     * @param stage
     * @return
     * @throws IOException
     */
    public HelloController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, "hello-view.fxml","MEMORAMA - Selección de Dificultad",300,300);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        HelloController controller = fxmlLoader.getController();

        return controller;
    }

    /**
     *
     * @param filas
     * @param columnas
     * @throws IOException
     */
    private void iniciarJuego(int filas, int columnas) throws IOException {
        String nombre = nombreJugadorField.getText().trim();
        Jugador jugador = new Jugador(nombre);
        if (jugador.validarNombre()) {
            Stage stage = new PantallaUtils().cerrarEstaPantalla(botonFacil);
            // Configurar el controlador del juego
            GameController gameController = new GameController().showEstaPantalla(stage);
            gameController.initGame(jugador, stage, filas, columnas);
            gameController.setNombreJugador();
        }else{
            new AlertController().showAlert("Nombre vacío", "Nombre vacío", Alert.AlertType.INFORMATION);
            nombreJugadorField.clear();
        }
    }
}
