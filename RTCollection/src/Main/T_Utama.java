/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CRUD.CRUDBarang;
import CRUD.CRUDJenisBarang;
import CRUD.CRUDKaryawan;
import CRUD.CRUDPelanggan;
import CRUD.CRUDPemasok;
import Controller.CKaryawan;
import Controller.CPemasokkan;
import Controller.CTable;
import Controller.CTableBarang;
import RTClass.Barang;
import RTClass.DetailPemasokkan;
import RTClass.Karyawan;
import RTClass.Pemasokkan;
import connection.DBConnect;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Polman
 */
public class T_Utama extends javax.swing.JFrame {

    /**
     * Creates new form T_Utama
     */
    // ================================ Variable & Prosedur Umum ==============================================
    private void show(JPanel panel){
        panelDashboard.setVisible(false);
        panelPengolahanData.setVisible(false);
        panelPemasokkan.setVisible(false);
        
        panel.setVisible(true);
    }
    // ================================ Variable & Prosedur Pemasokkan ==============================================
    int TotalHarga = 0;
    int Harga = 0;
    int TotalBiaya = 0;
    public String kry_id = "";
    private DefaultTableModel modelBarang = new DefaultTableModel();
    private DefaultTableModel modelKeranjang = new DefaultTableModel();    
    CTableBarang ControllerBarang = new CTableBarang();
    CTable ControllerPemasok = new CTable();
    CPemasokkan ControllerPemasokkan = new CPemasokkan();
    Barang barang = new Barang();
    SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
    
    
    private void clear(){
        txtBarang.setText("");
        txtJumlah.setValue(1);
        txtPemasok.setText("");
        txtTotalHarga.setText("");
        txtUkuran.setText("");
        hitungKodi();
        hitungTotal();
    }
    private void hitungTotal(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjang.getRowCount();i++){
            total = total +(int) modelKeranjang.getValueAt(i, 5);
        }
        txtTotalBiaya.setText(kursIndonesia.format(total));
        TotalBiaya = total;
    }
    private void hitungKodi(){
        int total = 0;
        for(int i = 0 ; i < modelKeranjang.getRowCount();i++){
            total = total +(int) modelKeranjang.getValueAt(i, 4);
        }
        txtjumlah_kodi.setText(Integer.toString(total));
    }
    private void addDataBarang(String pemasok){
        
        modelBarang.getDataVector().removeAllElements();

        modelBarang.fireTableDataChanged();
        ControllerBarang.addDataBarangRestock(modelBarang,pemasok);
    }
    private void addColumn() {
        modelBarang.addColumn("No");
        modelBarang.addColumn("Barang");
        modelBarang.addColumn("Jenis");
        modelBarang.addColumn("Ukuran");
        modelBarang.addColumn("Stock");
        modelBarang.addColumn("Pemasok");
        
        modelKeranjang.addColumn("No");
        modelKeranjang.addColumn("Barang");
        modelKeranjang.addColumn("Ukuran");
        modelKeranjang.addColumn("Pemasok");
        modelKeranjang.addColumn("Jumlah");
        modelKeranjang.addColumn("Harga");
    }
    private void addComboBox(){
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "select * from Pemasok";
            connection.result = connection.stat.executeQuery(query);
            
            while (connection.result.next()) {
                
                cmbPemasok.addItem(connection.result.getString("pms_nama"));
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Gagal111 addDataPemasok!\n" + e.toString());
        }
    }
    // ==========================================================================================================
    
    
    Karyawan karyawan_login = new Karyawan();
    
    SimpleDateFormat settingTanggal = new SimpleDateFormat("dd-MM-yyyy");
    CKaryawan ControllerKaryawan = new CKaryawan();
    
    public T_Utama() {
        initComponents();
        //========== DEKLARASI TRANSAKSI PEMASOKKAN =======
        karyawan_login = ControllerKaryawan.getDatabyNama("Kiky Rahmawati Sitombingg");
        System.out.println(karyawan_login.getAllData());
        tableBarang.setModel(modelBarang);
        tableKeranjang.setModel(modelKeranjang);
        txtinfoTanggal.setText(formatTanggal.format(new Date()));
        
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        txtTanggalPemasokkan.setText(formatTanggal.format(new Date()));
        
        addColumn();
        addComboBox();
        
        // ================================================
        karyawan_info.setText(karyawan_login.getKry_nama());
        txtinfoTanggal.setText(settingTanggal.format(new Date()));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }
    public T_Utama(String pengguna, String posisi) {
        initComponents();
        //========== DEKLARASI TRANSAKSI PEMASOKKAN =======
        karyawan_login = ControllerKaryawan.getDatabyNama(pengguna);
        tableBarang.setModel(modelBarang);
        tableKeranjang.setModel(modelKeranjang);
        txtinfoTanggal.setText(formatTanggal.format(new Date()));
        
        
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        addColumn();
        addComboBox();
        
        // ================================================
        karyawan_info.setText(karyawan_login.getKry_nama());
        txtinfoTanggal.setText(settingTanggal.format(new Date()));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAtas = new javax.swing.JPanel();
        batas = new javax.swing.JLabel();
        txtinfoTanggal = new javax.swing.JLabel();
        panelPengolahanData = new javax.swing.JPanel();
        btnBarang = new javax.swing.JButton();
        btnJBarang = new javax.swing.JButton();
        btnPemasok = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        btnKaryawan = new javax.swing.JButton();
        panelKiri = new javax.swing.JPanel();
        karyawan_info = new javax.swing.JLabel();
        btnMenuUtama = new javax.swing.JButton();
        btnPemesananPelanggan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnPengolahanData = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnPemesananPemasok = new javax.swing.JButton();
        panelDashboard = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        kiribawahdashboard = new javax.swing.JLabel();
        panelPemasokkan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cmbPemasok = new javax.swing.JComboBox<>();
        panelDeskripsi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalPemasokkan = new javax.swing.JTextField();
        panelDetailBeli = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        txtPemasok = new javax.swing.JTextField();
        Ukuran = new javax.swing.JLabel();
        Ukuran1 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JSpinner();
        Ukuran2 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        btnKeranjang = new javax.swing.JButton();
        txtUkuran = new javax.swing.JTextField();
        panelKeranjang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKeranjang = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        Ukuran3 = new javax.swing.JLabel();
        txtjumlah_kodi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotalBiaya = new javax.swing.JLabel();
        btnProses = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);
        setExtendedState(1);
        setName("frameUtama"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1368, 708));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAtas.setBackground(new java.awt.Color(153, 153, 255));

        batas.setForeground(new java.awt.Color(0, 0, 0));
        batas.setText("Batas kanan atas");

        txtinfoTanggal.setForeground(new java.awt.Color(0, 0, 0));
        txtinfoTanggal.setText("jLabel2");

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(txtinfoTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 853, Short.MAX_VALUE)
                .addComponent(batas)
                .addGap(18, 18, 18))
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batas)
                    .addComponent(txtinfoTanggal))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(panelAtas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 30));

        panelPengolahanData.setBackground(new java.awt.Color(255, 255, 255));

        btnBarang.setText("Barang");
        btnBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnJBarang.setText("Jenis Barang");
        btnJBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnJBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJBarangActionPerformed(evt);
            }
        });

        btnPemasok.setText("Pemasok");
        btnPemasok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemasokActionPerformed(evt);
            }
        });

        btnPelanggan.setText("Pelanggan");
        btnPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });

        btnKaryawan.setText("Karyawan");
        btnKaryawan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPengolahanDataLayout = new javax.swing.GroupLayout(panelPengolahanData);
        panelPengolahanData.setLayout(panelPengolahanDataLayout);
        panelPengolahanDataLayout.setHorizontalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJBarang)
                    .addComponent(btnPemasok)
                    .addComponent(btnPelanggan)
                    .addComponent(btnKaryawan)
                    .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(926, Short.MAX_VALUE))
        );

        panelPengolahanDataLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBarang, btnJBarang, btnKaryawan, btnPelanggan, btnPemasok});

        panelPengolahanDataLayout.setVerticalGroup(
            panelPengolahanDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengolahanDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnJBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemasok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKaryawan)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        getContentPane().add(panelPengolahanData, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelKiri.setBackground(new java.awt.Color(204, 204, 204));
        panelKiri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKiriMouseClicked(evt);
            }
        });

        karyawan_info.setForeground(new java.awt.Color(0, 0, 0));
        karyawan_info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        karyawan_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/userTawal.png"))); // NOI18N
        karyawan_info.setText("INFO YANG LOGIN");
        karyawan_info.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMenuUtama.setText("Menu Utama");
        btnMenuUtama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuUtamaActionPerformed(evt);
            }
        });

        btnPemesananPelanggan.setText("Pemesanan Pelanggan");
        btnPemesananPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPelangganActionPerformed(evt);
            }
        });

        jButton3.setText("Pembayaran");

        btnPengolahanData.setText("PENGOLAHAN DATA");
        btnPengolahanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengolahanDataActionPerformed(evt);
            }
        });

        jButton5.setText("jButton5");

        btnKeluar.setText("KELUAR");

        jButton4.setText("Barang Keluar");

        jButton6.setText("Barang Masuk");

        btnPemesananPemasok.setText("Pemesanan Pemasok");
        btnPemesananPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemesananPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKiriLayout = new javax.swing.GroupLayout(panelKiri);
        panelKiri.setLayout(panelKiriLayout);
        panelKiriLayout.setHorizontalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPemesananPemasok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelKiriLayout.createSequentialGroup()
                        .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMenuUtama, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(btnPemesananPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPengolahanData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(karyawan_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelKiriLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMenuUtama, btnPemesananPelanggan, btnPengolahanData, jButton3});

        panelKiriLayout.setVerticalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(karyawan_info, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemesananPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPemesananPemasok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(40, 40, 40)
                .addComponent(btnPengolahanData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(81, 81, 81)
                .addComponent(btnKeluar)
                .addContainerGap())
        );

        panelKiriLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnKeluar, btnMenuUtama, btnPemesananPelanggan, btnPengolahanData, jButton3, jButton5});

        getContentPane().add(panelKiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 29, 240, 680));

        panelDashboard.setBackground(new java.awt.Color(255, 255, 255));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Logo.PNG"))); // NOI18N

        kiribawahdashboard.setForeground(new java.awt.Color(0, 0, 0));
        kiribawahdashboard.setText("Batas kiri bawah dashboard");

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kiribawahdashboard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDashboardLayout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(Logo)
                .addGap(430, 430, 430))
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(kiribawahdashboard)
                .addContainerGap())
        );

        getContentPane().add(panelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        panelPemasokkan.setBackground(new java.awt.Color(187, 187, 187));
        panelPemasokkan.setMaximumSize(new java.awt.Dimension(1100, 652));
        panelPemasokkan.setName(""); // NOI18N
        panelPemasokkan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBarang);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("TABLE PEMASOK");

        cmbPemasok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Pemasok -" }));
        cmbPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPemasokkan.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 640));

        panelDeskripsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("DESKRIPSI PEMASOKAN");

        jLabel2.setText("Tanggal Transaksi");

        txtTanggalPemasokkan.setEnabled(false);

        javax.swing.GroupLayout panelDeskripsiLayout = new javax.swing.GroupLayout(panelDeskripsi);
        panelDeskripsi.setLayout(panelDeskripsiLayout);
        panelDeskripsiLayout.setHorizontalGroup(
            panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggalPemasokkan, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        panelDeskripsiLayout.setVerticalGroup(
            panelDeskripsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeskripsiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTanggalPemasokkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panelPemasokkan.add(panelDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 590, 100));

        panelDetailBeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelDetailBeli.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("DESKRIPSI DETAIL PEMASOKKAN");

        jLabel4.setText("Barang");

        txtBarang.setEnabled(false);

        txtPemasok.setEnabled(false);
        txtPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPemasokActionPerformed(evt);
            }
        });

        Ukuran.setText("Pemasok");

        Ukuran1.setText("Jumlah Kodi");

        txtJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        txtJumlah.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtJumlahStateChanged(evt);
            }
        });
        txtJumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahMouseClicked(evt);
            }
        });
        txtJumlah.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtJumlahPropertyChange(evt);
            }
        });

        Ukuran2.setText("Total Harga");

        txtTotalHarga.setEnabled(false);
        txtTotalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHargaActionPerformed(evt);
            }
        });

        btnKeranjang.setText("Masukkan ke keranjang");
        btnKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeranjangActionPerformed(evt);
            }
        });

        txtUkuran.setEnabled(false);

        javax.swing.GroupLayout panelDetailBeliLayout = new javax.swing.GroupLayout(panelDetailBeli);
        panelDetailBeli.setLayout(panelDetailBeliLayout);
        panelDetailBeliLayout.setHorizontalGroup(
            panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPemasok)
                            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                                .addComponent(Ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ukuran2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailBeliLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKeranjang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDetailBeliLayout.setVerticalGroup(
            panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailBeliLayout.createSequentialGroup()
                .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKeranjang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(Ukuran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ukuran1)
                            .addComponent(Ukuran2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDetailBeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailBeliLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)))
                .addGap(12, 12, Short.MAX_VALUE))
        );

        panelPemasokkan.add(panelDetailBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 590, -1));

        panelKeranjang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKeranjangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableKeranjang);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("DAFTAR PEMASOKKAN");

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Ukuran3.setText("Total Harga");

        txtjumlah_kodi.setText("jTextField1");

        jLabel6.setText("Jumlah Kodi Barang");

        txtTotalBiaya.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTotalBiaya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalBiaya.setText("jLabel7");

        javax.swing.GroupLayout panelKeranjangLayout = new javax.swing.GroupLayout(panelKeranjang);
        panelKeranjang.setLayout(panelKeranjangLayout);
        panelKeranjangLayout.setHorizontalGroup(
            panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtjumlah_kodi))
                .addGap(190, 190, 190)
                .addComponent(Ukuran3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        panelKeranjangLayout.setVerticalGroup(
            panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKeranjangLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKeranjangLayout.createSequentialGroup()
                        .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Ukuran3))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKeranjangLayout.createSequentialGroup()
                        .addGroup(panelKeranjangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjumlah_kodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalBiaya))
                        .addContainerGap())))
        );

        panelPemasokkan.add(panelKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 590, 370));

        btnProses.setText("Proses Pemasokkan");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        panelPemasokkan.add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 620, -1, -1));

        getContentPane().add(panelPemasokkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 1110, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnPengolahanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengolahanDataActionPerformed
        show(panelPengolahanData);
        
    }//GEN-LAST:event_btnPengolahanDataActionPerformed

    private void panelKiriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKiriMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, this.getSize().toString());
    }//GEN-LAST:event_panelKiriMouseClicked

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
        barang = ControllerBarang.getSelectedRowBarangPemasokkan(tableBarang);

        txtBarang.setText(barang.getNama_Barang());
        txtUkuran.setText(barang.getUkuran_Barang());
        txtPemasok.setText(ControllerPemasok.getPemasokNama(String.valueOf(barang.getPmsk_Barang())));
        TotalHarga = (int) Math.round(barang.getHargaKodian_Barang());
        Harga = (int) Math.round(barang.getHargaKodian_Barang());
        txtTotalHarga.setText(kursIndonesia.format((int) Math.round(barang.getHargaKodian_Barang())));
    }//GEN-LAST:event_tableBarangMouseClicked

    private void cmbPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemasokActionPerformed
        // TODO add your handling code here:
        modelKeranjang.getDataVector().removeAllElements();

        modelKeranjang.fireTableDataChanged();
        clear();
        addDataBarang((String) cmbPemasok.getSelectedItem());
    }//GEN-LAST:event_cmbPemasokActionPerformed

    private void txtPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPemasokActionPerformed

    private void txtJumlahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtJumlahStateChanged
        // TODO add your handling code here:
        try{
            int jumlah = Integer.parseInt(txtJumlah.getValue().toString());
            TotalHarga = jumlah * Harga;
            txtTotalHarga.setText(kursIndonesia.format((TotalHarga)));

            //System.out.println(total);
        }catch(Exception ex){
            System.out.println("Error cuy txtJumlahPropertyChange "+ex);
        }
    }//GEN-LAST:event_txtJumlahStateChanged

    private void txtJumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtJumlahMouseClicked

    private void txtJumlahPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtJumlahPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_txtJumlahPropertyChange

    private void txtTotalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHargaActionPerformed

    private void btnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeranjangActionPerformed
        // TODO add your handling code here:
        if(txtBarang.getText().equals("")){
            return;
        }
        for(int i = 0 ; i < modelKeranjang.getRowCount() ; i ++){
            if(modelKeranjang.getValueAt(i, 1).toString().equals(txtBarang.getText()) && modelKeranjang.getValueAt(i, 2).toString().equals(txtUkuran.getText())){
                modelKeranjang.removeRow(i);
            }
        }
        Object[] rowData = new Object[]{modelKeranjang.getRowCount()+1,txtBarang.getText(),txtUkuran.getText(),txtPemasok.getText(),txtJumlah.getValue(),TotalHarga};
        modelKeranjang.addRow(rowData);
        clear();
        hitungTotal();
        hitungKodi();
    }//GEN-LAST:event_btnKeranjangActionPerformed

    private void tableKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKeranjangMouseClicked
        // TODO add your handling code here:
        barang = ControllerBarang.getSelectedRowBarangPemasokkan(tableBarang);

        txtBarang.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 1));
        txtUkuran.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 2));
        txtPemasok.setText((String) tableKeranjang.getValueAt(tableKeranjang.getSelectedRow(), 3));
        Harga = (int) Math.round(barang.getHargaKodian_Barang());
        txtTotalHarga.setText(kursIndonesia.format((int) Math.round(barang.getHargaKodian_Barang())));
    }//GEN-LAST:event_tableKeranjangMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        modelKeranjang.getDataVector().removeAllElements();

        modelKeranjang.fireTableDataChanged();
        clear();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");

        Pemasokkan pemasokkan = new Pemasokkan();
        pemasokkan.setPmsk_id(ControllerPemasokkan.getLastID());
        pemasokkan.setPms_id(ControllerPemasok.getPemasokId((String)cmbPemasok.getSelectedItem()));
        pemasokkan.setPmsk_jumlah_barang(Integer.valueOf(txtjumlah_kodi.getText())*20);
        pemasokkan.setPmsk_jumlah_kodi(Integer.valueOf(txtjumlah_kodi.getText()));
        pemasokkan.setPmsk_tgl_transaksi(new Date());
        pemasokkan.setPmsk_uang_dibayar(0);
        pemasokkan.setPmsk_total_uang(TotalBiaya);
        //pemasokkan.setKry_id("202007010002");
        pemasokkan.setKry_id(karyawan_login.getKry_id());
        pemasokkan.setStatus("Proses");

        System.out.println(pemasokkan.getAllData());
        ControllerPemasokkan.simpanPemasokkan(pemasokkan);
        DetailPemasokkan detailPemasok = new DetailPemasokkan();
        detailPemasok.setPmsk_id(pemasokkan.getPmsk_id());
        for(int i = 0 ; i < tableKeranjang.getRowCount();i++){
            barang = ControllerBarang.getDatabyNamaUkuran(modelKeranjang.getValueAt(i, 1).toString(), modelKeranjang.getValueAt(i, 2).toString());
            detailPemasok.setB_id(Integer.toString(barang.getID_Barang()));
            detailPemasok.setDetail_jumlah_kodi((int)modelKeranjang.getValueAt(i, 4));
            detailPemasok.setDetail_jumlah_barang(((int)modelKeranjang.getValueAt(i, 4)) * 20);
            detailPemasok.setDetail_harga((int)modelKeranjang.getValueAt(i, 5));
            ControllerPemasokkan.simpanDetailPemasokkan(detailPemasok);
        }
        JOptionPane.showMessageDialog(this, "Berhasil memporses....");
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnPemesananPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemesananPemasokActionPerformed
        // TODO add your handling code here:
        show(panelPemasokkan);
    }//GEN-LAST:event_btnPemesananPemasokActionPerformed

    private void btnPemesananPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemesananPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPemesananPelangganActionPerformed

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuUtamaActionPerformed
        // TODO add your handling code here:
        show(panelDashboard);
    }//GEN-LAST:event_btnMenuUtamaActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        // TODO add your handling code here:
        CRUDBarang karyawan = new CRUDBarang();
        karyawan.setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnJBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJBarangActionPerformed
        // TODO add your handling code here:
        CRUDJenisBarang barang = new CRUDJenisBarang();
        barang.setVisible(true);
    }//GEN-LAST:event_btnJBarangActionPerformed

    private void btnPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemasokActionPerformed
        // TODO add your handling code here:
        CRUDPemasok pemasok = new CRUDPemasok();
        pemasok.setVisible(true);
    }//GEN-LAST:event_btnPemasokActionPerformed

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
        // TODO add your handling code here:
        CRUDPelanggan pelanggan = new CRUDPelanggan();
        pelanggan.setVisible(true);
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void btnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaryawanActionPerformed
        // TODO add your handling code here:
        CRUDKaryawan karyawan = new CRUDKaryawan();
        karyawan.setVisible(true);
    }//GEN-LAST:event_btnKaryawanActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T_Utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Ukuran;
    private javax.swing.JLabel Ukuran1;
    private javax.swing.JLabel Ukuran2;
    private javax.swing.JLabel Ukuran3;
    private javax.swing.JLabel batas;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnJBarang;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnKeranjang;
    private javax.swing.JButton btnMenuUtama;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPemasok;
    private javax.swing.JButton btnPemesananPelanggan;
    private javax.swing.JButton btnPemesananPemasok;
    private javax.swing.JButton btnPengolahanData;
    private javax.swing.JButton btnProses;
    private javax.swing.JComboBox<String> cmbPemasok;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel karyawan_info;
    private javax.swing.JLabel kiribawahdashboard;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelDeskripsi;
    private javax.swing.JPanel panelDetailBeli;
    private javax.swing.JPanel panelKeranjang;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelPemasokkan;
    private javax.swing.JPanel panelPengolahanData;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tableKeranjang;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JSpinner txtJumlah;
    private javax.swing.JTextField txtPemasok;
    private javax.swing.JTextField txtTanggalPemasokkan;
    private javax.swing.JLabel txtTotalBiaya;
    private javax.swing.JTextField txtTotalHarga;
    private javax.swing.JTextField txtUkuran;
    private javax.swing.JLabel txtinfoTanggal;
    private javax.swing.JTextField txtjumlah_kodi;
    // End of variables declaration//GEN-END:variables
}
