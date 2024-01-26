package entity;

import java.util.Date;

public class HewanEntity {
    protected String nama;
    protected double berat;
    protected Date tanggal;

    public HewanEntity(String nama, double berat, Date tanggal) {
        this.nama = nama;
        this.berat = berat;
        this.tanggal = tanggal;
    }

    // Getters and Setters
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
