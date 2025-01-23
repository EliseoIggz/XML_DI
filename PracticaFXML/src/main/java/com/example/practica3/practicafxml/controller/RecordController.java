package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.model.Jugador;
import com.example.practica3.practicafxml.model.Record;
import com.example.practica3.practicafxml.model.RecordManager;
import com.example.practica3.practicafxml.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class RecordController {
    @FXML
    private Label resultadoLabel;
    @FXML
    private ListView<String> recordsListView;

    private RecordManager recordManager = new RecordManager();

    /**
     * Método para setear el mensaje del Label y agregar el objeto record a la List
     * @param jugador
     * @param ganado
     */
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


    /**
     * Método para limpiar la List y volcar los nuevos datos actualizados
     */
    private void actualizarRecords() {
        recordsListView.getItems().clear();
        for (Record record : recordManager.getRecords()) {
            recordsListView.getItems().add(record.toString());
        }
    }

    /**
     * Método para mostrar la pantalla de records
     * @param stage
     * @return
     * @throws IOException
     */
    public RecordController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, "record-view.fxml","Records",400,400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        RecordController controller = fxmlLoader.getController();
        return controller;
    }
}
