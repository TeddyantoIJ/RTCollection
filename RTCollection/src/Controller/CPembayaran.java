/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Pembayaran;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rizky
 */
public class CPembayaran {
    public void simpanPembayaranPemasok(Pembayaran pmbyrn){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into BayarPemasok values (?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pmbyrn.getBpmsk_id());
            connection.pstat.setString(2, pmbyrn.getPms_id());
            connection.pstat.setString(3, pmbyrn.getKry_id());
            connection.pstat.setString(4, tanggal);
            connection.pstat.setDouble(5, pmbyrn.getBpmsk_uang_dibayar());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpan Pembayaran Pemasokkan "+e.toString());
        }
    }
    
    public void simpanPembayaranPelanggan(Pembayaran pmbyrn){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pembayaran values (?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pmbyrn.getPmby_id());
            connection.pstat.setString(2, pmbyrn.getPgn_id());
            connection.pstat.setString(3, pmbyrn.getKry_id());
            connection.pstat.setString(4, tanggal);
            connection.pstat.setDouble(5, pmbyrn.getPmby_uang_masuk());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpan Pembayaran Pelanggan "+e.toString());
        }
    }
    
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(Bpmks_id) Total from BayarPemasok";
                connection.result = connection.stat.executeQuery(query);
                int count = 0;
                while (connection.result.next()) {
                    
                    count = Integer.parseInt(connection.result.getString("total"));
                    }
                connection.stat.close();
                connection.result.close();
                
                
                count++;
                
                if(Integer.toString(count).length() == 1)
            {
                return "000" + count;
            }
            else if (Integer.toString(count).length() == 2)
            {
                return "00" + count;
            }
            else if (Integer.toString(count).length() == 3)
            {
                return "0" + count;
            }
            else
            {
                return Integer.toString(count);
            }
        }
        catch(Exception e){
            System.out.println("Error!!\n" + e.toString());
        }
        return null;
    }
}
