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
import Controller.CLaporan;
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
import java.awt.Component;
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
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Controller.CLaporan;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import connection.DBConnect;
import java.awt.Component;
import static java.awt.PageAttributes.MediaType.D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Polman
 */
public class T_Utama extends javax.swing.JFrame {

    /**
     * Creates new form T_Utama
     */
    // ================================ Variable & Prosedur Umum ==============================================
    SimpleDateFormat formatSQL = new SimpleDateFormat("yyyyMMdd");
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
        panelLaporan.setVisible(false);
        
        panel.setVisible(true);
        
        spesifikinstatiasi(panel.getName());
    }
    
    private void spesifikinstatiasi(String panel){
        switch(panel){
            case "panelDashboard":{
                getAllDataTransaksi();
                getDataUangTransaksi(formatSQL.format(dateDariDashboard.getDate()), formatSQL.format(dateSampaiDashboard.getDate()));
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
            case "panelLaporan":{
                break;
            }
        }
    }
    // ================================ DASHBOARD ==============================================
    private void instantiasiDashboard(){
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    private void getAllDataTransaksi(){
        try {
            
            DBConnect connection = new DBConnect();
            connection.stat = null;
            String query = "";
            //PEMASOK
            //=================PEMESANAN
            connection.stat = connection.conn.createStatement();
            query = "select count(pmsk_id) total from Pemasokkan\n" +
                        "where status = 'Proses'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtPemesananPemasokDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //=================BARANG MASUK
            connection.stat = connection.conn.createStatement();
            query = "select count(pmsk_id) total from Pemasokkan\n" +
                        "where status = 'Sukses'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtBarangMasukDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //=================PEMBAYARAN
            connection.stat = connection.conn.createStatement();
            query = "select count(Bpmks_id) total from BayarPemasok";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtPembayaranPemasokDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //PELANGGAN
            //=================Pemesanan
            connection.stat = connection.conn.createStatement();
            query = "select count(pmsn_id) total from Pemesanan\n" +
            "where pmsn_status = 'Disiapkan'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtPemesananPelangganDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //=================BARANG KELUAR
            connection.stat = connection.conn.createStatement();
            query = "select count(pmsn_id) total from Pemesanan\n" +
            "where pmsn_status = 'Dikirim'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtBarangKeluarPemasokDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //=================KONFIRMASI BARANG
            connection.stat = connection.conn.createStatement();
            query = "select count(pmsn_id) total from Pemesanan\n" +
            "where pmsn_status = 'Diterima'";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtKonfirmasiBarangDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
            
            //================= PEMBAYARAN
            connection.stat = connection.conn.createStatement();
            query = "select count(pmby_id) total from Pembayaran";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                txtPembayaranPelangganDashboard.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }
    private void getDataUangTransaksi(String dari, String sampai){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = null;
            connection.stat = connection.conn.createStatement();
            String query = "select sum(bpmks_uang_dibayar) uang from BayarPemasok\n" +
                            "where bpmks_tgl_transaksi between '"+dari+"' and '"+sampai+"'\n";
            System.out.println(query);            
            try{
                connection.result = connection.stat.executeQuery(query);
            
                if (!connection.result.next()) {
                    txtUangKeluarDashboard.setText(kursIndonesia.format(0));
                }else{
                    txtUangKeluarDashboard.setText(kursIndonesia.format(connection.result.getInt("uang")));
                }
            }catch(Exception a){
                txtUangKeluarDashboard.setText(kursIndonesia.format(0));
            }
            
            connection.stat.close();
            connection.result.close();
            
        //================================
            query = "select sum(pmby_uang_masuk) uang from Pembayaran \n"+
                    "where pmby_tgl_transaksi between '"+dari+"' and '"+sampai+"'\n";
            System.out.println(query);
            connection.stat = connection.conn.createStatement();
            connection.result = connection.stat.executeQuery(query);
            try{
                if(!connection.result.next()) {
                    txtUangMasukDashboard.setText(kursIndonesia.format(0));
                }else{
                    txtUangMasukDashboard.setText(kursIndonesia.format(connection.result.getInt("uang")));
                }
            
            }catch(Exception b){
                    txtUangMasukDashboard.setText(kursIndonesia.format(0));
            }
            
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 getDataUangTransaksi!\n" + e.toString());
        }
    }
    //==========================DEKLARASI VARIABLE LAPORAN =====================================
    private DefaultTableModel modelPemasokkanLaporan = new DefaultTableModel();
    private DefaultTableModel modelPengirimanLaporan = new DefaultTableModel();
    private DefaultTableModel modelTPelangganLaporan = new DefaultTableModel();
    private DefaultTableModel modelTPemasokLaporan = new DefaultTableModel();
    
    CLaporan ControllerLaporan = new CLaporan();
    
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
    private void addColumnLaporan(){
        modelPemasokkanLaporan.addColumn("No");
        modelPemasokkanLaporan.addColumn("Kode");
        modelPemasokkanLaporan.addColumn("Tanggal");
        modelPemasokkanLaporan.addColumn("Barang");
        modelPemasokkanLaporan.addColumn("Size");
        modelPemasokkanLaporan.addColumn("Pemasok");
        modelPemasokkanLaporan.addColumn("Kodi");
        modelPemasokkanLaporan.addColumn("Harga");
        modelPemasokkanLaporan.addColumn("T kodi");
        modelPemasokkanLaporan.addColumn("T harga");
        modelPemasokkanLaporan.addColumn("Status");
//        modelPemasokkanLaporan.addColumn("Pelayan");


//kode\ tanggal \ toko\ nama\ barang\ ukuran\ jumlah kodi\ harga\ total kodi\ total uang\ status
        modelPengirimanLaporan.addColumn("No");
        modelPengirimanLaporan.addColumn("Kode");
        modelPengirimanLaporan.addColumn("Tanggal");
        modelPengirimanLaporan.addColumn("Toko");
        modelPengirimanLaporan.addColumn("Nama");
        modelPengirimanLaporan.addColumn("Barang");
        modelPengirimanLaporan.addColumn("Size");
        modelPengirimanLaporan.addColumn("Kodi");
        modelPengirimanLaporan.addColumn("Harga");
        modelPengirimanLaporan.addColumn("T kodi");
        modelPengirimanLaporan.addColumn("T harga");
        modelPengirimanLaporan.addColumn("Status");
//        modelPengirimanLaporan.addColumn("Pelayan");
        
    // kode \ tanggal \ nama \ toko \ pasar \ pembayaran \ pelayan
        modelTPelangganLaporan.addColumn("No");
        modelTPelangganLaporan.addColumn("Kode");
        modelTPelangganLaporan.addColumn("Tanggal");
        modelTPelangganLaporan.addColumn("Nama");
        modelTPelangganLaporan.addColumn("Toko");
        modelTPelangganLaporan.addColumn("Pasar");
        modelTPelangganLaporan.addColumn("Pembayaran");
        modelTPelangganLaporan.addColumn("Penanggung Jawab");
        
        //kode \ tanggal \ nama \ alamat \ pembayaran \ pelayan
        modelTPemasokLaporan.addColumn("No");
        modelTPemasokLaporan.addColumn("Kode");
        modelTPemasokLaporan.addColumn("Tanggal");
        modelTPemasokLaporan.addColumn("Nama");
        modelTPemasokLaporan.addColumn("Alamat");
        modelTPemasokLaporan.addColumn("Pembayaran");
        modelTPemasokLaporan.addColumn("Penanggung Jawab");
        
    }
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    public void instantiasiLaporan(){
        dateSejakLaporan.setDate(new Date());
        dateSampaiLaporan.setDate(new Date());
        addColumnLaporan();
        
        
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
    
//    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
 //   DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
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
        txtNamaPelanggan1.setText("-");
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
        resizeColumnWidth(tblBarangPemesanan);
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
    // ====================================== PENGELOLAAN DATA =======================================================
    private void instantisasiPengelolaanData(){
        addTotalDataPengelolaanData();
    }
    private void addTotalDataPengelolaanData(){
        // ===================================== BARANG ============================
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select count(b_id) total from Barang";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                infoBarangPengelolaanData.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
        // ===================================== JENIS BARANG ============================
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            
            String query = "select count(jb_id) total from JenisBarang";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                infoJenisBarangPengelolaanData.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
        // ===================================== PEMASOK ============================
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            

            String query = "select count(pms_id) total from Pemasok";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                infoPemasokPengelolaanData.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
        // ===================================== PELANGGAN ============================
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            

            String query = "select count(pgn_id) total from Pelanggan";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                infoPelangganPengelolaanData.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
        // ===================================== KARYAWAN ============================
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select count(kry_id)-1 total from Karyawan";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                infoKaryawanPengelolaanData.setText(connection.result.getString("total"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
    }
    
    Karyawan karyawan_login = new Karyawan();
    
    SimpleDateFormat settingTanggal = new SimpleDateFormat("dd-MM-yyyy");
    CKaryawan ControllerKaryawan = new CKaryawan();
    
    public T_Utama() {
        initComponents();
        //====================== PENGATURAN TAMPILAN AWAL =======================
        instantiasiDashboard();
        
        dateDariDashboard.setDate(new Date());
        dateSampaiDashboard.setDate(new Date());
        show(panelDashboard);
        
        instantiasiKonfirmasiBarangKeluar();
        instantiasiPembayaranPemasok();
        instantiasiBarangKeluar();
        instantiasiPembayaranPelanggan();
        instantiasiBarangMasuk();
        instantiasiPemesanan();
        instantiasiPemasokkan();
        instantiasiLaporan();
        instantisasiPengelolaanData();
        
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
        karyawan_login = ControllerKaryawan.getDatabyNama(pengguna);
        
        instantiasiDashboard();
        dateDariDashboard.setDate(new Date());
        dateSampaiDashboard.setDate(new Date());
        show(panelDashboard);
        
        
        instantiasiKonfirmasiBarangKeluar();
        instantiasiPembayaranPemasok();
        instantiasiBarangKeluar();
        instantiasiPembayaranPelanggan();
        instantiasiBarangMasuk();
        instantiasiPemesanan();
        instantiasiPemasokkan();
        instantiasiLaporan();
        instantisasiPengelolaanData();
        
        
        if(karyawan_login.getKry_jabatan().equals("Kasir")){
            btnPengolahanData.setEnabled(false);
            btnLaporan.setEnabled(false);
        }
        //==========================DEKLARASI VARIABLE PEMBAYARAN PEMASOK =====================================
        
        //======================= DEKLARASI BARANG KELUAR =============================
        
        //======================= DEKLARASI PEMBAYARAN PELANGGAN =============================
         
        //======================= DEKLARASI BARANG MASUK =============================
          
        //================= DEKLARASI PEMESANAN =======================
         
        
        //========== DEKLARASI TRANSAKSI PEMASOKKAN =======
         
        // ================================================
        karyawan_info.setText(karyawan_login.getKry_nama());
        karyawan_info_posisi.setText(karyawan_login.getKry_jabatan());
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
        txtinfoTanggal = new javax.swing.JLabel();
        karyawan_info = new javax.swing.JLabel();
        karyawan_info_posisi = new javax.swing.JLabel();
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
        infoKaryawanPengelolaanData = new javax.swing.JButton();
        infoPelangganPengelolaanData = new javax.swing.JButton();
        infoPemasokPengelolaanData = new javax.swing.JButton();
        infoJenisBarangPengelolaanData = new javax.swing.JButton();
        infoBarangPengelolaanData = new javax.swing.JButton();
        panelKiri = new javax.swing.JPanel();
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
        btnLaporan = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        panelDashboard = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        kiribawahdashboard = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        txtPemesananPemasokDashboard = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        txtPemesananPelangganDashboard = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        txtBarangMasukDashboard = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        txtBarangKeluarPemasokDashboard = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        txtPembayaranPemasokDashboard = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtKonfirmasiBarangDashboard = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        txtPembayaranPelangganDashboard = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtUangKeluarDashboard = new javax.swing.JLabel();
        txtUangMasukDashboard = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        dateDariDashboard = new com.toedter.calendar.JDateChooser();
        dateSampaiDashboard = new com.toedter.calendar.JDateChooser();
        btnLihatDashboard = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        btnHitungDashboard = new javax.swing.JButton();
        txtUangDashboard = new javax.swing.JLabel();
        txtFitureDashboard = new javax.swing.JLabel();
        btnHitungDashboard1 = new javax.swing.JButton();
        btnHitungDashboard2 = new javax.swing.JButton();
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
        panelLaporan = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        dateSejakLaporan = new com.toedter.calendar.JDateChooser();
        dateSampaiLaporan = new com.toedter.calendar.JDateChooser();
        jLabel82 = new javax.swing.JLabel();
        btnPemasokkanLaporan = new javax.swing.JButton();
        btnPengirimanLaporan = new javax.swing.JButton();
        btnTPelangganLaporan = new javax.swing.JButton();
        btnTPemasokLaporan = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);
        setExtendedState(1);
        setName("frameUtama"); // NOI18N
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAtas.setBackground(new java.awt.Color(0, 153, 153));

        txtinfoTanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtinfoTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txtinfoTanggal.setText("jLabel2");

        karyawan_info.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        karyawan_info.setForeground(new java.awt.Color(255, 255, 255));
        karyawan_info.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        karyawan_info.setText("Login");
        karyawan_info.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        karyawan_info_posisi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        karyawan_info_posisi.setForeground(new java.awt.Color(255, 255, 255));
        karyawan_info_posisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        karyawan_info_posisi.setText("Login");
        karyawan_info_posisi.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(txtinfoTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                .addComponent(karyawan_info, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(karyawan_info_posisi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(karyawan_info)
                        .addComponent(karyawan_info_posisi))
                    .addComponent(txtinfoTanggal))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        getContentPane().add(panelAtas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 30));

        panelPemesanan.setBackground(new java.awt.Color(0, 204, 204));
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

        btnRefreshPemesanan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnRefreshPemesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/refresh.png"))); // NOI18N
        btnRefreshPemesanan.setText("Refresh");
        btnRefreshPemesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRefreshPemesanan.setIconTextGap(10);
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
                    .addGroup(pnlDataBarangLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataBarangLayout.setVerticalGroup(
            pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataBarangLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnRefreshPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(pnlDataBarangLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        panelPemesanan.add(pnlDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 630));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Total Jumlah Kodi");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 263, -1, -1));

        jLabel9.setText("Total Harga");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 263, -1, -1));

        txtJumlahKodiPemesanan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtJumlahKodiPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahKodiPemesananMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtJumlahKodiPemesananMouseEntered(evt);
            }
        });
        jPanel2.add(txtJumlahKodiPemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 294, 117, -1));

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
        jPanel2.add(txtTotalPemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 294, 186, -1));

        jLabel11.setText("Rp. ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 292, -1, 30));

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

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 74, 528, 180));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/reset.png"))); // NOI18N
        jButton1.setText("Reset");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 10, 140, 50));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("KERANJANG");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 22, 237, -1));

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
        cmbPelanggan.setForeground(new java.awt.Color(0, 102, 102));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        btnKeranjangPemesanan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
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

        panelPengolahanData.setBackground(new java.awt.Color(0, 204, 204));
        panelPengolahanData.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelPengolahanDataPropertyChange(evt);
            }
        });

        btnBarang.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BarangKelola.png"))); // NOI18N
        btnBarang.setText("Barang");
        btnBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarang.setIconTextGap(40);
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnJBarang.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnJBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/JenisBarangKelola.png"))); // NOI18N
        btnJBarang.setText("Jenis Barang");
        btnJBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnJBarang.setIconTextGap(40);
        btnJBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJBarangActionPerformed(evt);
            }
        });

        btnPemasok.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PemasokKelola.png"))); // NOI18N
        btnPemasok.setText("Pemasok");
        btnPemasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemasok.setIconTextGap(40);
        btnPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasokActionPerformed(evt);
            }
        });

        btnPelanggan.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PelangganKelola.png"))); // NOI18N
        btnPelanggan.setText("Pelanggan");
        btnPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPelanggan.setIconTextGap(40);
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });

        btnKaryawan.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/KaryawanKelola.png"))); // NOI18N
        btnKaryawan.setText("Karyawan");
        btnKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKaryawan.setIconTextGap(40);
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        infoKaryawanPengelolaanData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoKaryawanPengelolaanData.setText("Karyawan");
        infoKaryawanPengelolaanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoKaryawanPengelolaanDataActionPerformed(evt);
            }
        });

        infoPelangganPengelolaanData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoPelangganPengelolaanData.setText("Pelanggan");
        infoPelangganPengelolaanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoPelangganPengelolaanDataActionPerformed(evt);
            }
        });

        infoPemasokPengelolaanData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoPemasokPengelolaanData.setText("Pemasok");
        infoPemasokPengelolaanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoPemasokPengelolaanDataActionPerformed(evt);
            }
        });

        infoJenisBarangPengelolaanData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoJenisBarangPengelolaanData.setText("Jenis Barang");
        infoJenisBarangPengelolaanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoJenisBarangPengelolaanDataActionPerformed(evt);
            }
        });

        infoBarangPengelolaanData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        infoBarangPengelolaanData.setText("Barang");
        infoBarangPengelolaanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoBarangPengelolaanDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPengolahanDataLayout = new javax.swing.GroupLayout(panelPengolahanData);
        panelPengolahanData.setLayout(panelPengolahanDataLayout);
        panelPengolahanDataLayout.setHorizontalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(infoBarangPengelolaanData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoJenisBarangPengelolaanData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnJBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                        .addComponent(btnPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPengolahanDataLayout.createSequentialGroup()
                        .addComponent(infoPemasokPengelolaanData, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoPelangganPengelolaanData, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoKaryawanPengelolaanData, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPengolahanDataLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBarang, btnJBarang, btnKaryawan, btnPelanggan, btnPemasok, infoKaryawanPengelolaanData, infoPelangganPengelolaanData});

        panelPengolahanDataLayout.setVerticalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBarang)
                    .addComponent(btnJBarang)
                    .addComponent(btnPemasok)
                    .addComponent(btnPelanggan)
                    .addComponent(btnKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoBarangPengelolaanData, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoJenisBarangPengelolaanData)
                    .addComponent(infoPemasokPengelolaanData)
                    .addComponent(infoPelangganPengelolaanData)
                    .addComponent(infoKaryawanPengelolaanData))
                .addContainerGap(466, Short.MAX_VALUE))
        );

        panelPengolahanDataLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {infoBarangPengelolaanData, infoJenisBarangPengelolaanData, infoKaryawanPengelolaanData, infoPelangganPengelolaanData, infoPemasokPengelolaanData});

        getContentPane().add(panelPengolahanData, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelKiri.setBackground(new java.awt.Color(0, 153, 153));
        panelKiri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKiriMouseClicked(evt);
            }
        });
        panelKiri.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenuUtama.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnMenuUtama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menuUtama.png"))); // NOI18N
        btnMenuUtama.setText("Menu Utama");
        btnMenuUtama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuUtama.setFocusable(false);
        btnMenuUtama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuUtama.setIconTextGap(10);
        btnMenuUtama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuUtamaActionPerformed(evt);
            }
        });
        panelKiri.add(btnMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 126, 214, 43));

        btnPemesananPelanggan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPemesananPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pemesananPelanggan.png"))); // NOI18N
        btnPemesananPelanggan.setText("Pemesanan");
        btnPemesananPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPemesananPelanggan.setFocusable(false);
        btnPemesananPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemesananPelanggan.setIconTextGap(10);
        btnPemesananPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPelangganActionPerformed(evt);
            }
        });
        panelKiri.add(btnPemesananPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 175, 214, 43));

        btnPembayaranPelanggan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPembayaranPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bayarPelanggan.png"))); // NOI18N
        btnPembayaranPelanggan.setText("Pembayaran Pelanggan");
        btnPembayaranPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaranPelanggan.setFocusable(false);
        btnPembayaranPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPembayaranPelanggan.setIconTextGap(10);
        btnPembayaranPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranPelangganActionPerformed(evt);
            }
        });
        panelKiri.add(btnPembayaranPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 273, 214, 43));

        btnPengolahanData.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPengolahanData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/kelolaButton.png"))); // NOI18N
        btnPengolahanData.setText("Pengelolaan Data");
        btnPengolahanData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPengolahanData.setFocusable(false);
        btnPengolahanData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPengolahanData.setIconTextGap(10);
        btnPengolahanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengolahanDataActionPerformed(evt);
            }
        });
        panelKiri.add(btnPengolahanData, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 567, 214, 43));

        btnKonfirmasiKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnKonfirmasiKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/konfirmasiPengiriman.png"))); // NOI18N
        btnKonfirmasiKeluar.setText("Konfirmasi Pengiriman");
        btnKonfirmasiKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKonfirmasiKeluar.setFocusable(false);
        btnKonfirmasiKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKonfirmasiKeluar.setIconTextGap(10);
        btnKonfirmasiKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiKeluarActionPerformed(evt);
            }
        });
        panelKiri.add(btnKonfirmasiKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 469, 214, 43));

        btnKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/keluar.png"))); // NOI18N
        btnKeluar.setText("KELUAR");
        btnKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeluar.setFocusable(false);
        btnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKeluar.setIconTextGap(10);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        panelKiri.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 616, 214, 43));

        btnBarangKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnBarangKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/barangKeluar.png"))); // NOI18N
        btnBarangKeluar.setText("Barang Keluar");
        btnBarangKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBarangKeluar.setFocusable(false);
        btnBarangKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarangKeluar.setIconTextGap(10);
        btnBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangKeluarActionPerformed(evt);
            }
        });
        panelKiri.add(btnBarangKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 371, 214, 43));

        btnBarangMasuk.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnBarangMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/barangMasuk.png"))); // NOI18N
        btnBarangMasuk.setText("Barang Masuk");
        btnBarangMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBarangMasuk.setFocusable(false);
        btnBarangMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarangMasuk.setIconTextGap(10);
        btnBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangMasukActionPerformed(evt);
            }
        });
        panelKiri.add(btnBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 420, 214, 43));

        btnPemesananPemasok.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPemesananPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pemasokan.png"))); // NOI18N
        btnPemesananPemasok.setText("Pemasokkan");
        btnPemesananPemasok.setToolTipText("");
        btnPemesananPemasok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPemesananPemasok.setFocusable(false);
        btnPemesananPemasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemesananPemasok.setIconTextGap(10);
        btnPemesananPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPemasokActionPerformed(evt);
            }
        });
        panelKiri.add(btnPemesananPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 224, 214, 43));

        btnPembayaranPemasok.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPembayaranPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bayarPemasok.png"))); // NOI18N
        btnPembayaranPemasok.setText("Pembayaran Pemasok");
        btnPembayaranPemasok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembayaranPemasok.setFocusable(false);
        btnPembayaranPemasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPembayaranPemasok.setIconTextGap(10);
        btnPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranPemasokActionPerformed(evt);
            }
        });
        panelKiri.add(btnPembayaranPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 322, 214, 43));

        btnLaporan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/reportButton.png"))); // NOI18N
        btnLaporan.setText("Laporan");
        btnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporan.setFocusable(false);
        btnLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLaporan.setIconTextGap(10);
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });
        panelKiri.add(btnLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 518, 214, 43));

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));

        jLabel81.setFont(new java.awt.Font("Kristen ITC", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("RTCollection");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        panelKiri.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 214, -1));

        getContentPane().add(panelKiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 29, 240, 730));

        panelDashboard.setBackground(new java.awt.Color(255, 255, 255));
        panelDashboard.setName("panelDashboard"); // NOI18N
        panelDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Logo.PNG"))); // NOI18N
        panelDashboard.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        kiribawahdashboard.setText("Batas kiri bawah dashboard");
        panelDashboard.add(kiribawahdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 638, -1, -1));

        jLabel77.setBackground(new java.awt.Color(51, 0, 255));
        jLabel77.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel77.setText("Proses Bisnis RTCollection");
        panelDashboard.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 240, 33));

        jLabel78.setBackground(new java.awt.Color(51, 0, 255));
        jLabel78.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel78.setText("* Dengan Pemasok");
        panelDashboard.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 227, 240, -1));

        jPanel6.setBackground(new java.awt.Color(51, 51, 255));

        jLabel84.setBackground(new java.awt.Color(51, 0, 255));
        jLabel84.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel84.setText("Pemesanan");

        txtPemesananPemasokDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPemesananPemasokDashboard.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(txtPemesananPemasokDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtPemesananPemasokDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel84)
                .addContainerGap())
        );

        panelDashboard.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 252, -1, -1));

        jPanel13.setBackground(new java.awt.Color(204, 0, 0));

        jLabel86.setBackground(new java.awt.Color(51, 0, 255));
        jLabel86.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel86.setText("Pemesanan");

        txtPemesananPelangganDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPemesananPelangganDashboard.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtPemesananPelangganDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtPemesananPelangganDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel86)
                .addContainerGap())
        );

        panelDashboard.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 426, -1, -1));

        jLabel79.setBackground(new java.awt.Color(51, 0, 255));
        jLabel79.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel79.setText("* Dengan Pelanggan");
        panelDashboard.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 401, 240, -1));

        jLabel90.setBackground(new java.awt.Color(51, 0, 255));
        jLabel90.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        panelDashboard.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 287, -1, -1));

        jLabel91.setBackground(new java.awt.Color(51, 0, 255));
        jLabel91.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        panelDashboard.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 466, -1, -1));

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));

        jLabel85.setBackground(new java.awt.Color(51, 0, 255));
        jLabel85.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel85.setText("Barang Masuk");

        txtBarangMasukDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtBarangMasukDashboard.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtBarangMasukDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtBarangMasukDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel85)
                .addContainerGap())
        );

        panelDashboard.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 252, -1, -1));

        jPanel14.setBackground(new java.awt.Color(255, 153, 0));

        jLabel87.setBackground(new java.awt.Color(51, 0, 255));
        jLabel87.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel87.setText("Barang Keluar");

        txtBarangKeluarPemasokDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtBarangKeluarPemasokDashboard.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txtBarangKeluarPemasokDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtBarangKeluarPemasokDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel87)
                .addContainerGap())
        );

        panelDashboard.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 426, -1, -1));

        jLabel93.setBackground(new java.awt.Color(51, 0, 255));
        jLabel93.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        panelDashboard.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 274, -1, -1));

        jLabel92.setBackground(new java.awt.Color(51, 0, 255));
        jLabel92.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        panelDashboard.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 505, -1, -1));

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        jLabel88.setBackground(new java.awt.Color(51, 0, 255));
        jLabel88.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel88.setText("Pembayaran");

        txtPembayaranPemasokDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPembayaranPemasokDashboard.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtPembayaranPemasokDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtPembayaranPemasokDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel88)
                .addContainerGap())
        );

        panelDashboard.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 252, -1, -1));

        jPanel15.setBackground(new java.awt.Color(0, 255, 0));

        txtKonfirmasiBarangDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtKonfirmasiBarangDashboard.setText("0");

        jLabel89.setBackground(new java.awt.Color(51, 0, 255));
        jLabel89.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel89.setText("Konfirmasi Barang");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txtKonfirmasiBarangDashboard))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel89)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtKonfirmasiBarangDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel89)
                .addContainerGap())
        );

        panelDashboard.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 426, -1, -1));

        jLabel94.setBackground(new java.awt.Color(51, 0, 255));
        jLabel94.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        panelDashboard.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(747, 503, -1, -1));

        jPanel16.setBackground(new java.awt.Color(255, 255, 0));

        jLabel95.setBackground(new java.awt.Color(51, 0, 255));
        jLabel95.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel95.setText("Pembayaran");

        txtPembayaranPelangganDashboard.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPembayaranPelangganDashboard.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtPembayaranPelangganDashboard)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtPembayaranPelangganDashboard)
                .addGap(18, 18, 18)
                .addComponent(jLabel95)
                .addContainerGap())
        );

        panelDashboard.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(862, 426, -1, -1));

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Arrows.png"))); // NOI18N
        panelDashboard.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 283, -1, -1));

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Arrows.png"))); // NOI18N
        panelDashboard.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 466, -1, -1));

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Arrows.png"))); // NOI18N
        panelDashboard.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 457, -1, -1));

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Arrows.png"))); // NOI18N
        panelDashboard.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 274, -1, -1));

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Arrows.png"))); // NOI18N
        panelDashboard.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 455, -1, -1));

        jPanel17.setBackground(new java.awt.Color(200, 242, 242));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel102.setText("Transaksi Uang RTCollection");
        jPanel17.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, -1, -1));

        jLabel101.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel101.setText("Uang Keluar");
        jPanel17.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 54, -1, -1));

        txtUangKeluarDashboard.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUangKeluarDashboard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtUangKeluarDashboard.setText("jLabel103");
        jPanel17.add(txtUangKeluarDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 85, 278, -1));

        txtUangMasukDashboard.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUangMasukDashboard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtUangMasukDashboard.setText("jLabel103");
        jPanel17.add(txtUangMasukDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 85, 278, -1));

        jLabel105.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel105.setText("Uang Masuk");
        jPanel17.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 54, -1, -1));

        dateDariDashboard.setForeground(new java.awt.Color(0, 102, 102));
        dateDariDashboard.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDariDashboardPropertyChange(evt);
            }
        });
        jPanel17.add(dateDariDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 8, 175, -1));

        dateSampaiDashboard.setForeground(new java.awt.Color(0, 102, 102));
        dateSampaiDashboard.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateSampaiDashboardPropertyChange(evt);
            }
        });
        jPanel17.add(dateSampaiDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 8, 169, -1));

        btnLihatDashboard.setText("Lihat");
        btnLihatDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatDashboardActionPerformed(evt);
            }
        });
        jPanel17.add(btnLihatDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 8, -1, 29));

        jLabel106.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("-");
        jPanel17.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 8, 43, 29));

        btnHitungDashboard.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnHitungDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Hitung.png"))); // NOI18N
        btnHitungDashboard.setText("Untung");
        btnHitungDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungDashboardActionPerformed(evt);
            }
        });
        jPanel17.add(btnHitungDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 126, -1, 35));

        txtUangDashboard.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUangDashboard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtUangDashboard.setText("Rp 0,00");
        jPanel17.add(txtUangDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 148, 448, -1));

        txtFitureDashboard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtFitureDashboard.setText("Fiture");
        jPanel17.add(txtFitureDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 152, -1, -1));

        btnHitungDashboard1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnHitungDashboard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Hitung.png"))); // NOI18N
        btnHitungDashboard1.setText("Rugi");
        btnHitungDashboard1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungDashboard1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnHitungDashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 167, 102, 35));

        btnHitungDashboard2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnHitungDashboard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Hitung.png"))); // NOI18N
        btnHitungDashboard2.setText("Zakat");
        btnHitungDashboard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungDashboard2ActionPerformed(evt);
            }
        });
        jPanel17.add(btnHitungDashboard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 145, -1, 35));

        panelDashboard.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 820, 210));

        getContentPane().add(panelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelPemasokkan.setBackground(new java.awt.Color(0, 204, 204));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnKeranjang.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
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

        jButton8.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/reset.png"))); // NOI18N
        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Ukuran3.setText("Total Harga");

        txtjumlah_kodi.setText("0");
        txtjumlah_kodi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlah_kodiActionPerformed(evt);
            }
        });

        jLabel6.setText("Jumlah Kodi Barang");

        txtTotalBiaya.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTotalBiaya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

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
                        .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
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

        btnProses.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/prosesButton.png"))); // NOI18N
        btnProses.setText("Proses Pemasokkan");
        btnProses.setIconTextGap(10);
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        panelPemasokkan.add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(889, 620, 210, -1));

        getContentPane().add(panelPemasokkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelBarangMasuk.setBackground(new java.awt.Color(0, 204, 204));
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

        btnSuksesBarangMasuk.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnSuksesBarangMasuk.setText("Sukses");
        btnSuksesBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuksesBarangMasukActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(btnSuksesBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 93, 209, -1));

        btnGagalBarangMasuk.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnGagalBarangMasuk.setText("Gagal");
        btnGagalBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGagalBarangMasukActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk.add(btnGagalBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 93, 209, -1));

        btnKonfirmasiBarangMasuk.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnKonfirmasiBarangMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/konfirmasiPengiriman.png"))); // NOI18N
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

        panelPembayaranPelanggan.setBackground(new java.awt.Color(0, 204, 204));
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

        cmbPelanggan1.setForeground(new java.awt.Color(0, 102, 102));
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
                .addGroup(panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDaftarBarangMasuk1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDaftarBarangMasuk1Layout.setVerticalGroup(
            panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk1Layout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasuk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );

        panelPembayaranPelanggan.add(panelDaftarBarangMasuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1070, 370));

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
        panelDeskripsiBarangMasuk1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        lblKeteranganPembayaran.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblKeteranganPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKeteranganPembayaran.setText("[Keterangan]");
        lblKeteranganPembayaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDeskripsiBarangMasuk1.add(lblKeteranganPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 410, 55));

        btnBatal.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/keluar.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setIconTextGap(10);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 190, -1));

        btnBayar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bayarButton.png"))); // NOI18N
        btnBayar.setText("Bayar");
        btnBayar.setIconTextGap(10);
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        panelDeskripsiBarangMasuk1.add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 190, -1));

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

        panelPembayaranPelanggan.add(panelDeskripsiBarangMasuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 1070, 221));

        getContentPane().add(panelPembayaranPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        PanelTransaksiBarangKeluar.setBackground(new java.awt.Color(0, 204, 204));
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

        btnTambahkanPengirimanBarangKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
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
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDaftarBarangMasuk2Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBarangBarangKeluar)
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
        txtKeteranganKarungBarangKeluar.setForeground(new java.awt.Color(0, 102, 102));
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

        btnBatalkanKarungBarangKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
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

        btnKonfirmasiPemberangkatanBarangKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnKonfirmasiPemberangkatanBarangKeluar.setText("KONFIRMASI PEMBERANGKATAN");
        btnKonfirmasiPemberangkatanBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiPemberangkatanBarangKeluarActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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
                            .addComponent(btnKonfirmasiPemberangkatanBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btnKonfirmasiPemberangkatanBarangKeluar))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(PanelTransaksiBarangKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        panelPembayaranPemasok.setBackground(new java.awt.Color(0, 204, 204));
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

        cmbPemasokPembayaranPemasok.setForeground(new java.awt.Color(0, 102, 102));
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
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addGroup(panelDaftarBarangMasuk3Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDaftarBarangMasuk3Layout.setVerticalGroup(
            panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDaftarBarangMasuk3Layout.createSequentialGroup()
                .addGroup(panelDaftarBarangMasuk3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPemasokPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelPembayaranPemasok.add(panelDaftarBarangMasuk3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1080, 370));

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

        btnBatalPembayaranPemasok.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnBatalPembayaranPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/keluar.png"))); // NOI18N
        btnBatalPembayaranPemasok.setText("Batal");
        btnBatalPembayaranPemasok.setIconTextGap(10);
        btnBatalPembayaranPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPembayaranPemasokActionPerformed(evt);
            }
        });

        btnBayarPembayaranPemasok.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btnBayarPembayaranPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bayarButton.png"))); // NOI18N
        btnBayarPembayaranPemasok.setText("Bayar");
        btnBayarPembayaranPemasok.setIconTextGap(10);
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
                    .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(lblKeteranganPembayaranPembayaranPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBayarPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(btnBatalPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(52, 52, 52)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel66)
                        .addGap(138, 138, 138))))
        );
        panelDeskripsiBarangMasuk2Layout.setVerticalGroup(
            panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiBarangMasuk2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDeskripsiBarangMasuk2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jLabel66)
                        .addGap(6, 6, 6)
                        .addComponent(lblKeteranganPembayaranPembayaranPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBayarPembayaranPemasok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatalPembayaranPemasok)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelPembayaranPemasok.add(panelDeskripsiBarangMasuk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 1080, 220));

        getContentPane().add(panelPembayaranPemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 630));

        panelKonfirmasiKeluar.setBackground(new java.awt.Color(0, 204, 204));
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

        btnKonfirmasiKonfirmasiKeluar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnKonfirmasiKonfirmasiKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/konfirmasiPengiriman.png"))); // NOI18N
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
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtStatusPengirimanKonfirmasiKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKonfirmasiKonfirmasiKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
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
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelKonfirmasiKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        panelLaporan.setBackground(new java.awt.Color(0, 204, 204));
        panelLaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelLaporan.setPreferredSize(new java.awt.Dimension(1100, 652));

        jPanel10.setBackground(new java.awt.Color(0, 153, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Tanggal Awal");

        dateSejakLaporan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateSejakLaporanPropertyChange(evt);
            }
        });

        dateSampaiLaporan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateSampaiLaporanPropertyChange(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Tanggal Akhir");

        btnPemasokkanLaporan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPemasokkanLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PemasokanLap.png"))); // NOI18N
        btnPemasokkanLaporan.setText("Pemasokkan");
        btnPemasokkanLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemasokkanLaporan.setIconTextGap(50);
        btnPemasokkanLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasokkanLaporanActionPerformed(evt);
            }
        });

        btnPengirimanLaporan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnPengirimanLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PengirimanLap.png"))); // NOI18N
        btnPengirimanLaporan.setText("Pengiriman");
        btnPengirimanLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPengirimanLaporan.setIconTextGap(50);
        btnPengirimanLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengirimanLaporanActionPerformed(evt);
            }
        });

        btnTPelangganLaporan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnTPelangganLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BayarPelangganLap.png"))); // NOI18N
        btnTPelangganLaporan.setText("Pembayaran Pelanggan");
        btnTPelangganLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTPelangganLaporan.setIconTextGap(50);
        btnTPelangganLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTPelangganLaporanActionPerformed(evt);
            }
        });

        btnTPemasokLaporan.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnTPemasokLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BayarPemasokLap.png"))); // NOI18N
        btnTPemasokLaporan.setText("Pembayaran Pemasok");
        btnTPemasokLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTPemasokLaporan.setIconTextGap(50);
        btnTPemasokLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTPemasokLaporanActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Report.png"))); // NOI18N
        jLabel83.setText("DETAIL LAPORAN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateSampaiLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateSejakLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTPemasokLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPengirimanLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPemasokkanLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTPelangganLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateSejakLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(dateSampaiLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnPemasokkanLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPengirimanLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTPelangganLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTPemasokLaporan)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLaporanLayout = new javax.swing.GroupLayout(panelLaporan);
        panelLaporan.setLayout(panelLaporanLayout);
        panelLaporanLayout.setHorizontalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLaporanLayout.createSequentialGroup()
                .addContainerGap(706, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLaporanLayout.setVerticalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

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
        if(txtjumlah_kodi.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "Isi data dengan benar");
        }
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
        ControllerKaryawan.addTransaksiKaryawan(karyawan_login.getKry_id());
        JOptionPane.showMessageDialog(this, "Berhasil memproses....");
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
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, Integer.parseInt(tblBarangPemesanan.getValueAt(tblBarangPemesanan.getSelectedRow(), 5).toString()), 1);  
        
        txtJumlahPemesanan.setModel(model1);
        txtBarangPemesanan.setText(barangPemesanan.getNama_Barang());
        txtUkuranPemesanan.setText(barangPemesanan.getUkuran_Barang());

        TotalHargaPemesanan = (int) Math.round(barangPemesanan.getHargaKodian_Barang());
        HargaPemesanan = (int) Math.round(barangPemesanan.getHargaJualKodian_Barang());
        txtTotalHargaPemesanan.setText(kursIndonesia.format((int) Math.round(barangPemesanan.getHargaJualKodian_Barang())));
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
                ControllerKaryawan.addTransaksiKaryawan(karyawan_login.getKry_id());
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
        this.dispose();
        Login a = new Login();
        a.setVisible(true);
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
            
            if((long) kursIndonesia.parse(txtKembalian.getText()) < 0l){
               txtKembalian.setText("Tidak ada kembalian");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_txtBayarKeyReleased

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        if(txtNamaToko.getText().equals("-")){
            JOptionPane.showMessageDialog(this, "Pilih data pelanggan dengan benar!");
            return;
        }
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setPmby_id(date.format(new Date())+"-"+ControllerPembayaranPembayaranPelanggan.getLastID());
        pembayaran.setPgn_id(controlTablePembayaranPelanggan.getPelangganId((String)cmbPelanggan1.getSelectedItem()));
        pembayaran.setKry_id(karyawan_login.getKry_id());
        //pembayaran.setKry_id("202006290001");
        //pembayaran.setPmby_tgl_transaksi(new Date());
        pembayaran.setPmby_uang_masuk(Integer.valueOf(txtBayar.getText()));

        ControllerPembayaranPembayaranPelanggan.simpanPembayaranPelanggan(pembayaran);
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
        ControllerKaryawan.addTransaksiKaryawan(karyawan_login.getKry_id());
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (cbPengiriman.isSelected() && cmbStatusBarangKeluar.getSelectedItem().toString().equals("Disiapkan")) {
            btnTambahkanPengirimanBarangKeluar.setEnabled(true);
            txtIDPengirimanBarangKeluar.setText(formatter.format(new Date()) + "-" + ControllerPengirimanBarangKeluar.getLastID());
        } else {
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
            txtIDPengirimanBarangKeluar.setText("[ ID PENGIRIMAN ]");
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
        if (cbPengiriman.isSelected() && cmbStatusBarangKeluar.getSelectedItem().toString().equals("Disiapkan")) {
            btnTambahkanPengirimanBarangKeluar.setEnabled(true);
            txtIDPengirimanBarangKeluar.setText(formatter.format(new Date()) + "-" + ControllerPengirimanBarangKeluar.getLastID());
        } else {
            btnTambahkanPengirimanBarangKeluar.setEnabled(false);
            txtIDPengirimanBarangKeluar.setText("[ ID PENGIRIMAN ]");
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
        ControllerKaryawan.addTransaksiKaryawan(kar.getKry_id());
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
            if((long) kursIndonesia.parse(txtKembalianPembayaranPemasok.getText()) < 0l){
               txtKembalianPembayaranPemasok.setText("Tidak ada kembalian");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_txtBayarPembayaranPemasokKeyReleased

    private void btnBatalPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPembayaranPemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalPembayaranPemasokActionPerformed

    private void btnBayarPembayaranPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarPembayaranPemasokActionPerformed
        // TODO add your handling code here:
        if(txtNamaPemasokPembayaranPemasok.getText().equals("-")){
            JOptionPane.showMessageDialog(this, "Tidak dapat melanjutkan transaksi");
            return;
        }
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setBpmsk_id(date.format(new Date())+"-"+ControllerPembayaranPembayaranPemasok.getLastID());
        pembayaran.setPms_id(ControllerTablePembayaranPemasok.getPemasokId((String)cmbPemasokPembayaranPemasok.getSelectedItem()));
        //pembayaran.setKry_id("202006290001");
        pembayaran.setKry_id(karyawan_login.getKry_id());
        pembayaran.setBpmsk_tgl_transaksi(new Date());
        pembayaran.setBpmsk_uang_dibayar(Integer.valueOf(txtBayarPembayaranPemasok.getText()));

        ControllerPembayaranPembayaranPemasok.simpanPembayaranPemasok(pembayaran);
        try {
            ControllerPemasokPembayaranPemasok.updateTransaksiPembayaran(Long.parseLong(txtBayarPembayaranPemasok.getText()), Long.parseLong((kursIndonesia.parse(txtSisaHutangPembayaranPemasok.getText())).toString()), pemasokPembayaranPemasok.getPms_id().toString());

            //ControllerTablePembayaranPemasok.addDataPemasok(modelPemasokPembayaranPemasok,(String) cmbPemasokPembayaranPemasok.getSelectedItem());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Tidak dapat menyimpan ke pemasok!");
        }

        JOptionPane.showMessageDialog(this, "Pembayaran berhasil....");
        ControllerKaryawan.addTransaksiKaryawan(karyawan_login.getKry_id());
        ClearPembayaranPemasok();
        addDataPemasok();
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

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        // TODO add your handling code here:
        show(panelLaporan);
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void infoKaryawanPengelolaanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoKaryawanPengelolaanDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoKaryawanPengelolaanDataActionPerformed

    private void infoPelangganPengelolaanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoPelangganPengelolaanDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoPelangganPengelolaanDataActionPerformed

    private void infoPemasokPengelolaanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoPemasokPengelolaanDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoPemasokPengelolaanDataActionPerformed

    private void infoJenisBarangPengelolaanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoJenisBarangPengelolaanDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoJenisBarangPengelolaanDataActionPerformed

    private void infoBarangPengelolaanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoBarangPengelolaanDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoBarangPengelolaanDataActionPerformed

    private void dateSejakLaporanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateSejakLaporanPropertyChange
        // TODO add your handling code here:
        try{
            if(dateSejakLaporan.getDate().compareTo(dateSampaiLaporan.getDate()) > 0){
                dateSejakLaporan.setDate(dateSampaiLaporan.getDate());
                JOptionPane.showMessageDialog(this, "Tidak dapat memilih lebih dari tanggal akhir");
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_dateSejakLaporanPropertyChange

    private void btnPemasokkanLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemasokkanLaporanActionPerformed

        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;
        Map <String, Object> parameter = new HashMap<String, Object>();
        parameter.put("tanggal1", dateSejakLaporan.getDate());
        parameter.put("tanggal2", dateSampaiLaporan.getDate());
        try {
            File file = new File("src/Report/LapBarangMasuk.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnPemasokkanLaporanActionPerformed

    private void btnPengirimanLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengirimanLaporanActionPerformed
        // TODO add your handling code here:
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;
        Map <String, Object> parameter = new HashMap<String, Object>();
        parameter.put("tanggal1", dateSejakLaporan.getDate());
        parameter.put("tanggal2", dateSampaiLaporan.getDate());
        try {
            File file = new File("src/Report/LapBarangKeluar.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnPengirimanLaporanActionPerformed

    private void btnTPelangganLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTPelangganLaporanActionPerformed
        // TODO add your handling code here:
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;
        Map <String, Object> parameter = new HashMap<String, Object>();
        parameter.put("tanggal1", dateSejakLaporan.getDate());
        parameter.put("tanggal2", dateSampaiLaporan.getDate());
        try {
            File file = new File("src/Report/LapPembayaranPelanggan.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnTPelangganLaporanActionPerformed

    private void btnTPemasokLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTPemasokLaporanActionPerformed
        // TODO add your handling code here:
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;
        Map <String, Object> parameter = new HashMap<String, Object>();
        parameter.put("tanggal1", dateSejakLaporan.getDate());
        parameter.put("tanggal2", dateSampaiLaporan.getDate());
        try {
            File file = new File("src/Report/LapPembayaranPemasok.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnTPemasokLaporanActionPerformed

    private void btnLihatDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatDashboardActionPerformed
        // TODO add your handling code here:
        getDataUangTransaksi(formatSQL.format(dateDariDashboard.getDate()), formatSQL.format(dateSampaiDashboard.getDate()));
    }//GEN-LAST:event_btnLihatDashboardActionPerformed
    private void ubahFitureDashbord(String info){
        int uang = 0;
        int masuk = 0;
        int keluar = 0;
        try{
            masuk = Integer.valueOf(kursIndonesia.parse(txtUangMasukDashboard.getText()).toString());
            keluar = Integer.valueOf(kursIndonesia.parse(txtUangKeluarDashboard.getText()).toString());
        }catch(Exception EX){
            
        }
        switch(info){
            case "Untung":{
                txtFitureDashboard.setText(info);
                uang=masuk - keluar;
                if(uang > 0){
                    txtUangDashboard.setText(kursIndonesia.format(uang));
                }else{
                    txtUangDashboard.setText("Tidak ada keuntungan");
                }
                break;
            }
            case "Rugi":{
                uang = keluar - masuk;
                txtFitureDashboard.setText(info);
                if(uang > 0){
                    txtUangDashboard.setText(kursIndonesia.format(uang));
                }else{
                    txtUangDashboard.setText("Tidak ada kerugian");
                }
                break;
            }
            case "Zakat":{
                txtFitureDashboard.setText(info);
                long uang1;
                uang1 = masuk - keluar;
                uang = (int) (uang1 / 1000) * 25;
                txtUangDashboard.setText(kursIndonesia.format(uang1)+" x 2.5 = "+ kursIndonesia.format(uang));
                break;
            }
            default:{
                break;
            }
        }
    }
    private void btnHitungDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungDashboardActionPerformed
        // TODO add your handling code here:
        ubahFitureDashbord(btnHitungDashboard.getText());
        
        

    }//GEN-LAST:event_btnHitungDashboardActionPerformed

    private void txtjumlah_kodiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlah_kodiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlah_kodiActionPerformed

    private void dateSampaiDashboardPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateSampaiDashboardPropertyChange
        // TODO add your handling code here:
        try{
            if(dateSampaiDashboard.getDate().compareTo(dateDariDashboard.getDate()) < 0){
                dateSampaiDashboard.setDate(dateDariDashboard.getDate());
                JOptionPane.showMessageDialog(this, "Tidak dapat memilih kurang dari tanggal awal");
            }
        }catch(Exception EX){
            
        }
    }//GEN-LAST:event_dateSampaiDashboardPropertyChange

    private void dateDariDashboardPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDariDashboardPropertyChange
        // TODO add your handling code here:
        try{
            if(dateDariDashboard.getDate().compareTo(dateSampaiDashboard.getDate()) > 0){
                dateDariDashboard.setDate(dateSampaiDashboard.getDate());
                JOptionPane.showMessageDialog(this, "Tidak dapat memilih lebih dari tanggal akhir");
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_dateDariDashboardPropertyChange

    private void dateSampaiLaporanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateSampaiLaporanPropertyChange
        // TODO add your handling code here:
        try{
            if(dateSampaiLaporan.getDate().compareTo(dateSejakLaporan.getDate()) < 0){
                dateSampaiLaporan.setDate(dateSejakLaporan.getDate());
                JOptionPane.showMessageDialog(this, "Tidak dapat memilih kurang dari tanggal awal");
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_dateSampaiLaporanPropertyChange

    private void panelPengolahanDataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelPengolahanDataPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPengolahanDataPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        instantisasiPengelolaanData();
    }//GEN-LAST:event_formWindowActivated

    private void btnHitungDashboard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungDashboard1ActionPerformed
        // TODO add your handling code here:
        ubahFitureDashbord(btnHitungDashboard1.getText());
        
    }//GEN-LAST:event_btnHitungDashboard1ActionPerformed

    private void btnHitungDashboard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungDashboard2ActionPerformed
        // TODO add your handling code here:
        ubahFitureDashbord(btnHitungDashboard2.getText());
    }//GEN-LAST:event_btnHitungDashboard2ActionPerformed

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
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnBarangKeluar;
    private javax.swing.JButton btnBarangMasuk;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBatalPembayaranPemasok;
    private javax.swing.JButton btnBatalkanKarungBarangKeluar;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnBayarPembayaranPemasok;
    private javax.swing.JButton btnGagalBarangMasuk;
    private javax.swing.JButton btnHitungDashboard;
    private javax.swing.JButton btnHitungDashboard1;
    private javax.swing.JButton btnHitungDashboard2;
    private javax.swing.JButton btnJBarang;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnKeranjangPemesanan;
    private javax.swing.JButton btnKonfirmasiBarangMasuk;
    private javax.swing.JButton btnKonfirmasiKeluar;
    private javax.swing.JButton btnKonfirmasiKonfirmasiKeluar;
    private javax.swing.JButton btnKonfirmasiPemberangkatanBarangKeluar;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLihatDashboard;
    private javax.swing.JButton btnMenuUtama;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPemasok;
    private javax.swing.JButton btnPemasokkanLaporan;
    private javax.swing.JButton btnPembayaranPelanggan;
    private javax.swing.JButton btnPembayaranPemasok;
    private javax.swing.JButton btnPemesananPelanggan;
    private javax.swing.JButton btnPemesananPemasok;
    private javax.swing.JButton btnPengirimanLaporan;
    private javax.swing.JButton btnPengolahanData;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnRefreshPemesanan;
    private javax.swing.JButton btnSemuaKonfirmasiKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSuksesBarangMasuk;
    private javax.swing.JButton btnTPelangganLaporan;
    private javax.swing.JButton btnTPemasokLaporan;
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
    private com.toedter.calendar.JDateChooser dateDariDashboard;
    private com.toedter.calendar.JDateChooser dateSampaiDashboard;
    private com.toedter.calendar.JDateChooser dateSampaiLaporan;
    private com.toedter.calendar.JDateChooser dateSejakLaporan;
    private javax.swing.JLabel dummy1;
    private javax.swing.JButton infoBarangPengelolaanData;
    private javax.swing.JButton infoJenisBarangPengelolaanData;
    private javax.swing.JButton infoKaryawanPengelolaanData;
    private javax.swing.JButton infoPelangganPengelolaanData;
    private javax.swing.JButton infoPemasokPengelolaanData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
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
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
    private javax.swing.JLabel karyawan_info_posisi;
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
    private javax.swing.JPanel panelLaporan;
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
    private javax.swing.JLabel txtBarangKeluarPemasokDashboard;
    private javax.swing.JLabel txtBarangMasukDashboard;
    private javax.swing.JTextField txtBarangPemesanan;
    private javax.swing.JLabel txtBatasKeteranganBarangKeluar;
    private javax.swing.JLabel txtBatasKeteranganBarangKeluar1;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtBayarPembayaranPemasok;
    private javax.swing.JLabel txtFitureDashboard;
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
    private javax.swing.JLabel txtKonfirmasiBarangDashboard;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNamaPelanggan1;
    private javax.swing.JTextField txtNamaPelangganKonfirmasiKeluar;
    private javax.swing.JTextField txtNamaPemasokBarangMasuk;
    private javax.swing.JTextField txtNamaPemasokPembayaranPemasok;
    private javax.swing.JTextField txtNamaToko;
    private javax.swing.JTextField txtNomorKarungBarangKeluar;
    private javax.swing.JTextField txtPemasok;
    private javax.swing.JLabel txtPembayaranPelangganDashboard;
    private javax.swing.JLabel txtPembayaranPemasokDashboard;
    private javax.swing.JLabel txtPemesananPelangganDashboard;
    private javax.swing.JLabel txtPemesananPemasokDashboard;
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
    private javax.swing.JLabel txtUangDashboard;
    private javax.swing.JTextField txtUangDibayarBarangMasuk;
    private javax.swing.JLabel txtUangKeluarDashboard;
    private javax.swing.JLabel txtUangMasukDashboard;
    private javax.swing.JTextField txtUangTransaksiBarangMasuk;
    private javax.swing.JTextField txtUkuran;
    private javax.swing.JTextField txtUkuranBarangKeluar;
    private javax.swing.JTextField txtUkuranPemesanan;
    private javax.swing.JLabel txtinfoTanggal;
    private javax.swing.JTextField txtjumlah_kodi;
    // End of variables declaration//GEN-END:variables
}
