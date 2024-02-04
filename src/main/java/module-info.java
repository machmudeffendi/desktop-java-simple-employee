module dev.machmudeffendi.tugas2pbokaryawan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires com.dlsc.formsfx;

    opens dev.machmudeffendi.tugas2pbokaryawan to javafx.fxml;
    opens dev.machmudeffendi.tugas2pbokaryawan.models to javafx.base;
    exports dev.machmudeffendi.tugas2pbokaryawan;
    exports dev.machmudeffendi.tugas2pbokaryawan.controllers;
    opens dev.machmudeffendi.tugas2pbokaryawan.controllers to javafx.fxml;
}