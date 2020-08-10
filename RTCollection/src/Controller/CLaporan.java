/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import connection.DBConnect;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Polman
 */
public class CLaporan {
    
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

    public CLaporan() {
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    
    
    //================================= PEMASOKKAN ====================================
    public DefaultTableModel addDataPemasokkan(DefaultTableModel model, Date sejak, Date sampai){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
        String tempkode = "";
        boolean sama = false;
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from laporanBarangMasuk where tanggal between '"+formatter2.format(sejak).toString()+"' and '"+formatter2.format(sampai).toString()+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                sama = false;
                Object[] obj = new Object[12];
                obj[0] = i;
                obj[1] = connection.result.getString("kode");
                if(obj[1].toString().equals(tempkode)){
                    sama = true;
                }else{
                    tempkode = connection.result.getString("kode");
                }
                
                obj[2] = formatter.format(connection.result.getDate("tanggal"));
                obj[3] = connection.result.getString("b_nama");
                obj[4] = connection.result.getString("b_ukuran");
                
                obj[5] = connection.result.getString("pemasok");
                obj[6] = connection.result.getString("jumlah kodi");
                //obj[5] = connection.result.getString("harga");
                obj[7] = kursIndonesia.format(Integer.valueOf(connection.result.getString("harga").substring(0,connection.result.getString("harga").length()-5)));
                obj[8] = connection.result.getString("total kodi");
                obj[9] = kursIndonesia.format(Integer.valueOf(connection.result.getString("total uang").substring(0,connection.result.getString("total uang").length()-5)));
                //obj[7] = connection.result.getString("total uang");
                obj[10] = connection.result.getString("status");
                //obj[11] = connection.result.getString("pelayan");
                
                i++;
                //System.out.println(obj[1].toString());
                if(sama){
                    obj[1] = "";
                    obj[2] = "";
                    obj[5] = "";
                    obj[8] = "";
                    obj[9] = "";
                    obj[10] = "";
                    obj[11] = "";
                }
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasokkan!\n" + e.toString());
        }
        return model;
    }
    
    //================================= BARANG KELUAR ====================================
    public DefaultTableModel addDataBarangKeluar(DefaultTableModel model, Date sejak, Date sampai){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
        String tempkode = "";
        boolean sama = false;
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from laporanBarangKeluar where tanggal between '"+formatter2.format(sejak).toString()+"' and '"+formatter2.format(sampai).toString()+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                sama = false;
                Object[] obj = new Object[12];
                obj[0] = i;
                obj[1] = connection.result.getString("kode");
                if(obj[1].toString().equals(tempkode)){
                    sama = true;
                }else{
                    tempkode = connection.result.getString("kode");
                }
                
                obj[2] = formatter.format(connection.result.getDate("tanggal"));
                obj[3] = connection.result.getString("toko");
                obj[4] = connection.result.getString("nama");
                obj[5] = connection.result.getString("barang");
                obj[6] = connection.result.getString("ukuran");
                
                
                obj[7] = connection.result.getString("jumlah kodi");
                //obj[5] = connection.result.getString("harga");
                obj[8] = kursIndonesia.format(Integer.valueOf(connection.result.getString("harga").substring(0,connection.result.getString("harga").length()-5)));
                obj[9] = connection.result.getString("total kodi");
                obj[10] = kursIndonesia.format(Integer.valueOf(connection.result.getString("total uang").substring(0,connection.result.getString("total uang").length()-5)));
                //obj[7] = connection.result.getString("total uang");
                obj[11] = connection.result.getString("status");
                //obj[11] = connection.result.getString("pelayan");
                
                i++;
                //System.out.println(obj[1].toString());
                if(sama){
                    obj[1] = "";
                    obj[2] = "";
                    obj[3] = "";
                    obj[4] = "";
                    
                    obj[9] = "";
                    obj[10] = "";
                    obj[11] = "";
                }
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataBarangKeluar!\n" + e.toString());
        }
        return model;
    }
    //================================= PEMBAYARAN PELANGGAN ====================================
    public DefaultTableModel addDataPembayaranPelanggan(DefaultTableModel model, Date sejak, Date sampai){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
        String tempkode = "";
        boolean sama = false;
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from laporanPembayaranPelanggan where tanggal between '"+formatter2.format(sejak).toString()+"' and '"+formatter2.format(sampai).toString()+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                sama = false;
                Object[] obj = new Object[8];
                obj[0] = i;
                obj[1] = connection.result.getString("kode");
                if(obj[1].toString().equals(tempkode)){
                    sama = true;
                }else{
                    tempkode = connection.result.getString("kode");
                }
                
                obj[2] = formatter.format(connection.result.getDate("tanggal"));
                obj[3] = connection.result.getString("nama");
                obj[4] = connection.result.getString("toko");
                
                obj[5] = connection.result.getString("pasar");
                obj[6] = kursIndonesia.format(connection.result.getInt("pembayaran"));
                obj[7] = connection.result.getString("pelayan");
                
                i++;
                //System.out.println(obj[1].toString());
                
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasokkan!\n" + e.toString());
        }
        return model;
    }
    //================================= PEMBAYARAN PEMASOK ====================================
    public DefaultTableModel addDataPembayaranPemasok(DefaultTableModel model, Date sejak, Date sampai){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
        String tempkode = "";
        boolean sama = false;
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from laporanPembayaranPemasok where tanggal between '"+formatter2.format(sejak).toString()+"' and '"+formatter2.format(sampai).toString()+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                sama = false;
                Object[] obj = new Object[7];
                obj[0] = i;
                obj[1] = connection.result.getString("kode");
                if(obj[1].toString().equals(tempkode)){
                    sama = true;
                }else{
                    tempkode = connection.result.getString("kode");
                }
                
                obj[2] = formatter.format(connection.result.getDate("tanggal"));
                obj[3] = connection.result.getString("nama");
                obj[4] = connection.result.getString("alamat");
                
                
                obj[5] = kursIndonesia.format(connection.result.getInt("pembayaran"));
                obj[6] = connection.result.getString("pelayan");
                
                i++;
                //System.out.println(obj[1].toString());
                
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasokkan!\n" + e.toString());
        }
        return model;
    }
}
