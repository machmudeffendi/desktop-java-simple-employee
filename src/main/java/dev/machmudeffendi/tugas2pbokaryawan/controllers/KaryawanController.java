package dev.machmudeffendi.tugas2pbokaryawan.controllers;

import dev.machmudeffendi.tugas2pbokaryawan.models.Karyawan;
import dev.machmudeffendi.tugas2pbokaryawan.models.dao.KaryawanDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KaryawanController {
    @FXML
    private TextField nipfield;
    @FXML
    private TextField namafield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField noteleponfield;
    @FXML
    private TextField alamatfield;
    @FXML
    private TextField jabatanfield;
    @FXML
    private TableView<Karyawan> karyawanTable;
    @FXML
    private TableColumn<Karyawan, String> nipColumn;
    @FXML
    private TableColumn<Karyawan, String> namaColumn;
    @FXML
    private TableColumn<Karyawan, String> emailColumn;
    @FXML
    private TableColumn<Karyawan, String> noTeleponColumn;
    @FXML
    private TableColumn<Karyawan, String> alamatColumn;
    @FXML
    private TableColumn<Karyawan, String> jabatanColumn;

    public void initialize() {
        karyawanTable.setItems(KaryawanDAO.getKaryawan());

        nipColumn.setCellValueFactory(new PropertyValueFactory<>("nip"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        noTeleponColumn.setCellValueFactory(new PropertyValueFactory<>("noTelepon"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        jabatanColumn.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
    }

    @FXML
    protected void onSimpanClick(){
        KaryawanDAO.insert(
                nipfield.getText(),
                namafield.getText(),
                emailfield.getText(),
                noteleponfield.getText(),
                alamatfield.getText(),
                jabatanfield.getText()
        );

        nipfield.clear();
        namafield.clear();
        emailfield.clear();
        noteleponfield.clear();
        alamatfield.clear();
        jabatanfield.clear();
    }
}