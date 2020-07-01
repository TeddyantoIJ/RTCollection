/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTClass;

/**
 *
 * @author rizky
 */
public class JenisBarang {
    int jb_id;
    String jb_nama;
    
    public int getID_JenisBarang(){
        return jb_id;
    }
    
    public void setID_JenisBarang(int id){
        this.jb_id = id;
    }
    
    public String getNama_JenisBarang(){
        return jb_nama;
    }
    
    public void setNama_JenisBarang(String nama){
        this.jb_nama = nama;
    }
    
    public JenisBarang getAllData(){
        JenisBarang jb = new JenisBarang();
        return jb;
    }
}
