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
public class Pemasokkan {
    private String pmsk_id = "";
    private String pms_id = "";
    private Date pmsk_tgl_transaksi = null;
    private int pmsk_jumlah_barang = 0;
    private int pmsk_jumlah_kodi = 0;
    private int pmsk_total_uang = 0;
    private int pmsk_uang_dibayar = 0;
    private String kry_id = "";
    private String status = "";
    
    public Pemasokkan(){
        
    }

    public String getAllData(){
        return "pmsk_id     : "+pmsk_id+
             "\npms_id      : "+pms_id+
             "\npmsk_tgl    : "+pmsk_tgl_transaksi.toString()+
             "\njumlag barang : "+pmsk_jumlah_barang+
             "\nkodi        : "+pmsk_jumlah_kodi+
             "\ntotal uang  : "+pmsk_total_uang+
             "\nbayar       : "+pmsk_uang_dibayar+
             "\nkry_id      : "+kry_id+
             "\nstatus      : "+status;
    }
    public void setKry_id(String kry_id) {
        this.kry_id = kry_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKry_id() {
        return kry_id;
    }

    public String getStatus() {
        return status;
    }

    public void setPmsk_id(String pmsk_id) {
        this.pmsk_id = pmsk_id;
    }

    public void setPms_id(String pms_id) {
        this.pms_id = pms_id;
    }

    public void setPmsk_tgl_transaksi(Date pmsk_tgl_transaksi) {
        this.pmsk_tgl_transaksi = pmsk_tgl_transaksi;
    }

    public void setPmsk_jumlah_barang(int pmsk_jumlah_barang) {
        this.pmsk_jumlah_barang = pmsk_jumlah_barang;
    }

    public void setPmsk_jumlah_kodi(int pmsk_jumlah_kodi) {
        this.pmsk_jumlah_kodi = pmsk_jumlah_kodi;
    }

    public void setPmsk_total_uang(int pmsk_total_uang) {
        this.pmsk_total_uang = pmsk_total_uang;
    }

    public void setPmsk_uang_dibayar(int pmsk_uang_dibayar) {
        this.pmsk_uang_dibayar = pmsk_uang_dibayar;
    }

    public String getPmsk_id() {
        return pmsk_id;
    }

    public String getPms_id() {
        return pms_id;
    }

    public Date getPmsk_tgl_transaksi() {
        return pmsk_tgl_transaksi;
    }

    public int getPmsk_jumlah_barang() {
        return pmsk_jumlah_barang;
    }

    public int getPmsk_jumlah_kodi() {
        return pmsk_jumlah_kodi;
    }

    public int getPmsk_total_uang() {
        return pmsk_total_uang;
    }

    public int getPmsk_uang_dibayar() {
        return pmsk_uang_dibayar;
    }
    
    
}
