package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import com.example.practica3.practicafxml.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    public HelloController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, "hello-view.fxml","MEMORAMA - Selección de Dificultad",300,300);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        HelloController controller = fxmlLoader.getController();

        return controller;
    }

    private void iniciarJuego(int filas, int columnas) throws IOException {
        String nombre = nombreJugadorField.getText().trim();
        if (!nombre.isEmpty()) {
            Jugador jugador = new Jugador(nombre);
            Stage stage = new PantallaUtils().cerrarEstaPantalla(botonFacil);
//            // Cargar la vista del juego
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/game-view.fxml"));
//            //Stage stage = primaryStage;
//            Scene scene = new Scene(loader.load());
//            scene.getStylesheets().add(getClass().getResource("/com/example/practica3/practicafxml/styles.css").toExternalForm());
//
//            stage.setScene(scene);

            // Configurar el controlador del juego
            GameController gameController = new GameController().showEstaPantalla(stage);

            gameController.initGame(jugador, stage, filas, columnas);
            gameController.setNombreJugador();

            //stage.show();
        }
    }
}
