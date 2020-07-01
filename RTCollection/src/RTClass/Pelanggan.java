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
public class Pelanggan {
    private String pgn_id = "";
    private String pgn_nama = "";
    private String pgn_nama_toko = "";
    private String pgn_no_hp = "";
    private String pgn_email = "";
    private String pgn_alamat = "";
    private String pgn_nama_pasar = "";
    private int pgn_jumlah_transaksi = 0;
    private int pgn_uang_transaksi = 0;
    private int pgn_total_hutang = 0;

    public Pelanggan() {
    }

    public Pelanggan(Pelanggan masuk){
        pgn_id = masuk.getPgn_id();
        pgn_nama = masuk.getPgn_nama();
        pgn_nama_toko = masuk.getPgn_nama_toko();
        pgn_no_hp = masuk.getPgn_no_hp();
        pgn_email = masuk.getPgn_email();
        pgn_alamat = masuk.getPgn_alamat();
        pgn_nama_pasar = masuk.getPgn_nama_pasar();
        pgn_jumlah_transaksi = masuk.getPgn_jumlah_transaksi();
        pgn_uang_transaksi = masuk.getPgn_uang_transaksi();
        pgn_total_hutang = masuk.getPgn_total_hutang();
    }

    public void setPgn_email(String email) {
        this.pgn_email = email;
    }

    public String getPgn_email() {
        return this.pgn_email;
    }
    
    public void setPgn_id(String pgn_id) {
        this.pgn_id = pgn_id;
    }

    public void setPgn_nama(String pgn_nama) {
        this.pgn_nama = pgn_nama;
    }

    public void setPgn_nama_toko(String pgn_nama_toko) {
        this.pgn_nama_toko = pgn_nama_toko;
    }

    public void setPgn_no_hp(String pgn_no_hp) {
        this.pgn_no_hp = pgn_no_hp;
    }

    public void setPgn_alamat(String pgn_alamat) {
        this.pgn_alamat = pgn_alamat;
    }

    public void setPgn_nama_pasar(String pgn_nama_pasar) {
        this.pgn_nama_pasar = pgn_nama_pasar;
    }

    public void setPgn_jumlah_transaksi(int pgn_jumlah_transaksi) {
        this.pgn_jumlah_transaksi = pgn_jumlah_transaksi;
    }

    public void setPgn_uang_transaksi(int pgn_uang_transaksi) {
        this.pgn_uang_transaksi = pgn_uang_transaksi;
    }

    public void setPgn_total_hutang(int pgn_total_hutang) {
        this.pgn_total_hutang = pgn_total_hutang;
    }

    public String getPgn_id() {
        return pgn_id;
    }

    public String getPgn_nama() {
        return pgn_nama;
    }

    public String getPgn_nama_toko() {
        return pgn_nama_toko;
    }

    public String getPgn_no_hp() {
        return pgn_no_hp;
    }

    public String getPgn_alamat() {
        return pgn_alamat;
    }

    public String getPgn_nama_pasar() {
        return pgn_nama_pasar;
    }

    public int getPgn_jumlah_transaksi() {
        return pgn_jumlah_transaksi;
    }

    public int getPgn_uang_transaksi() {
        return pgn_uang_transaksi;
    }

    public int getPgn_total_hutang() {
        return pgn_total_hutang;
    }
    
    
}
