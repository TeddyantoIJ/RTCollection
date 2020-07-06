/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTClass;

import java.util.Date;

/**
 *
 * @author rizky
 */
public class Pemesanan {
    String pmsn_id = "";
    int pgn_id;
    int b_id;
    int d_jumlah_barang;
    int d_jumlah_kodi;
    Double d_total_harga;
    Date pmsn_tgl_transaksi = null;
    int pmsn_jumlah_barang;
    int pmsn_jumlah_kodi;
    Double pmsn_total_uang;
    String kry_id = "";
    String status = "";
            
    public Pemesanan(){
    }

    public void setPmsn_id(String pmsn_id) {
        this.pmsn_id = pmsn_id;
    }

    public void setPmsn_tgl_transaksi(Date pmsn_tgl_transaksi) {
        this.pmsn_tgl_transaksi = pmsn_tgl_transaksi;
    }
    
    public void setPgn_id(int pgn_id) {
        this.pgn_id = pgn_id;
    }
    
    public void setDetailJumlahBarang(int d_jumlah_barang) {
        this.d_jumlah_barang = d_jumlah_barang;
    }
    
    public void setDetailJumlahKodi(int d_jumlah_kodi) {
        this.d_jumlah_kodi = d_jumlah_kodi;
    }
    
    public void setb_id(int b_id) {
        this.b_id = b_id;
    }
    
    public void setDetailHarga(Double d_total_harga) {
        this.d_total_harga = d_total_harga;
    }

    public void setPmsn_jumlah_barang(int pmsn_jumlah_barang) {
        this.pmsn_jumlah_barang = pmsn_jumlah_barang;
    }
    
    public void setPmsn_jumlah_kodi(int pmsn_jumlah_kodi) {
        this.pmsn_jumlah_kodi = pmsn_jumlah_kodi;
    }

    public void setPmsn_total_uang(Double pmsn_total_uang) {
        this.pmsn_total_uang = pmsn_total_uang;
    }

    public String getPmsn_id() {
        return pmsn_id;
    }

    public Date getPmsn_tgl_transaksi() {
        return pmsn_tgl_transaksi;
    }
    
    public int getPgn_id() {
        return pgn_id;
    }
    
    public String getKry_id() {
        return kry_id;
    }
    
    public void setKry_id(String kry_id) {
        this.kry_id = kry_id;
    }
    
    public String getPmsn_Status() {
        return status;
    }
    
    public void setPmsn_Status(String status) {
        this.status = status;
    }
    
    public int getB_id() {
        return b_id;
    }
    
    public int getDetailJumlahBarang() {
        return d_jumlah_barang;
    }
    
    public int getDetailJumlahKodi() {
        return d_jumlah_kodi;
    }
    
    public Double getDetailHarga() {
        return d_total_harga;
    }
    

    public int getPmsn_jumlah_barang() {
        return pmsn_jumlah_barang;
    }
    
    public int getPmsn_jumlah_kodi() {
        return pmsn_jumlah_kodi;
    }
    
    public Double getPmsn_total_uang() {
        return pmsn_total_uang;
    }
    
    public Pemesanan getAllData(){
        Pemesanan pesan = new Pemesanan();
        return pesan;
    }
}
