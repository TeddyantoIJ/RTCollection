/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.JenisBarang;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rizky
 */
public class CJenisBarang {
    DBConnect connection = new DBConnect();
    public String getLastID(){
    
        try{
            connection.stat = connection.conn.createStatement();
            String query = "SELECT COUNT(jb_id) Total FROM JenisBarang";
            connection.result = connection.stat.executeQuery(query);
            int count = 0;
            while (connection.result.next()){
                count = Integer.parseInt(connection.result.getString("total"));
                
            
            }
            connection.stat.close();
            connection.result.close();
            count ++;
            
            
            if (Integer.toString(count).length() == 4){
                return "" + count;
            }
            else if (Integer.toString(count).length() == 3){
                return "0" + count;
            }
            else if (Integer.toString(count).length() == 2){
                return "00" + count;
            }
            else{
                return "000" + count;
            }
            
        }catch(Exception ex){
           System.out.println("Error GetLastID : "+ex);
           return "";
        }
    }
    
    public void simpanJenisBarang(JenisBarang jns){
        DBConnect connection = new DBConnect();
        try{
            String query = "INSERT INTO JenisBarang values (?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setInt(1, jns.getID_JenisBarang());
            connection.pstat.setString(2, jns.getNama_JenisBarang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
            
        }
        catch(Exception ex){
            System.out.println("Error di method simpan Jenis Barang" + ex.toString());
        }
    }
    
    public void ubahJenisBarang(JenisBarang jns){
        try {
            String query = "UPDATE JenisBarang SET jb_nama=? WHERE jb_id='" + jns.getID_JenisBarang()+"'";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, jns.getNama_JenisBarang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Error di method ubah Jenis Barang "+e.toString());
        }
    }
    
    public void hapusJenisBarang(JenisBarang jns){
        try{
                String query = "DELETE FROM JenisBarang WHERE jb_id='" + jns.getID_JenisBarang()+"'";
                connection.pstat = connection.conn.prepareStatement(query);

                connection.pstat.executeUpdate();
                connection.pstat.close();
            }
            catch(Exception e){
                System.out.println("Terjadi error pada saat hapus data jenis barang: " + e);
            }
    }
}
