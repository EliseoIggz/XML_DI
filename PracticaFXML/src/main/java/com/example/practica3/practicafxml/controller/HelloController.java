package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nombreJugadorField;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    public void onBotonFacilClick() throws IOException {
        iniciarJuego(4, 3); // Configuración para modo Fácil
    }

    @FXML
    public void onBotonDificilClick() throws IOException {
        iniciarJuego(5, 4); // Configuración para modo Difícil
    }

    private void iniciarJuego(int filas, int columnas) throws IOException {
        String nombre = nombreJugadorField.getText().trim();
        if (!nombre.isEmpty()) {
            Jugador jugador = new Jugador(nombre);

            // Cargar la vista del juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/game-view.fxml"));
            Stage stage = primaryStage;
            stage.setScene(new Scene(loader.load()));

            // Configurar el controlador del juego
            GameController gameController = loader.getController();
            gameController.initGame(jugador, stage, filas, columnas);

            stage.show();
        }
    }
}
