/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.Barang;
import connection.DBConnect;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky
 */
public class CTableBarang {
    private DefaultTableModel model = new DefaultTableModel();
    CTableJenisBarang ControllerJenis = new CTableJenisBarang();
    CTable ControllerPemasok = new CTable();
    
    public CTableBarang(){
    }
    
    public DefaultTableModel addDataBarang(DefaultTableModel model){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM Barang";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                Object[] obj = new Object[11];
                obj[0] = connection.result.getString("b_id");
                obj[1] = ControllerJenis.getJenis(connection.result.getString("jb_id"));
                obj[2] = connection.result.getString("b_nama");
                obj[3] = connection.result.getString("b_bahan");
                obj[4] = connection.result.getString("b_ukuran");
                obj[5] = connection.result.getString("b_stok");
                obj[6] = connection.result.getString("b_harga_satuan");
                obj[7] = connection.result.getString("b_harga_kodian");
                obj[8] = connection.result.getString("b_harga_jual_satuan");
                obj[9] = connection.result.getString("b_harga_jual_kodian");
                obj[10] = ControllerPemasok.getPemasokNama(connection.result.getString("pms_id")) ;
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di CTable Barang!\n" + e.toString());
        }
        return model;
    }
    
    public DefaultTableModel addDataBarangRestock(DefaultTableModel model, String pemasok){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM Barang where pms_id = '"+ControllerPemasok.getPemasokId(pemasok)+"'";
            connection.result = connection.stat.executeQuery(query);
            int i = 1;
            while (connection.result.next()) {
                Object[] obj = new Object[6];
                obj[0] = i;
                
                obj[1] = connection.result.getString("b_nama");
                obj[2] = ControllerJenis.getJenis(connection.result.getString("jb_id"));
                obj[3] = connection.result.getString("b_ukuran");
                obj[4] = connection.result.getString("b_stok");
                obj[5] = ControllerPemasok.getPemasokNama(connection.result.getString("pms_id"));
                model.addRow(obj);
                i++;
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di CTable Barang!\n" + e.toString());
        }
        return model;
    }
    public Barang getSelectedRowBarang(JTable in){
        Barang B = new Barang();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        
        try{
            B.setID_Barang(Integer.parseInt(in.getValueAt(i, 0).toString()));
            B.setID_JenisBarang(Integer.parseInt(in.getValueAt(i, 1).toString()));
            B.setNama_Barang(in.getValueAt(i, 2).toString());
            B.setBahan_Barang(in.getValueAt(i, 3).toString());
            B.setUkuran_Barang(in.getValueAt(i, 4).toString());
            B.setStok_Barang(Integer.parseInt(in.getValueAt(i, 5).toString()));
            B.setHargaSatuan_Barang(Double.parseDouble(in.getValueAt(i, 6).toString()));
            B.setHargaKodian_Barang(Double.parseDouble(in.getValueAt(i, 7).toString()));
            B.setHargaJualSatuan_Barang(Double.parseDouble(in.getValueAt(i, 8).toString()));
            B.setHargaJualKodian_Barang(Double.parseDouble(in.getValueAt(i, 9).toString()));
            B.setPmsk_Barang(Integer.parseInt(in.getValueAt(i, 10).toString()));
            
        }catch(Exception ex){
            System.out.println("Error getSelectedRow Barang : "+ex.toString());
            return null;
        }
        return B;
    }
    
    public Barang getSelectedRowBarangPemasokkan(JTable in){
        Barang B = new Barang();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        System.out.println("Row Selekted "+i);
        try{
            B = getDatabyNamaUkuran(in.getValueAt(i, 1).toString(),in.getValueAt(i, 3).toString());
            
        }catch(Exception ex){
            System.out.println("Error getSelectedRowBarangPemasokkan Barang : "+ex.toString());
            return null;
        }
        return B;
    }
    public Barang getDatabyNamaUkuran(String nama, String ukuran){
        Barang B = new Barang();
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM Barang where b_nama = '"+nama+"' and b_ukuran ='"+ukuran+"'";
            System.out.println(query);
            connection.result = connection.stat.executeQuery(query);
            int i = 1;
            while (connection.result.next()) {
                
            B.setID_Barang(connection.result.getInt("b_id"));
            B.setID_JenisBarang(connection.result.getInt("jb_id"));
            B.setNama_Barang(connection.result.getString("b_nama"));
            B.setBahan_Barang(connection.result.getString("b_bahan"));
            B.setUkuran_Barang(connection.result.getString("b_ukuran"));
            B.setStok_Barang(connection.result.getInt("b_stok"));
            B.setHargaSatuan_Barang(connection.result.getDouble("b_harga_satuan"));
            B.setHargaKodian_Barang(connection.result.getDouble("b_harga_kodian"));
            B.setHargaJualSatuan_Barang(connection.result.getDouble("b_harga_jual_satuan"));
            B.setHargaJualKodian_Barang(connection.result.getDouble("b_harga_jual_kodian"));
            B.setPmsk_Barang(connection.result.getInt("pms_id"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di getDatabyNamaUkuran !\n" + e.toString());
        }
        return B;
    }
}
