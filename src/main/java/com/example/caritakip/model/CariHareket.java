package com.example.caritakip.model;

import java.time.LocalDate;

public class CariHareket {
    private int id;
    private String cariId;
    private LocalDate tarih;
    private String aciklama;
    private double borc;
    private double alacak;

    public CariHareket(int id, String cariId, LocalDate tarih, String aciklama, double borc, double alacak) {
        this.id = id;
        this.cariId = cariId;
        this.tarih = tarih;
        this.aciklama = aciklama;
        this.borc = borc;
        this.alacak = alacak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCariId() {
        return cariId;
    }

    public void setCariId(String cariId) {
        this.cariId = cariId;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public double getBorc() {
        return borc;
    }

    public void setBorc(double borc) {
        this.borc = borc;
    }

    public double getAlacak() {
        return alacak;
    }

    public void setAlacak(double alacak) {
        this.alacak = alacak;
    }
}
