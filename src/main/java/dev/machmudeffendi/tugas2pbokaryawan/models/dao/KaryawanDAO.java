package dev.machmudeffendi.tugas2pbokaryawan.models.dao;

import dev.machmudeffendi.tugas2pbokaryawan.models.Karyawan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KaryawanDAO {
    private static final String tableName = "Karyawan";
    private static final String idColumn = "id";
    private static final String nipColumn = "nip";
    private static final String namaColumn = "nama";
    private static final String emailColumn = "email";
    private static final String noTeleponColumn = "noTelepon";
    private static final String alamatColumn = "alamat";
    private static final String jabatanColumn = "jabatan";

    private static final ObservableList<Karyawan> karyawan;

    static {
        karyawan = FXCollections.observableArrayList();
        try {
            updateKaryawanFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Karyawan> getKaryawan(){
        return FXCollections.unmodifiableObservableList(karyawan);
    }
    private static void updateKaryawanFromDB() throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try(Connection connection = DatabaseDAO.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            karyawan.clear();
            while (rs.next()){
                karyawan.add(new Karyawan(
                        rs.getInt(idColumn),
                        rs.getString(nipColumn),
                        rs.getString(namaColumn),
                        rs.getString(emailColumn),
                        rs.getString(noTeleponColumn),
                        rs.getString(alamatColumn),
                        rs.getString(jabatanColumn)
                ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ":  Tidak mendapatkan data karyawan dari database "
            );
            karyawan.clear();
        }
    }

    public static void update(Karyawan newKaryawan) {
        int rows = CRUDHelperDAO.update(
                tableName,
                new String[]{nipColumn, namaColumn, emailColumn, noTeleponColumn, alamatColumn, jabatanColumn},
                new Object[]{newKaryawan.getNip(), newKaryawan.getNama(), newKaryawan.getEmail(), newKaryawan.getNoTelepon(), newKaryawan.getAlamat(), newKaryawan.getJabatan()},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR},
                idColumn,
                Types.INTEGER,
                newKaryawan.getId()
        );

        if(rows == 0)
            throw new IllegalStateException("Karyawan tobe updated with id " + newKaryawan.getId() + " didn't exist in database");

        Optional<Karyawan> optionalKaryawan = getKaryawan(newKaryawan.getId());
        optionalKaryawan.ifPresentOrElse((oldKaryawan) -> {
            karyawan.remove(oldKaryawan);
            karyawan.add(newKaryawan);
        }, () -> {
            throw new IllegalStateException("Person to be updated with id "+ newKaryawan.getId() + " didn't exist in database");
        });
    }

    public static void insert(String nip, String name, String email, String noTelepon, String alamat, String jabatan) {
        int id = (int) CRUDHelperDAO.create(
                tableName,
                new String[]{nipColumn, namaColumn, emailColumn, noTeleponColumn, alamatColumn, jabatanColumn},
                new Object[]{nip, name, email, noTelepon, alamat, jabatan},
                new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}
        );

        karyawan.add(new Karyawan(
                id, nip, name, email, noTelepon, alamat, jabatan
        ));
    }

    public static void delete(int id){
        CRUDHelperDAO.delete(tableName, id);

        Optional<Karyawan> karyawan1 = getKaryawan(id);
        karyawan1.ifPresent(karyawan::remove);
    }

    public static Optional<Karyawan> getKaryawan(int id){
        for (Karyawan karyawan1 : karyawan){
            if(karyawan1.getId() == id) return Optional.of(karyawan1);
        }
        return Optional.empty();
    }
}
