package com.example.practica3.practicafxml.utils;

import javafx.scene.control.Alert;

public class AlertView {
    public Alert addAlertView(String title, String mensaje, Alert.AlertType alertType) {
        Alert alerta = new Alert(alertType);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        return alerta;
    }
}
