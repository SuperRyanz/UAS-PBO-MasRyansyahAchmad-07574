package controllers;

import entity.KambingEntity;
import handlers.KambingDatabase;
import java.util.List;

public class KambingController {

    public KambingController() {
    }

    // Menambahkan data kambing baru
    public void addGoat(KambingEntity kambing) {
        List<KambingEntity> goats = KambingDatabase.loadGoatData();
        goats.add(kambing);
        KambingDatabase.saveGoatData(goats);
    }

    // Menghapus data kambing berdasarkan nama
    public void deleteGoat(String nama) {
        List<KambingEntity> goats = KambingDatabase.loadGoatData();
        goats.removeIf(goat -> goat.getNama().equals(nama));
        KambingDatabase.saveGoatData(goats);
    }

    // Memperbarui data kambing
    public void updateGoat(KambingEntity updatedGoat, String originalName) {
        List<KambingEntity> goats = KambingDatabase.loadGoatData();
        for (int i = 0; i < goats.size(); i++) {
            if (goats.get(i).getNama().equals(originalName)) {
                // Perbarui kambing dengan mempertahankan identitas uniknya
                KambingEntity goatToUpdate = goats.get(i);
                goatToUpdate.setNama(updatedGoat.getNama());
                goatToUpdate.setPanjang(updatedGoat.getPanjang());
                goatToUpdate.setBerat(updatedGoat.getBerat());
                goatToUpdate.setTinggi(updatedGoat.getTinggi());
                goatToUpdate.setStatus(updatedGoat.getStatus());
                goatToUpdate.setTanggal(updatedGoat.getTanggal());
                break;
            }
        }
        KambingDatabase.saveGoatData(goats);
    }

    // Mendapatkan daftar semua kambing
    public List<KambingEntity> getGoats() {
        return KambingDatabase.loadGoatData();
    }
}
