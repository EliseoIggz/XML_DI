module com.example.practica3.practicafxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires kernel;
    requires layout;

    opens com.example.practica3.practicafxml to javafx.fxml;
    exports com.example.practica3.practicafxml;
    exports com.example.practica3.practicafxml.controller;
    opens com.example.practica3.practicafxml.controller to javafx.fxml;
    exports com.example.practica3.practicafxml.model;
    opens com.example.practica3.practicafxml.model to javafx.fxml;
}