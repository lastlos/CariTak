package com.example.caritakip.service;

import com.example.caritakip.model.Cari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CariService {

    public List<Cari> getCariList() {
        List<Cari> cariList = new ArrayList<>();
        String sql = "SELECT id, adSoyad, firmaAdi, telefon FROM cariler";

        try (Connection conn = DatabaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                double bakiye = getBakiyeForCari(id);
                Cari cari = new Cari(
                        id,
                        rs.getString("adSoyad"),
                        rs.getString("firmaAdi"),
                        rs.getString("telefon"),
                        bakiye
                );
                cariList.add(cari);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cariList;
    }

    public void addCari(Cari cari) {
        String sql = "INSERT INTO cariler(id, adSoyad, firmaAdi, telefon) VALUES(?,?,?,?)";
        String newId = (cari.getId() == null || cari.getId().isEmpty()) ? UUID.randomUUID().toString() : cari.getId();

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newId);
            pstmt.setString(2, cari.getAdSoyad());
            pstmt.setString(3, cari.getFirmaAdi());
            pstmt.setString(4, cari.getTelefon());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCari(Cari cari) {
        String sql = "UPDATE cariler SET adSoyad = ?, firmaAdi = ?, telefon = ? WHERE id = ?";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cari.getAdSoyad());
            pstmt.setString(2, cari.getFirmaAdi());
            pstmt.setString(3, cari.getTelefon());
            pstmt.setString(4, cari.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCari(String id) {
        String sql = "DELETE FROM cariler WHERE id = ?";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getBakiyeForCari(String cariId) {
        String sql = "SELECT SUM(alacak) - SUM(borc) as bakiye FROM cari_hareketleri WHERE cariId = ?";
        double bakiye = 0;

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cariId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bakiye = rs.getDouble("bakiye");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bakiye;
    }
}
