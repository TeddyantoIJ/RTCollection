/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.DetailPemesanan;
import RTClass.Pemesanan;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 *
 * @author rizky
 */
public class CPemesanan {
    public void simpanPemesanan(Pemesanan psn){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(psn.getPmsn_tgl_transaksi());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Pemesanan values (?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, psn.getPmsn_id());
            connection.pstat.setString(2, tanggal);
            connection.pstat.setInt(3, psn.getPgn_id());
            connection.pstat.setInt(4, psn.getPmsn_jumlah_barang());
            connection.pstat.setInt(5, psn.getPmsn_jumlah_kodi());
            connection.pstat.setDouble(6, psn.getPmsn_total_uang());
            connection.pstat.setString(7, psn.getPmsn_Status());
            connection.pstat.setString(8, psn.getKry_id());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal pemesanan "+e.toString());
        }
    }
    
    public void simpanDetailPemesanan(DetailPemesanan psn){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String tanggal = formatter.format(psn.getPmsn_tgl_transaksi());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into DetailPemesanan values (?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            String idDetail = psn.getPmsn_id();
            connection.pstat.setString(1, idDetail);
            connection.pstat.setString(2, psn.getB_id());
            connection.pstat.setInt(3, psn.getDetail_jumlah_barang());
            connection.pstat.setInt(4, psn.getDetail_jumlah_kodi());
            connection.pstat.setDouble(5, psn.getDetail_total_uang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal detail pemesanan "+e.toString());
        }
    }
    
//    public void updateKaryawan(Karyawan kar){
//        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String tanggal = formatter.format(kar.getKry_tgl_lahir());
//        DBConnect connection = new DBConnect();
//        try {
//            String query = "update karyawan"
//                    + " set kry_nama = ?, kry_no_hp = ?, kry_email = ?, kry_tgl_lahir = ?, "
//                    + "alamat = ?, kry_username = ?, kry_password = ?, kry_jabatan = ? "
//                    + "where kry_id ='"+kar.getKry_id()+"'";
//            connection.pstat = connection.conn.prepareStatement(query);
//            connection.pstat.setString(1, kar.getKry_nama());
//            connection.pstat.setString(2, kar.getKry_no_hp());
//            connection.pstat.setString(3, kar.getKry_email());
//            connection.pstat.setString(4, tanggal);
//            connection.pstat.setString(5, kar.getAlamat());
//            connection.pstat.setString(6, kar.getKry_username());
//            connection.pstat.setString(7, kar.getKry_password());
//            connection.pstat.setString(8, kar.getKry_jabatan());
//            
//            connection.pstat.executeUpdate();
//            connection.pstat.close();
//        } catch (Exception e) {
//            System.out.println("Gagal updateKaryawan "+e.toString());
//        }
//    }
    
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(pmsn_id) Total from Pemesanan";
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
