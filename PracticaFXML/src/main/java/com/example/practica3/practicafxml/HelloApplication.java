package com.example.practica3.practicafxml;

import com.example.practica3.practicafxml.controller.HelloController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Método de la clase principal que inicia el programa
 * Se instancia un controlador de la primera ventana y llama al método de abrirla
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new HelloController().showEstaPantalla(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
