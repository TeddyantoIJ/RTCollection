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
public class Karyawan {
    String kry_id = "";
    String kry_nama = "";
    String kry_no_hp = "";
    String kry_email = "";
    Date kry_tgl_lahir = null;
    String alamat = "";
    String kry_total_transaksi = "";
    String kry_username = "";
    String kry_password = "";
    String kry_jabatan = "";
    public Karyawan(){
    }

    public void setKry_id(String kry_id) {
        this.kry_id = kry_id;
    }

    public void setKry_nama(String kry_nama) {
        this.kry_nama = kry_nama;
    }

    public void setKry_no_hp(String kry_no_hp) {
        this.kry_no_hp = kry_no_hp;
    }

    public void setKry_email(String kry_email) {
        this.kry_email = kry_email;
    }

    public void setKry_tgl_lahir(Date kry_tgl_lahir) {
        this.kry_tgl_lahir = kry_tgl_lahir;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setKry_total_transaksi(String kry_total_transaksi) {
        this.kry_total_transaksi = kry_total_transaksi;
    }

    public void setKry_username(String kry_username) {
        this.kry_username = kry_username;
    }

    public void setKry_password(String kry_password) {
        this.kry_password = kry_password;
    }

    public void setKry_jabatan(String kry_jabatan) {
        this.kry_jabatan = kry_jabatan;
    }

    

    public String getKry_id() {
        return kry_id;
    }

    public String getKry_nama() {
        return kry_nama;
    }

    public String getKry_no_hp() {
        return kry_no_hp;
    }

    public String getKry_email() {
        return kry_email;
    }

    public Date getKry_tgl_lahir() {
        return kry_tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKry_total_transaksi() {
        return kry_total_transaksi;
    }

    public String getKry_username() {
        return kry_username;
    }

    public String getKry_password() {
        return kry_password;
    }

    public String getKry_jabatan() {
        return kry_jabatan;
    }
    
    public String getAllData(){
        return getKry_id() + " , " + getAlamat()+ " , " +getKry_email()+ " , " +getKry_jabatan()+ " , " +getKry_nama()+ " , " +getKry_no_hp()+ " , " +getKry_password()+ " , " +getKry_total_transaksi()+ " , " +getKry_username();
    }
}
