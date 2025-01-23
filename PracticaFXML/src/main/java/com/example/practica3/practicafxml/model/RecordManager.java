package com.example.practica3.practicafxml.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordManager {
    //private static final String FILE_PATH = "records.dat";
    private List<Record> records;

    public RecordManager() {
        this.records = new ArrayList<>();
        cargarRecords();
    }

    public void agregarRecord(Record record) {
        records.add(record);
        Collections.sort(records);
        guardarRecords();
    }

    public List<Record> getRecords() {
        return records;
    }

    private void guardarRecords() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
//            oos.writeObject(records);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void cargarRecords() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
//            records = (List<Record>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            records = new ArrayList<>();
//        }
    }
}
