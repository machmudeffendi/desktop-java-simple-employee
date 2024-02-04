package dev.machmudeffendi.tugas2pbokaryawan.models;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Karyawan {
    private final int id;
    private final ReadOnlyStringProperty nip;
    private final ReadOnlyStringProperty nama;
    private final ReadOnlyStringProperty email;
    private final ReadOnlyStringProperty noTelepon;
    private final ReadOnlyStringProperty alamat;
    private final ReadOnlyStringProperty jabatan;

    public Karyawan(int id, String nip, String nama, String email, String noTelepon, String alamat, String jabatan) {
        this.id = id;
        this.nip = new SimpleStringProperty(nip);
        this.nama = new SimpleStringProperty(nama);
        this.email = new SimpleStringProperty(email);
        this.noTelepon = new SimpleStringProperty(noTelepon);
        this.alamat = new SimpleStringProperty(alamat);
        this.jabatan = new SimpleStringProperty(jabatan);
    }

    public int getId() {
        return id;
    }

    public String getNip() {
        return nip.get();
    }

    public ReadOnlyStringProperty nipProperty() {
        return nip;
    }

    public String getNama() {
        return nama.get();
    }

    public ReadOnlyStringProperty namaProperty() {
        return nama;
    }

    public String getEmail() {
        return email.get();
    }

    public ReadOnlyStringProperty emailProperty() {
        return email;
    }

    public String getNoTelepon() {
        return noTelepon.get();
    }

    public ReadOnlyStringProperty noTeleponProperty() {
        return noTelepon;
    }

    public String getAlamat() {
        return alamat.get();
    }

    public ReadOnlyStringProperty alamatProperty() {
        return alamat;
    }

    public String getJabatan() {
        return jabatan.get();
    }

    public ReadOnlyStringProperty jabatanProperty() {
        return jabatan;
    }
}
