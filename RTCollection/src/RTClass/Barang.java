/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTClass;

/**
 *
 * @author rizky
 */
public class Barang {
    int b_id;
    String b_nama;
    String b_bahan;
    String b_ukuran;
    int b_stok;
    Double b_harga_satuan;
    Double b_harga_kodian;
    Double b_harga_jual_satuan;
    Double b_harga_jual_kodian;
    int pemasok_id;
    int jb_id;
    
    
    public int getID_Barang(){
        return b_id;
    }
    
    public void setID_Barang(int id){
        this.b_id = id;
    }
    
    public int getID_JenisBarang(){
        return jb_id;
    }
    
    public void setID_JenisBarang(int id){
        this.jb_id = id;
    }
    
    public String getNama_Barang(){
        return b_nama;
    }
    
    public void setNama_Barang(String nama){
        this.b_nama = nama;
    }
    
    public String getBahan_Barang(){
        return b_bahan;
    }
    
    public void setBahan_Barang(String bahan){
        this.b_bahan = bahan;
    }
    
    public String getUkuran_Barang(){
        return b_ukuran;
    }
    
    public void setUkuran_Barang(String ukuran){
        this.b_ukuran = ukuran;
    }
    
    public int getStok_Barang(){
        return b_stok;
    }
    
    public void setStok_Barang(int stok){
        this.b_stok = stok;
    }
    
    public Double getHargaSatuan_Barang(){
        return b_harga_satuan;
    }
    
    public void setHargaSatuan_Barang(Double hargaSatuan){
        this.b_harga_satuan = hargaSatuan;
    }
    
    public Double getHargaKodian_Barang(){
        return b_harga_kodian;
    }
    
    public Double getHargaJualSatuan_Barang(){
        return b_harga_jual_satuan;
    }
    
    public void setHargaJualSatuan_Barang(Double hargaJualSatuan){
        this.b_harga_jual_satuan = hargaJualSatuan;
    }
    
    public void setHargaKodian_Barang(Double hargaKodian){
        this.b_harga_kodian = hargaKodian;
    }
    
    public Double getHargaJualKodian_Barang(){
        return b_harga_jual_kodian;
    }
    
    public void setHargaJualKodian_Barang(Double hargaJualKodian){
        this.b_harga_jual_kodian = hargaJualKodian;
    }
    
    public int getPmsk_Barang(){
        return pemasok_id;
    }
    
    public void setPmsk_Barang(int pmsk_id){
        this.pemasok_id = pmsk_id;
    }

    public Barang getAllData(){
        Barang b = new Barang();
        return b;
    }
}
