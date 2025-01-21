package com.example.practica3.practicafxml.controller;

import com.example.practica3.practicafxml.utils.AlertView;
import javafx.scene.control.Alert;


public class AlertController {
    public void showAlert(String title, String sms, Alert.AlertType alertType){
        Alert alerta = new AlertView().addAlertView(title, sms, alertType);
        alerta.showAndWait();
    }
}
