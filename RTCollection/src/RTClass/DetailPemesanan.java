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
public class DetailPemesanan {
    String pmsn_id = "";
    String b_id = "";
    int detail_jumlah_barang = 0;
    int detail_jumlah_kodi = 0;
    int detail_total_uang = 0;

    public DetailPemesanan() {
    }

    public void setPmsn_id(String pmsn_id) {
        this.pmsn_id = pmsn_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public void setDetail_jumlah_barang(int detail_jumlah_barang) {
        this.detail_jumlah_barang = detail_jumlah_barang;
    }

    public void setDetail_jumlah_kodi(int detail_jumlah_kodi) {
        this.detail_jumlah_kodi = detail_jumlah_kodi;
    }

    public void setDetail_total_uang(int detail_total_uang) {
        this.detail_total_uang = detail_total_uang;
    }

    public String getPmsn_id() {
        return pmsn_id;
    }

    public String getB_id() {
        return b_id;
    }

    public int getDetail_jumlah_barang() {
        return detail_jumlah_barang;
    }

    public int getDetail_jumlah_kodi() {
        return detail_jumlah_kodi;
    }

    public int getDetail_total_uang() {
        return detail_total_uang;
    }
    
    
}
