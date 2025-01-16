package com.example.practica3.practicafxml;

import com.example.practica3.practicafxml.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica3/practicafxml/hello-view.fxml"));
//        Scene scene = new Scene(loader.load());
//        scene.getStylesheets().add(getClass().getResource("/com/example/practica3/practicafxml/styles.css").toExternalForm());
//        stage.setScene(scene);
//        stage.setTitle("MEMORAMA - Selección de Dificultad");
//        HelloController controller = loader.getController();
//        controller.setStage(stage);
//        stage.show();

        new HelloController().showEstaPantalla(stage);
        //controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
