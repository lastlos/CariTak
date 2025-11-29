package com.example.caritakip;

import com.example.caritakip.model.Cari;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.UUID;

public class CariDialogController {

    @FXML private TextField adSoyadField;
    @FXML private TextField firmaAdiField;
    @FXML private TextField telefonField;

    private String cariId;

    public void setCari(Cari cari) {
        adSoyadField.setText(cari.getAdSoyad());
        firmaAdiField.setText(cari.getFirmaAdi());
        telefonField.setText(cari.getTelefon());
        this.cariId = cari.getId();
    }

    public Cari getNewCari() {
        String adSoyad = adSoyadField.getText();
        String firmaAdi = firmaAdiField.getText();
        String telefon = telefonField.getText();
        
        if (cariId == null) {
            cariId = UUID.randomUUID().toString();
        }
        
        return new Cari(cariId, adSoyad, firmaAdi, telefon, 0);
    }

    public boolean validateInput() {
        String adSoyad = adSoyadField.getText();

        if (adSoyad == null || adSoyad.trim().isEmpty()) {
            showAlert("Geçersiz Girdi", "Ad Soyad alanı boş bırakılamaz.");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
