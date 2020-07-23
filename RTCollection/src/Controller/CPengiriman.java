/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Pengiriman;
import connection.DBConnect;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Polman
 */
public class CPengiriman {
    public void simpanPengiriman(Pengiriman pgrm){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pengiriman values (?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pgrm.getPgrm_id());
            connection.pstat.setDate(2, (java.sql.Date) pgrm.getPgrm_tgl_transaksi());
            connection.pstat.setInt(3, pgrm.getPgrm_jumlah_barang());
            connection.pstat.setInt(4, pgrm.getPgrm_jumlah_kodi());
            connection.pstat.setInt(5, pgrm.getPgrm_jumlah_karung());
            connection.pstat.setString(6, pgrm.getKry_id());
            connection.pstat.setString(7, "Dikirim");
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpanPengiriman "+e.toString());
        }
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
