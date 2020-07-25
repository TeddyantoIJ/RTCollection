/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Karung;
import connection.DBConnect;

/**
 *
 * @author Polman
 */
public class CKarung {
    public void simpanKarung(Karung krg){
        
        
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Karung values (?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, krg.getKrg_id());
            connection.pstat.setString(2, krg.getPgrm_id());
            connection.pstat.setInt(3, krg.getKrg_jumlah_kodi());
            connection.pstat.setInt(4, krg.getKrg_jumlah_jenis_barang());
            connection.pstat.setString(5, krg.getKeterangan());
            
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpanKarung "+e.toString());
        }
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(krg_id) Total from Karung";
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
