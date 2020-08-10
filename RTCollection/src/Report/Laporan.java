/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import Controller.CLaporan;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import connection.DBConnect;
import java.awt.Component;
import static java.awt.PageAttributes.MediaType.D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Polman
 */
public class Laporan extends javax.swing.JFrame {

    /**
     * Creates new form Laporan
     */
    //==========================DEKLARASI VARIABLE LAPORAN =====================================
    private DefaultTableModel modelPemasokkanLaporan = new DefaultTableModel();
    private DefaultTableModel modelPengirimanLaporan = new DefaultTableModel();
    private DefaultTableModel modelTPelangganLaporan = new DefaultTableModel();
    private DefaultTableModel modelTPemasokLaporan = new DefaultTableModel();
    
    CLaporan ControllerLaporan = new CLaporan();
    
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
    private void addColumnLaporan(){
        modelPemasokkanLaporan.addColumn("No");
        modelPemasokkanLaporan.addColumn("Kode");
        modelPemasokkanLaporan.addColumn("Tanggal");
        modelPemasokkanLaporan.addColumn("Barang");
        modelPemasokkanLaporan.addColumn("Size");
        modelPemasokkanLaporan.addColumn("Pemasok");
        modelPemasokkanLaporan.addColumn("Kodi");
        modelPemasokkanLaporan.addColumn("Harga");
        modelPemasokkanLaporan.addColumn("T kodi");
        modelPemasokkanLaporan.addColumn("T harga");
        modelPemasokkanLaporan.addColumn("Status");
//        modelPemasokkanLaporan.addColumn("Pelayan");


//kode\ tanggal \ toko\ nama\ barang\ ukuran\ jumlah kodi\ harga\ total kodi\ total uang\ status
        modelPengirimanLaporan.addColumn("No");
        modelPengirimanLaporan.addColumn("Kode");
        modelPengirimanLaporan.addColumn("Tanggal");
        modelPengirimanLaporan.addColumn("Toko");
        modelPengirimanLaporan.addColumn("Nama");
        modelPengirimanLaporan.addColumn("Barang");
        modelPengirimanLaporan.addColumn("Size");
        modelPengirimanLaporan.addColumn("Kodi");
        modelPengirimanLaporan.addColumn("Harga");
        modelPengirimanLaporan.addColumn("T kodi");
        modelPengirimanLaporan.addColumn("T harga");
        modelPengirimanLaporan.addColumn("Status");
//        modelPengirimanLaporan.addColumn("Pelayan");
        
    // kode \ tanggal \ nama \ toko \ pasar \ pembayaran \ pelayan
        modelTPelangganLaporan.addColumn("No");
        modelTPelangganLaporan.addColumn("Kode");
        modelTPelangganLaporan.addColumn("Tanggal");
        modelTPelangganLaporan.addColumn("Nama");
        modelTPelangganLaporan.addColumn("Toko");
        modelTPelangganLaporan.addColumn("Pasar");
        modelTPelangganLaporan.addColumn("Pembayaran");
        modelTPelangganLaporan.addColumn("Penanggung Jawab");
        
        //kode \ tanggal \ nama \ alamat \ pembayaran \ pelayan
        modelTPemasokLaporan.addColumn("No");
        modelTPemasokLaporan.addColumn("Kode");
        modelTPemasokLaporan.addColumn("Tanggal");
        modelTPemasokLaporan.addColumn("Nama");
        modelTPemasokLaporan.addColumn("Alamat");
        modelTPemasokLaporan.addColumn("Pembayaran");
        modelTPemasokLaporan.addColumn("Penanggung Jawab");
        
    }
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    public void instantiasiLaporan(){
        dateSejakLaporan.setDate(new Date());
        dateSampaiLaporan.setDate(new Date());
        addColumnLaporan();
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    public Laporan() {
        initComponents();
        instantiasiLaporan();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLaporan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateSejakLaporan = new com.toedter.calendar.JDateChooser();
        dateSampaiLaporan = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btnPemasokkanLaporan = new javax.swing.JButton();
        btnPengirimanLaporan = new javax.swing.JButton();
        btnTPelangganLaporan = new javax.swing.JButton();
        btnTPemasokLaporan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelLaporan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tanggal Awal");

        dateSejakLaporan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateSejakLaporanPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tanggal Akhir");

        btnPemasokkanLaporan.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        btnPemasokkanLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PemasokanLap.png"))); // NOI18N
        btnPemasokkanLaporan.setText("Pemasokkan");
        btnPemasokkanLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemasokkanLaporan.setIconTextGap(50);
        btnPemasokkanLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasokkanLaporanActionPerformed(evt);
            }
        });

        btnPengirimanLaporan.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        btnPengirimanLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/PengirimanLap.png"))); // NOI18N
        btnPengirimanLaporan.setText("Pengiriman");
        btnPengirimanLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPengirimanLaporan.setIconTextGap(50);
        btnPengirimanLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengirimanLaporanActionPerformed(evt);
            }
        });

        btnTPelangganLaporan.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        btnTPelangganLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BayarPelangganLap.png"))); // NOI18N
        btnTPelangganLaporan.setText("Pembayaran Pelanggan");
        btnTPelangganLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTPelangganLaporan.setIconTextGap(50);
        btnTPelangganLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTPelangganLaporanActionPerformed(evt);
            }
        });

        btnTPemasokLaporan.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        btnTPemasokLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BayarPemasokLap.png"))); // NOI18N
        btnTPemasokLaporan.setText("Pembayaran Pemasok");
        btnTPemasokLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTPemasokLaporan.setIconTextGap(50);
        btnTPemasokLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTPemasokLaporanActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Report.png"))); // NOI18N
        jLabel4.setText("DETAIL LAPORAN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateSampaiLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateSejakLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTPemasokLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPengirimanLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPemasokkanLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTPelangganLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateSampaiLaporan, dateSejakLaporan});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateSejakLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(dateSampaiLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnPemasokkanLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPengirimanLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTPelangganLaporan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTPemasokLaporan)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateSampaiLaporan, dateSejakLaporan});

        javax.swing.GroupLayout panelLaporanLayout = new javax.swing.GroupLayout(panelLaporan);
        panelLaporan.setLayout(panelLaporanLayout);
        panelLaporanLayout.setHorizontalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLaporanLayout.createSequentialGroup()
                .addContainerGap(759, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLaporanLayout.setVerticalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPemasokkanLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemasokkanLaporanActionPerformed

            JasperReport jasperReport;
            JasperDesign jasperDesign;
            JasperPrint jasperPrint;
            Map <String, Object> parameter = new HashMap<String, Object>();
            parameter.put("tanggal1", dateSejakLaporan.getDate());
            parameter.put("tanggal2", dateSampaiLaporan.getDate());
            try {
                File file = new File("src/Report/LapBarangMasuk.jrxml");
                jasperDesign = JRXmlLoader.load(file);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_btnPemasokkanLaporanActionPerformed

    private void dateSejakLaporanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateSejakLaporanPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dateSejakLaporanPropertyChange

    private void btnPengirimanLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengirimanLaporanActionPerformed
        // TODO add your handling code here:
        JasperReport jasperReport;
            JasperDesign jasperDesign;
            JasperPrint jasperPrint;
            Map <String, Object> parameter = new HashMap<String, Object>();
            parameter.put("tanggal1", dateSejakLaporan.getDate());
            parameter.put("tanggal2", dateSampaiLaporan.getDate());
            try {
                File file = new File("src/Report/LapBarangKeluar.jrxml");
                jasperDesign = JRXmlLoader.load(file);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_btnPengirimanLaporanActionPerformed

    private void btnTPelangganLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTPelangganLaporanActionPerformed
        // TODO add your handling code here:
            JasperReport jasperReport;
            JasperDesign jasperDesign;
            JasperPrint jasperPrint;
            Map <String, Object> parameter = new HashMap<String, Object>();
            parameter.put("tanggal1", dateSejakLaporan.getDate());
            parameter.put("tanggal2", dateSampaiLaporan.getDate());
            try {
                File file = new File("src/Report/LapPembayaranPelanggan.jrxml");
                jasperDesign = JRXmlLoader.load(file);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_btnTPelangganLaporanActionPerformed

    private void btnTPemasokLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTPemasokLaporanActionPerformed
        // TODO add your handling code here:
            JasperReport jasperReport;
            JasperDesign jasperDesign;
            JasperPrint jasperPrint;
            Map <String, Object> parameter = new HashMap<String, Object>();
            parameter.put("tanggal1", dateSejakLaporan.getDate());
            parameter.put("tanggal2", dateSampaiLaporan.getDate());
            try {
                File file = new File("src/Report/LapPembayaranPemasok.jrxml");
                jasperDesign = JRXmlLoader.load(file);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, DBConnect.getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_btnTPemasokLaporanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPemasokkanLaporan;
    private javax.swing.JButton btnPengirimanLaporan;
    private javax.swing.JButton btnTPelangganLaporan;
    private javax.swing.JButton btnTPemasokLaporan;
    private com.toedter.calendar.JDateChooser dateSampaiLaporan;
    private com.toedter.calendar.JDateChooser dateSejakLaporan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelLaporan;
    // End of variables declaration//GEN-END:variables
}
