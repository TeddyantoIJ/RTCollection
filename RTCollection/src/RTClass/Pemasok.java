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
public class Pemasok {
    private String pms_id = "";
    private String pms_nama = "";
    private String pms_alamat = "";
    private String pms_no_hp = "";
    private int pms_jumlah_transaksi = 0;
    private int pms_uang_transaksi = 0;
    private int pms_total_hutang = 0;
    
    public void setPms_id(String pms_id) {
        this.pms_id = pms_id;
    }

    public void setPms_nama(String pms_nama) {
        this.pms_nama = pms_nama;
    }

    public void setPms_alamat(String pms_alamat) {
        this.pms_alamat = pms_alamat;
    }

    public void setPms_no_hp(String pms_no_hp) {
        this.pms_no_hp = pms_no_hp;
    }

    public void setPms_jumlah_transaksi(int pms_jumlah_transaksi) {
        this.pms_jumlah_transaksi = pms_jumlah_transaksi;
    }

    public void setPms_uang_transaksi(int pms_uang_transaksi) {
        this.pms_uang_transaksi = pms_uang_transaksi;
    }

    public void setPms_total_hutang(int pms_total_hutang) {
        this.pms_total_hutang = pms_total_hutang;
    }
    
    public String getPms_id() {
        return pms_id;
    }

    public String getPms_nama() {
        return pms_nama;
    }

    public String getPms_alamat() {
        return pms_alamat;
    }

    public String getPms_no_hp() {
        return pms_no_hp;
    }

    public int getPms_jumlah_transaksi() {
        return pms_jumlah_transaksi;
    }

    public int getPms_uang_transaksi() {
        return pms_uang_transaksi;
    }

    public int getPms_total_hutang() {
        return pms_total_hutang;
    }

    public Pemasok() {
    }
    
    
}
