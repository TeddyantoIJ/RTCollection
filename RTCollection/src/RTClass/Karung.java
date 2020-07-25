/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTClass;

/**
 *
 * @author Polman
 */
public class Karung {
    String krg_id;
    String pgrm_id;
    int krg_jumlah_kodi;
    int krg_jumlah_jenis_barang;
    String keterangan;

    public Karung() {
    }

    public void setKrg_id(String krg_id) {
        this.krg_id = krg_id;
    }

    public void setPgrm_id(String pgrm_id) {
        this.pgrm_id = pgrm_id;
    }

    public void setKrg_jumlah_kodi(int krg_jumlah_kodi) {
        this.krg_jumlah_kodi = krg_jumlah_kodi;
    }

    public void setKrg_jumlah_jenis_barang(int krg_jumlah_jenis_barang) {
        this.krg_jumlah_jenis_barang = krg_jumlah_jenis_barang;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKrg_id() {
        return krg_id;
    }

    public String getPgrm_id() {
        return pgrm_id;
    }

    public int getKrg_jumlah_kodi() {
        return krg_jumlah_kodi;
    }

    public int getKrg_jumlah_jenis_barang() {
        return krg_jumlah_jenis_barang;
    }

    public String getKeterangan() {
        return keterangan;
    }
    
    
}
