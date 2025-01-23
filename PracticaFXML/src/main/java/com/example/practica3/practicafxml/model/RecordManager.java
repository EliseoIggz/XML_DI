package com.example.practica3.practicafxml.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordManager {
    private static final String FILE_PATH = "records.dat";
    private List<Record> records;

    /**
     * Constructor del RecordManager con una llamada al metodo que carga los datos del .dat en la List de records
     */
    public RecordManager() {
        this.records = new ArrayList<>();
        cargarRecords();
    }

    /**
     * Metodo parqa agregar un record a la List y luego la guarda la List actualizada en el .dat
     * @param record
     */
    public void agregarRecord(Record record) {
        records.add(record);
        Collections.sort(records);
        guardarRecords();
    }

    public List<Record> getRecords() {
        return records;
    }

    /**
     * Método para guardar la List en el .dat
     */
    private void guardarRecords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener la List del .dat
     */
    private void cargarRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            records = (List<Record>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            records = new ArrayList<>();
        }
    }
}
