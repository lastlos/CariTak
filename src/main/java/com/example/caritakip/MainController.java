package com.example.caritakip;

import com.example.caritakip.model.Cari;
import com.example.caritakip.model.CariHareket;
import com.example.caritakip.service.CariHareketService;
import com.example.caritakip.service.CariService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class MainController {

    @FXML private TableView<Cari> cariTable;
    @FXML private TableColumn<Cari, String> adSoyadColumn;
    @FXML private TableColumn<Cari, String> firmaAdiColumn;
    @FXML private TableColumn<Cari, String> telefonColumn;
    @FXML private TableColumn<Cari, Double> bakiyeColumn;

    @FXML private TableView<CariHareket> hareketTable;
    @FXML private TableColumn<CariHareket, LocalDate> tarihColumn;
    @FXML private TableColumn<CariHareket, String> aciklamaColumn;
    @FXML private TableColumn<CariHareket, Double> borcColumn;
    @FXML private TableColumn<CariHareket, Double> alacakColumn;

    private final CariService cariService;
    private final CariHareketService cariHareketService;

    public MainController() {
        cariService = new CariService();
        cariHareketService = new CariHareketService();
    }

    @FXML
    public void initialize() {
        adSoyadColumn.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
        firmaAdiColumn.setCellValueFactory(new PropertyValueFactory<>("firmaAdi"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        bakiyeColumn.setCellValueFactory(new PropertyValueFactory<>("bakiye"));

        tarihColumn.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        aciklamaColumn.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        borcColumn.setCellValueFactory(new PropertyValueFactory<>("borc"));
        alacakColumn.setCellValueFactory(new PropertyValueFactory<>("alacak"));

        cariTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        loadHareketData(newValue.getId());
                    } else {
                        hareketTable.setItems(FXCollections.observableArrayList());
                    }
                });

        loadCariData();
    }

    private void loadCariData() {
        ObservableList<Cari> cariList = FXCollections.observableArrayList(cariService.getCariList());
        cariTable.setItems(cariList);
        hareketTable.setItems(FXCollections.observableArrayList());
    }

    private void loadHareketData(String cariId) {
        ObservableList<CariHareket> hareketList = FXCollections.observableArrayList(cariHareketService.getCariHareketleri(cariId));
        hareketTable.setItems(hareketList);
    }
    
    @FXML
    private void handleNewHareket() {
        Cari selectedCari = cariTable.getSelectionModel().getSelectedItem();
        if (selectedCari == null) {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen bir işlem eklemek için önce bir cari seçin.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CariHareketDialog.fxml"));
            DialogPane dialogPane = loader.load();
            CariHareketDialogController controller = loader.getController();
            controller.setCariId(selectedCari.getId());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Yeni Cari İşlem");

            final Button okButton = (Button) dialog.getDialogPane().lookupButton(dialogPane.getButtonTypes().get(0));
            okButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
                if (!controller.validateInput()) {
                    event.consume();
                }
            });

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                CariHareket newHareket = controller.getNewHareket();
                cariHareketService.addCariHareket(newHareket);
                loadHareketData(selectedCari.getId());
                loadCariData();
            }

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Arayüz Hatası", "Yeni işlem ekleme formu yüklenemedi.");
        }
    }


    @FXML
    private void handleNewCari() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CariDialog.fxml"));
            DialogPane dialogPane = loader.load();
            CariDialogController controller = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Yeni Cari Ekle");

            final Button okButton = (Button) dialog.getDialogPane().lookupButton(dialogPane.getButtonTypes().get(0));
            okButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
                if (!controller.validateInput()) {
                    event.consume();
                }
            });

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                Cari newCari = controller.getNewCari();
                cariService.addCari(newCari);
                loadCariData();
            }

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Arayüz Hatası", "Yeni cari ekleme formu yüklenemedi.");
        }
    }

    @FXML
    private void handleEditCari() {
        Cari selectedCari = cariTable.getSelectionModel().getSelectedItem();
        if (selectedCari == null) {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen düzenlemek için bir cari seçin.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CariDialog.fxml"));
            DialogPane dialogPane = loader.load();
            CariDialogController controller = loader.getController();
            controller.setCari(selectedCari);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Cari Düzenle");

            final Button okButton = (Button) dialog.getDialogPane().lookupButton(dialogPane.getButtonTypes().get(0));
            okButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
                if (!controller.validateInput()) {
                    event.consume();
                }
            });

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                Cari updatedCari = controller.getNewCari();
                cariService.updateCari(updatedCari);
                loadCariData();
            }

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Arayüz Hatası", "Cari düzenleme formu yüklenemedi.");
        }
    }

    @FXML
    private void handleDeleteCari() {
        Cari selectedCari = cariTable.getSelectionModel().getSelectedItem();
        if (selectedCari != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cari Sil");
            alert.setHeaderText("Bu cariyi ve tüm hareketlerini silmek istediğinizden emin misiniz?");
            alert.setContentText(selectedCari.getAdSoyad());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cariService.deleteCari(selectedCari.getId());
                loadCariData();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Lütfen silmek için bir cari seçin.");
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
