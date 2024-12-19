package com.example.practica3.practicafxml;

import com.example.practica3.practicafxml.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/hello-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("MEMORAMA - Selección de Dificultad");

        // Pasar el Stage al controlador
        HelloController controller = loader.getController();
        controller.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
