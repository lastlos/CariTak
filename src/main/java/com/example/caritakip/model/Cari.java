package com.example.caritakip.model;

public class Cari {
    private String id;
    private String adSoyad;
    private String firmaAdi;
    private String telefon;
    private double bakiye;

    public Cari(String id, String adSoyad, String firmaAdi, String telefon, double bakiye) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.firmaAdi = firmaAdi;
        this.telefon = telefon;
        this.bakiye = bakiye;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }
}
