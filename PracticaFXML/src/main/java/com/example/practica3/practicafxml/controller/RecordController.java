package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import com.example.practica3.practicafxml.model.Record;
import com.example.practica3.practicafxml.model.RecordManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class RecordController {
    @FXML
    private Label resultadoLabel;
    @FXML
    private ListView<String> recordsListView;

    private RecordManager recordManager = new RecordManager();

    public void showResult(Jugador jugador, boolean ganado) {
        if (ganado) {
            int tiempoFinal = jugador.getTiempo();
            resultadoLabel.setText("¡Ganaste! Tiempo: " + tiempoFinal + " segundos");

            // Agregar record y actualizar la lista
            recordManager.agregarRecord(new Record(jugador.getNombre(), tiempoFinal));
            actualizarRecords();
        } else {
            resultadoLabel.setText("¡Perdiste! Inténtalo de nuevo.");
        }
    }

    private void actualizarRecords() {
        recordsListView.getItems().clear();
        for (Record record : recordManager.getRecords()) {
            recordsListView.getItems().add(record.toString());
        }
    }
}
