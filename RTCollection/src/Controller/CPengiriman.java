/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.DetailPengiriman;
import RTClass.Pengiriman;
import connection.DBConnect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Polman
 */
public class CPengiriman {
    public CPengiriman(){
    }
    public void simpanPengiriman(Pengiriman pgrm){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pengiriman values (?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pgrm.getPgrm_id());
            connection.pstat.setString(2, tanggal);
            connection.pstat.setInt(3, pgrm.getPgrm_jumlah_barang());
            connection.pstat.setInt(4, pgrm.getPgrm_jumlah_kodi());
            connection.pstat.setInt(5, pgrm.getPgrm_jumlah_karung());
            connection.pstat.setString(6, pgrm.getKry_id());
            connection.pstat.setString(7, "Dikirim");
            connection.pstat.setString(8, pgrm.getPgn_id());
            
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpanPengiriman "+e.toString());
        }
    }
    
    public void updateStatus(String pgrm_id, String status){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "update Pengiriman set status = '"+status+"' where pgrm_id = ?";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pgrm_id);

            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updateStatus "+e.toString());
        }
    }
    public void simpanDetailPengiriman(DetailPengiriman dit){
        
        //String tanggal = formatter.format(psn.getPmsn_tgl_transaksi());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into DetailPengiriman values (?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.setString(1, dit.getPmsn_id());
            connection.pstat.setString(2, dit.getPgrm_id());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal detail simpanDetailPemesanan "+e.toString());
        }
    }
    public List<String> getIDPemesananDetail(String pgrm_id){
        List<String> List_pmsn_id = new ArrayList<>();
        
        
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM DetailPengiriman where pgrm_id = '"+pgrm_id+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i = 1;
            while (connection.result.next()) {
                
                List_pmsn_id.add(connection.result.getString("pmsn_id"));
                
                i++;
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di CTable Barang getIDPemesananDetail!\n" + e.toString());
        }
        return List_pmsn_id;
    
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(pgrm_id) Total from Pengiriman";
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
            System.out.println("getLastID!!\n" + e.toString());
        }
        return null;
    }
}
