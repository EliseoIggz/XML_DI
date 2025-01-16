package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import com.example.practica3.practicafxml.utils.PantallaUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GameController {
    @FXML
    private Label timerLabel;
    @FXML
    private GridPane gameBoard;
    @FXML
    private Label labelJugador;
    private Jugador jugador;
    private Stage stage;
    private Timer timer;
    private int tiempoRestante = 45; // Segundos del temporizador
    private int filas;
    private int columnas;

    public void initGame(Jugador jugador, Stage stage, int filas, int columnas) {
        this.jugador = jugador;
        this.stage = stage;
        this.filas = filas;
        this.columnas = columnas;

        initializeBoard();
    }

    public void setNombreJugador(){
        labelJugador.setText(labelJugador.getText() + jugador.getNombre());
    }

    public GameController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, "game-view.fxml","Memorama - Juego",300,400);

        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        GameController controller = fxmlLoader.getController();

        return controller;
    }

    private void initializeBoard() {
        gameBoard.getChildren().clear(); // Limpiar tablero si se reutiliza

        // Generar una lista de imágenes duplicadas para formar los pares
        List<Image> images = new ArrayList<>();
        for (int i = 1; i <= (filas * columnas) / 2; i++) {
            Image image = new Image(getClass().getResourceAsStream("/images/img" + i + ".png"));
            images.add(image);
            images.add(image); // Añadir la pareja
        }

        // Mezclar las imágenes aleatoriamente
        Collections.shuffle(images);

        // Inicializar el tablero con las imágenes tapadas
        Button[][] buttons = new Button[filas][columnas];
        ImageView[][] imageViews = new ImageView[filas][columnas];
        boolean[][] revealed = new boolean[filas][columnas]; // Control de las cartas descubiertas

        for (int i = 0, index = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++, index++) {
                final int row = i; // Capturar valor de i
                final int col = j; // Capturar valor de j
                Button card = new Button();
                card.setPrefSize(80, 80);

                // Imagen asociada al botón
                ImageView imageView = new ImageView(images.get(index));
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
                imageView.setVisible(false); // Ocultar la imagen inicialmente
                imageViews[i][j] = imageView;

                card.setGraphic(imageView);
                card.setOnAction(e -> {
                    handleCardClick(card, imageView, row, col, revealed);
                });

                gameBoard.add(card, j, i);
                // Añadimos a la matriz de botones el boton que hemos configurada con todo listo
                buttons[i][j] = card;
            }
        }

        // Iniciar el temporizador al cargar el tablero
        startTimer();
    }

    // Control de clic en las cartas
    private Button firstCard = null;
    private ImageView firstImageView = null;
    private int firstRow = -1, firstCol = -1;
    private void handleCardClick(Button card,
                                 ImageView imageView,
                                 int row,
                                 int col,
//                                 Button[][] buttons,
//                                 ImageView[][] imageViews,
                                 boolean[][] revealed) {

        if (revealed[row][col] || card == firstCard) {

            return; // Si ya está descubierta o es la misma carta, ignorar
        }
        // Mostrar la carta
        imageView.setVisible(true);
        card.setStyle("-fx-background-color: white;");

        if (firstCard == null) {
            // Primer clic
            firstCard = card;
            firstImageView = imageView;
            firstRow = row;
            firstCol = col;
        } else {
            // Segundo clic
            if (imageView.getImage().equals(firstImageView.getImage())) {
                // Si coinciden, mantenerlas descubiertas
                revealed[row][col] = true;
                revealed[firstRow][firstCol] = true;
                firstCard = null;
                firstImageView = null;
                firstRow = -1;
                firstCol = -1;

                // Comprobar si el juego ha terminado
                if (isGameWon(revealed)) {
                    endGame(true);
                }
            } else {
                // Si no coinciden, esperar y volver a taparlas
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            imageView.setVisible(false);
                            card.setStyle("-fx-background-color: #d6d6d6;");
                            firstImageView.setVisible(false);
                            firstCard.setStyle("-fx-background-color: #d6d6d6;");
                            // Reiniciar el estado del primer clic
                            firstCard = null;
                            firstImageView = null;
                            firstRow = -1;
                            firstCol = -1;
                        });
                    }
                }, 1000); // Esperar 1 segundo
            }
        }
    }

    // Método para comprobar si todas las cartas están descubiertas
    private boolean isGameWon(boolean[][] revealed) {
        for (boolean[] row : revealed) {
            for (boolean cell : row) {
                if (!cell) {
                    return false;
                }
            }
        }
        return true;
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
        // Detener el temporizador si todavía está activo
        if (timer != null) {
            timer.cancel();
        }
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/record-view.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/com/example/practica3/practicafxml/styles.css").toExternalForm());

                stage.setScene(scene);

                RecordController recordController = loader.getController();
                if (ganado) {
                    jugador.setTiempo(45 - tiempoRestante); // Calcular tiempo total
                }
                recordController.showResult(jugador, ganado);

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
