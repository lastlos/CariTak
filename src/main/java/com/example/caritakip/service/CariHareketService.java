package com.example.caritakip.service;

import com.example.caritakip.model.CariHareket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CariHareketService {

    public List<CariHareket> getCariHareketleri(String cariId) {
        List<CariHareket> hareketList = new ArrayList<>();
        String sql = "SELECT * FROM cari_hareketleri WHERE cariId = ? ORDER BY tarih DESC";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cariId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CariHareket hareket = new CariHareket(
                        rs.getInt("id"),
                        rs.getString("cariId"),
                        LocalDate.parse(rs.getString("tarih")),
                        rs.getString("aciklama"),
                        rs.getDouble("borc"),
                        rs.getDouble("alacak")
                );
                hareketList.add(hareket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hareketList;
    }

    public void addCariHareket(CariHareket hareket) {
        String sql = "INSERT INTO cari_hareketleri(cariId, tarih, aciklama, borc, alacak) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hareket.getCariId());
            pstmt.setString(2, hareket.getTarih().toString());
            pstmt.setString(3, hareket.getAciklama());
            pstmt.setDouble(4, hareket.getBorc());
            pstmt.setDouble(5, hareket.getAlacak());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
