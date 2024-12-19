package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    @FXML
    private Label timerLabel;
    @FXML
    private GridPane gameBoard;

    private Jugador jugador;
    private Stage stage;
    private Timer timer;
    private int tiempoRestante = 30; // Segundos del temporizador
    private int filas;
    private int columnas;

    public void initGame(Jugador jugador, Stage stage, int filas, int columnas) {
        this.jugador = jugador;
        this.stage = stage;
        this.filas = filas;
        this.columnas = columnas;

        initializeBoard();
        startTimer();
    }

    private void initializeBoard() {
        gameBoard.getChildren().clear(); // Limpiar tablero si se reutiliza
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Button card = new Button("?");
                card.setPrefSize(80, 80);
                card.setOnAction(e -> {
                    // Lógica para girar cartas
                });
                gameBoard.add(card, j, i);
            }
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (tiempoRestante > 0) {
                        timerLabel.setText("Tiempo: " + tiempoRestante);
                        tiempoRestante--;
                    } else {
                        timer.cancel();
                        endGame(false);
                    }
                });
            }
        }, 0, 1000);
    }

    private void endGame(boolean ganado) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/record-view.fxml"));
                stage.setScene(new Scene(loader.load()));

                RecordController recordController = loader.getController();
                if (ganado) {
                    jugador.setTiempo(30 - tiempoRestante); // Calcular tiempo total
                }
                recordController.showResult(jugador, ganado);

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
