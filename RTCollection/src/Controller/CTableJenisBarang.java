/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RTClass.JenisBarang;
import connection.DBConnect;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky
 */
public class CTableJenisBarang {
    private DefaultTableModel model = new DefaultTableModel();
    
    public CTableJenisBarang(){
    }
    
    public DefaultTableModel addDataJenisBarang(DefaultTableModel model){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM JenisBarang";
            connection.result = connection.stat.executeQuery(query);

            while (connection.result.next()) {
                Object[] obj = new Object[2];
                obj[0] = connection.result.getString("jb_id");
                obj[1] = connection.result.getString("jb_nama");
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di CTable JenisBarang!\n" + e.toString());
        }
        return model;
    }
    public String getJenis(String id){
        String output = "";
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM JenisBarang where jb_id = '"+id+"'";
            connection.result = connection.stat.executeQuery(query);
            
            while (connection.result.next()) {
                
                
                output = connection.result.getString("jb_nama");
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Error di CTable JenisBarang!\n" + e.toString());
        }
        return output;
    }
    public JenisBarang getSelectedRowJenisBarang(JTable in){
        JenisBarang JB = new JenisBarang();
        if(in.getSelectedRow() == -1){
            return null;
        }
        int i = in.getSelectedRow();
        
        try{
            JB.setID_JenisBarang(Integer.parseInt(in.getValueAt(i, 0).toString()));
            JB.setNama_JenisBarang(in.getValueAt(i, 1).toString());
        }catch(Exception ex){
            System.out.println("Error getSelectedRow Jenis Barang : "+ex.toString());
            return null;
        }
        return JB;
    }
    
}
