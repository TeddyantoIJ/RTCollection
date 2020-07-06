/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import RTClass.Karyawan;
import connection.DBConnect;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Polman
 */
public class CKaryawan {
    
    public void simpanKaryawan(Karyawan kar){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(kar.getKry_tgl_lahir());
        DBConnect connection = new DBConnect();
        try {
            String query = "insert into Karyawan values (?,?,?,?,?,?,?,?,?,?)";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, kar.getKry_id());
            connection.pstat.setString(2, kar.getKry_nama());
            connection.pstat.setString(3, kar.getKry_no_hp());
            connection.pstat.setString(4, kar.getKry_email());
            connection.pstat.setString(5, tanggal);
            connection.pstat.setString(6, kar.getAlamat());
            connection.pstat.setString(7, kar.getKry_total_transaksi());
            connection.pstat.setString(8, kar.getKry_username());
            connection.pstat.setString(9, kar.getKry_password());
            connection.pstat.setString(10, kar.getKry_jabatan());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal simpanKaryawan "+e.toString());
        }
    }
    public void updateKaryawan(Karyawan kar){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String tanggal = formatter.format(kar.getKry_tgl_lahir());
        DBConnect connection = new DBConnect();
        try {
            String query = "update karyawan"
                    + " set kry_nama = ?, kry_no_hp = ?, kry_email = ?, kry_tgl_lahir = ?, "
                    + "alamat = ?, kry_username = ?, kry_password = ?, kry_jabatan = ? "
                    + "where kry_id ='"+kar.getKry_id()+"'";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1, kar.getKry_nama());
            connection.pstat.setString(2, kar.getKry_no_hp());
            connection.pstat.setString(3, kar.getKry_email());
            connection.pstat.setString(4, tanggal);
            connection.pstat.setString(5, kar.getAlamat());
            connection.pstat.setString(6, kar.getKry_username());
            connection.pstat.setString(7, kar.getKry_password());
            connection.pstat.setString(8, kar.getKry_jabatan());
            
            connection.pstat.executeUpdate();
            connection.pstat.close();
        } catch (Exception e) {
            System.out.println("Gagal updateKaryawan "+e.toString());
        }
    }
    public String getLastID(){
        
        DBConnect connection = new DBConnect();
        try{
            connection.stat = connection.conn.createStatement();
                String query = "select count(kry_id) Total from Karyawan";
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
    public Karyawan getDatabyNama(String nama){
        Karyawan B = new Karyawan();
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM Karyawan where kry_nama = '"+nama+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i = 1;
            while (connection.result.next()) {
                
            B.setKry_id(connection.result.getString("kry_id"));
            B.setKry_nama(connection.result.getString("kry_nama"));
            B.setAlamat(connection.result.getString("alamat"));
            B.setKry_email(connection.result.getString("kry_email"));
            B.setKry_jabatan(connection.result.getString("kry_jabatan"));
            B.setKry_no_hp(connection.result.getString("kry_no_hp"));
            B.setKry_password(connection.result.getString("kry_password"));
            B.setKry_tgl_lahir(connection.result.getDate("kry_tgl_lahir"));
            B.setKry_total_transaksi(connection.result.getString("kry_total_transaksi"));
            B.setKry_username(connection.result.getString("kry_username"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di getDatabyNamaUkuran !\n" + e.toString());
        }
        return B;
    }
}
