package com.example.practica3.practicafxml.model;

import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordManagerTest {
    private String url = "jdbc:sqlite:data/bbddPrueba.db";
    private String sqlInsert = "INSERT INTO USER (nombre, tiempo) VALUES (?, ?)";
    private String query = "SELECT nombre, tiempo FROM USER ";
    private Connection conn;


    private List<Record> records;

    @BeforeEach
    public Connection conectarBD() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

    @Test


}