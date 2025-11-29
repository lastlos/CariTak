package com.example.caritakip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import com.example.caritakip.service.DatabaseService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        DatabaseService.initializeDatabase();

        URL fxmlLocation = getClass().getResource("MainView.fxml");
        if (fxmlLocation == null) {
            System.err.println("FXML dosyası bulunamadı!");
            return;
        }
        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("CariTak - Cari Takip Programı");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
