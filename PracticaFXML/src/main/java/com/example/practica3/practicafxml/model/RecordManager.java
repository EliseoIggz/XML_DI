package com.example.practica3.practicafxml.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordManager implements DatabaseOperations {
    private String url = "jdbc:sqlite:data/bbddPrueba.db";
    private String sqlInsert = "INSERT INTO USER (nombre, tiempo) VALUES (?, ?)";
    private String query = "SELECT nombre, tiempo FROM USER ";


    private List<Record> records;

    public RecordManager() {
        this.records = new ArrayList<>();
        cargarRecords();
    }

    public void agregarRecord(Record record) {
        records.add(record);
        Collections.sort(records);
        guardarRecords(record);
    }

    public List<Record> getRecords() {
        return records;
    }

    public boolean guardarRecords(Record record) {
        // Conectar a la base de datos y realizar la inserción
        try (Connection conn = conectarBD()) {
            // Preparar la sentencia con los valores
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(1, record.getNombre());
                pstmt.setInt(2, record.getTiempo());
                // Ejecutar la inserción
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarRecords() {
        // Conexión a la base de datos y ejecución de la consulta
        try {
            PreparedStatement pstmt = conectarBD().prepareStatement(query);
            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int tiempo = rs.getInt("tiempo");
                records.add(new Record(nombre, tiempo));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }

    public Connection conectarBD() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }
}
