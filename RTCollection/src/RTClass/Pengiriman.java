/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTClass;

import java.util.Date;

/**
 *
 * @author Polman
 */
public class Pengiriman {
    String pgrm_id = "";
    Date pgrm_tgl_transaksi = null;
    int pgrm_jumlah_barang = 0;
    int pgrm_jumlah_kodi = 0;
    int pgrm_jumlah_karung =0;
    String kry_id = "";
    String status = "";
    String pgn_id = "";

    public String getPgn_id() {
        return pgn_id;
    }

    public void setPgn_id(String pgn_id) {
        this.pgn_id = pgn_id;
    }
    
    public Pengiriman(){
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setPgrm_id(String pgrm_id) {
        this.pgrm_id = pgrm_id;
    }

    public void setPgrm_tgl_transaksi(Date pgrm_tgl_transaksi) {
        this.pgrm_tgl_transaksi = pgrm_tgl_transaksi;
    }

    public void setPgrm_jumlah_barang(int pgrm_jumlah_barang) {
        this.pgrm_jumlah_barang = pgrm_jumlah_barang;
    }

    public void setPgrm_jumlah_kodi(int pgrm_jumlah_kodi) {
        this.pgrm_jumlah_kodi = pgrm_jumlah_kodi;
    }

    public void setPgrm_jumlah_karung(int pgrm_jumlah_karung) {
        this.pgrm_jumlah_karung = pgrm_jumlah_karung;
    }

    public void setKry_id(String kry_id) {
        this.kry_id = kry_id;
    }
    
    public String getPgrm_id() {
        return pgrm_id;
    }

    public Date getPgrm_tgl_transaksi() {
        return pgrm_tgl_transaksi;
    }

    public int getPgrm_jumlah_barang() {
        return pgrm_jumlah_barang;
    }

    public int getPgrm_jumlah_kodi() {
        return pgrm_jumlah_kodi;
    }

    public int getPgrm_jumlah_karung() {
        return pgrm_jumlah_karung;
    }

    public String getKry_id() {
        return kry_id;
    }
    
}
