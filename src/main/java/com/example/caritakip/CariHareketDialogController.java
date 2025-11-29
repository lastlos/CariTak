package com.example.caritakip;

import com.example.caritakip.model.CariHareket;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CariHareketDialogController {

    @FXML private DatePicker tarihPicker;
    @FXML private TextField aciklamaField;
    @FXML private TextField borcField;
    @FXML private TextField alacakField;

    private String cariId;

    public void setCariId(String cariId) {
        this.cariId = cariId;
        tarihPicker.setValue(LocalDate.now());
    }

    public CariHareket getNewHareket() {
        LocalDate tarih = tarihPicker.getValue();
        String aciklama = aciklamaField.getText();

        double borc = 0.0;
        if (borcField.getText() != null && !borcField.getText().trim().isEmpty()) {
            borc = Double.parseDouble(borcField.getText().replace(',', '.'));
        }

        double alacak = 0.0;
        if (alacakField.getText() != null && !alacakField.getText().trim().isEmpty()) {
            alacak = Double.parseDouble(alacakField.getText().replace(',', '.'));
        }
        
        return new CariHareket(0, cariId, tarih, aciklama, borc, alacak);
    }

    public boolean validateInput() {
        if (tarihPicker.getValue() == null) {
            showAlert("Geçersiz Girdi", "Tarih alanı boş bırakılamaz.");
            return false;
        }

        String borcStr = borcField.getText();
        String alacakStr = alacakField.getText();

        boolean borcEmpty = borcStr == null || borcStr.trim().isEmpty();
        boolean alacakEmpty = alacakStr == null || alacakStr.trim().isEmpty();

        if (borcEmpty && alacakEmpty) {
            showAlert("Geçersiz Girdi", "Borç veya Alacak alanlarından en az biri doldurulmalıdır.");
            return false;
        }
        
        if (!borcEmpty && !alacakEmpty) {
            showAlert("Geçersiz Girdi", "Borç ve Alacak alanları aynı anda doldurulamaz.");
            return false;
        }

        try {
            if (!borcEmpty) Double.parseDouble(borcStr.replace(',', '.'));
            if (!alacakEmpty) Double.parseDouble(alacakStr.replace(',', '.'));
        } catch (NumberFormatException e) {
            showAlert("Geçersiz Girdi", "Borç ve Alacak alanlarına geçerli bir sayı girilmelidir.");
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
