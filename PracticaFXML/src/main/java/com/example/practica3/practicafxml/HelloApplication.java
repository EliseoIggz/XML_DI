package com.example.practica3.practicafxml;

import com.example.practica3.practicafxml.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new HelloController().showEstaPantalla(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
