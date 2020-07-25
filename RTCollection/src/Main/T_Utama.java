/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CRUD.CRUDBarang;
import CRUD.CRUDJenisBarang;
import CRUD.CRUDKaryawan;
import CRUD.CRUDPelanggan;
import CRUD.CRUDPemasok;
import Controller.CBarang;
import Controller.CDetailKarung;
import Controller.CKarung;
import Controller.CKaryawan;
import Controller.CPelanggan;
import Controller.CPemasokkan;
import Controller.CPembayaran;
import Controller.CPemesanan;   
import Controller.CPengiriman;
import Controller.CSupplier;
import Controller.CTable;
import Controller.CTableBarang;
import RTClass.Barang;
import RTClass.DetailKarung;
import RTClass.DetailPemasokkan;
import RTClass.DetailPemesanan;
import RTClass.DetailPengiriman;
import RTClass.Karung;
import RTClass.Karyawan;
import RTClass.Pelanggan;
import RTClass.Pemasok;
import RTClass.Pemasokkan;
import RTClass.Pembayaran;
import RTClass.Pemesanan;
import RTClass.Pengiriman;
import connection.DBConnect;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Polman
 */
public class T_Utama extends javax.swing.JFrame {

    /**
     * Creates new form T_Utama
     */
    // ================================ Variable & Prosedur Umum ==============================================
    private void show(JPanel panel){
        panelDashboard.setVisible(false);
        panelPengolahanData.setVisible(false);
        panelPemasokkan.setVisible(false);
        panelPemesanan.setVisible(false);
        panelBarangMasuk.setVisible(false);
        panelPembayaranPelanggan.setVisible(false);
        PanelTransaksiBarangKeluar.setVisible(false);
        panelPembayaranPemasok.setVisible(false);
        panelKonfirmasiKeluar.setVisible(false);
        
        
        panel.setVisible(true);
        
        //spesifikinstatiasi(panel.getName());
    }
    
    private void spesifikinstatiasi(String panel){
        switch(panel){
            case "panelDashboard":{
                break;
            }
            case "panelPengolahanData":{
                break;
            }
            case "panelPemasokkan":{
                
                break;
            }
            case "panelPemesanan":{
                
                break;
            }
            case "panelBarangMasuk":{
                
                break;
            }
            case "panelPembayaranPelanggan":{
                
                break;
            }
            case "PanelTransaksiBarangKeluar":{
                
                break;
            }
            case "panelPembayaranPemasok":{
                
                break;
            }
            case "panelKonfirmasiKeluar":{
                
                break;
            }
        }
    }
    //==========================DEKLARASI VARIABLE KONFIRMASI BARANG KELUAR =====================================
    private DefaultTableModel modelPengirimanKonfirmasiKeluar = new DefaultTableModel();
    private DefaultTableModel modelDetailKarungKonfirmasiKeluar = new DefaultTableModel();

    CTableBarang ControllerKonfirmasiKeluar = new CTableBarang();
    CPengiriman ControllerPengirimanKonfirmasiKeluar = new CPengiriman();
    CPemesanan ControllerPemesananKonfirmasiKeluar = new CPemesanan();
    CPelanggan ControllerPelangganKonfirmasiBarang = new CPelanggan();

    

    private void instantiasiKonfirmasiBarangKeluar() {
        addColumnKonfirmasiKeluar();
        tableKarungKonfirmasiKeluar.setModel(modelDetailKarungKonfirmasiKeluar);
        tablePengirimanKonfirmasiKeluar.setModel(modelPengirimanKonfirmasiKeluar);
        ButtonGroup a = new ButtonGroup();
        a.add(rbGagalKonfirmasiKeluar);
        a.add(rbDiterimaKonfirmasiKeluar);

        addDataKonfirmasiKeluar(0);
        addComboBoxKonfirmasiBarangKeluar();
    }

    private void addComboBoxKonfirmasiBarangKeluar() {
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pelanggan";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {

                cmbNamaPelangganKonfirmasiKeluar.addItem(connection.result.getString("pgn_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }

    private void addDataKonfirmasiKeluar(int i) {
        modelPengirimanKonfirmasiKeluar.getDataVector().clear();
        modelDetailKarungKonfirmasiKeluar.getDataVector().clear();
        modelDetailKarungKonfirmasiKeluar.fireTableDataChanged();
        if (i == 0) {
            ControllerKonfirmasiKeluar.addkonfirmasiBarangKeluar(modelPengirimanKonfirmasiKeluar, "Semua");
        } else {
            ControllerKonfirmasiKeluar.addkonfirmasiBarangKeluar(modelPengirimanKonfirmasiKeluar, cmbNamaPelangganKonfirmasiKeluar.getSelectedItem().toString());
        }
        modelPengirimanKonfirmasiKeluar.fireTableDataChanged();
        txtNamaPelangganKonfirmasiKeluar.setText("-");
        txtKodeTransaksiKonfirmasiKeluar.setText("-");
    }

    private void addColumnKonfirmasiKeluar() {
        modelPengirimanKonfirmasiKeluar.addColumn("No");
        modelPengirimanKonfirmasiKeluar.addColumn("Kode");
        modelPengirimanKonfirmasiKeluar.addColumn("Pelanggan");
        modelPengirimanKonfirmasiKeluar.addColumn("Jumlah Barang");
        modelPengirimanKonfirmasiKeluar.addColumn("Jumlah Kodi");
        modelPengirimanKonfirmasiKeluar.addColumn("Jumlah Karung");
        modelPengirimanKonfirmasiKeluar.addColumn("Tanggal");
        modelPengirimanKonfirmasiKeluar.addColumn("Pengemudi");
        modelPengirimanKonfirmasiKeluar.addColumn("Status");

        modelDetailKarungKonfirmasiKeluar.addColumn("No");
        modelDetailKarungKonfirmasiKeluar.addColumn("Kode Karung");
        modelDetailKarungKonfirmasiKeluar.addColumn("Barang");
        modelDetailKarungKonfirmasiKeluar.addColumn("Ukuran");
        modelDetailKarungKonfirmasiKeluar.addColumn("Jumlah Barang");
        modelDetailKarungKonfirmasiKeluar.addColumn("Jumlah Kodi");
        modelDetailKarungKonfirmasiKeluar.addColumn("Keterangan");
    }
    //==========================DEKLARASI VARIABLE PEMBAYARAN PEMASOK =====================================
    public String kry_id = "";
    Pemasok pemasokPembayaranPemasok = new Pemasok();
    CSupplier ControllerPemasokPembayaranPemasok = new CSupplier();
    CTable ControllerTablePembayaranPemasok = new CTable();
    private DefaultTableModel modelPemasokPembayaranPemasok = new DefaultTableModel();
    CPembayaran ControllerPembayaranPembayaranPemasok = new CPembayaran();
    CPemasokkan ControllerPemasokkan = new CPemasokkan();
    
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
    private void instantiasiPembayaranPemasok(){
            addColumnPembayaranPemasok();
            addComboBoxPembayaranPemasok();
            tablePemasokPembayaranPemasok.setModel(modelPemasokPembayaranPemasok);
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
        }
    private void addColumnPembayaranPemasok() {
        modelPemasokPembayaranPemasok.addColumn("No");
        modelPemasokPembayaranPemasok.addColumn("Nama");
        modelPemasokPembayaranPemasok.addColumn("Alamat");
        modelPemasokPembayaranPemasok.addColumn("No HP");
        modelPemasokPembayaranPemasok.addColumn("Transaksi");
        modelPemasokPembayaranPemasok.addColumn("Pembayaran");
        modelPemasokPembayaranPemasok.addColumn("Hutang");
    }
    
    private void addComboBoxPembayaranPemasok(){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok";
            connection.result = connection.stat.executeQuery(query);
            
            while (connection.result.next()) {
                
                cmbPemasokPembayaranPemasok.addItem(connection.result.getString("pms_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }
    private void addDataPemasok(){
        
        modelPemasokPembayaranPemasok.getDataVector().removeAllElements();

        modelPemasokPembayaranPemasok.fireTableDataChanged();
        ControllerTablePembayaranPemasok.addDataPemasok(modelPemasokPembayaranPemasok,(String) cmbPemasokPembayaranPemasok.getSelectedItem());
    }
    private void ClearPembayaranPemasok() {

        txtNamaPemasokPembayaranPemasok.setText("-");
        txtJumlahTransaksiPemasokPembayaranPemasok.setText("0");
        txtHutangPemasokPembayaranPemasok.setText(kursIndonesia.format(0));

        txtSisaHutangPembayaranPemasok.setText(kursIndonesia.format(0));
        txtBayarPembayaranPemasok.setText(kursIndonesia.format(0));
        txtKembalianPembayaranPemasok.setText(kursIndonesia.format(0));

    }
    //==========================DEKLARASI VARIABLE BARANG KELUAR =====================================
    private DefaultTableModel modelBarangKeluar = new DefaultTableModel();
    private DefaultTableModel modelDetailBarangKeluar = new DefaultTableModel();
    private DefaultTableModel modelKarungBarangKeluar = new DefaultTableModel();
    private DefaultTableModel modelDetailKarungBarangKeluar = new DefaultTableModel();

    CPengiriman ControllerPengirimanBarangKeluar = new CPengiriman();
    CKarung ControllerKarungBarangKeluar = new CKarung();
    CDetailKarung ControllerDetailKarungBarangKeluar = new CDetailKarung();
    CPemesanan ControllerPemesananBarangKeluar = new CPemesanan();
    CPelanggan ControllerPelangganBarangKeluar = new CPelanggan();
    
    Pengiriman PengirimanBarangKeluar;
    
    
    CTableBarang ControllerTabelBarangKeluar = new CTableBarang();

    List<String> detailKarungIndexBarangKeluar = new ArrayList<String>();
    List<String> detailKarungBarangBarangKeluar = new ArrayList<String>();
    List<String> detailKarungUkuranBarangKeluar = new ArrayList<String>();
    List<String> detailKarungKodiBarangKeluar = new ArrayList<String>();
    
    private void instantiasiBarangKeluar(){
            addColumnBarangKeluar();
        
            PengirimanBarangKeluar = new Pengiriman();
            tableBarangKeluar.setModel(modelBarangKeluar);
            tableDetailBarangKeluar.setModel(modelDetailBarangKeluar);
            tableKarungPengirimanBarangKeluar.setModel(modelKarungBarangKeluar);
            tableDetailKarungPengirimanBarangKeluar.setModel(modelDetailKarungBarangKeluar);
            addComboBoxBarangKeluar();
            addDataBarangKeluar();
            addComboBoxPengemudiBarangKeluar();
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
        }
    private void addComboBoxPengemudiBarangKeluar(){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select kry_nama from Karyawan where kry_jabatan ='Pengemudi'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {

                cmbPengemudiBarangKeluar.addItem(connection.result.getString("kry_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }
    private void updateStatusPemesananBarangKeluar(){
        DetailPengiriman dit = new DetailPengiriman();
        for(int i = 0; i < tableBarangKeluar.getRowCount(); i++){
            if(tableBarangKeluar.getValueAt(i, 6).toString().equals("0")){
                ControllerPemesananBarangKeluar.setStatus(tableBarangKeluar.getValueAt(i, 1).toString(), "Dikirim");
                dit.setPmsn_id(tableBarangKeluar.getValueAt(i, 1).toString());
                dit.setPgrm_id(txtIDPengirimanBarangKeluar.getText());
                ControllerPengirimanBarangKeluar.simpanDetailPengiriman(dit);
            }
        }
        modelDetailBarangKeluar.getDataVector().removeAllElements();

        modelDetailBarangKeluar.fireTableDataChanged();
        addDataBarangKeluar();
    }
    private void addDataBarangKeluar() {

        modelBarangKeluar.getDataVector().removeAllElements();
        modelBarangKeluar.fireTableDataChanged();
        
        ControllerTabelBarangKeluar.addDataBarangKeluar(modelBarangKeluar, (String) cmbStatusBarangKeluar.getSelectedItem(), (String) cmbNamaPelangganBarangKeluar.getSelectedItem());
    }
    private void addComboBoxBarangKeluar() {
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pelanggan";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {

                cmbNamaPelangganBarangKeluar.addItem(connection.result.getString("pgn_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }

    private void addColumnBarangKeluar() {
        modelBarangKeluar.addColumn("No");
        modelBarangKeluar.addColumn("Kode");
        modelBarangKeluar.addColumn("Pelanggan nama");
        modelBarangKeluar.addColumn("Pelanggan toko");
        modelBarangKeluar.addColumn("Pasar");
        modelBarangKeluar.addColumn("Tanggal transaksi");
        modelBarangKeluar.addColumn("Jumlah kodi");
        modelBarangKeluar.addColumn("Harga");
        modelBarangKeluar.addColumn("Status");

        modelDetailBarangKeluar.addColumn("No");
        modelDetailBarangKeluar.addColumn("Barang");
        modelDetailBarangKeluar.addColumn("Ukuran");
        modelDetailBarangKeluar.addColumn("Jumlah kodi");
        modelDetailBarangKeluar.addColumn("Harga");

        modelKarungBarangKeluar.addColumn("No");
        modelKarungBarangKeluar.addColumn("Kode");
        modelKarungBarangKeluar.addColumn("Keterangan");
        modelKarungBarangKeluar.addColumn("Kodi");
        modelKarungBarangKeluar.addColumn("Jenis");

        modelDetailKarungBarangKeluar.addColumn("No");
        modelDetailKarungBarangKeluar.addColumn("Barang");
        modelDetailKarungBarangKeluar.addColumn("Ukuran");
        modelDetailKarungBarangKeluar.addColumn("Kodi");
    }
    private void showDetailKarungBarangKeluar(String index) {

        modelDetailKarungBarangKeluar.getDataVector().removeAllElements();
        modelDetailKarungBarangKeluar.fireTableDataChanged();
        //JOptionPane.showMessageDialog(this, detailKarungIndexBarangKeluar.size());
        int baris = 1;
        for (int i = 0; i < detailKarungIndexBarangKeluar.size(); i++) {
            //JOptionPane.showMessageDialog(this, "ukuran index : " + detailKarungIndexBarangKeluar.size());
            if (detailKarungIndexBarangKeluar.get(i).toString().equals((index))) {
                Object[] dataRow = {baris, detailKarungBarangBarangKeluar.get(i), detailKarungUkuranBarangKeluar.get(i), detailKarungKodiBarangKeluar.get(i)};

                modelDetailKarungBarangKeluar.addRow(dataRow);
                baris++;
            }
        }
    }
    private void hitungKodiKarungBarangKeluar() {
        int karung = tableKarungPengirimanBarangKeluar.getRowCount();
        int kodi = 0;
        for (int i = 0; i < tableKarungPengirimanBarangKeluar.getRowCount(); i++) {

            kodi = kodi + Integer.parseInt(tableKarungPengirimanBarangKeluar.getValueAt(i, 3).toString());

        }
        txtJumlahKarungPengirimanBarang.setText(Integer.toString(karung));
        txtJumlahKodiPengirimanBarang.setText(Integer.toString(kodi));
    }
    private void isiJumlahDetailKarungTransaksiBarangKeluar() {
        int nomorkarung = 1;
        int jumlah = 0;
        for (int i = 0; i < detailKarungIndexBarangKeluar.size(); i++) {
            //JOptionPane.showMessageDialog(this, "Nomor karung : "+nomorkarung+"\nIndex karung detail : "+detailKarungIndexBarangKeluar.get(i));
//            JOptionPane.showMessageDialog(this, );
            if (nomorkarung == Integer.parseInt(detailKarungIndexBarangKeluar.get(i))) {
                jumlah++;
            } else if (nomorkarung != Integer.parseInt(detailKarungIndexBarangKeluar.get(i))) {
                tableKarungPengirimanBarangKeluar.setValueAt(jumlah, nomorkarung - 1, 4);
                jumlah = 1;
                nomorkarung++;
            }

            if (i + 1 == detailKarungIndexBarangKeluar.size()) {
                tableKarungPengirimanBarangKeluar.setValueAt(jumlah, nomorkarung - 1, 4);
            }
        }
    }
    private void perbaruiDetailBarangKeluar() {
        modelDetailBarangKeluar.getDataVector().removeAllElements();

        modelDetailBarangKeluar.fireTableDataChanged();
        ControllerTabelBarangKeluar.addDataDetailBarangKeluar(modelDetailBarangKeluar, (String) tableBarangKeluar.getValueAt(tableBarangKeluar.getSelectedRow(), 1));
    }
    private void clearBarangKeluar(){
        modelKarungBarangKeluar.setRowCount(0);
        modelDetailKarungBarangKeluar.setRowCount(0);
        
        modelKarungBarangKeluar.fireTableDataChanged();
        modelDetailKarungBarangKeluar.fireTableDataChanged();
        
        detailKarungBarangBarangKeluar.clear();
        detailKarungIndexBarangKeluar.clear();
        detailKarungKodiBarangKeluar.clear();
        detailKarungUkuranBarangKeluar.clear();
        txtNomorKarungBarangKeluar.setText("[NO KARUNG]");
        txtJumlahKodiPengirimanBarang.setText("[JUMLAH KODI]");
        txtJumlahKarungPengirimanBarang.setText("[JUMLAH KARUNG]");
        
        txtBarangBarangKeluar.setText("[NAMA BARANG]");
        txtJumlahKodiBarangKeluar.setValue(1);
        txtUkuranBarangKeluar.setText("[UKURAN]");
    }
    private void simpanDataPengirimanBarangKeluar(Pengiriman png){
        ControllerPengirimanBarangKeluar.simpanPengiriman(png);
        Karung krg = new Karung();
        for(int i = 0 ; i < tableKarungPengirimanBarangKeluar.getRowCount(); i++){
            krg.setKrg_id(tableKarungPengirimanBarangKeluar.getValueAt(i, 1).toString());
            krg.setKrg_jumlah_jenis_barang(Integer.valueOf(tableKarungPengirimanBarangKeluar.getValueAt(i, 4).toString()));
            krg.setKrg_jumlah_kodi(Integer.valueOf(tableKarungPengirimanBarangKeluar.getValueAt(i, 3).toString()));
            krg.setKeterangan(tableKarungPengirimanBarangKeluar.getValueAt(i, 2).toString());
            krg.setPgrm_id(PengirimanBarangKeluar.getPgrm_id());
            ControllerKarungBarangKeluar.simpanKarung(krg);
            simpanKarungBarangKeluar(i,krg.getKrg_id());
        }
        //updateStatusPemesananBarangKeluar();
        
    }
    private void simpanKarungBarangKeluar(int index, String krg_id){
        DetailKarung DetailKarung = new DetailKarung();
        Barang barang = new Barang();
        for(int i = 0 ; i < detailKarungIndexBarangKeluar.size();i++){
            if(detailKarungIndexBarangKeluar.get(i).equals(Integer.toString(index+1))){
                 barang = ControllerTabelBarangKeluar.getDatabyNamaUkuran(detailKarungBarangBarangKeluar.get(i), detailKarungUkuranBarangKeluar.get(i));
                 DetailKarung.setB_id(barang.getID_Barang());
                 DetailKarung.setDetail_jumlah_kodi(Integer.valueOf(detailKarungKodiBarangKeluar.get(i)));
                 DetailKarung.setDetail_jumlah_barang(DetailKarung.getDetail_jumlah_kodi()*20);
                 DetailKarung.setKrg_id(krg_id);
                 ControllerDetailKarungBarangKeluar.simpanDetailKarung(DetailKarung);
            }
        }
        updateStatusPemesananBarangKeluar();
    }
    // =================================== DEKLARASI VARIABLE DAN PROSEDUR PEMBAYARAN PELANGGAN ===========================
    
    //public String kry_id = "";
    Pelanggan pelangganPembayarnPelanggan = new Pelanggan();
    CPelanggan controllerPelangganPembayaranPelanggan = new CPelanggan();
    CTable controlTablePembayaranPelanggan = new CTable();
    private DefaultTableModel modelPelangganPembayaranPelanggan = new DefaultTableModel();
    CPembayaran ControllerPembayaranPembayaranPelanggan = new CPembayaran();
    CPemesanan ControllerPemesananPembayaranPelanggan = new CPemesanan();
    
   // DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
   // DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    private void instantiasiPembayaranPelanggan(){
        addColumnPembayaranPelanggan();
        addComboBoxPembayaranPelanggan();
        tablePelanggan.setModel(modelPelangganPembayaranPelanggan);
    }
    private void addColumnPembayaranPelanggan() {
        modelPelangganPembayaranPelanggan.addColumn("No");
        modelPelangganPembayaranPelanggan.addColumn("Nama");
        modelPelangganPembayaranPelanggan.addColumn("Toko");
        modelPelangganPembayaranPelanggan.addColumn("No HP");
        modelPelangganPembayaranPelanggan.addColumn("Email");
        modelPelangganPembayaranPelanggan.addColumn("Alamat");
        modelPelangganPembayaranPelanggan.addColumn("Pasar");
        modelPelangganPembayaranPelanggan.addColumn("Transaksi");
        modelPelangganPembayaranPelanggan.addColumn("Pembayaran");
        modelPelangganPembayaranPelanggan.addColumn("Hutang");
    }
    private void clearPembayaranPelanggan() {

        txtNamaToko.setText("-");
        txtNamaPelanggan.setText("-");
        txtJumlahTransaksiPelanggan.setText("0");
        txtHutangPelanggan.setText(kursIndonesia.format(0));

        txtSisaHutang.setText(kursIndonesia.format(0));
        txtBayar.setText(kursIndonesia.format(0));
        txtKembalian.setText(kursIndonesia.format(0));
        

    }
    private void addDataPelangganPembayaranPelanggan(){
        
        modelPelangganPembayaranPelanggan.getDataVector().removeAllElements();

        modelPelangganPembayaranPelanggan.fireTableDataChanged();
        controlTablePembayaranPelanggan.addDataPelanggan(modelPelangganPembayaranPelanggan,(String) cmbPelanggan1.getSelectedItem());
    }
    private void addComboBoxPembayaranPelanggan(){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pelanggan";
            connection.result = connection.stat.executeQuery(query);
            
            while (connection.result.next()) {
                
                cmbPelanggan1.addItem(connection.result.getString("pgn_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal addDataPelanggan!\n" + e.toString());
        }
    }
    //==========================DEKLARASI VARIABLE dan PROSEDUR BARANG MASUK =====================================
    private DefaultTableModel modelBarangMasuk = new DefaultTableModel();
    private DefaultTableModel modelDetailBarangMasuk = new DefaultTableModel();
    CTableBarang ControllerTableBarangBarangMasuk = new CTableBarang();
    CSupplier ControllerPemasokBarangMasuk = new CSupplier();
    CPemasokkan ControllerPemasokkanBarangMasuk = new CPemasokkan();
    CBarang ControllerBarangBarangMasuk = new CBarang();

    Pemasok pemasok = new Pemasok();

    //DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    //DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

    long sisaHutangBarangMasuk = 0;
    long calonSisaHutangBarangMasuk = 0;
    
    private void instantiasiBarangMasuk(){
        addColumnBarangMasuk();
        addDataBarangMasuk();
        tableBarangMasuk.setModel(modelBarangMasuk);
        tableDetailBarangMasuk.setModel(modelDetailBarangMasuk);
        //resizeColumnWidth(tableBarangMasuk);

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    private void addColumnBarangMasuk() {
        modelBarangMasuk.addColumn("No");
        modelBarangMasuk.addColumn("Kode Transaksi");
        modelBarangMasuk.addColumn("Pemasok");
        modelBarangMasuk.addColumn("Tanggal Transaksi");
        modelBarangMasuk.addColumn("Banyak Barang (Kodi)");
        modelBarangMasuk.addColumn("Harga");
        modelBarangMasuk.addColumn("Status");

        modelDetailBarangMasuk.addColumn("No");
        modelDetailBarangMasuk.addColumn("Barang");
        modelDetailBarangMasuk.addColumn("Ukuran");
        modelDetailBarangMasuk.addColumn("Banyak Barang (Kodi)");
        modelDetailBarangMasuk.addColumn("Harga");
    }

    private void addDataBarangMasuk() {

        modelBarangMasuk.getDataVector().removeAllElements();

        modelBarangMasuk.fireTableDataChanged();
        ControllerTableBarangBarangMasuk.addDataBarangMasuk(modelBarangMasuk, (String) cmbStatusBarangMasuk.getSelectedItem());
    }
    private void perbaruiDetail() {
        modelDetailBarangMasuk.getDataVector().removeAllElements();

        modelDetailBarangMasuk.fireTableDataChanged();
        ControllerTableBarangBarangMasuk.addDataDetailBarangMasuk(modelDetailBarangMasuk, (String) tableBarangMasuk.getValueAt(tableBarangMasuk.getSelectedRow(), 1));
    }
    //==================================== PEMESANAN VARIABLE dan PROSEDUR =========================================
    DefaultTableModel modelBarangPemesanan = new DefaultTableModel();
    DefaultTableModel modelKeranjangPemesanan = new DefaultTableModel();
    CTableBarang controllerTable = new CTableBarang();
    CPemesanan controllerPemesanan = new CPemesanan();
    DBConnect connection = new DBConnect();
    Pemesanan psn = new Pemesanan();
    Barang barangPemesanan = new Barang();
    CTableBarang ControllerBarang = new CTableBarang();
    int TotalHargaPemesanan = 0;
    int HargaPemesanan = 0;
    //DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    //DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    int TotalBiayaPemesanan = 0;
    CBarang controllerBarangPemesanan = new CBarang();
    
    private void instantiasiPemesanan(){
        addColumnPemesanan();
        addDataPemesanan();
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        
        tblBarangPemesanan.setModel(modelBarangPemesanan);
        tblKeranjangPemesanan.setModel(modelKeranjangPemesanan);
        
        
        //lebarKolomBarangPemesanan();
        tampilPelangganPemesanan();
        txtNamaPelanggan.setEnabled(false);
        txtJumlahKodiPemesanan.setEnabled(false);
        txtTotalPemesanan.setEnabled(false);
    }
    private void clearPemesanan(){
        txtBarangPemesanan.setText("");
        txtJumlahPemesanan.setValue(1);
        txtTotalHargaPemesanan.setText("");
        txtUkuranPemesanan.setText("");
        hitungKodiPemesanan();
        hitungTotalPemesanan();
    }
    private void hitungTotalPemesanan(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjangPemesanan.getRowCount();i++){
            total = total +(int) modelKeranjangPemesanan.getValueAt(i, 4);
        }
        txtTotalPemesanan.setText(kursIndonesia.format(total));
        TotalBiayaPemesanan = total;
    }
    private void hitungKodiPemesanan(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjangPemesanan.getRowCount();i++){
            total = total +(int) modelKeranjangPemesanan.getValueAt(i, 3);
        }
        txtJumlahKodiPemesanan.setText(Integer.toString(total));
        //txtJumlahBarangPemesanan.setText(Integer.toString(total*20));
    }
    public void clearPesananPemesanan(){
        cmbPelanggan.setSelectedIndex(0);
        txtNamaPelanggan.setText("");
        txtJumlahKodiPemesanan.setText("");
        txtTotalPemesanan.setText("");
    }
    private void addColumnPemesanan(){
        modelBarangPemesanan.addColumn("No");
        modelBarangPemesanan.addColumn("Nama Barang");
        modelBarangPemesanan.addColumn("Jenis Barang");
        modelBarangPemesanan.addColumn("Ukuran");
        modelBarangPemesanan.addColumn("Bahan");
        modelBarangPemesanan.addColumn("Stok");        
        modelBarangPemesanan.addColumn("Harga Jual Satuan");
        modelBarangPemesanan.addColumn("Harga Jual Kodian");

        modelKeranjangPemesanan.addColumn("No");
        modelKeranjangPemesanan.addColumn("Barang");
        modelKeranjangPemesanan.addColumn("Ukuran");
        modelKeranjangPemesanan.addColumn("Jumlah");
        modelKeranjangPemesanan.addColumn("Harga");
    }
    private void addDataPemesanan(){
        modelBarangPemesanan.getDataVector().removeAllElements();

        modelBarangPemesanan.fireTableDataChanged();
        
        modelBarangPemesanan = controllerTable.addDataBarangPemesanan(modelBarangPemesanan);
    }
    private void tampilPelangganPemesanan(){
         try{
           connection.stat = connection.conn.createStatement();
           String sql = "SELECT pgn_nama_toko,pgn_id FROM Pelanggan";
           connection.result = connection.stat.executeQuery(sql);
           
           while(connection.result.next()){
               cmbPelanggan.addItem(connection.result.getString("pgn_nama_toko"));
           }
           connection.stat.close();
           connection.result.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Terjadi error saat load data pelanggan " + ex);
        }
    }
    // ================================ Variable & Prosedur Pemasokkan ==============================================
    int TotalHarga = 0;
    int Harga = 0;
    int TotalBiaya = 0;
//    public String kry_id = "";
    private DefaultTableModel modelBarang = new DefaultTableModel();
    private DefaultTableModel modelKeranjang = new DefaultTableModel();    
    //CTableBarang ControllerBarang = new CTableBarang();
    CTable ControllerPemasok = new CTable();
    //CPemasokkan ControllerPemasokkan = new CPemasokkan();
    Barang barang = new Barang();
    SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MM-yyyy");
    //DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    //DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
    private void instantiasiPemasokkan(){
        karyawan_login = ControllerKaryawan.getDatabyNama("Kiky Rahmawati Sitombingg");
        System.out.println(karyawan_login.getAllData());
        tableBarang.setModel(modelBarang);
        tableKeranjang.setModel(modelKeranjang);
        txtinfoTanggal.setText(formatTanggal.format(new Date()));
        
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        txtTanggalPemasokkan.setText(formatTanggal.format(new Date()));
        
        addColumnPemasokkan();
        addComboBoxPemasokkan();
    }
    private void clearBarangMasuk(){
        txtBarang.setText("");
        txtJumlah.setValue(1);
        txtPemasok.setText("");
        txtTotalHarga.setText("");
        txtUkuran.setText("");
        hitungKodi();
        hitungTotal();
    }
    private void hitungTotal(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjang.getRowCount();i++){
            total = total +(int) modelKeranjang.getValueAt(i, 5);
        }
        txtTotalBiaya.setText(kursIndonesia.format(total));
        TotalBiaya = total;
    }
    private void hitungKodi(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjang.getRowCount();i++){
            total = total +(int) modelKeranjang.getValueAt(i, 4);
        }
        txtjumlah_kodi.setText(Integer.toString(total));
    }
    private void addDataBarang(String pemasok){
        
        modelBarang.getDataVector().removeAllElements();

        modelBarang.fireTableDataChanged();
        ControllerBarang.addDataBarangRestock(modelBarang,pemasok);
    }
    private void addColumnPemasokkan() {
        modelBarang.addColumn("No");
        modelBarang.addColumn("Barang");
        modelBarang.addColumn("Jenis");
        modelBarang.addColumn("Ukuran");
        modelBarang.addColumn("Stock");
        modelBarang.addColumn("Pemasok");
        
        modelKeranjang.addColumn("No");
        modelKeranjang.addColumn("Barang");
        modelKeranjang.addColumn("Ukuran");
        modelKeranjang.addColumn("Pemasok");
        modelKeranjang.addColumn("Jumlah");
        modelKeranjang.addColumn("Harga");
    }
    private void addComboBoxPemasokkan(){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok";
            connection.result = connection.stat.executeQuery(query);
            
            while (connection.result.next()) {
                
                cmbPemasok.addItem(connection.result.getString("pms_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }
    // ==========================================================================================================
    
    
    Karyawan karyawan_login = new Karyawan();
    
    SimpleDateFormat settingTanggal = new SimpleDateFormat("dd-MM-yyyy");
    CKaryawan ControllerKaryawan = new CKaryawan();
    
    public T_Utama() {
        initComponents();
        //====================== PENGATURAN TAMPILAN AWAL =======================
        show(panelDashboard);
        
        instantiasiKonfirmasiBarangKeluar();
        instantiasiPembayaranPemasok();
        instantiasiBarangKeluar();
        instantiasiPembayaranPelanggan();
        instantiasiBarangMasuk();
        instantiasiPemesanan();
        instantiasiPemasokkan();
        //==========================DEKLARASI VARIABLE PEMBAYARAN PEMASOK =====================================
        
        //======================= DEKLARASI BARANG KELUAR =============================
        
        //======================= DEKLARASI PEMBAYARAN PELANGGAN =============================
        
        //======================= DEKLARASI BARANG MASUK =============================
        
        //================= DEKLARASI PEMESANAN =======================
            
        //========== DEKLARASI TRANSAKSI PEMASOKKAN =======
        
        // ================================================
        karyawan_info.setText(karyawan_login.getKry_nama());
        txtinfoTanggal.setText(settingTanggal.format(new Date()));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }
    
    
    public T_Utama(String pengguna, String posisi) {
        initComponents();
        //====================== PENGATURAN TAMPILAN AWAL =======================
        show(panelDashboard);
        
        instantiasiKonfirmasiBarangKeluar();
        instantiasiPembayaranPemasok();
        instantiasiBarangKeluar();
        instantiasiPembayaranPelanggan();
        instantiasiBarangMasuk();
        instantiasiPemesanan();
        instantiasiPemasokkan();
        //==========================DEKLARASI VARIABLE PEMBAYARAN PEMASOK =====================================
        
        //======================= DEKLARASI BARANG KELUAR =============================
        
        //======================= DEKLARASI PEMBAYARAN PELANGGAN =============================
         
        //======================= DEKLARASI BARANG MASUK =============================
          
        //================= DEKLARASI PEMESANAN =======================
         
        
        //========== DEKLARASI TRANSAKSI PEMASOKKAN =======
         
        // ================================================
        karyawan_info.setText(karyawan_login.getKry_nama());
        txtinfoTanggal.setText(settingTanggal.format(new Date()));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAtas = new javax.swing.JPanel();
        batas = new javax.swing.JLabel();
        txtinfoTanggal = new javax.swing.JLabel();
        panelPemesanan = new javax.swing.JPanel();
        pnlDataBarang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBarangPemesanan = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnRefreshPemesanan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtJumlahKodiPemesanan = new javax.swing.JTextField();
        txtTotalPemesanan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKeranjangPemesanan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbPelanggan = new javax.swing.JComboBox<>();
        txtNamaPelanggan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        panelDetailBeli1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtBarangPemesanan = new javax.swing.JTextField();
        Ukuran4 = new javax.swing.JLabel();
        txtJumlahPemesanan = new javax.swing.JSpinner();
        Ukuran5 = new javax.swing.JLabel();
        txtTotalHargaPemesanan = new javax.swing.JTextField();
        btnKeranjangPemesanan = new javax.swing.JButton();
        txtUkuranPemesanan = new javax.swing.JTextField();
        panelPengolahanData = new javax.swing.JPanel();
        btnBarang = new javax.swing.JButton();
        btnJBarang = new javax.swing.JButton();
        btnPemasok = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        btnKaryawan = new javax.swing.JButton();
        panelKiri = new javax.swing.JPanel();
        karyawan_info = new javax.swing.JLabel();
        btnMenuUtama = new javax.swing.JButton();
        btnPemesananPelanggan = new javax.swing.JButton();
        btnPembayaranPelanggan = new javax.swing.JButton();
        btnPengolahanData = new javax.swing.JButton();
        btnKonfirmasiKeluar = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnBarangKeluar = new javax.swing.JButton();
        btnBarangMasuk = new javax.swing.JButton();
        btnPemesananPemasok = new javax.swing.JButton();
        btnPembayaranPemasok = new javax.swing.JButton();
        panelDashboard = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        kiribawahdashboard = new javax.swing.JLabel();
        panelPemasokkan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cmbPemasok = new javax.swing.JComboBox<>();
        panelDeskripsi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalPemasokkan = new javax.swing.JTextField();
        panelDetailBeli = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        txtPemasok = new javax.swing.JTextField();
        Ukuran = new javax.swing.JLabel();
        Ukuran1 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JSpinner();
        Ukuran2 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        btnKeranjang = new javax.swing.JButton();
        txtUkuran = new javax.swing.JTextField();
        panelKeranjang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKeranjang = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        Ukuran3 = new javax.swing.JLabel();
        txtjumlah_kodi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotalBiaya = new javax.swing.JLabel();
        btnProses = new javax.swing.JButton();
        panelBarangMasuk = new javax.swing.JPanel();
        panelDaftarBarangMasuk = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        cmbStatusBarangMasuk = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableBarangMasuk = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableDetailBarangMasuk = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        panelDeskripsiBarangMasuk = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtNamaPemasokBarangMasuk = new javax.swing.JTextField();
        txtJumlahTransaksiBarangMasuk = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtUangTransaksiBarangMasuk = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtHutangBarangMasuk = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtUangDibayarBarangMasuk = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtSisaHutangBarangMasuk = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        lblKeteranganBarangMasuk = new javax.swing.JLabel();
        btnSuksesBarangMasuk = new javax.swing.JButton();
        btnGagalBarangMasuk = new javax.swing.JButton();
        btnKonfirmasiBarangMasuk = new javax.swing.JButton();
        txtTagihanBarangMasuk = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        panelPembayaranPelanggan = new javax.swing.JPanel();
        panelDaftarBarangMasuk1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablePelanggan = new javax.swing.JTable();
        cmbPelanggan1 = new javax.swing.JComboBox<>();
        panelDeskripsiBarangMasuk1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNamaToko = new javax.swing.JTextField();
        txtJumlahTransaksiPelanggan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtHutangPelanggan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtSisaHutang = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        lblKeteranganPembayaran = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtKembalian = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtNamaPelanggan1 = new javax.swing.JTextField();
        PanelTransaksiBarangKeluar = new javax.swing.JPanel();
        panelDaftarBarangMasuk2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cmbStatusBarangKeluar = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableBarangKeluar = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableDetailBarangKeluar = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtJumlahKodiBarangKeluar = new javax.swing.JSpinner();
        btnTambahkanPengirimanBarangKeluar = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        txtBarangBarangKeluar = new javax.swing.JTextField();
        txtUkuranBarangKeluar = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        cmbNamaPelangganBarangKeluar = new javax.swing.JComboBox<>();
        cbPengiriman = new javax.swing.JCheckBox();
        panelDataPengirimanBarangBarangKeluar = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        txtIDPengirimanBarangKeluar = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableKarungPengirimanBarangKeluar = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtJumlahKodiPengirimanBarang = new javax.swing.JTextField();
        txtJumlahKarungPengirimanBarang = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtKeteranganKarungBarangKeluar = new javax.swing.JTextArea();
        txtBatasKeteranganBarangKeluar = new javax.swing.JLabel();
        dummy1 = new javax.swing.JLabel();
        txtBatasKeteranganBarangKeluar1 = new javax.swing.JLabel();
        btnTulisKeteranganBarangKeluar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableDetailKarungPengirimanBarangKeluar = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        txtNomorKarungBarangKeluar = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btnBatalkanKarungBarangKeluar = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        btnKonfirmasiPemberangkatanBarangKeluar = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        cmbPengemudiBarangKeluar = new javax.swing.JComboBox<>();
        panelPembayaranPemasok = new javax.swing.JPanel();
        panelDaftarBarangMasuk3 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablePemasokPembayaranPemasok = new javax.swing.JTable();
        cmbPemasokPembayaranPemasok = new javax.swing.JComboBox<>();
        panelDeskripsiBarangMasuk2 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtNamaPemasokPembayaranPemasok = new javax.swing.JTextField();
        txtJumlahTransaksiPemasokPembayaranPemasok = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtHutangPemasokPembayaranPemasok = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtBayarPembayaranPemasok = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtSisaHutangPembayaranPemasok = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        lblKeteranganPembayaranPembayaranPemasok = new javax.swing.JLabel();
        btnBatalPembayaranPemasok = new javax.swing.JButton();
        btnBayarPembayaranPemasok = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        txtKembalianPembayaranPemasok = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        panelKonfirmasiKeluar = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablePengirimanKonfirmasiKeluar = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableKarungKonfirmasiKeluar = new javax.swing.JTable();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        rbDiterimaKonfirmasiKeluar = new javax.swing.JRadioButton();
        rbGagalKonfirmasiKeluar = new javax.swing.JRadioButton();
        jLabel74 = new javax.swing.JLabel();
        txtNamaPelangganKonfirmasiKeluar = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtKodeTransaksiKonfirmasiKeluar = new javax.swing.JTextField();
        btnKonfirmasiKonfirmasiKeluar = new javax.swing.JButton();
        txtStatusPengirimanKonfirmasiKeluar = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        cmbNamaPelangganKonfirmasiKeluar = new javax.swing.JComboBox<>();
        btnSemuaKonfirmasiKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);
        setExtendedState(1);
        setName("frameUtama"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAtas.setBackground(new java.awt.Color(153, 153, 255));

        batas.setForeground(new java.awt.Color(0, 0, 0));
        batas.setText("Batas kanan atas");

        txtinfoTanggal.setForeground(new java.awt.Color(0, 0, 0));
        txtinfoTanggal.setText("jLabel2");

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(txtinfoTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 853, Short.MAX_VALUE)
                .addComponent(batas)
                .addGap(18, 18, 18))
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batas)
                    .addComponent(txtinfoTanggal))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(panelAtas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 30));

        panelPemesanan.setBackground(new java.awt.Color(187, 187, 187));
        panelPemesanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelPemesanan.setPreferredSize(new java.awt.Dimension(1100, 652));
        panelPemesanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDataBarang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblBarangPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBarangPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangPemesananMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBarangPemesanan);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("DATA BARANG");

        btnRefreshPemesanan.setText("Refresh");
        btnRefreshPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPemesananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataBarangLayout = new javax.swing.GroupLayout(pnlDataBarang);
        pnlDataBarang.setLayout(pnlDataBarangLayout);
        pnlDataBarangLayout.setHorizontalGroup(
            pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(pnlDataBarangLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(btnRefreshPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDataBarangLayout.setVerticalGroup(
            pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataBarangLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnRefreshPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPemesanan.add(pnlDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 630));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Total Jumlah Kodi");

        jLabel9.setText("Total Harga");

        txtJumlahKodiPemesanan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtJumlahKodiPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahKodiPemesananMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJumlahKodiPemesananMouseEntered(evt);
            }
        });

        txtTotalPemesanan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTotalPemesananMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTotalPemesananMouseEntered(evt);
            }
        });
        txtTotalPemesanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalPemesananKeyReleased(evt);
            }
        });

        jLabel11.setText("Rp. ");

        tblKeranjangPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKeranjangPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeranjangPemesananMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKeranjangPemesanan);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("KERANJANG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtJumlahKodiPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahKodiPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        panelPemesanan.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 259, 560, -1));

        btnSimpan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Simpan Pesanan.png"))); // NOI18N
        btnSimpan.setText("Simpan Pesanan");
        btnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        panelPemesanan.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 240, 55));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setText("Nama Toko");

        jLabel14.setText("Nama Pelanggan");

        cmbPelanggan.setBackground(new java.awt.Color(149, 225, 225));
        cmbPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "== Pilih Toko ==" }));
        cmbPelanggan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPelangganItemStateChanged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("DESKRIPSI PEMESANAN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPelanggan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelPemesanan.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 560, -1));

        panelDetailBeli1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDetailBeli1.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("DESKRIPSI DETAIL PEMESANAN");

        jLabel17.setText("Barang");

        txtBarangPemesanan.setEnabled(false);

        Ukuran4.setText("Jumlah Kodi");

        txtJumlahPemesanan.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        txtJumlahPemesanan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtJumlahPemesananStateChanged(evt);
            }
        });
        txtJumlahPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahPemesananMouseClicked(evt);
            }
        });
        txtJumlahPemesanan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtJumlahPemesananPropertyChange(evt);
            }
        });

        Ukuran5.setText("Total Harga");

        txtTotalHargaPemesanan.setEnabled(false);
        txtTotalHargaPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHargaPemesananActionPerformed(evt);
            }
        });

        btnKeranjangPemesanan.setText("Masukkan ke keranjang");
        btnKeranjangPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangPemesananActionPerformed(evt);
            }
        });

        txtUkuranPemesanan.setEnabled(false);

        javax.swing.GroupLayout panelDetailBeli1Layout = new javax.swing.GroupLayout(panelDetailBeli1);
        panelDetailBeli1.setLayout(panelDetailBeli1Layout);
        panelDetailBeli1Layout.setHorizontalGroup(
            panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)
                        .addComponent(btnKeranjangPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarangPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUkuranPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlahPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHargaPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelDetailBeli1Layout.setVerticalGroup(
            panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKeranjangPemesanan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ukuran4)
                            .addComponent(Ukuran5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJumlahPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHargaPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(panelDetailBeli1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarangPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUkuranPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeli1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel17)))
                .addGap(12, 12, Short.MAX_VALUE))
        );

        panelPemesanan.add(panelDetailBeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 560, -1));

        getContentPane().add(panelPemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        panelPengolahanData.setBackground(new java.awt.Color(255, 255, 255));

        btnBarang.setText("Barang");
        btnBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnJBarang.setText("Jenis Barang");
        btnJBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnJBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJBarangActionPerformed(evt);
            }
        });

        btnPemasok.setText("Pemasok");
        btnPemasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasokActionPerformed(evt);
            }
        });

        btnPelanggan.setText("Pelanggan");
        btnPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });

        btnKaryawan.setText("Karyawan");
        btnKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPengolahanDataLayout = new javax.swing.GroupLayout(panelPengolahanData);
        panelPengolahanData.setLayout(panelPengolahanDataLayout);
        panelPengolahanDataLayout.setHorizontalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJBarang)
                    .addComponent(btnPemasok)
                    .addComponent(btnPelanggan)
                    .addComponent(btnKaryawan)
                    .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(926, Short.MAX_VALUE))
        );

        panelPengolahanDataLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBarang, btnJBarang, btnKaryawan, btnPelanggan, btnPemasok});

        panelPengolahanDataLayout.setVerticalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnJBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemasok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKaryawan)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        getContentPane().add(panelPengolahanData, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelKiri.setBackground(new java.awt.Color(204, 204, 204));
        panelKiri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKiriMouseClicked(evt);
            }
        });

        karyawan_info.setForeground(new java.awt.Color(0, 0, 0));
        karyawan_info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        karyawan_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/userTawal.png"))); // NOI18N
        karyawan_info.setText("INFO YANG LOGIN");
        karyawan_info.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMenuUtama.setText("Menu Utama");
        btnMenuUtama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuUtamaActionPerformed(evt);
            }
        });

        btnPemesananPelanggan.setText("Pemesanan Pelanggan");
        btnPemesananPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPelangganActionPerformed(evt);
            }
        });

        btnPembayaranPelanggan.setText("Pembayaran Pelanggan");
        btnPembayaranPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranPelangganActionPerformed(evt);
            }
        });

        btnPengolahanData.setText("PENGOLAHAN DATA");
        btnPengolahanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengolahanDataActionPerformed(evt);
            }
        });

        btnKonfirmasiKeluar.setText("Konfirmasi Pengiriman");
        btnKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiKeluarActionPerformed(evt);
            }
        });

        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnBarangKeluar.setText("Barang Keluar");
        btnBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangKeluarActionPerformed(evt);
            }
        });

        btnBarangMasuk.setText("Barang Masuk");
        btnBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangMasukActionPerformed(evt);
            }
        });

        btnPemesananPemasok.setText("Pemesanan Pemasok");
        btnPemesananPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPemasokActionPerformed(evt);
            }
        });

        btnPembayaranPemasok.setText("Pembayaran Pemasok");
        btnPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKiriLayout = new javax.swing.GroupLayout(panelKiri);
        panelKiri.setLayout(panelKiriLayout);
        panelKiriLayout.setHorizontalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelKiriLayout.createSequentialGroup()
                        .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMenuUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(btnPemesananPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPembayaranPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(karyawan_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKiriLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPemesananPemasok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPengolahanData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(btnPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelKiriLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMenuUtama, btnPembayaranPelanggan, btnPemesananPelanggan, btnPengolahanData});

        panelKiriLayout.setVerticalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(karyawan_info, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemesananPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemesananPemasok)
                .addGap(76, 76, 76)
                .addComponent(btnPembayaranPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPembayaranPemasok)
                .addGap(31, 31, 31)
                .addComponent(btnBarangKeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarangMasuk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKonfirmasiKeluar)
                .addGap(27, 27, 27)
                .addComponent(btnPengolahanData)
                .addGap(31, 31, 31)
                .addComponent(btnKeluar)
                .addContainerGap())
        );

        panelKiriLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnKeluar, btnKonfirmasiKeluar, btnMenuUtama, btnPembayaranPelanggan, btnPemesananPelanggan, btnPengolahanData});

        getContentPane().add(panelKiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 29, 240, 680));

        panelDashboard.setBackground(new java.awt.Color(255, 255, 255));
        panelDashboard.setName("panelDashboard"); // NOI18N

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Logo.PNG"))); // NOI18N

        kiribawahdashboard.setForeground(new java.awt.Color(0, 0, 0));
        kiribawahdashboard.setText("Batas kiri bawah dashboard");

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kiribawahdashboard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDashboardLayout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(Logo)
                .addGap(430, 430, 430))
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(kiribawahdashboard)
                .addContainerGap())
        );

        getContentPane().add(panelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelPemasokkan.setBackground(new java.awt.Color(187, 187, 187));
        panelPemasokkan.setMaximumSize(new java.awt.Dimension(1100, 652));
        panelPemasokkan.setName("panelPemasokkan"); // NOI18N
        panelPemasokkan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBarang);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("DATA PEMASOK");

        cmbPemasok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pemasok -" }));
        cmbPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPemasokkan.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 640));

        panelDeskripsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("DESKRIPSI PEMASOKAN");

        jLabel2.setText("Tanggal Transaksi");

        txtTanggalPemasokkan.setEnabled(false);

        javax.swing.GroupLayout panelDeskripsiLayout = new javax.swing.GroupLayout(panelDeskripsi);
        panelDeskripsi.setLayout(panelDeskripsiLayout);
        panelDeskripsiLayout.setHorizontalGroup(
            panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggalPemasokkan, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        panelDeskripsiLayout.setVerticalGroup(
            panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTanggalPemasokkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panelPemasokkan.add(panelDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 590, 100));

        panelDetailBeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDetailBeli.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("DESKRIPSI DETAIL PEMASOKKAN");

        jLabel4.setText("Barang");

        txtBarang.setEnabled(false);

        txtPemasok.setEnabled(false);
        txtPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPemasokActionPerformed(evt);
            }
        });

        Ukuran.setText("Pemasok");

        Ukuran1.setText("Jumlah Kodi");

        txtJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        txtJumlah.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtJumlahStateChanged(evt);
            }
        });
        txtJumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahMouseClicked(evt);
            }
        });
        txtJumlah.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtJumlahPropertyChange(evt);
            }
        });

        Ukuran2.setText("Total Harga");

        txtTotalHarga.setEnabled(false);
        txtTotalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHargaActionPerformed(evt);
            }
        });

        btnKeranjang.setText("Masukkan ke keranjang");
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });

        txtUkuran.setEnabled(false);

        javax.swing.GroupLayout panelDetailBeliLayout = new javax.swing.GroupLayout(panelDetailBeli);
        panelDetailBeli.setLayout(panelDetailBeliLayout);
        panelDetailBeliLayout.setHorizontalGroup(
            panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPemasok)
                            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                                .addComponent(Ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailBeliLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKeranjang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDetailBeliLayout.setVerticalGroup(
            panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKeranjang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(Ukuran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ukuran1)
                            .addComponent(Ukuran2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)))
                .addGap(12, 12, Short.MAX_VALUE))
        );

        panelPemasokkan.add(panelDetailBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 590, -1));

        panelKeranjang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKeranjangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableKeranjang);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("DAFTAR PEMASOKKAN");

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Ukuran3.setText("Total Harga");

        txtjumlah_kodi.setText("jTextField1");

        jLabel6.setText("Jumlah Kodi Barang");

        txtTotalBiaya.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTotalBiaya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalBiaya.setText("jLabel7");

        javax.swing.GroupLayout panelKeranjangLayout = new javax.swing.GroupLayout(panelKeranjang);
        panelKeranjang.setLayout(panelKeranjangLayout);
        panelKeranjangLayout.setHorizontalGroup(
            panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtjumlah_kodi))
                .addGap(190, 190, 190)
                .addComponent(Ukuran3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        panelKeranjangLayout.setVerticalGroup(
            panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKeranjangLayout.createSequentialGroup()
                        .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Ukuran3))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKeranjangLayout.createSequentialGroup()
                        .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjumlah_kodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalBiaya))
                        .addContainerGap())))
        );

        panelPemasokkan.add(panelKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 590, 370));

        btnProses.setText("Proses Pemasokkan");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        panelPemasokkan.add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 620, -1, -1));

        getContentPane().add(panelPemasokkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelBarangMasuk.setBackground(new java.awt.Color(187, 187, 187));
        panelBarangMasuk.setMinimumSize(new java.awt.Dimension(1100, 652));
        panelBarangMasuk.setName("panelBarangMasuk"); // NOI18N
        panelBarangMasuk.setPreferredSize(new java.awt.Dimension(1100, 652));
        panelBarangMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDaftarBarangMasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setText("DAFTAR BARANG MASUK");

        cmbStatusBarangMasuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proses", "Sukses", "Gagal" }));
        cmbStatusBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusBarangMasukActionPerformed(evt);
            }
        });

        jLabel32.setText("Status");

        tableBarangMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMasukMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableBarangMasuk);

        tableDetailBarangMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDetailBarangMasuk.setEnabled(false);
        tableDetailBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailBarangMasukMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tableDetailBarangMasuk);

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setText("DETAIL DAFTAR BARANG MASUK");

        javax.swing.GroupLayout panelDaftarBarangMasukLayout = new javax.swing.GroupLayout(panelDaftarBarangMasuk);
        panelDaftarBarangMasuk.setLayout(panelDaftarBarangMasukLayout);
        panelDaftarBarangMasukLayout.setHorizontalGroup(
            panelDaftarBarangMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasukLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDaftarBarangMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDaftarBarangMasukLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelDaftarBarangMasukLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbStatusBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDaftarBarangMasukLayout.createSequentialGroup()
                        .addComponent(jScrollPane8)
                        .addContainerGap())
                    .addGroup(panelDaftarBarangMasukLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelDaftarBarangMasukLayout.setVerticalGroup(
            panelDaftarBarangMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasukLayout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatusBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        panelBarangMasuk.add(panelDaftarBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1100, 450));

        panelDeskripsiBarangMasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk.setPreferredSize(new java.awt.Dimension(1100, 185));
        panelDeskripsiBarangMasuk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setText("DESKRIPSI TRANSAKSI BARANG MASUK");
        panelDeskripsiBarangMasuk.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 287, 33));

        jLabel35.setText("Nama");
        panelDeskripsiBarangMasuk.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 50, 99, -1));

        txtNamaPemasokBarangMasuk.setText("-");
        panelDeskripsiBarangMasuk.add(txtNamaPemasokBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 46, 240, -1));

        txtJumlahTransaksiBarangMasuk.setText("-");
        panelDeskripsiBarangMasuk.add(txtJumlahTransaksiBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 82, 240, -1));

        jLabel36.setText("Jumlah transaksi");
        panelDeskripsiBarangMasuk.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 86, -1, -1));

        txtUangTransaksiBarangMasuk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUangTransaksiBarangMasuk.setText("Rp. 0,00");
        panelDeskripsiBarangMasuk.add(txtUangTransaksiBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 118, 240, -1));

        jLabel37.setText("Uang transaksi");
        panelDeskripsiBarangMasuk.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 122, 99, -1));

        jLabel38.setText("Hutang");
        panelDeskripsiBarangMasuk.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 154, 99, -1));

        txtHutangBarangMasuk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHutangBarangMasuk.setText("Rp. 0,00");
        panelDeskripsiBarangMasuk.add(txtHutangBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 154, 240, -1));

        jLabel39.setText("Banyak uang dibayar");
        panelDeskripsiBarangMasuk.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 83, -1, -1));

        txtUangDibayarBarangMasuk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUangDibayarBarangMasuk.setText("0");
        txtUangDibayarBarangMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUangDibayarBarangMasukKeyReleased(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(txtUangDibayarBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 105, 179, -1));

        jLabel40.setText("Rp");
        panelDeskripsiBarangMasuk.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 109, -1, -1));

        jLabel41.setText("Sisa hutang pada pemasok");
        panelDeskripsiBarangMasuk.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 135, -1, -1));

        txtSisaHutangBarangMasuk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSisaHutangBarangMasuk.setText("Rp. 0,00");
        panelDeskripsiBarangMasuk.add(txtSisaHutangBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 157, 200, -1));

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel42.setText("KETERANGAN TRANSAKSI BARANG MASUK");
        panelDeskripsiBarangMasuk.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(734, 7, -1, -1));

        lblKeteranganBarangMasuk.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblKeteranganBarangMasuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKeteranganBarangMasuk.setText("[Keterangan]");
        lblKeteranganBarangMasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk.add(lblKeteranganBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 32, 428, 55));

        btnSuksesBarangMasuk.setText("Sukses");
        btnSuksesBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuksesBarangMasukActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(btnSuksesBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 93, 209, -1));

        btnGagalBarangMasuk.setText("Gagal");
        btnGagalBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGagalBarangMasukActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(btnGagalBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 93, 209, -1));

        btnKonfirmasiBarangMasuk.setText("Konfirmasi");
        btnKonfirmasiBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiBarangMasukActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(btnKonfirmasiBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 131, 430, -1));

        txtTagihanBarangMasuk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTagihanBarangMasuk.setText("Rp. 0,00");
        panelDeskripsiBarangMasuk.add(txtTagihanBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 50, 200, -1));

        jLabel43.setText("Tagihan");
        panelDeskripsiBarangMasuk.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        panelBarangMasuk.add(panelDeskripsiBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 460, 1100, 190));

        getContentPane().add(panelBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelPembayaranPelanggan.setName("panelPembayaranPelanggan"); // NOI18N
        panelPembayaranPelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDaftarBarangMasuk1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("DAFTAR PELANGGAN");

        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePelangganMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablePelanggan);

        cmbPelanggan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pelanggan -" }));
        cmbPelanggan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelanggan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDaftarBarangMasuk1Layout = new javax.swing.GroupLayout(panelDaftarBarangMasuk1);
        panelDaftarBarangMasuk1.setLayout(panelDaftarBarangMasuk1Layout);
        panelDaftarBarangMasuk1Layout.setHorizontalGroup(
            panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(panelDaftarBarangMasuk1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDaftarBarangMasuk1Layout.setVerticalGroup(
            panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk1Layout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );

        panelPembayaranPelanggan.add(panelDaftarBarangMasuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 1104, 400));

        panelDeskripsiBarangMasuk1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk1.setPreferredSize(new java.awt.Dimension(1100, 185));
        panelDeskripsiBarangMasuk1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setText("DESKRIPSI TRANSAKSI PEMBAYARAN PEMASOKKAN");
        panelDeskripsiBarangMasuk1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 380, 33));

        jLabel20.setText("Nama Toko");
        panelDeskripsiBarangMasuk1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 50, 99, -1));

        txtNamaToko.setText("-");
        panelDeskripsiBarangMasuk1.add(txtNamaToko, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 240, -1));

        txtJumlahTransaksiPelanggan.setText("-");
        panelDeskripsiBarangMasuk1.add(txtJumlahTransaksiPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 240, -1));

        jLabel21.setText("Jumlah transaksi");
        panelDeskripsiBarangMasuk1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel22.setText("Hutang");
        panelDeskripsiBarangMasuk1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 99, -1));

        txtHutangPelanggan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHutangPelanggan.setText("Rp. 0,00");
        txtHutangPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHutangPelangganActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(txtHutangPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 240, -1));

        jLabel23.setText("Kembali ");
        panelDeskripsiBarangMasuk1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        txtBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBayar.setText("0");
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 179, -1));

        jLabel24.setText("Rp");
        panelDeskripsiBarangMasuk1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        jLabel25.setText("Sisa hutang pada pelanggan");
        panelDeskripsiBarangMasuk1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        txtSisaHutang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSisaHutang.setText("Rp. 0,00");
        panelDeskripsiBarangMasuk1.add(txtSisaHutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 200, -1));

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setText("KETERANGAN PEMBAYARAN");
        panelDeskripsiBarangMasuk1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        lblKeteranganPembayaran.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblKeteranganPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKeteranganPembayaran.setText("[Keterangan]");
        lblKeteranganPembayaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk1.add(lblKeteranganPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 32, 428, 55));

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, 209, -1));

        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 200, -1));

        jLabel27.setText("Banyak uang dibayar");
        panelDeskripsiBarangMasuk1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        txtKembalian.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKembalian.setText("0");
        txtKembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKembalianKeyReleased(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(txtKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 179, -1));

        jLabel28.setText("Rp");
        panelDeskripsiBarangMasuk1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jLabel29.setText("Nama Pelanggan");
        panelDeskripsiBarangMasuk1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtNamaPelanggan1.setText("-");
        panelDeskripsiBarangMasuk1.add(txtNamaPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 240, -1));

        panelPembayaranPelanggan.add(panelDeskripsiBarangMasuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 414, -1, 221));

        getContentPane().add(panelPembayaranPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        PanelTransaksiBarangKeluar.setName("PanelTransaksiBarangKeluar"); // NOI18N

        panelDaftarBarangMasuk2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setText("DAFTAR BARANG KELUAR");

        cmbStatusBarangKeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disiapkan", "Dikirim", "Diterima", "Gagal" }));
        cmbStatusBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusBarangKeluarActionPerformed(evt);
            }
        });

        jLabel44.setText("Status");

        tableBarangKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangKeluarMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableBarangKeluar);

        tableDetailBarangKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDetailBarangKeluar.setEnabled(false);
        tableDetailBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailBarangKeluarMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tableDetailBarangKeluar);

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel45.setText("DAFTAR DETAIL BARANG KELUAR");

        jLabel46.setText("JUMLAH KODI");

        txtJumlahKodiBarangKeluar.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        txtJumlahKodiBarangKeluar.setEditor(new javax.swing.JSpinner.NumberEditor(txtJumlahKodiBarangKeluar, ""));
        txtJumlahKodiBarangKeluar.setEnabled(false);

        btnTambahkanPengirimanBarangKeluar.setText("TAMBAHKAN KE PENGIRIMAN");
        btnTambahkanPengirimanBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahkanPengirimanBarangKeluarActionPerformed(evt);
            }
        });

        jLabel47.setText("BARANG");

        txtBarangBarangKeluar.setText("[ NAMA BARANG ] ");
        txtBarangBarangKeluar.setEnabled(false);

        txtUkuranBarangKeluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUkuranBarangKeluar.setText("[ UKURAN ]");
        txtUkuranBarangKeluar.setEnabled(false);

        jLabel48.setText("Pelanggan");

        cmbNamaPelangganBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaPelangganBarangKeluarActionPerformed(evt);
            }
        });

        cbPengiriman.setText("Lakukan Pengiriman");
        cbPengiriman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPengirimanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDaftarBarangMasuk2Layout = new javax.swing.GroupLayout(panelDaftarBarangMasuk2);
        panelDaftarBarangMasuk2.setLayout(panelDaftarBarangMasuk2Layout);
        panelDaftarBarangMasuk2Layout.setHorizontalGroup(
            panelDaftarBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDaftarBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBarangBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(txtUkuranBarangKeluar)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(txtJumlahKodiBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTambahkanPengirimanBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbPengiriman)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbStatusBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNamaPelangganBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDaftarBarangMasuk2Layout.setVerticalGroup(
            panelDaftarBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatusBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel48)
                    .addComponent(cmbNamaPelangganBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPengiriman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDaftarBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtJumlahKodiBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahkanPengirimanBarangKeluar)
                    .addComponent(jLabel47)
                    .addComponent(txtBarangBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUkuranBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
        );

        panelDataPengirimanBarangBarangKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel49.setText("DATA PENGIRIMAN BARANG");

        txtIDPengirimanBarangKeluar.setText("[ ID PENGIRIMAN ]");
        txtIDPengirimanBarangKeluar.setEnabled(false);

        tableKarungPengirimanBarangKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKarungPengirimanBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKarungPengirimanBarangKeluarMouseClicked(evt);
            }
        });
        tableKarungPengirimanBarangKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableKarungPengirimanBarangKeluarKeyTyped(evt);
            }
        });
        jScrollPane10.setViewportView(tableKarungPengirimanBarangKeluar);

        jLabel50.setText("JUMLAH KODI");

        jLabel51.setText("JUMLAH KARUNG");

        txtJumlahKodiPengirimanBarang.setText("[ JUMLAH KODI ]");

        txtJumlahKarungPengirimanBarang.setText("[ JUMLAH KARUNG ]");

        jLabel52.setText("KARUNG PENGIRIMAN  ");

        javax.swing.GroupLayout panelDataPengirimanBarangBarangKeluarLayout = new javax.swing.GroupLayout(panelDataPengirimanBarangBarangKeluar);
        panelDataPengirimanBarangBarangKeluar.setLayout(panelDataPengirimanBarangBarangKeluarLayout);
        panelDataPengirimanBarangBarangKeluarLayout.setHorizontalGroup(
            panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDPengirimanBarangKeluar))
                    .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createSequentialGroup()
                        .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createSequentialGroup()
                                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(txtJumlahKodiPengirimanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(txtJumlahKarungPengirimanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDataPengirimanBarangBarangKeluarLayout.setVerticalGroup(
            panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtIDPengirimanBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataPengirimanBarangBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahKodiPengirimanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlahKarungPengirimanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setText("KETERANGAN KARUNG");

        txtKeteranganKarungBarangKeluar.setColumns(20);
        txtKeteranganKarungBarangKeluar.setLineWrap(true);
        txtKeteranganKarungBarangKeluar.setRows(5);
        txtKeteranganKarungBarangKeluar.setText("TULIS KETERANGAN KARUNG DI SINI");
        txtKeteranganKarungBarangKeluar.setAutoscrolls(false);
        txtKeteranganKarungBarangKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtKeteranganKarungBarangKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKeteranganKarungBarangKeluarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeteranganKarungBarangKeluarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeteranganKarungBarangKeluarKeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(txtKeteranganKarungBarangKeluar);

        txtBatasKeteranganBarangKeluar.setText("0");

        dummy1.setText("/");

        txtBatasKeteranganBarangKeluar1.setText("50");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(18, 18, 18)
                        .addComponent(txtBatasKeteranganBarangKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dummy1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBatasKeteranganBarangKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtBatasKeteranganBarangKeluar)
                    .addComponent(dummy1)
                    .addComponent(txtBatasKeteranganBarangKeluar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTulisKeteranganBarangKeluar.setText("TULIS KETERANGAN");
        btnTulisKeteranganBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTulisKeteranganBarangKeluarActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableDetailKarungPengirimanBarangKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableDetailKarungPengirimanBarangKeluar.setEnabled(false);
        tableDetailKarungPengirimanBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailKarungPengirimanBarangKeluarMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tableDetailKarungPengirimanBarangKeluar);

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel54.setText("DETAIL KARUNG");

        txtNomorKarungBarangKeluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomorKarungBarangKeluar.setText("[ NO KARUNG ]");
        txtNomorKarungBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomorKarungBarangKeluarActionPerformed(evt);
            }
        });
        txtNomorKarungBarangKeluar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNomorKarungBarangKeluarPropertyChange(evt);
            }
        });

        jLabel55.setText("Nomor Karung");

        jLabel56.setText("AKSI");

        btnBatalkanKarungBarangKeluar.setText("BATALKAN PENGARUNGAN");
        btnBatalkanKarungBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalkanKarungBarangKeluarActionPerformed(evt);
            }
        });

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("ISI KETERANGAN DI SAMPING");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomorKarungBarangKeluar))
                            .addComponent(jLabel54))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addComponent(btnBatalkanKarungBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomorKarungBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatalkanKarungBarangKeluar)
                .addContainerGap())
        );

        btnKonfirmasiPemberangkatanBarangKeluar.setText("KONFIRMASI PEMBERANGKATAN");
        btnKonfirmasiPemberangkatanBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiPemberangkatanBarangKeluarActionPerformed(evt);
            }
        });

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("PENGEMUDI");

        cmbPengemudiBarangKeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pengemudi -" }));

        javax.swing.GroupLayout PanelTransaksiBarangKeluarLayout = new javax.swing.GroupLayout(PanelTransaksiBarangKeluar);
        PanelTransaksiBarangKeluar.setLayout(PanelTransaksiBarangKeluarLayout);
        PanelTransaksiBarangKeluarLayout.setHorizontalGroup(
            PanelTransaksiBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTransaksiBarangKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTransaksiBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTransaksiBarangKeluarLayout.createSequentialGroup()
                        .addComponent(panelDataPengirimanBarangBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelTransaksiBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTulisKeteranganBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKonfirmasiPemberangkatanBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPengemudiBarangKeluar, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panelDaftarBarangMasuk2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelTransaksiBarangKeluarLayout.setVerticalGroup(
            PanelTransaksiBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTransaksiBarangKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDaftarBarangMasuk2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTransaksiBarangKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDataPengirimanBarangBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelTransaksiBarangKeluarLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTulisKeteranganBarangKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPengemudiBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKonfirmasiPemberangkatanBarangKeluar))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(PanelTransaksiBarangKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        panelPembayaranPemasok.setMinimumSize(new java.awt.Dimension(1100, 652));
        panelPembayaranPemasok.setName("panelPembayaranPemasok"); // NOI18N
        panelPembayaranPemasok.setPreferredSize(new java.awt.Dimension(1100, 652));
        panelPembayaranPemasok.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDaftarBarangMasuk3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel58.setText("DAFTAR PEMASOK");

        tablePemasokPembayaranPemasok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePemasokPembayaranPemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePemasokPembayaranPemasokMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tablePemasokPembayaranPemasok);

        cmbPemasokPembayaranPemasok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pemasok -" }));
        cmbPemasokPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemasokPembayaranPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDaftarBarangMasuk3Layout = new javax.swing.GroupLayout(panelDaftarBarangMasuk3);
        panelDaftarBarangMasuk3.setLayout(panelDaftarBarangMasuk3Layout);
        panelDaftarBarangMasuk3Layout.setHorizontalGroup(
            panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                    .addGroup(panelDaftarBarangMasuk3Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDaftarBarangMasuk3Layout.setVerticalGroup(
            panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk3Layout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelPembayaranPemasok.add(panelDaftarBarangMasuk3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1100, 400));

        panelDeskripsiBarangMasuk2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk2.setPreferredSize(new java.awt.Dimension(1100, 185));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel59.setText("DESKRIPSI TRANSAKSI PEMBAYARAN PEMASOKKAN");

        jLabel60.setText("Nama");

        txtNamaPemasokPembayaranPemasok.setText("-");

        txtJumlahTransaksiPemasokPembayaranPemasok.setText("-");

        jLabel61.setText("Jumlah transaksi");

        jLabel62.setText("Hutang");

        txtHutangPemasokPembayaranPemasok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHutangPemasokPembayaranPemasok.setText("Rp. 0,00");

        jLabel63.setText("Kembali ");

        txtBayarPembayaranPemasok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBayarPembayaranPemasok.setText("0");
        txtBayarPembayaranPemasok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarPembayaranPemasokKeyReleased(evt);
            }
        });

        jLabel64.setText("Rp");

        jLabel65.setText("Sisa hutang pada pemasok");

        txtSisaHutangPembayaranPemasok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSisaHutangPembayaranPemasok.setText("Rp. 0,00");

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel66.setText("KETERANGAN PEMBAYARAN");

        lblKeteranganPembayaranPembayaranPemasok.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblKeteranganPembayaranPembayaranPemasok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKeteranganPembayaranPembayaranPemasok.setText("[Keterangan]");
        lblKeteranganPembayaranPembayaranPemasok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBatalPembayaranPemasok.setText("Batal");
        btnBatalPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPembayaranPemasokActionPerformed(evt);
            }
        });

        btnBayarPembayaranPemasok.setText("Bayar");
        btnBayarPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarPembayaranPemasokActionPerformed(evt);
            }
        });

        jLabel67.setText("Banyak uang dibayar");

        txtKembalianPembayaranPemasok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKembalianPembayaranPemasok.setText("0");
        txtKembalianPembayaranPemasok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKembalianPembayaranPemasokKeyReleased(evt);
            }
        });

        jLabel68.setText("Rp");

        javax.swing.GroupLayout panelDeskripsiBarangMasuk2Layout = new javax.swing.GroupLayout(panelDeskripsiBarangMasuk2);
        panelDeskripsiBarangMasuk2.setLayout(panelDeskripsiBarangMasuk2Layout);
        panelDeskripsiBarangMasuk2Layout.setHorizontalGroup(
            panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNamaPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtJumlahTransaksiPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtHutangPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(7, 7, 7)))
                        .addGap(23, 23, 23)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel67)
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addGap(15, 15, 15)
                                .addComponent(txtBayarPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel65)
                            .addComponent(jLabel63)
                            .addComponent(txtSisaHutangPembayaranPemasok)))
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(jLabel64)
                        .addGap(15, 15, 15)
                        .addComponent(txtKembalianPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel66))
                            .addComponent(lblKeteranganPembayaranPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBayarPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                            .addComponent(btnBatalPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelDeskripsiBarangMasuk2Layout.setVerticalGroup(
            panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(txtNamaPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtJumlahTransaksiPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)
                                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(txtHutangPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel67)
                                .addGap(14, 14, 14)
                                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(txtBayarPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(jLabel65)
                                .addGap(14, 14, 14)
                                .addComponent(txtSisaHutangPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel63)))
                        .addGap(4, 4, 4)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(txtKembalianPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel66)
                        .addGap(3, 3, 3)
                        .addComponent(lblKeteranganPembayaranPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBayarPembayaranPemasok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalPembayaranPemasok)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelPembayaranPemasok.add(panelDeskripsiBarangMasuk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 1100, 210));

        getContentPane().add(panelPembayaranPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 630));

        panelKonfirmasiKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelKonfirmasiKeluar.setName("panelKonfirmasiKeluar"); // NOI18N

        jLabel70.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel70.setText("KONFIRMASI PENGIRIMAN BARANG");

        tablePengirimanKonfirmasiKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePengirimanKonfirmasiKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePengirimanKonfirmasiKeluarMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tablePengirimanKonfirmasiKeluar);

        tableKarungKonfirmasiKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKarungKonfirmasiKeluar.setEnabled(false);
        jScrollPane15.setViewportView(tableKarungKonfirmasiKeluar);

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel71.setText("DATA PENGIRIMAN");

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel72.setText("DATA DETAIL PENGIRIMAN");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel73.setText("AKSI");

        rbDiterimaKonfirmasiKeluar.setSelected(true);
        rbDiterimaKonfirmasiKeluar.setText("Diterima");
        rbDiterimaKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDiterimaKonfirmasiKeluarActionPerformed(evt);
            }
        });

        rbGagalKonfirmasiKeluar.setText("Gagal");
        rbGagalKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGagalKonfirmasiKeluarActionPerformed(evt);
            }
        });

        jLabel74.setText("Nama Pelanggan");

        txtNamaPelangganKonfirmasiKeluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNamaPelangganKonfirmasiKeluar.setText("-");
        txtNamaPelangganKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganKonfirmasiKeluarActionPerformed(evt);
            }
        });
        txtNamaPelangganKonfirmasiKeluar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNamaPelangganKonfirmasiKeluarPropertyChange(evt);
            }
        });

        jLabel75.setText("Kode Transaksi");

        txtKodeTransaksiKonfirmasiKeluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKodeTransaksiKonfirmasiKeluar.setText("-");
        txtKodeTransaksiKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeTransaksiKonfirmasiKeluarActionPerformed(evt);
            }
        });
        txtKodeTransaksiKonfirmasiKeluar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtKodeTransaksiKonfirmasiKeluarPropertyChange(evt);
            }
        });

        btnKonfirmasiKonfirmasiKeluar.setText("KONFIRMASI");
        btnKonfirmasiKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiKonfirmasiKeluarActionPerformed(evt);
            }
        });

        txtStatusPengirimanKonfirmasiKeluar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtStatusPengirimanKonfirmasiKeluar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStatusPengirimanKonfirmasiKeluar.setText("Diterima");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbGagalKonfirmasiKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbDiterimaKonfirmasiKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodeTransaksiKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaPelangganKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKonfirmasiKonfirmasiKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(txtStatusPengirimanKonfirmasiKeluar))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbDiterimaKonfirmasiKeluar)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbGagalKonfirmasiKeluar)
                            .addComponent(txtNamaPelangganKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKodeTransaksiKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txtStatusPengirimanKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKonfirmasiKonfirmasiKeluar)))
                .addContainerGap())
        );

        jLabel76.setText("Pelanggan");

        cmbNamaPelangganKonfirmasiKeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pelanggan -" }));
        cmbNamaPelangganKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaPelangganKonfirmasiKeluarActionPerformed(evt);
            }
        });

        btnSemuaKonfirmasiKeluar.setText("Semua");
        btnSemuaKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemuaKonfirmasiKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKonfirmasiKeluarLayout = new javax.swing.GroupLayout(panelKonfirmasiKeluar);
        panelKonfirmasiKeluar.setLayout(panelKonfirmasiKeluarLayout);
        panelKonfirmasiKeluarLayout.setHorizontalGroup(
            panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKonfirmasiKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKonfirmasiKeluarLayout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSemuaKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNamaPelangganKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelKonfirmasiKeluarLayout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(jLabel72)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKonfirmasiKeluarLayout.setVerticalGroup(
            panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKonfirmasiKeluarLayout.createSequentialGroup()
                .addGroup(panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKonfirmasiKeluarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelKonfirmasiKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel76)
                                .addComponent(cmbNamaPelangganKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSemuaKonfirmasiKeluar))
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelKonfirmasiKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnPengolahanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengolahanDataActionPerformed
        show(panelPengolahanData);
        
    }//GEN-LAST:event_btnPengolahanDataActionPerformed

    private void panelKiriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKiriMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, this.getSize().toString());
    }//GEN-LAST:event_panelKiriMouseClicked

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
        barang = ControllerBarang.getSelectedRowBarangPemasokkan(tableBarang);

        txtBarang.setText(barang.getNama_Barang());
        txtUkuran.setText(barang.getUkuran_Barang());
        txtPemasok.setText(ControllerPemasok.getPemasokNama(String.valueOf(barang.getPmsk_Barang())));
        TotalHarga = (int) Math.round(barang.getHargaKodian_Barang());
        Harga = (int) Math.round(barang.getHargaKodian_Barang());
        txtTotalHarga.setText(kursIndonesia.format((int) Math.round(barang.getHargaKodian_Barang())));
    }//GEN-LAST:event_tableBarangMouseClicked

    private void cmbPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemasokActionPerformed
        // TODO add your handling code here:
        modelKeranjang.getDataVector().removeAllElements();

        modelKeranjang.fireTableDataChanged();
        clearBarangMasuk();
        addDataBarang((String) cmbPemasok.getSelectedItem());
    }//GEN-LAST:event_cmbPemasokActionPerformed

    private void txtPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPemasokActionPerformed

    private void txtJumlahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtJumlahStateChanged
        // TODO add your handling code here:
        try{
            int jumlah = Integer.parseInt(txtJumlah.getValue().toString());
            TotalHarga = jumlah * Harga;
            txtTotalHarga.setText(kursIndonesia.format((TotalHarga)));

            //System.out.println(total);
        }catch(Exception ex){
            System.out.println("Error cuy txtJumlahPropertyChange "+ex);
        }
    }//GEN-LAST:event_txtJumlahStateChanged

    private void txtJumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtJumlahMouseClicked

    private void txtJumlahPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtJumlahPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_txtJumlahPropertyChange

    private void txtTotalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHargaActionPerformed

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        // TODO add your handling code here:
        if(txtBarang.getText().equals("")){
            return;
        }
        for(int i = 0 ; i < modelKeranjang.getRowCount() ; i ++){
            if(modelKeranjang.getValueAt(i, 1).toString().equals(txtBarang.getText()) && modelKeranjang.getValueAt(i, 2).toString().equals(txtUkuran.getText())){
                modelKeranjang.removeRow(i);
            }
        }
        Object[] rowData = new Object[]{modelKeranjang.getRowCount()+1,txtBarang.getText(),txtUkuran.getText(),txtPemasok.getText(),txtJumlah.getValue(),TotalHarga};
        modelKeranjang.addRow(rowData);
        clearBarangMasuk();
        hitungTotal();
        hitungKodi();
    }//GEN-LAST:event_btnKeranjangActionPerformed

    private void tableKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKeranjangMouseClicked
        // TODO add your handling code here:
        //barang = ControllerBarang.getSelectedRowBarangPemasokkan(tableKeranjang);
        
        txtBarang.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 1));
        txtUkuran.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 2));
        txtPemasok.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 3));
        Harga = ((int) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 5)) / ((int) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 4));
        txtTotalHarga.setText(kursIndonesia.format((int) Math.round(Harga)));
    }//GEN-LAST:event_tableKeranjangMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        modelKeranjang.getDataVector().removeAllElements();

        modelKeranjang.fireTableDataChanged();
        clearBarangMasuk();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pemasokkan pemasokkan = new Pemasokkan();
        pemasokkan.setPmsk_id(date.format(new Date())+"-"+ControllerPemasokkan.getLastID());
        pemasokkan.setPms_id(ControllerPemasok.getPemasokId((String)cmbPemasok.getSelectedItem()));
        pemasokkan.setPmsk_jumlah_barang(Integer.valueOf(txtjumlah_kodi.getText())*20);
        pemasokkan.setPmsk_jumlah_kodi(Integer.valueOf(txtjumlah_kodi.getText()));
        pemasokkan.setPmsk_tgl_transaksi(new Date());
        pemasokkan.setPmsk_uang_dibayar(0);
        pemasokkan.setPmsk_total_uang(TotalBiaya);
        //pemasokkan.setKry_id("202007010002");
        pemasokkan.setKry_id(karyawan_login.getKry_id());
        pemasokkan.setStatus("Proses");

        System.out.println(pemasokkan.getAllData());
        ControllerPemasokkan.simpanPemasokkan(pemasokkan);
        DetailPemasokkan detailPemasok = new DetailPemasokkan();
        detailPemasok.setPmsk_id(pemasokkan.getPmsk_id());
        for(int i = 0 ; i < tableKeranjang.getRowCount();i++){
            barang = ControllerBarang.getDatabyNamaUkuran(modelKeranjang.getValueAt(i, 1).toString(), modelKeranjang.getValueAt(i, 2).toString());
            detailPemasok.setB_id(Integer.toString(barang.getID_Barang()));
            detailPemasok.setDetail_jumlah_kodi((int)modelKeranjang.getValueAt(i, 4));
            detailPemasok.setDetail_jumlah_barang(((int)modelKeranjang.getValueAt(i, 4)) * 20);
            detailPemasok.setDetail_harga((int)modelKeranjang.getValueAt(i, 5));
            ControllerPemasokkan.simpanDetailPemasokkan(detailPemasok);
        }
        JOptionPane.showMessageDialog(this, "Berhasil memporses....");
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnPemesananPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemesananPemasokActionPerformed
        // TODO add your handling code here:
        show(panelPemasokkan);
    }//GEN-LAST:event_btnPemesananPemasokActionPerformed

    private void btnPemesananPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemesananPelangganActionPerformed
        // TODO add your handling code here:
        show(panelPemesanan);
    }//GEN-LAST:event_btnPemesananPelangganActionPerformed

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuUtamaActionPerformed
        // TODO add your handling code here:
        show(panelDashboard);
    }//GEN-LAST:event_btnMenuUtamaActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        // TODO add your handling code here:
        CRUDBarang karyawan = new CRUDBarang();
        karyawan.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnJBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJBarangActionPerformed
        // TODO add your handling code here:
        CRUDJenisBarang barang = new CRUDJenisBarang();
        barang.setVisible(true);
    }//GEN-LAST:event_btnJBarangActionPerformed

    private void btnPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemasokActionPerformed
        // TODO add your handling code here:
        CRUDPemasok pemasok = new CRUDPemasok();
        pemasok.setVisible(true);
    }//GEN-LAST:event_btnPemasokActionPerformed

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
        // TODO add your handling code here:
        CRUDPelanggan pelanggan = new CRUDPelanggan();
        pelanggan.setVisible(true);
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void btnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaryawanActionPerformed
        // TODO add your handling code here:
        CRUDKaryawan karyawan = new CRUDKaryawan();
        karyawan.setVisible(true);
    }//GEN-LAST:event_btnKaryawanActionPerformed

    private void tblBarangPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangPemesananMouseClicked
        // TODO add your handling code here:
        barangPemesanan = ControllerBarang.getSelectedRowBarangPemasokkan(tblBarangPemesanan);
        // from 0 to 9, in 1.0 steps start value 5  
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 0, Integer.parseInt(tblBarangPemesanan.getValueAt(tblBarangPemesanan.getSelectedRow(), 5).toString()), 1);  
        
        txtJumlahPemesanan.setModel(model1);
        txtBarangPemesanan.setText(barangPemesanan.getNama_Barang());
        txtUkuranPemesanan.setText(barangPemesanan.getUkuran_Barang());

        TotalHargaPemesanan = (int) Math.round(barangPemesanan.getHargaKodian_Barang());
        HargaPemesanan = (int) Math.round(barangPemesanan.getHargaKodian_Barang());
        txtTotalHargaPemesanan.setText(kursIndonesia.format((int) Math.round(barangPemesanan.getHargaKodian_Barang())));
    }//GEN-LAST:event_tblBarangPemesananMouseClicked

    private void txtJumlahKodiPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahKodiPemesananMouseClicked
        // TODO add your handling code here:
        //        getSumJumlahKodi();
    }//GEN-LAST:event_txtJumlahKodiPemesananMouseClicked

    private void txtJumlahKodiPemesananMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahKodiPemesananMouseEntered
        // TODO add your handling code here:
        //getSumJumlahKodi();
    }//GEN-LAST:event_txtJumlahKodiPemesananMouseEntered

    private void txtTotalPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTotalPemesananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPemesananMouseClicked

    private void txtTotalPemesananMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTotalPemesananMouseEntered
        // TODO add your handling code here:
        double temp1,temp2,temp3, total = 0.0;

        int i = tblKeranjangPemesanan.getSelectedRow();
        if(i == -1){
            return;
        }
        int j = tblKeranjangPemesanan.getModel().getRowCount();

        for (int k = 0; k < j; k++){
            temp1 = (Double.parseDouble((String) tblKeranjangPemesanan.getValueAt(k, 3)));
            temp2 = (Double.parseDouble((String) tblKeranjangPemesanan.getValueAt(k, 5)));
            temp3 = temp1 * temp2;
            total = total + temp3;
        }
        txtTotalPemesanan.setText(String.valueOf(total));
    }//GEN-LAST:event_txtTotalPemesananMouseEntered

    private void txtTotalPemesananKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPemesananKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPemesananKeyReleased

    private void tblKeranjangPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeranjangPemesananMouseClicked
        // TODO add your handling code here:

        txtBarangPemesanan.setText((String) tblKeranjangPemesanan.getValueAt(tblKeranjangPemesanan.getSelectedRow(), 1));
        txtUkuranPemesanan.setText((String) tblKeranjangPemesanan.getValueAt(tblKeranjangPemesanan.getSelectedRow(), 2));

        TotalHargaPemesanan = (int)(tblKeranjangPemesanan.getValueAt(tblKeranjangPemesanan.getSelectedRow(), 4));
        HargaPemesanan = (TotalHargaPemesanan / (int) tblKeranjangPemesanan.getValueAt(tblKeranjangPemesanan.getSelectedRow(), 3));
        txtTotalHargaPemesanan.setText(kursIndonesia.format((int) Math.round(HargaPemesanan)));
    }//GEN-LAST:event_tblKeranjangPemesananMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        modelKeranjangPemesanan.getDataVector().removeAllElements();

        modelKeranjangPemesanan.fireTableDataChanged();
        clearPemesanan();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(cmbPelanggan.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Isi data dengan benar!");
            return;
        }
        if(txtJumlahKodiPemesanan.toString().equals("0")){
            JOptionPane.showMessageDialog(this, "Isi data dengan benar!");
            return;
        }
        int stock, stock2 = 0;
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        String IdPelanggan = (String) cmbPelanggan.getSelectedItem();
        //String namaKry = lblnama.getText();

        if (cmbPelanggan.getSelectedItem().equals("== Pilih Pelanggan ==") || txtTotalPemesanan.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
        }
        else{

            try{
                connection.stat = connection.conn.createStatement();
                String sql = "SELECT pgn_id,pgn_nama_toko FROM Pelanggan WHERE pgn_nama_toko = '" + cmbPelanggan.getSelectedItem() + "'";
                connection.result = connection.stat.executeQuery(sql);

                while (connection.result.next()){
                    IdPelanggan = connection.result.getString("pgn_id");
                }

                connection.stat = connection.conn.createStatement();
                //    String sql1 = "SELECT kry_id,kry_nama FROM Karyawan WHERE kry_nama = '" + lblnama.getText()+ "'";
                //     connection.result = connection.stat.executeQuery(sql1);

                while (connection.result.next()){
                    //        namaKry = connection.result.getString("kry_id");
                }

                psn.setPmsn_id(formatDate.format(new Date()) +"-"+ controllerPemesanan.getLastID());
                psn.setPgn_id(Integer.parseInt(IdPelanggan));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                psn.setPmsn_tgl_transaksi(date);
                psn.setPmsn_jumlah_barang((Integer.parseInt(txtJumlahKodiPemesanan.getText()) * 20));
                psn.setPmsn_jumlah_kodi(Integer.parseInt(txtJumlahKodiPemesanan.getText()));
                psn.setPmsn_total_uang(Double.parseDouble(String.valueOf(TotalBiayaPemesanan)));
                psn.setPmsn_Status("Disiapkan");
                //psn.setKry_id("202007010002");
                psn.setKry_id(karyawan_login.getKry_id());
                controllerPemesanan.simpanPemesanan(psn);

                DetailPemesanan detailPemesanan = new DetailPemesanan();
                detailPemesanan.setPmsn_id(psn.getPmsn_id());
                for(int i = 0 ; i < tblKeranjangPemesanan.getRowCount();i++){
                    barangPemesanan = ControllerBarang.getDatabyNamaUkuran(tblKeranjangPemesanan.getValueAt(i, 1).toString(), tblKeranjangPemesanan.getValueAt(i, 2).toString());
                    detailPemesanan.setB_id(Integer.toString(barangPemesanan.getID_Barang()));
                    detailPemesanan.setDetail_jumlah_kodi((int)tblKeranjangPemesanan.getValueAt(i, 3));
                    detailPemesanan.setDetail_jumlah_barang(((int)tblKeranjangPemesanan.getValueAt(i, 3)) * 20);
                    detailPemesanan.setDetail_total_uang((int)tblKeranjangPemesanan.getValueAt(i, 4));
                    controllerPemesanan.simpanDetailPemesanan(detailPemesanan);
                    controllerBarangPemesanan.updateJumlahBarangDipesan(tblKeranjangPemesanan.getValueAt(i, 3).toString(),tblKeranjangPemesanan.getValueAt(i, 1).toString(),tblKeranjangPemesanan.getValueAt(i, 2).toString());
                }

                JOptionPane.showMessageDialog(this, "Berhasil menambahkan pesanan..");
                clearPesananPemesanan();

            }
            catch (SQLException ex){
                System.out.println("Terjadi error saat insert " + ex);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void cmbPelangganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPelangganItemStateChanged
        // TODO add your handling code here:
        try{
            DBConnect c = new DBConnect();
            c.stat = c.conn.createStatement();
            String sql = "SELECT * FROM Pelanggan";
            c.result = c.stat.executeQuery(sql);

            while (c.result.next()) {
                if(c.result.getString("pgn_nama_toko").equals(cmbPelanggan.getSelectedItem().toString())){
                    txtNamaPelanggan.setText(c.result.getString("pgn_nama"));
                }
            }
            c.stat.close();
            c.result.close();

        } catch (SQLException ex){
            System.out.println("Terjadi error"+ex);
        }
    }//GEN-LAST:event_cmbPelangganItemStateChanged

    private void txtJumlahPemesananStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtJumlahPemesananStateChanged
        // TODO add your handling code here:
        try{
            int jumlah = Integer.parseInt(txtJumlahPemesanan.getValue().toString());
            TotalHargaPemesanan = jumlah * HargaPemesanan;
            txtTotalHargaPemesanan.setText(kursIndonesia.format((TotalHargaPemesanan)));

            //System.out.println(total);
        }catch(Exception ex){
            System.out.println("Error cuy txtJumlahPropertyChange "+ex);
        }
    }//GEN-LAST:event_txtJumlahPemesananStateChanged

    private void txtJumlahPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahPemesananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahPemesananMouseClicked

    private void txtJumlahPemesananPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtJumlahPemesananPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahPemesananPropertyChange

    private void txtTotalHargaPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHargaPemesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHargaPemesananActionPerformed

    private void btnKeranjangPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangPemesananActionPerformed
        // TODO add your handling code here:
        if(txtBarangPemesanan.getText().equals("")){
            return;
        }
        for(int i = 0 ; i < modelKeranjangPemesanan.getRowCount() ; i ++){
            if(modelKeranjangPemesanan.getValueAt(i, 1).toString().equals(txtBarangPemesanan.getText()) && modelKeranjangPemesanan.getValueAt(i, 2).toString().equals(txtUkuranPemesanan.getText())){
                modelKeranjangPemesanan.removeRow(i);
            }
        }
        hitungTotalPemesanan();
        hitungKodiPemesanan();
        Object[] rowData = new Object[]{modelKeranjangPemesanan.getRowCount()+1,txtBarangPemesanan.getText(),txtUkuranPemesanan.getText(), txtJumlahPemesanan.getValue(),TotalHargaPemesanan};
        modelKeranjangPemesanan.addRow(rowData);
        clearPemesanan();

    }//GEN-LAST:event_btnKeranjangPemesananActionPerformed

    private void cmbStatusBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusBarangMasukActionPerformed
        // TODO add your handling code here:
        clearBarangMasuk();
        modelDetailBarangMasuk.getDataVector().removeAllElements();
        modelDetailBarangMasuk.fireTableDataChanged();

        modelBarangMasuk.getDataVector().removeAllElements();
        modelBarangMasuk.fireTableDataChanged();
        ControllerTableBarangBarangMasuk.addDataBarangMasuk(modelBarangMasuk, (String) cmbStatusBarangMasuk.getSelectedItem());
    }//GEN-LAST:event_cmbStatusBarangMasukActionPerformed

    private void tableBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMasukMouseClicked
        // TODO add your handling code here:
        if (!cmbStatusBarangMasuk.getSelectedItem().toString().equals("Proses")) {
            btnKonfirmasiBarangMasuk.setEnabled(false);
        } else {
            btnKonfirmasiBarangMasuk.setEnabled(true);
        }

        pemasok = ControllerPemasokBarangMasuk.getDataPemasok(tableBarangMasuk.getValueAt(tableBarangMasuk.getSelectedRow(), 2).toString());
        perbaruiDetail();
        txtNamaPemasokBarangMasuk.setText(pemasok.getPms_nama());
        txtJumlahTransaksiBarangMasuk.setText(Integer.toString(pemasok.getPms_jumlah_transaksi()));
        txtUangTransaksiBarangMasuk.setText(kursIndonesia.format(pemasok.getPms_uang_transaksi()));
        txtHutangBarangMasuk.setText(kursIndonesia.format(pemasok.getPms_total_hutang()));

        if (!cmbStatusBarangMasuk.getSelectedItem().toString().equals("Proses")) {
            btnKonfirmasiBarangMasuk.setEnabled(false);
            txtSisaHutangBarangMasuk.setText(kursIndonesia.format(0));
            txtTagihanBarangMasuk.setText(kursIndonesia.format(0));
            txtUangDibayarBarangMasuk.setText("0");

            txtSisaHutangBarangMasuk.setEnabled(false);
            txtTagihanBarangMasuk.setEnabled(false);
            txtUangDibayarBarangMasuk.setEnabled(false);
        } else {
            txtSisaHutangBarangMasuk.setEnabled(true);
            txtTagihanBarangMasuk.setEnabled(true);
            txtUangDibayarBarangMasuk.setEnabled(true);
            btnKonfirmasiBarangMasuk.setEnabled(true);
            txtSisaHutangBarangMasuk.setText(kursIndonesia.format(pemasok.getPms_total_hutang()));
            txtTagihanBarangMasuk.setText(tableBarangMasuk.getValueAt(tableBarangMasuk.getSelectedRow(), 5).toString());

            try {
                // a = tagihan
                // b = bayar
                // c = total hutang
                // d = calon sisa hutang
                // d = (a+c)-d
                txtSisaHutangBarangMasuk.setText(kursIndonesia.format(((long) kursIndonesia.parse(txtTagihanBarangMasuk.getText()) + (long) kursIndonesia.parse(txtHutangBarangMasuk.getText())) - Long.parseLong(txtUangDibayarBarangMasuk.getText())));
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_tableBarangMasukMouseClicked

    private void tableDetailBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailBarangMasukMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDetailBarangMasukMouseClicked

    private void txtUangDibayarBarangMasukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUangDibayarBarangMasukKeyReleased
        // TODO add your handling code here:
        if (txtUangDibayarBarangMasuk.getText().equals("")) {
            txtSisaHutangBarangMasuk.setText(txtHutangBarangMasuk.getText());
            return;
        }

        try {
            // a = tagihan
            // b = bayar
            // c = total hutang
            // d = calon sisa hutang
            // d = (a+c)-d
            txtSisaHutangBarangMasuk.setText(kursIndonesia.format(((long) kursIndonesia.parse(txtTagihanBarangMasuk.getText()) + (long) kursIndonesia.parse(txtHutangBarangMasuk.getText())) - Long.parseLong(txtUangDibayarBarangMasuk.getText())));
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_txtUangDibayarBarangMasukKeyReleased

    private void btnSuksesBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuksesBarangMasukActionPerformed
        // TODO add your handling code here:
        txtUangDibayarBarangMasuk.setEnabled(true);
        lblKeteranganBarangMasuk.setText("Sukses");
    }//GEN-LAST:event_btnSuksesBarangMasukActionPerformed

    private void btnGagalBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGagalBarangMasukActionPerformed
        // TODO add your handling code here:
        txtUangDibayarBarangMasuk.setText("0");
        try {
            // a = tagihan
            // b = bayar
            // c = total hutang
            // d = calon sisa hutang
            // d = (a+c)-d
            txtSisaHutangBarangMasuk.setText(kursIndonesia.format(((long) kursIndonesia.parse(txtTagihanBarangMasuk.getText()) + (long) kursIndonesia.parse(txtHutangBarangMasuk.getText())) - Long.parseLong(txtUangDibayarBarangMasuk.getText())));
        } catch (Exception ex) {

        }
        lblKeteranganBarangMasuk.setText("Gagal");
        txtUangDibayarBarangMasuk.setEnabled(false);
    }//GEN-LAST:event_btnGagalBarangMasukActionPerformed

    private void btnKonfirmasiBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiBarangMasukActionPerformed
        // TODO add your handling code here:
        if (lblKeteranganBarangMasuk.getText().equals("[Keterangan]")) {
            JOptionPane.showMessageDialog(this, "Belum pilih status");
            return;
        }

        try {
            if (lblKeteranganBarangMasuk.getText().equals("Sukses")) {
                ControllerPemasokBarangMasuk.updateTransaksiPemasokkan(Long.parseLong(txtUangDibayarBarangMasuk.getText()), Long.parseLong((kursIndonesia.parse(txtSisaHutangBarangMasuk.getText())).toString()), pemasok.getPms_id().toString());
                for (int i = 0; i < tableDetailBarangMasuk.getRowCount(); i++) {
                    ControllerBarangBarangMasuk.updateJumlahBarang(tableDetailBarangMasuk.getValueAt(i, 3).toString(),tableDetailBarangMasuk.getValueAt(i, 1).toString(),tableDetailBarangMasuk.getValueAt(i, 2).toString());
                }
            }
            ControllerPemasokkanBarangMasuk.updateStatusPemasokkan(lblKeteranganBarangMasuk.getText(), (String) tableBarangMasuk.getValueAt(tableBarangMasuk.getSelectedRow(), 1));

            addDataBarangMasuk();
            JOptionPane.showMessageDialog(this, "Transaksi berhasil dilaksanakan!");
            clearBarangMasuk();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat menyimpan!");
        }
    }//GEN-LAST:event_btnKonfirmasiBarangMasukActionPerformed

    private void btnBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangMasukActionPerformed
        // TODO add your handling code here:
        show(panelBarangMasuk);
    }//GEN-LAST:event_btnBarangMasukActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void tablePelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePelangganMouseClicked
        // TODO add your handling code here:
        clearPembayaranPelanggan();
        pelangganPembayarnPelanggan = controlTablePembayaranPelanggan.getSelectedRowPelanggan(tablePelanggan);

        txtNamaToko.setText(pelangganPembayarnPelanggan.getPgn_nama_toko());
        txtNamaPelanggan1.setText(pelangganPembayarnPelanggan.getPgn_nama());
        txtJumlahTransaksiPelanggan.setText(Integer.toString(pelangganPembayarnPelanggan.getPgn_jumlah_transaksi()));
        txtHutangPelanggan.setText(kursIndonesia.format(pelangganPembayarnPelanggan.getPgn_total_hutang()));
        int status = pelangganPembayarnPelanggan.getPgn_uang_transaksi();

        if ( pelangganPembayarnPelanggan.getPgn_total_hutang() == 0){
            lblKeteranganPembayaran.setText("LUNAS");
            if (lblKeteranganPembayaran.getText().equals("LUNAS"))
            {
                btnBayar.setEnabled(false);
            }
        }
        else{
            lblKeteranganPembayaran.setText("BELUM LUNAS");
        }
    }//GEN-LAST:event_tablePelangganMouseClicked

    private void cmbPelanggan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelanggan1ActionPerformed
        // TODO add your handling code here:
        modelPelangganPembayaranPelanggan.getDataVector().removeAllElements();

        modelPelangganPembayaranPelanggan.fireTableDataChanged();
        clearPembayaranPelanggan();
        controlTablePembayaranPelanggan.addDataPelanggan(modelPelangganPembayaranPelanggan,(String) cmbPelanggan1.getSelectedItem());
    }//GEN-LAST:event_cmbPelanggan1ActionPerformed

    private void txtHutangPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHutangPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHutangPelangganActionPerformed

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        // TODO add your handling code here:

        if (txtBayar.getText().equals("")) {
            txtSisaHutang.setText(txtHutangPelanggan.getText());
            return;
        }

        try {

            txtSisaHutang.setText(kursIndonesia.format(((long) kursIndonesia.parse(txtHutangPelanggan.getText())) - Long.parseLong(txtBayar.getText())));
            txtKembalian.setText(kursIndonesia.format((Long.parseLong(txtBayar.getText())) - (long) kursIndonesia.parse(txtHutangPelanggan.getText())));

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_txtBayarKeyReleased

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setPmby_id(ControllerPembayaranPembayaranPelanggan.getLastID());
        pembayaran.setPgn_id(controlTablePembayaranPelanggan.getPelangganId((String)cmbPelanggan1.getSelectedItem()));
        pembayaran.setKry_id(karyawan_login.getKry_id());
        //pembayaran.setKry_id("202006290001");
        pembayaran.setPmby_tgl_transaksi(new Date());
        pembayaran.setPmby_uang_masuk(Integer.valueOf(txtBayar.getText()));

        ControllerPembayaranPembayaranPelanggan.simpanPembayaranPemasok(pembayaran);
        try {
            controllerPelangganPembayaranPelanggan.updateTransaksiPembayaranPelanggan(Long.parseLong(txtBayar.getText()), Long.parseLong((kursIndonesia.parse(txtSisaHutang.getText())).toString()), pelangganPembayarnPelanggan.getPgn_id().toString());

            controlTablePembayaranPelanggan.addDataPemasok(modelPelangganPembayaranPelanggan,(String) cmbPelanggan1.getSelectedItem());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat menyimpan ke pelangganPembayarnPelanggan!");
        }

        JOptionPane.showMessageDialog(this, "Pembayaran berhasil....");
        addDataPelangganPembayaranPelanggan();
        clearPembayaranPelanggan();
        cmbPelanggan1.setSelectedIndex(0);
        lblKeteranganPembayaran.setText("[Keterangan]");
    }//GEN-LAST:event_btnBayarActionPerformed

    private void txtKembalianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKembalianKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianKeyReleased

    private void btnPembayaranPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranPelangganActionPerformed
        // TODO add your handling code here:
        show(panelPembayaranPelanggan);
    }//GEN-LAST:event_btnPembayaranPelangganActionPerformed

    private void btnRefreshPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPemesananActionPerformed
        // TODO add your handling code here:
        //addColumnPemesanan();
        addDataPemesanan();
    }//GEN-LAST:event_btnRefreshPemesananActionPerformed

    private void cmbStatusBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusBarangKeluarActionPerformed
        // TODO add your handling code here:
        clearBarangKeluar();
        modelBarangKeluar.getDataVector().removeAllElements();

        modelBarangKeluar.fireTableDataChanged();

        modelDetailBarangKeluar.getDataVector().removeAllElements();

        modelDetailBarangKeluar.fireTableDataChanged();
        ControllerTabelBarangKeluar.addDataBarangKeluar(modelBarangKeluar, (String) cmbStatusBarangKeluar.getSelectedItem(), (String) cmbNamaPelangganBarangKeluar.getSelectedItem());
        if(!cmbStatusBarangKeluar.getSelectedItem().toString().equals("Disiapkan")){
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
        }else{
            btnTambahkanPengirimanBarangKeluar.setEnabled(true);
        }
    }//GEN-LAST:event_cmbStatusBarangKeluarActionPerformed

    private void tableBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangKeluarMouseClicked
        // TODO add your handling code here:

        perbaruiDetailBarangKeluar();
    }//GEN-LAST:event_tableBarangKeluarMouseClicked

    private void tableDetailBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailBarangKeluarMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tableDetailBarangKeluarMouseClicked

    private void btnTambahkanPengirimanBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahkanPengirimanBarangKeluarActionPerformed
        // TODO add your handling code here:
        //        if (tableDetailBarangKeluar.getSelectedRow() == -1) {
            //            JOptionPane.showMessageDialog(this, "Tidak ada data terpilih! ");
            //            return;
            //        }
        if(tableBarangKeluar.getValueAt(tableBarangKeluar.getSelectedRow(), 6).toString().equals("0")){
            JOptionPane.showMessageDialog(this, "Data sudah dimasukkan!");
            return;
        }
        int kode = 64;

        // jika kosong
        //       masukkan data semua
        //             jika semua data lebih dari 40
        //           masukkan 40 saja
        //                  sisanya masukkan ke karung berikutnya
        Object[] dataRowKarung = {"", "", "", "", ""};
        Object[] dataRowDetailKarung = {"", "", "", ""};
        for(int i = 0; i < tableDetailBarangKeluar.getRowCount(); i ++){
            //            tableDetailBarangKeluar.setRowSelectionInterval(kode, kode);
            try {
                txtBarangBarangKeluar.setText(tableDetailBarangKeluar.getValueAt(i, 1).toString());
                txtUkuranBarangKeluar.setText(tableDetailBarangKeluar.getValueAt(i, 2).toString());
                txtJumlahKodiBarangKeluar.setValue(Integer.parseInt((String) tableDetailBarangKeluar.getValueAt(i, 3)));
            } catch (Exception ex) {

            }
            if (tableKarungPengirimanBarangKeluar.getRowCount() == 0) {
                while ((int) txtJumlahKodiBarangKeluar.getValue() > 0) {
                    if ((int) txtJumlahKodiBarangKeluar.getValue() >= 40) {
                        Object[] data = {tableKarungPengirimanBarangKeluar.getRowCount() + 1, txtIDPengirimanBarangKeluar.getText() + (char) (kode + (tableKarungPengirimanBarangKeluar.getRowCount() + 1)), "ISI DI SAMPING", "40", ""};

                        //System.out.println(i);
                        Object[] dataDetail = {"", txtBarangBarangKeluar.getText(), txtUkuranBarangKeluar.getText(), "40"};

                        detailKarungIndexBarangKeluar.add(Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                        detailKarungBarangBarangKeluar.add(txtBarangBarangKeluar.getText());
                        detailKarungUkuranBarangKeluar.add(txtUkuranBarangKeluar.getText());
                        detailKarungKodiBarangKeluar.add("40");

                        //JOptionPane.showMessageDialog(this, Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                        dataRowDetailKarung = dataDetail;
                        dataRowKarung = data;

                        txtJumlahKodiBarangKeluar.setValue((int) txtJumlahKodiBarangKeluar.getValue() - 40);
                    } else {
                        Object[] data = {tableKarungPengirimanBarangKeluar.getRowCount() + 1, txtIDPengirimanBarangKeluar.getText() + (char) (kode + tableKarungPengirimanBarangKeluar.getRowCount() + 1), "ISI DI SAMPING", txtJumlahKodiBarangKeluar.getValue(), ""};
                        Object[] dataDetail = {"", txtBarangBarangKeluar.getText(), txtUkuranBarangKeluar.getText(), txtJumlahKodiBarangKeluar.getValue()};

                        //JOptionPane.showMessageDialog(this, "Masuk else");
                        detailKarungIndexBarangKeluar.add(Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                        detailKarungBarangBarangKeluar.add(txtBarangBarangKeluar.getText());
                        detailKarungUkuranBarangKeluar.add(txtUkuranBarangKeluar.getText());
                        detailKarungKodiBarangKeluar.add(txtJumlahKodiBarangKeluar.getValue().toString());

                        dataRowDetailKarung = dataDetail;
                        dataRowKarung = data;

                        txtJumlahKodiBarangKeluar.setValue(0);

                    }
                    modelKarungBarangKeluar.addRow(dataRowKarung);
                    //modelDetailKarungBarangKeluar.addRow(dataRowDetailKarung);
                }
                isiJumlahDetailKarungTransaksiBarangKeluar();
            } else {
                // ELSE UNTUK ROW LEBIH DARI 1
                //  jikasudah ada 1 karung
                // periksa apakah sudah 40 isinya
                // jika sudah, buat karung baru
                //
                // jika belum , masukkan ke detil karung dan ubah jumlahnya
                // ubah tulisan jumlah kodi nya, dan masukkan detitlnya sesuai row paling bawah
                while ((int) txtJumlahKodiBarangKeluar.getValue() > 0) {
                    if (Integer.parseInt(tableKarungPengirimanBarangKeluar.getValueAt((tableKarungPengirimanBarangKeluar.getRowCount() - 1), 3).toString()) == 40) {
                        if ((int) txtJumlahKodiBarangKeluar.getValue() >= 40) {
                            Object[] data = {tableKarungPengirimanBarangKeluar.getRowCount() + 1, txtIDPengirimanBarangKeluar.getText() + (char) (kode + tableKarungPengirimanBarangKeluar.getRowCount() + 1), "ISI DI SAMPING", "40", ""};

                            //System.out.println(i);
                            Object[] dataDetail = {"", txtBarangBarangKeluar.getText(), txtUkuranBarangKeluar.getText(), "40"};

                            detailKarungIndexBarangKeluar.add(Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                            detailKarungBarangBarangKeluar.add(txtBarangBarangKeluar.getText());
                            detailKarungUkuranBarangKeluar.add(txtUkuranBarangKeluar.getText());
                            detailKarungKodiBarangKeluar.add("40");

                            //JOptionPane.showMessageDialog(this, Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                            dataRowDetailKarung = dataDetail;
                            dataRowKarung = data;

                            txtJumlahKodiBarangKeluar.setValue((int) txtJumlahKodiBarangKeluar.getValue() - 40);
                        } else {
                            Object[] data = {tableKarungPengirimanBarangKeluar.getRowCount() + 1, txtIDPengirimanBarangKeluar.getText() + (char) (kode + tableKarungPengirimanBarangKeluar.getRowCount() + 1), "ISI DI SAMPING", txtJumlahKodiBarangKeluar.getValue(), ""};
                            Object[] dataDetail = {"", txtBarangBarangKeluar.getText(), txtUkuranBarangKeluar.getText(), txtJumlahKodiBarangKeluar.getValue()};

                            //    JOptionPane.showMessageDialog(this, "Masuk else");
                            detailKarungIndexBarangKeluar.add(Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount() + 1));
                            detailKarungBarangBarangKeluar.add(txtBarangBarangKeluar.getText());
                            detailKarungUkuranBarangKeluar.add(txtUkuranBarangKeluar.getText());
                            detailKarungKodiBarangKeluar.add(txtJumlahKodiBarangKeluar.getValue().toString());

                            dataRowDetailKarung = dataDetail;
                            dataRowKarung = data;

                            txtJumlahKodiBarangKeluar.setValue(0);

                        }
                        modelKarungBarangKeluar.addRow(dataRowKarung);
                        //modelDetailKarungBarangKeluar.addRow(dataRowDetailKarung);

                    } else {
                        // jika blm 40
                        // periksa jumlah kodi nya
                        // jika lebih dari 40, masukkan hingga karung itu 40 -> tambahkan ke detil
                        // jika tidak lebih dari 40, masukkan hingga total 40 ->
                        // jika kurang ya masukkan saja semua ->
                        int disana = 0;
                        int jumlahsaya = Integer.parseInt(txtJumlahKodiBarangKeluar.getValue().toString());
                        int jumlahygada = Integer.parseInt(tableKarungPengirimanBarangKeluar.getValueAt(tableKarungPengirimanBarangKeluar.getRowCount() - 1, 3).toString());
                        int jumlahketerima = 0;
                        int daya = 40;

                        int kebutuhan = daya - jumlahygada;
                        if (kebutuhan > jumlahsaya) {
                            disana = jumlahygada + jumlahsaya;
                            jumlahketerima = jumlahsaya;
                            jumlahsaya = 0;
                        } else if (jumlahsaya > kebutuhan) {
                            disana = daya;
                            jumlahketerima = kebutuhan;
                            jumlahsaya = jumlahsaya - kebutuhan;
                        }

                        tableKarungPengirimanBarangKeluar.setValueAt(Integer.toString(disana), tableKarungPengirimanBarangKeluar.getRowCount() - 1, 3);

                        //JOptionPane.showMessageDialog(this, "Masuk sisa itu loh");
                        detailKarungIndexBarangKeluar.add(Integer.toString(tableKarungPengirimanBarangKeluar.getRowCount()));
                        detailKarungBarangBarangKeluar.add(txtBarangBarangKeluar.getText());
                        detailKarungUkuranBarangKeluar.add(txtUkuranBarangKeluar.getText());
                        detailKarungKodiBarangKeluar.add(Integer.toString(jumlahketerima));

                        txtJumlahKodiBarangKeluar.setValue(jumlahsaya);

                    }
                }
                isiJumlahDetailKarungTransaksiBarangKeluar();
            }
        }

        //        if (Integer.parseInt(tableDetailBarangKeluar.getValueAt(tableDetailBarangKeluar.getRowCount() - 1, 3).toString()) <= 40) {
            //            JOptionPane.showMessageDialog(this, "Bawah 40! ");
            //        }
        tableBarangKeluar.setValueAt(0, tableBarangKeluar.getSelectedRow(), 6);
        //tableDetailBarangKeluar.setValueAt(0, tableDetailBarangKeluar.getSelectedRow(), 3);
        hitungKodiKarungBarangKeluar();
        //modelDetailBarangKeluar
    }//GEN-LAST:event_btnTambahkanPengirimanBarangKeluarActionPerformed

    private void cmbNamaPelangganBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaPelangganBarangKeluarActionPerformed
        // TODO add your handling code here:
        clearBarangKeluar();
        modelBarangKeluar.getDataVector().removeAllElements();

        modelBarangKeluar.fireTableDataChanged();

        modelDetailBarangKeluar.getDataVector().removeAllElements();

        modelDetailBarangKeluar.fireTableDataChanged();
        ControllerTabelBarangKeluar.addDataBarangKeluar(modelBarangKeluar, (String) cmbStatusBarangKeluar.getSelectedItem(), (String) cmbNamaPelangganBarangKeluar.getSelectedItem());
    }//GEN-LAST:event_cmbNamaPelangganBarangKeluarActionPerformed

    private void cbPengirimanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPengirimanActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (cbPengiriman.isSelected()) {
            btnTambahkanPengirimanBarangKeluar.setEnabled(true);
            txtIDPengirimanBarangKeluar.setText(formatter.format(new Date()) + "-" + ControllerPengirimanBarangKeluar.getLastID());
        } else {
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
            txtIDPengirimanBarangKeluar.setText("[ ID PENGIRIMAN ]");
        }
        if(!cmbStatusBarangKeluar.getSelectedItem().toString().equals("Disiapkan")){
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
        }else{
            btnTambahkanPengirimanBarangKeluar.setEnabled(true);
        }
    }//GEN-LAST:event_cbPengirimanActionPerformed

    private void tableKarungPengirimanBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKarungPengirimanBarangKeluarMouseClicked
        // TODO add your handling code here:
        txtNomorKarungBarangKeluar.setText(tableKarungPengirimanBarangKeluar.getValueAt(tableKarungPengirimanBarangKeluar.getSelectedRow(), 0).toString());
        showDetailKarungBarangKeluar(txtNomorKarungBarangKeluar.getText());
        txtKeteranganKarungBarangKeluar.setText(tableKarungPengirimanBarangKeluar.getValueAt(tableKarungPengirimanBarangKeluar.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_tableKarungPengirimanBarangKeluarMouseClicked

    private void tableKarungPengirimanBarangKeluarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKarungPengirimanBarangKeluarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tableKarungPengirimanBarangKeluarKeyTyped

    private void txtKeteranganKarungBarangKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeteranganKarungBarangKeluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeteranganKarungBarangKeluarKeyPressed

    private void txtKeteranganKarungBarangKeluarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeteranganKarungBarangKeluarKeyReleased
        // TODO add your handling code here:
        txtBatasKeteranganBarangKeluar.setText(Integer.toString(txtKeteranganKarungBarangKeluar.getText().length()));
    }//GEN-LAST:event_txtKeteranganKarungBarangKeluarKeyReleased

    private void txtKeteranganKarungBarangKeluarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeteranganKarungBarangKeluarKeyTyped
        // TODO add your handling code here:
        txtBatasKeteranganBarangKeluar.setText(Integer.toString(txtKeteranganKarungBarangKeluar.getText().length()));
        if (txtKeteranganKarungBarangKeluar.getText().length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtKeteranganKarungBarangKeluarKeyTyped

    private void btnTulisKeteranganBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTulisKeteranganBarangKeluarActionPerformed
        // TODO add your handling code here:
        try {
            tableKarungPengirimanBarangKeluar.setValueAt(txtKeteranganKarungBarangKeluar.getText(), tableKarungPengirimanBarangKeluar.getSelectedRow(), 2);
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnTulisKeteranganBarangKeluarActionPerformed

    private void tableDetailKarungPengirimanBarangKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailKarungPengirimanBarangKeluarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDetailKarungPengirimanBarangKeluarMouseClicked

    private void txtNomorKarungBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomorKarungBarangKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomorKarungBarangKeluarActionPerformed

    private void txtNomorKarungBarangKeluarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNomorKarungBarangKeluarPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomorKarungBarangKeluarPropertyChange

    private void btnBatalkanKarungBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanKarungBarangKeluarActionPerformed
        // TODO add your handling code here:
        clearBarangKeluar();
        modelBarangKeluar.getDataVector().removeAllElements();

        modelBarangKeluar.fireTableDataChanged();

        modelDetailBarangKeluar.getDataVector().removeAllElements();

        modelDetailBarangKeluar.fireTableDataChanged();
        ControllerTabelBarangKeluar.addDataBarangKeluar(modelBarangKeluar, (String) cmbStatusBarangKeluar.getSelectedItem(), (String) cmbNamaPelangganBarangKeluar.getSelectedItem());
    }//GEN-LAST:event_btnBatalkanKarungBarangKeluarActionPerformed

    private void btnKonfirmasiPemberangkatanBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiPemberangkatanBarangKeluarActionPerformed
        // TODO add your handling code here:
        if(tableKarungPengirimanBarangKeluar.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dapat disimpan");
            return;
        }
        if(cmbPengemudiBarangKeluar.getSelectedItem().toString().equals("- Pilih Pengemudi -")){
            JOptionPane.showMessageDialog(this, "Pilih pengemudi dengan benar");
            return;
        }
        PengirimanBarangKeluar.setPgrm_id(txtIDPengirimanBarangKeluar.getText());
        int kodi = Integer.parseInt(txtJumlahKodiPengirimanBarang.getText());
        PengirimanBarangKeluar.setPgrm_jumlah_barang(kodi*20);
        PengirimanBarangKeluar.setPgrm_jumlah_karung(Integer.parseInt(txtJumlahKarungPengirimanBarang.getText()));
        PengirimanBarangKeluar.setPgrm_jumlah_kodi(Integer.parseInt(txtJumlahKodiPengirimanBarang.getText()));
        PengirimanBarangKeluar.setPgrm_tgl_transaksi(new Date());
        PengirimanBarangKeluar.setStatus("Dikirim");
        Karyawan kar = new Karyawan();
        kar = ControllerKaryawan.getDatabyNama(cmbPengemudiBarangKeluar.getSelectedItem().toString());
        //PengirimanBarangKeluar.setKry_id(kar.getKry_id());
        PengirimanBarangKeluar.setKry_id(kar.getKry_id());
        PengirimanBarangKeluar.setPgn_id(ControllerPelangganBarangKeluar.getIDPelanggan(tableBarangKeluar.getValueAt(tableBarangKeluar.getSelectedRow(), 2).toString()));
        
        simpanDataPengirimanBarangKeluar(PengirimanBarangKeluar);
        JOptionPane.showMessageDialog(this, "Pendataan pengiriman barang telah selasi");
    }//GEN-LAST:event_btnKonfirmasiPemberangkatanBarangKeluarActionPerformed

    private void btnBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangKeluarActionPerformed
        // TODO add your handling code here:
        show(PanelTransaksiBarangKeluar);
    }//GEN-LAST:event_btnBarangKeluarActionPerformed

    private void tablePemasokPembayaranPemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePemasokPembayaranPemasokMouseClicked
        // TODO add your handling code here:
        ClearPembayaranPemasok();
        pemasokPembayaranPemasok = ControllerTablePembayaranPemasok.getSelectedRowPemasok(tablePemasokPembayaranPemasok);
        txtNamaPemasokPembayaranPemasok.setText(pemasokPembayaranPemasok.getPms_nama());
        txtJumlahTransaksiPemasokPembayaranPemasok.setText(Integer.toString(pemasokPembayaranPemasok.getPms_jumlah_transaksi()));
        txtHutangPemasokPembayaranPemasok.setText(kursIndonesia.format(pemasokPembayaranPemasok.getPms_total_hutang()));
        int status = pemasokPembayaranPemasok.getPms_uang_transaksi();

        if ( pemasokPembayaranPemasok.getPms_total_hutang() == 0){
            lblKeteranganPembayaranPembayaranPemasok.setText("LUNAS");
            if (lblKeteranganPembayaranPembayaranPemasok.getText().equals("LUNAS"))
            {
                btnBayarPembayaranPemasok.setEnabled(false);
            }
        }
        else{
            lblKeteranganPembayaranPembayaranPemasok.setText("BELUM LUNAS");
        }
    }//GEN-LAST:event_tablePemasokPembayaranPemasokMouseClicked

    private void cmbPemasokPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemasokPembayaranPemasokActionPerformed
        // TODO add your handling code here:
        modelPemasokPembayaranPemasok.getDataVector().removeAllElements();

        modelPemasokPembayaranPemasok.fireTableDataChanged();
        ClearPembayaranPemasok();
        ControllerTablePembayaranPemasok.addDataPemasok(modelPemasokPembayaranPemasok,(String) cmbPemasokPembayaranPemasok.getSelectedItem());
    }//GEN-LAST:event_cmbPemasokPembayaranPemasokActionPerformed

    private void txtBayarPembayaranPemasokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarPembayaranPemasokKeyReleased
        // TODO add your handling code here:

        if (txtBayarPembayaranPemasok.getText().equals("")) {
            txtSisaHutangPembayaranPemasok.setText(txtHutangPemasokPembayaranPemasok.getText());
            return;
        }

        try {

            txtSisaHutangPembayaranPemasok.setText(kursIndonesia.format(((long) kursIndonesia.parse(txtHutangPemasokPembayaranPemasok.getText())) - Long.parseLong(txtBayarPembayaranPemasok.getText())));
            txtKembalianPembayaranPemasok.setText(kursIndonesia.format((Long.parseLong(txtBayarPembayaranPemasok.getText())) - (long) kursIndonesia.parse(txtHutangPemasokPembayaranPemasok.getText())));

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_txtBayarPembayaranPemasokKeyReleased

    private void btnBatalPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPembayaranPemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalPembayaranPemasokActionPerformed

    private void btnBayarPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarPembayaranPemasokActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setBpmsk_id(ControllerPembayaranPembayaranPemasok.getLastID());
        pembayaran.setPms_id(ControllerTablePembayaranPemasok.getPemasokId((String)cmbPemasokPembayaranPemasok.getSelectedItem()));
        pembayaran.setKry_id("202006290001");
        pembayaran.setBpmsk_tgl_transaksi(new Date());
        pembayaran.setBpmsk_uang_dibayar(Integer.valueOf(txtBayarPembayaranPemasok.getText()));

        ControllerPembayaranPembayaranPemasok.simpanPembayaranPemasok(pembayaran);
        try {
            ControllerPemasokPembayaranPemasok.updateTransaksiPembayaran(Long.parseLong(txtBayarPembayaranPemasok.getText()), Long.parseLong((kursIndonesia.parse(txtSisaHutangPembayaranPemasok.getText())).toString()), pemasokPembayaranPemasok.getPms_id().toString());

            ControllerTablePembayaranPemasok.addDataPemasok(modelPemasokPembayaranPemasok,(String) cmbPemasokPembayaranPemasok.getSelectedItem());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat menyimpan ke pemasok!");
        }

        JOptionPane.showMessageDialog(this, "Pembayaran berhasil....");
        addDataPemasok();
        ClearPembayaranPemasok();
        cmbPemasokPembayaranPemasok.setSelectedIndex(0);
        lblKeteranganPembayaranPembayaranPemasok.setText("[Keterangan]");
    }//GEN-LAST:event_btnBayarPembayaranPemasokActionPerformed

    private void txtKembalianPembayaranPemasokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKembalianPembayaranPemasokKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianPembayaranPemasokKeyReleased

    private void btnPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranPemasokActionPerformed
        // TODO add your handling code here:
        show(panelPembayaranPemasok);
    }//GEN-LAST:event_btnPembayaranPemasokActionPerformed

    private void tablePengirimanKonfirmasiKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePengirimanKonfirmasiKeluarMouseClicked
        // TODO add your handling code here:
        if(tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 8).toString().equals("Diterima") ||
            tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 8).toString().equals("Gagal")){
            btnKonfirmasiKonfirmasiKeluar.setEnabled(false);
        }else{
            btnKonfirmasiKonfirmasiKeluar.setEnabled(true);
        }
        modelDetailKarungKonfirmasiKeluar.getDataVector().clear();
        ControllerKonfirmasiKeluar.addKarungKonfirmasiKeluar(modelDetailKarungKonfirmasiKeluar, tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 1).toString());
        modelDetailKarungKonfirmasiKeluar.fireTableDataChanged();

        txtKodeTransaksiKonfirmasiKeluar.setText(tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 1).toString());
        txtNamaPelangganKonfirmasiKeluar.setText(tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_tablePengirimanKonfirmasiKeluarMouseClicked

    private void rbDiterimaKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDiterimaKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        if (rbDiterimaKonfirmasiKeluar.isSelected()) {
            txtStatusPengirimanKonfirmasiKeluar.setText("Diterima");
        } else {
            txtStatusPengirimanKonfirmasiKeluar.setText("Gagal");
        }
    }//GEN-LAST:event_rbDiterimaKonfirmasiKeluarActionPerformed

    private void rbGagalKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGagalKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        if (rbDiterimaKonfirmasiKeluar.isSelected()) {
            txtStatusPengirimanKonfirmasiKeluar.setText("Diterima");
        } else {
            txtStatusPengirimanKonfirmasiKeluar.setText("Gagal");
        }
    }//GEN-LAST:event_rbGagalKonfirmasiKeluarActionPerformed

    private void txtNamaPelangganKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganKonfirmasiKeluarActionPerformed

    private void txtNamaPelangganKonfirmasiKeluarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNamaPelangganKonfirmasiKeluarPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganKonfirmasiKeluarPropertyChange

    private void txtKodeTransaksiKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeTransaksiKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeTransaksiKonfirmasiKeluarActionPerformed

    private void txtKodeTransaksiKonfirmasiKeluarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtKodeTransaksiKonfirmasiKeluarPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeTransaksiKonfirmasiKeluarPropertyChange

    private void btnKonfirmasiKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        if (txtKodeTransaksiKonfirmasiKeluar.getText().equals("-")) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dikonfirmasi dari table pengiriman");
        }
        String status = txtStatusPengirimanKonfirmasiKeluar.getText();
        ControllerPengirimanKonfirmasiKeluar.updateStatus(txtKodeTransaksiKonfirmasiKeluar.getText(), status);
        List<String> pmsn_id = new ArrayList<>();
        String pelanggan = ControllerPelangganKonfirmasiBarang.getIDPelanggan(tablePengirimanKonfirmasiKeluar.getValueAt(tablePengirimanKonfirmasiKeluar.getSelectedRow(), 2).toString());
        pmsn_id = ControllerPengirimanKonfirmasiKeluar.getIDPemesananDetail(txtKodeTransaksiKonfirmasiKeluar.getText());
        for (int i = 0; i < pmsn_id.size(); i++) {
            ControllerPemesananKonfirmasiKeluar.setStatus(pmsn_id.get(i), status);
            if (status.equals("Diterima")) {
                ControllerPelangganKonfirmasiBarang.updateTransaksiKonfirmasiKeluar((ControllerPemesananKonfirmasiKeluar.getPmsn_Total_Uang(pmsn_id.get(i)).toString()), pelanggan );
            }
        }
        JOptionPane.showMessageDialog(this,"Konfirmasi berhasil, Terima Kasih");
        if(cmbNamaPelangganKonfirmasiKeluar.getSelectedItem().toString().equals("- Pilih Pelanggan -")){
            addDataKonfirmasiKeluar(0);
        }else{
            addDataKonfirmasiKeluar(1);
        }

    }//GEN-LAST:event_btnKonfirmasiKonfirmasiKeluarActionPerformed

    private void cmbNamaPelangganKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaPelangganKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        addDataKonfirmasiKeluar(1);
    }//GEN-LAST:event_cmbNamaPelangganKonfirmasiKeluarActionPerformed

    private void btnSemuaKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemuaKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        addDataKonfirmasiKeluar(0);
    }//GEN-LAST:event_btnSemuaKonfirmasiKeluarActionPerformed

    private void btnKonfirmasiKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiKeluarActionPerformed
        // TODO add your handling code here:
        show(panelKonfirmasiKeluar);
    }//GEN-LAST:event_btnKonfirmasiKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T_Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel PanelTransaksiBarangKeluar;
    private javax.swing.JLabel Ukuran;
    private javax.swing.JLabel Ukuran1;
    private javax.swing.JLabel Ukuran2;
    private javax.swing.JLabel Ukuran3;
    private javax.swing.JLabel Ukuran4;
    private javax.swing.JLabel Ukuran5;
    private javax.swing.JLabel batas;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnBarangKeluar;
    private javax.swing.JButton btnBarangMasuk;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBatalPembayaranPemasok;
    private javax.swing.JButton btnBatalkanKarungBarangKeluar;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnBayarPembayaranPemasok;
    private javax.swing.JButton btnGagalBarangMasuk;
    private javax.swing.JButton btnJBarang;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnKeranjangPemesanan;
    private javax.swing.JButton btnKonfirmasiBarangMasuk;
    private javax.swing.JButton btnKonfirmasiKeluar;
    private javax.swing.JButton btnKonfirmasiKonfirmasiKeluar;
    private javax.swing.JButton btnKonfirmasiPemberangkatanBarangKeluar;
    private javax.swing.JButton btnMenuUtama;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPemasok;
    private javax.swing.JButton btnPembayaranPelanggan;
    private javax.swing.JButton btnPembayaranPemasok;
    private javax.swing.JButton btnPemesananPelanggan;
    private javax.swing.JButton btnPemesananPemasok;
    private javax.swing.JButton btnPengolahanData;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnRefreshPemesanan;
    private javax.swing.JButton btnSemuaKonfirmasiKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSuksesBarangMasuk;
    private javax.swing.JButton btnTambahkanPengirimanBarangKeluar;
    private javax.swing.JButton btnTulisKeteranganBarangKeluar;
    private javax.swing.JCheckBox cbPengiriman;
    private javax.swing.JComboBox<String> cmbNamaPelangganBarangKeluar;
    private javax.swing.JComboBox<String> cmbNamaPelangganKonfirmasiKeluar;
    private javax.swing.JComboBox<String> cmbPelanggan;
    private javax.swing.JComboBox<String> cmbPelanggan1;
    private javax.swing.JComboBox<String> cmbPemasok;
    private javax.swing.JComboBox<String> cmbPemasokPembayaranPemasok;
    private javax.swing.JComboBox<String> cmbPengemudiBarangKeluar;
    private javax.swing.JComboBox<String> cmbStatusBarangKeluar;
    private javax.swing.JComboBox<String> cmbStatusBarangMasuk;
    private javax.swing.JLabel dummy1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel karyawan_info;
    private javax.swing.JLabel kiribawahdashboard;
    private javax.swing.JLabel lblKeteranganBarangMasuk;
    private javax.swing.JLabel lblKeteranganPembayaran;
    private javax.swing.JLabel lblKeteranganPembayaranPembayaranPemasok;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelBarangMasuk;
    private javax.swing.JPanel panelDaftarBarangMasuk;
    private javax.swing.JPanel panelDaftarBarangMasuk1;
    private javax.swing.JPanel panelDaftarBarangMasuk2;
    private javax.swing.JPanel panelDaftarBarangMasuk3;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelDataPengirimanBarangBarangKeluar;
    private javax.swing.JPanel panelDeskripsi;
    private javax.swing.JPanel panelDeskripsiBarangMasuk;
    private javax.swing.JPanel panelDeskripsiBarangMasuk1;
    private javax.swing.JPanel panelDeskripsiBarangMasuk2;
    private javax.swing.JPanel panelDetailBeli;
    private javax.swing.JPanel panelDetailBeli1;
    private javax.swing.JPanel panelKeranjang;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelKonfirmasiKeluar;
    private javax.swing.JPanel panelPemasokkan;
    private javax.swing.JPanel panelPembayaranPelanggan;
    private javax.swing.JPanel panelPembayaranPemasok;
    private javax.swing.JPanel panelPemesanan;
    private javax.swing.JPanel panelPengolahanData;
    private javax.swing.JPanel pnlDataBarang;
    private javax.swing.JRadioButton rbDiterimaKonfirmasiKeluar;
    private javax.swing.JRadioButton rbGagalKonfirmasiKeluar;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tableBarangKeluar;
    private javax.swing.JTable tableBarangMasuk;
    private javax.swing.JTable tableDetailBarangKeluar;
    private javax.swing.JTable tableDetailBarangMasuk;
    private javax.swing.JTable tableDetailKarungPengirimanBarangKeluar;
    private javax.swing.JTable tableKarungKonfirmasiKeluar;
    private javax.swing.JTable tableKarungPengirimanBarangKeluar;
    private javax.swing.JTable tableKeranjang;
    private javax.swing.JTable tablePelanggan;
    private javax.swing.JTable tablePemasokPembayaranPemasok;
    private javax.swing.JTable tablePengirimanKonfirmasiKeluar;
    private javax.swing.JTable tblBarangPemesanan;
    private javax.swing.JTable tblKeranjangPemesanan;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JTextField txtBarangBarangKeluar;
    private javax.swing.JTextField txtBarangPemesanan;
    private javax.swing.JLabel txtBatasKeteranganBarangKeluar;
    private javax.swing.JLabel txtBatasKeteranganBarangKeluar1;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtBayarPembayaranPemasok;
    private javax.swing.JTextField txtHutangBarangMasuk;
    private javax.swing.JTextField txtHutangPelanggan;
    private javax.swing.JTextField txtHutangPemasokPembayaranPemasok;
    private javax.swing.JTextField txtIDPengirimanBarangKeluar;
    private javax.swing.JSpinner txtJumlah;
    private javax.swing.JTextField txtJumlahKarungPengirimanBarang;
    private javax.swing.JSpinner txtJumlahKodiBarangKeluar;
    private javax.swing.JTextField txtJumlahKodiPemesanan;
    private javax.swing.JTextField txtJumlahKodiPengirimanBarang;
    private javax.swing.JSpinner txtJumlahPemesanan;
    private javax.swing.JTextField txtJumlahTransaksiBarangMasuk;
    private javax.swing.JTextField txtJumlahTransaksiPelanggan;
    private javax.swing.JTextField txtJumlahTransaksiPemasokPembayaranPemasok;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtKembalianPembayaranPemasok;
    private javax.swing.JTextArea txtKeteranganKarungBarangKeluar;
    private javax.swing.JTextField txtKodeTransaksiKonfirmasiKeluar;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNamaPelanggan1;
    private javax.swing.JTextField txtNamaPelangganKonfirmasiKeluar;
    private javax.swing.JTextField txtNamaPemasokBarangMasuk;
    private javax.swing.JTextField txtNamaPemasokPembayaranPemasok;
    private javax.swing.JTextField txtNamaToko;
    private javax.swing.JTextField txtNomorKarungBarangKeluar;
    private javax.swing.JTextField txtPemasok;
    private javax.swing.JTextField txtSisaHutang;
    private javax.swing.JTextField txtSisaHutangBarangMasuk;
    private javax.swing.JTextField txtSisaHutangPembayaranPemasok;
    private javax.swing.JTextField txtStatusPengirimanKonfirmasiKeluar;
    private javax.swing.JTextField txtTagihanBarangMasuk;
    private javax.swing.JTextField txtTanggalPemasokkan;
    private javax.swing.JLabel txtTotalBiaya;
    private javax.swing.JTextField txtTotalHarga;
    private javax.swing.JTextField txtTotalHargaPemesanan;
    private javax.swing.JTextField txtTotalPemesanan;
    private javax.swing.JTextField txtUangDibayarBarangMasuk;
    private javax.swing.JTextField txtUangTransaksiBarangMasuk;
    private javax.swing.JTextField txtUkuran;
    private javax.swing.JTextField txtUkuranBarangKeluar;
    private javax.swing.JTextField txtUkuranPemesanan;
    private javax.swing.JLabel txtinfoTanggal;
    private javax.swing.JTextField txtjumlah_kodi;
    // End of variables declaration//GEN-END:variables
}
