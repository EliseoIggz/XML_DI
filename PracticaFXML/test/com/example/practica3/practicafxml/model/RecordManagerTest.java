package com.example.practica3.practicafxml.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RecordManagerTest {
    private String url = "jdbc:sqlite:data/bbddPrueba.db";
    private Connection conn;
    private RecordManager recordManager;

    @BeforeEach
    public void conectarBD() throws SQLException {
        conn  = DriverManager.getConnection(url);
        recordManager = new RecordManager();
    }

    @Test
    public void testGuardarRecords() {
        Record record = new Record("Juan", 15);
        boolean insercionExitosa = recordManager.guardarRecords(record);

        if (insercionExitosa) {
            System.out.println("El record fue insertado correctamente.");
        } else {
            System.out.println("Error al insertar el record.");
        }
    }

    @Test
    public void testCargarRecords() {
        boolean cargaExitosa = recordManager.cargarRecords();

        if (cargaExitosa) {
            System.out.println("Los records se cargaron correctamente desde la base de datos.");
        } else {
            System.out.println("Hubo un error al cargar los records.");
        }

        assertTrue(!recordManager.getRecords().isEmpty(),"Se cargaron " + recordManager.getRecords().size() + " records.");
    }

    @AfterEach
    public void cerrarBD() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}