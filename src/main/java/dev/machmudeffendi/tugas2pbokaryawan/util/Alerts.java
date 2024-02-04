package dev.machmudeffendi.tugas2pbokaryawan.util;

import javafx.scene.control.Alert;

public class Alerts {
    public static Alert error(String windowTitle, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(windowTitle);
        alert.setHeaderText(header);
        alert.setContentText(description);
        return alert;
    }
}
