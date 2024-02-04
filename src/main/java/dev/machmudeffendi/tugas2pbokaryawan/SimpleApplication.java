package dev.machmudeffendi.tugas2pbokaryawan;

import dev.machmudeffendi.tugas2pbokaryawan.models.dao.DatabaseDAO;
import dev.machmudeffendi.tugas2pbokaryawan.util.Alerts;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if(DatabaseDAO.isOK()){
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960, 540);
            stage.setTitle("Aplikasi Karyawan");
            stage.setScene(scene);
            stage.show();
        } else {
            Alerts.error(
                    "Database error",
                    "Could not load database",
                    "Error loading SQLite database. See log. Quitting..."
            ).showAndWait();
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}