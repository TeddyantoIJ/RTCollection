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
public class DetailKarung {
    String krg_id;
    int b_id;
    int detail_jumlah_barang;
    int detail_jumlah_kodi;

    public DetailKarung() {
    }

    public void setKrg_id(String krg_id) {
        this.krg_id = krg_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public void setDetail_jumlah_barang(int detail_jumlah_barang) {
        this.detail_jumlah_barang = detail_jumlah_barang;
    }

    public void setDetail_jumlah_kodi(int detail_jumlah_kodi) {
        this.detail_jumlah_kodi = detail_jumlah_kodi;
    }

    public String getKrg_id() {
        return krg_id;
    }

    public int getB_id() {
        return b_id;
    }

    public int getDetail_jumlah_barang() {
        return detail_jumlah_barang;
    }

    public int getDetail_jumlah_kodi() {
        return detail_jumlah_kodi;
    }
    
    
}
