package entity;

import java.util.Date;

public class KambingEntity extends HewanEntity {
    private double panjang;
    private double tinggi;
    private String status;

    public KambingEntity(String nama, double panjang, double berat, double tinggi, String status, Date tanggal) {
        super(nama, berat, tanggal);
        this.panjang = panjang;
        this.tinggi = tinggi;
        this.status = status;
    }

    // Getters and Setters for new fields
    public double getPanjang() {
        return panjang;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
