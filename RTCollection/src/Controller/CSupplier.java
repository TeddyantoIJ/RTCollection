/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Pelanggan;
import RTClass.Pemasok;
import connection.DBConnect;

/**
 *
|       pms_id                      |
|       pms_nama                    |
|       pms_alamat                  |
|       pms_no_hp                   |
|       pms_jumlah_transaksi        |
|       pms_uang_transaksi          |
|       pms_total_hutang            |
 * @author Polman
 */
public class CSupplier {

    public CSupplier() {
    }
    public boolean simpanPemasok(Pemasok pem){
      
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pemasok values (?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pem.getPms_id());
            connection.pstat.setString(2, pem.getPms_nama());
            connection.pstat.setString(3, pem.getPms_alamat());
            connection.pstat.setString(4, pem.getPms_no_hp());
            connection.pstat.setInt(5, pem.getPms_jumlah_transaksi());
            connection.pstat.setInt(6, pem.getPms_uang_transaksi());
            connection.pstat.setInt(7, pem.getPms_total_hutang());
            
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
            return true;
        } catch (Exception e) {
            System.out.println("Gagal simpanPemasok "+e.toString());
            return false;
        }
    }
    public void updatePemasok(Pemasok pem){

        DBConnect connection = new DBConnect();
        try {
            String query = "update Pemasok"
                    + " set pms_nama = ?, pms_alamat = ?, pms_no_hp = ?, pms_jumlah_transaksi = ?, pms_uang_transaksi = ?, pms_total_hutang = ? "
                    + "where pms_id ='"+pem.getPms_id()+"'";
            
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.setString(1, pem.getPms_nama());
            connection.pstat.setString(2, pem.getPms_alamat());
            connection.pstat.setString(3, pem.getPms_no_hp());
            connection.pstat.setInt(4, pem.getPms_jumlah_transaksi());
            connection.pstat.setInt(5, pem.getPms_uang_transaksi());
            connection.pstat.setInt(6, pem.getPms_total_hutang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updatePemasok "+e.toString());
        }
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(pms_id) Total from Pemasok";
                connection.result = connection.stat.executeQuery(query);
                int count = 0;
                while (connection.result.next()) {
                    
                    count = Integer.parseInt(connection.result.getString("Total"));
                    }
                connection.stat.close();
                connection.result.close();
                
                
                count++;
                
                return count+"";
                
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        return null;
    }
    public String getIDPemasok(String nama){
        DBConnect connection = new DBConnect();
        String output = "";
        try{          
                
                connection.stat = connection.conn.createStatement();
                String query = "select pms_id from Pemasok where pms_nama = '"+nama+"'";
                connection.result = connection.stat.executeQuery(query);
               System.out.println(query);
                while (connection.result.next()) {
                    
                    output = (connection.result.getString("pms_id"));
                }
                
                connection.stat.close();
                connection.result.close();
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        return output;
    }
    public Pemasok getDataPemasok(String nama){
        DBConnect connection = new DBConnect();
        Pemasok output = new Pemasok();
        try{          
                
                connection.stat = connection.conn.createStatement();
                String query = "select * from Pemasok where pms_nama = '"+nama+"'";
                connection.result = connection.stat.executeQuery(query);
               System.out.println(query);
                while (connection.result.next()) {
                    
                    output.setPms_id(connection.result.getString("pms_id"));
                    output.setPms_nama(connection.result.getString("pms_nama"));
                    output.setPms_alamat(connection.result.getString("pms_alamat"));
                    output.setPms_no_hp(connection.result.getString("pms_no_hp"));
                    output.setPms_jumlah_transaksi(connection.result.getInt("pms_jumlah_transaksi"));
                    output.setPms_uang_transaksi(connection.result.getInt("pms_uang_transaksi"));
                    output.setPms_total_hutang(connection.result.getInt("pms_total_hutang"));
                }
                
                connection.stat.close();
                connection.result.close();
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        return output;
    }
    public void updateTransaksiPemasokkan(long uang, long hutang , String id){
            DBConnect connection = new DBConnect();
        try {
            String query = "update Pemasok"
                    + " set pms_jumlah_transaksi = pms_jumlah_transaksi+1, pms_uang_transaksi = pms_uang_transaksi + ?, pms_total_hutang = ? "
                    + "where pms_id ='"+id+"'";
            
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.setLong(1, uang);
            connection.pstat.setLong(2, hutang);
            
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updateTransaksiPemasokkan "+e.toString());
        }
    }
    public void updateTransaksiPembayaran(long uang, long hutang , String id){
            DBConnect connection = new DBConnect();
        try {
            String query = "update Pemasok"
                    + " set pms_uang_transaksi = pms_uang_transaksi + ?, pms_total_hutang = ? "
                    + "where pms_id ='"+id+"'";
            
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.setLong(1, uang);
            connection.pstat.setLong(2, hutang);
            
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updateTransaksiPembayaran "+e.toString());
        }
    }
}
