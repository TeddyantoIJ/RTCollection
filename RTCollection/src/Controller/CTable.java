/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Karyawan;
import RTClass.Pelanggan;
import RTClass.Pemasok;
import javax.swing.table.DefaultTableModel;
import connection.DBConnect;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
/**
 *
 * @author Polman
 */
public class CTable {
    private DefaultTableModel model = new DefaultTableModel();
    
    public CTable(){
    }
    
    //================================= KARYAWAN ====================================
    public DefaultTableModel addDataKaryawan(DefaultTableModel model){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Karyawan";
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                Object[] obj = new Object[11];
                obj[0] = i;
                obj[1] = connection.result.getString("kry_id");
                obj[2] = connection.result.getString("kry_nama");
                obj[3] = connection.result.getString("kry_no_hp");
                obj[4] = connection.result.getString("kry_email");
                obj[5] = formatter.format(connection.result.getDate("kry_tgl_lahir"));
                obj[6] = connection.result.getString("alamat");
                obj[7] = connection.result.getString("kry_total_transaksi");
                obj[8] = connection.result.getString("kry_jabatan");
                obj[9] = connection.result.getString("kry_username");
                obj[10] = connection.result.getString("kry_password");
                i++;
                System.out.println(obj[1].toString());
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataKaryawan!\n" + e.toString());
        }
        return model;
    }
    public Karyawan getSelectedRowKaryawan(JTable in){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Karyawan output = new Karyawan();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        
        try{
            output.setKry_id(in.getValueAt(i, 1).toString());
            output.setKry_nama(in.getValueAt(i, 2).toString());
            output.setKry_no_hp(in.getValueAt(i, 3).toString());
            output.setKry_email(in.getValueAt(i, 4).toString());
            output.setKry_tgl_lahir(formatter.parse(in.getValueAt(i, 5).toString()));
            output.setAlamat(in.getValueAt(i, 6).toString());
            output.setKry_username(in.getValueAt(i, 7).toString());
            output.setKry_password(in.getValueAt(i, 8).toString());
            output.setKry_jabatan(in.getValueAt(i, 9).toString());
        }catch(Exception ex){
            System.out.println("Error getSelectedRow : "+ex.toString());
            return null;
        }
        return output;
    }
    
    //================================= PELANGGAN ====================================
    CPelanggan controllerPelanggan = new CPelanggan();
    
    
    public DefaultTableModel addDataPelanggan(DefaultTableModel model){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pelanggan";
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                Object[] obj = new Object[10];
                obj[0] = i;
                obj[1] = connection.result.getString("pgn_nama");
                obj[2] = connection.result.getString("pgn_nama_toko");
                obj[3] = connection.result.getString("pgn_no_hp");
                obj[4] = connection.result.getString("pgn_email");
                obj[5] = connection.result.getString("pgn_alamat");
                obj[6] = connection.result.getString("pgn_nama_pasar");
                obj[7] = connection.result.getInt("pgn_jumlah_transaksi");
                obj[8] = connection.result.getInt("pgn_uang_transaksi");
                obj[9] = connection.result.getInt("pgn_total_hutang");
                i++;
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPelanggan!\n" + e.toString());
        }
        return model;
    }
    public Pelanggan getSelectedRowPelanggan(JTable in){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Pelanggan output = new Pelanggan();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        
        try{
            output.setPgn_id(controllerPelanggan.getIDPelanggan(in.getValueAt(i, 1).toString()));
            output.setPgn_nama(in.getValueAt(i, 1).toString());
            output.setPgn_nama_toko(in.getValueAt(i, 2).toString());
            output.setPgn_no_hp(in.getValueAt(i, 3).toString());
            output.setPgn_email((in.getValueAt(i, 4).toString()));
            output.setPgn_alamat(in.getValueAt(i, 5).toString());
            output.setPgn_nama_pasar(in.getValueAt(i, 6).toString());
            output.setPgn_jumlah_transaksi(Integer.parseInt(in.getValueAt(i, 7).toString()));
            output.setPgn_uang_transaksi(Integer.parseInt(in.getValueAt(i, 8).toString()));
            output.setPgn_total_hutang(Integer.parseInt(in.getValueAt(i, 9).toString()));
        }catch(Exception ex){
            System.out.println("Error getSelectedRowPelanggan : "+ex.toString());
            return null;
        }
        return output;
    }
    
    //================================= PEMASOK ====================================
    CSupplier controllerPemasok = new CSupplier();
    
    
    public DefaultTableModel addDataPemasok(DefaultTableModel model){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok";
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                Object[] obj = new Object[7];
                obj[0] = i;
                obj[1] = connection.result.getString("pms_nama");
                obj[2] = connection.result.getString("pms_alamat");
                obj[3] = connection.result.getString("pms_no_hp");
                obj[4] = connection.result.getInt("pms_jumlah_transaksi");
                obj[5] = connection.result.getInt("pms_uang_transaksi");
                obj[6] = connection.result.getInt("pms_total_hutang");
                i++;
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
        return model;
    }
    public String getPemasokNama(String id){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String output = "";
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok where pms_id = '"+id+"'";
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                
                output = connection.result.getString("pms_nama");
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
        return output;
    }
    public String getPemasokId(String nama){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String output = "";
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok where pms_nama = '"+nama+"'";
            connection.result = connection.stat.executeQuery(query);
            int i =1;
            while (connection.result.next()) {
                
                output = connection.result.getString("pms_id");
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
        return output;
    }
    public Pemasok getSelectedRowPemasok(JTable in){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Pemasok output = new Pemasok();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        
        try{
            output.setPms_id(controllerPemasok.getIDPemasok(in.getValueAt(i, 1).toString()));
            output.setPms_nama(in.getValueAt(i, 1).toString());
            output.setPms_alamat(in.getValueAt(i, 2).toString());
            output.setPms_no_hp(in.getValueAt(i, 3).toString());
            output.setPms_jumlah_transaksi(Integer.parseInt(in.getValueAt(i, 4).toString()));
            output.setPms_uang_transaksi(Integer.parseInt(in.getValueAt(i, 5).toString()));
            output.setPms_total_hutang(Integer.parseInt(in.getValueAt(i, 6).toString()));
        }catch(Exception ex){
            System.out.println("Error getSelectedRowPemasok : "+ex.toString());
            return null;
        }
        return output;
    }
       
    
}
