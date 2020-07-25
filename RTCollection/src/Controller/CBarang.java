/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Barang;
import connection.DBConnect;

/**
 *
 * @author rizky
 */
public class CBarang {
    DBConnect connection = new DBConnect();
    
    
    public String getLastID(){
    
        try{
            connection.stat = connection.conn.createStatement();
            String query = "SELECT COUNT(b_id) Total FROM Barang";
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
    public void simpanBarang(Barang brg){
        DBConnect connection = new DBConnect();
        try{
            String query = "INSERT INTO Barang values (?,?,?,?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setInt(1, brg.getID_Barang());
            connection.pstat.setInt(2, brg.getID_JenisBarang());
            connection.pstat.setString(3, brg.getNama_Barang());
            connection.pstat.setString(4, brg.getBahan_Barang());
            connection.pstat.setString(5, brg.getUkuran_Barang());
            connection.pstat.setInt(6, brg.getStok_Barang());
            connection.pstat.setDouble(7, brg.getHargaSatuan_Barang());
            connection.pstat.setDouble(8, brg.getHargaKodian_Barang());
            connection.pstat.setDouble(9, brg.getHargaJualSatuan_Barang());
            connection.pstat.setDouble(10, brg.getHargaJualKodian_Barang());
            connection.pstat.setDouble(11, brg.getPmsk_Barang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
            
        }
        catch(Exception ex){
            System.out.println("Error di method simpan Barang" + ex.toString());
        }
    }
    public void ubahBarang(Barang brg){
        try {
            String query = "UPDATE Barang SET jb_id=?, b_nama=?, b_bahan=?, b_ukuran=?, b_stok=?, b_harga_satuan=?, b_harga_kodian=?,"
                    + "b_harga_jual_satuan=?, b_harga_jual_kodian=? , pms_id=? WHERE b_id='" + brg.getID_Barang()+"'";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setInt(1, brg.getID_JenisBarang());
            connection.pstat.setString(2, brg.getNama_Barang());
            connection.pstat.setString(3, brg.getBahan_Barang());
            connection.pstat.setString(4, brg.getUkuran_Barang());
            connection.pstat.setInt(5, brg.getStok_Barang());
            connection.pstat.setDouble(6, brg.getHargaSatuan_Barang());
            connection.pstat.setDouble(7, brg.getHargaKodian_Barang());
            connection.pstat.setDouble(8, brg.getHargaJualSatuan_Barang());
            connection.pstat.setDouble(9, brg.getHargaJualKodian_Barang());
            connection.pstat.setDouble(10, brg.getPmsk_Barang());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Error di method ubah Barang "+e.toString());
        }
    }
    public void hapusBarang(Barang brg){
        try{
                String query = "DELETE FROM Barang WHERE b_id='" + brg.getID_Barang()+"'";
                connection.pstat = connection.conn.prepareStatement(query);

                connection.pstat.executeUpdate();
                connection.pstat.close();
            }
            catch(Exception e){
                System.out.println("Terjadi error pada saat hapus data barang: " + e);
            }
    }
    public void updateJumlahBarang(String jumlah, String nama, String ukuran){
        try {
            String query = "UPDATE Barang SET b_stok=b_stok + "+Integer.parseInt(jumlah)+" WHERE b_nama='" + nama+ "' and b_ukuran = '"+ukuran+"'";
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Error di method updateJumlahBarang "+e.toString()+"\nJumlah "+jumlah+"\nnama "+nama+"\nukuran "+ukuran);
        }
    }
    public void updateJumlahBarangDipesan(String jumlah, String nama, String ukuran){
        try {
            String query = "UPDATE Barang SET b_stok=b_stok - "+Integer.parseInt(jumlah)+" WHERE b_nama='" + nama+ "' and b_ukuran = '"+ukuran+"'";
            System.out.println(query);
            connection.pstat = connection.conn.prepareStatement(query);
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Error di method updateJumlahBarang "+e.toString()+"\nJumlah "+jumlah+"\nnama "+nama+"\nukuran "+ukuran);
        }
    }
}
