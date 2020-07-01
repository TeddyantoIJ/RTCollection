/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Karyawan;
import RTClass.Pelanggan;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Polman
 */
public class CPelanggan {
    
    public boolean simpanPelanggan(Pelanggan pel){
      
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pelanggan values (?,?,?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pel.getPgn_id());
            connection.pstat.setString(2, pel.getPgn_nama());
            connection.pstat.setString(3, pel.getPgn_nama_toko());
            connection.pstat.setString(4, pel.getPgn_no_hp());
            connection.pstat.setString(5, pel.getPgn_email());
            connection.pstat.setString(6, pel.getPgn_alamat());
            connection.pstat.setString(7, pel.getPgn_nama_pasar());
            connection.pstat.setInt(8, pel.getPgn_jumlah_transaksi());
            connection.pstat.setInt(9, pel.getPgn_uang_transaksi());
            connection.pstat.setInt(10, pel.getPgn_uang_transaksi());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
            return true;
        } catch (Exception e) {
            System.out.println("Gagal simpanPelanggan "+e.toString());
            return false;
        }
    }
    public void updatePelanggan(Pelanggan pel){

        DBConnect connection = new DBConnect();
        try {
            String query = "update Pelanggan"
                    + " set pgn_nama = ?, pgn_nama_toko = ?, pgn_no_hp = ?, pgn_email = ?, pgn_alamat = ?, "
                    + "pgn_nama_pasar = ?, pgn_jumlah_transaksi = ?, pgn_uang_transaksi = ?, pgn_total_hutang = ? "
                    + "where pgn_id ='"+pel.getPgn_id()+"'";
            
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.setString(1, pel.getPgn_nama());
            connection.pstat.setString(2, pel.getPgn_nama_toko());
            connection.pstat.setString(3, pel.getPgn_no_hp());
            connection.pstat.setString(4, pel.getPgn_email());
            connection.pstat.setString(5, pel.getPgn_alamat());
            connection.pstat.setString(6, pel.getPgn_nama_pasar());
            connection.pstat.setInt(7, pel.getPgn_jumlah_transaksi());
            connection.pstat.setInt(8, pel.getPgn_uang_transaksi());
            connection.pstat.setInt(9, pel.getPgn_uang_transaksi());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updatePelanggan "+e.toString());
        }
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(pgn_id) Total from Pelanggan";
                connection.result = connection.stat.executeQuery(query);
                int count = 0;
                while (connection.result.next()) {
                    
                    count = Integer.parseInt(connection.result.getString("total"));
                    }
                connection.stat.close();
                connection.result.close();
                
                
                count++;
                return "" + count;
                
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        return null;
    }
    public String getIDPelanggan(String nama){
        DBConnect connection = new DBConnect();
        String output = "";
        try{          
                
                connection.stat = connection.conn.createStatement();
                String query = "select pgn_id from Pelanggan where pgn_nama = '"+nama+"'";
                connection.result = connection.stat.executeQuery(query);
               System.out.println(query);
                while (connection.result.next()) {
                    
                    output = (connection.result.getString("pgn_id"));
                }
                
                connection.stat.close();
                connection.result.close();
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        
        return output;
    }
}
