/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import RTClass.DetailPemasokkan;
import RTClass.Pemasokkan;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Polman
 */
public class CPemasokkan {
    public void simpanPemasokkan(Pemasokkan pmsk){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pemasokkan values (?,?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, pmsk.getPmsk_id());
            connection.pstat.setString(2, pmsk.getPms_id());
            connection.pstat.setString(3, tanggal);
            connection.pstat.setInt(4, pmsk.getPmsk_jumlah_barang());
            connection.pstat.setInt(5, pmsk.getPmsk_jumlah_kodi());
            connection.pstat.setDouble(6, pmsk.getPmsk_total_uang());
            connection.pstat.setDouble(7, pmsk.getPmsk_uang_dibayar());
            connection.pstat.setString(8, pmsk.getKry_id());
            connection.pstat.setString(9, pmsk.getStatus());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpanPemasokkan "+e.toString());
        }
    }
    public void simpanDetailPemasokkan(DetailPemasokkan dpmsk){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(new Date());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into DetailPemasokkan values (?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, dpmsk.getPmsk_id());
            connection.pstat.setString(2, dpmsk.getB_id());
            connection.pstat.setInt(3, dpmsk.getDetail_jumlah_barang());
            connection.pstat.setInt(4, dpmsk.getDetail_jumlah_kodi());
            connection.pstat.setDouble(5, dpmsk.getDetail_harga());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal detail simpanDetailPemasokkan "+e.toString());
        }
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(pmsk_id) Total from Pemasokkan";
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
