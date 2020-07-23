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
public class Pembayaran {
    private String Bpmks_id = "";
    private String pms_id = "";
    private String kry_id = "";
    private Date bpmks_tgl_transaksi = null;
    private int bpmks_uang_dibayar = 0;
    //============================================
    private String pmby_id = "";
    private String pgn_id = "";
    private Date pmby_tgl_transaksi = null;
    private int pmby_uang_masuk = 0;
    
    //============================================= Bayar Pemasokan =======================================
    public Pembayaran(){
        
    }
    
    public void setBpmsk_id(String Bpmks_id) {
        this.Bpmks_id = Bpmks_id;
    }
    
    public void setKry_id(String kry_id) {
        this.kry_id = kry_id;
    }


    public void setPms_id(String pms_id) {
        this.pms_id = pms_id;
    }

    public void setBpmsk_tgl_transaksi(Date bpmks_tgl_transaksi) {
        this.bpmks_tgl_transaksi = bpmks_tgl_transaksi;
    }
    
    public void setBpmsk_uang_dibayar(int bpmks_uang_dibayar) {
        this.bpmks_uang_dibayar = bpmks_uang_dibayar;
    }

    public String getBpmsk_id() {
        return Bpmks_id;
    }

    public String getPms_id() {
        return pms_id;
    }
    
    public String getKry_id() {
        return kry_id;
    }

    public Date getBpmsk_tgl_transaksi() {
        return bpmks_tgl_transaksi;
    }
    
    public int getBpmsk_uang_dibayar() {
        return bpmks_uang_dibayar;
    }
    
    //============================================= Bayar Pelanggan =======================================
    public void setPmby_id(String pmby_id) {
        this.pmby_id = pmby_id;
    }

    public void setPgn_id(String pgn_id) {
        this.pgn_id = pgn_id;
    }

    public void setPmby_tgl_transaksi(Date pmby_tgl_transaksi) {
        this.pmby_tgl_transaksi = pmby_tgl_transaksi;
    }
    
    public void setPmby_uang_masuk(int pmby_uang_masuk) {
        this.pmby_uang_masuk = pmby_uang_masuk;
    }

    public String getPmby_id() {
        return pmby_id;
    }

    public String getPgn_id() {
        return pgn_id;
    }
    

    public Date getPmby_tgl_transaksi() {
        return pmby_tgl_transaksi;
    }
    
    public int getPmby_uang_masuk() {
        return pmby_uang_masuk;
    }

    
    

    
}
