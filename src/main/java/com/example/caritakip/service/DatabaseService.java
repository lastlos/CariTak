package com.example.caritakip.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:cari_takip.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Cariler tablosu
            String createCarilerTableSql = "CREATE TABLE IF NOT EXISTS cariler (" +
                    "id TEXT PRIMARY KEY," +
                    "adSoyad TEXT NOT NULL," +
                    "firmaAdi TEXT," +
                    "telefon TEXT" +
                    ");";
            stmt.execute(createCarilerTableSql);

            // Cari Hareketleri tablosu
            String createCariHareketleriTableSql = "CREATE TABLE IF NOT EXISTS cari_hareketleri (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "cariId TEXT NOT NULL," +
                    "tarih TEXT NOT NULL," +
                    "aciklama TEXT," +
                    "borc REAL DEFAULT 0," +
                    "alacak REAL DEFAULT 0," +
                    "FOREIGN KEY (cariId) REFERENCES cariler(id) ON DELETE CASCADE" +
                    ");";
            stmt.execute(createCariHareketleriTableSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
