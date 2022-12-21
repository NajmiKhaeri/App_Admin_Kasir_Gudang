/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appkasir;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ayu riyanti
 */
public class Fbarang extends javax.swing.JFrame {
    String ID = userlogin.getUserLogin();
    String uid = iduser.getIdUser();
    DefaultTableModel data;
    ResultSet rs;
    Statement st;
    Koneksi koneksi;
    Loginn Login;
    JasperReport jasrep;
    JasperDesign jasdes;
    JasperPrint jaspri;
    Map param = new HashMap();

    /**
     * Creates new form Fbarang 
     */
    public Fbarang() {
        initComponents();
        koneksi = new Koneksi();
        load_data();
        level();
        AutoID();
        pegawai.setText(ID);
        userid.setText(uid);
        setLocationRelativeTo(this);
    }
    private void load_data(){
    Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
    data = new DefaultTableModel(null,header);
//    String src = Search.getText();
//    String sql1 = "SELECT * FROM tbl_barang WHERE kategori='Makanan'";
//    String sql2 = "SELECT * FROM tbl_barang WHERE kategori='Minuman'";
//    String sql3 = "SELECT * FROM tbl_barang WHERE kategori='Pakaian'";
//    String sql4 = "SELECT * FROM tbl_barang WHERE kategori='Alat Tulis'";
//    String sql5 = "(kd_barang LIKE '%" + src + "%' OR nama_barang LIKE '%" + src + "%')";
//    String where = " WHERE ";
//    String and = " AND ";
//    String sql = "SELECT tbl_barang.kd_barang, tbl_barang.nama_barang, tbl_barang.harga_jual, "
//            + "tbl_barang.harga_beli, tbl_barang.stok, tbl_barang.nama_user FROM tbl_barang";
    
    
//    
    String sql = "SELECT * from tbl_barang";
    
    try{
        if (tablebox.getSelectedItem().equals("- PILIH -")) {
        st = koneksi.con.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {            
            String k1 = rs.getString(1);
            String k2 = rs.getString(2);
            String k3 = rs.getString(3);
            String k4 = rs.getString(4);
            String k5 = rs.getString(5);
            String k6 = rs.getString(6);
            String k7 = rs.getString(7);
            String k[]={k1,k2,k3,k4,k5,k6,k7};
            data.addRow(k);
        }
        gtable.setModel(data);   
        }        
    }
    catch(Exception e){
       JOptionPane.showMessageDialog(null, e);
    }
    
        try {
            String sql1 = "SELECT * FROM tbl_barang WHERE kategori='Makanan'";
            String sql2 = "SELECT * FROM tbl_barang WHERE kategori='Minuman'";
            String sql3 = "SELECT * FROM tbl_barang WHERE kategori='Pakaian'";
            String sql4 = "SELECT * FROM tbl_barang WHERE kategori='Alat Tulis'";
            if (tablebox.getSelectedItem().equals("Makanan")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql1);
                while (rs.next()) {
                    String k1 = rs.getString(1);
                    String k2 = rs.getString(2);
                    String k3 = rs.getString(3);
                    String k4 = rs.getString(4);
                    String k5 = rs.getString(5);
                    String k6 = rs.getString(6);
                    String k7 = rs.getString(7);
                    String k[]={k1,k2,k3,k4,k5,k6,k7};
                    data.addRow(k);
                }
                gtable.setModel(data);
            }
            if (tablebox.getSelectedItem().equals("Minuman")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql2);
                while (rs.next()) {
                    String k1 = rs.getString(1);
                    String k2 = rs.getString(2);
                    String k3 = rs.getString(3);
                    String k4 = rs.getString(4);
                    String k5 = rs.getString(5);
                    String k6 = rs.getString(6);
                    String k7 = rs.getString(7);
                    String k[]={k1,k2,k3,k4,k5,k6,k7};
                    data.addRow(k);
                }
                gtable.setModel(data);
            }
                if (tablebox.getSelectedItem().equals("Pakaian")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql3);
                while (rs.next()) {
                    String k1 = rs.getString(1);
                    String k2 = rs.getString(2);
                    String k3 = rs.getString(3);
                    String k4 = rs.getString(4);
                    String k5 = rs.getString(5);
                    String k6 = rs.getString(6);
                    String k7 = rs.getString(7);
                    String k[]={k1,k2,k3,k4,k5,k6,k7};
                    data.addRow(k);
                }
                gtable.setModel(data);
            }
                if (tablebox.getSelectedItem().equals("Alat Tulis")) {
                st = koneksi.con.createStatement();
                rs = st.executeQuery(sql4);
                while (rs.next()) {
                    String k1 = rs.getString(1);
                    String k2 = rs.getString(2);
                    String k3 = rs.getString(3);
                    String k4 = rs.getString(4);
                    String k5 = rs.getString(5);
                    String k6 = rs.getString(6);
                    String k7 = rs.getString(7);
                    String k[]={k1,k2,k3,k4,k5,k6,k7};
                    data.addRow(k);
                }
                gtable.setModel(data);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    
        public void level(){
        try {
            st=koneksi.con.createStatement();
            String query = "SELECT * FROM tbl_kategori";
            rs=st.executeQuery(query);
            while (rs.next()) {                
               kbox.addItem(rs.getString("kategori"));
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void AutoID(){
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%MK%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Makanan")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("MK"+NOL+NO);
                }
            }
            else{
                if (kbox.getSelectedItem().equals("Makanan")) {
                kdbarang.setText("MK0001");    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            st = koneksi.con.createStatement();
                String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%MI%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Minuman")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("MI"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Minuman")) {
                    kdbarang.setText("MI0001");
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%PK%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Pakaian")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("PK"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Pakaian")) {
                    kdbarang.setText("PK0001");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
            try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%AT%' ORDER BY kd_barang DESC";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String ID = rs.getString("kd_barang").substring(4);
                if (kbox.getSelectedItem().equals("Alat Tulis")) {
                String NO = ""+(Integer.parseInt(ID)+1);
                String NOL = "000";
                kdbarang.setText("AT"+NOL+NO);
                }
            }else{
                if (kbox.getSelectedItem().equals("Alat Tulis")) {
                    kdbarang.setText("AT0001");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    public void inputdata(){
        try {
            st = koneksi.con.createStatement();
            String sql = "INSERT INTO tbl_barang values('"+kdbarang.getText()+"', '"+namabarang.getText()+"', '"+kbox.getSelectedItem()+"', '"+hargaj.getText()+"', '"+hargab.getText()+"', '"+stok.getText()+"', '"+pegawai.getText()+"', '"+userid.getText()+"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "data berhasil dimasukan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    
    public void bersih(){
        kdbarang.setText("");
        kbox.setSelectedItem("-PILIH-");
        namabarang.setText("");
        hargaj.setText("");
        hargab.setText("");
        stok.setText("");
    }
    
    private void edit(){
        try {
            koneksi.con.createStatement().executeUpdate("UPDATE tbl_barang set nama_barang='"+namabarang.getText()+"',"
                    + "harga_jual='"+hargaj.getText()+"',harga_beli='"+hargab.getText()+"',stok='"+stok.getText()+"' " 
                    + "WHERE kd_barang='"+kdbarang.getText()+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pencarian(){
        if (combocari.getSelectedItem().equals("Nama_Barang")) {
        Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
        data = new DefaultTableModel(null,header);
        String sql5 = "SELECT * FROM tbl_barang WHERE nama_barang LIKE '%"+Search.getText()+"%';";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql5);
            while (rs.next()) {                
            String k1 = rs.getString(1);
            String k2 = rs.getString(2);
            String k3 = rs.getString(3);
            String k4 = rs.getString(4);
            String k5 = rs.getString(5);
            String k6 = rs.getString(6);
            String k7 = rs.getString(7);
            String k[]={k1,k2,k3,k4,k5,k6,k7};
            data.addRow(k);
            }
            gtable.setModel(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        }
        if (combocari.getSelectedItem().equals("ID_Barang")) {
       Object header[]={"ID_BARANG","NAMA","KATEGORI","HARGA_JUAL","HARGA_BELI","STOK","PEGAWAI"};
        data = new DefaultTableModel(null,header);
        String sql5 = "SELECT * FROM tbl_barang WHERE kd_barang LIKE '%"+Search.getText()+"%';";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql5);
            while (rs.next()) {                
            String k1 = rs.getString(1);
            String k2 = rs.getString(2);
            String k3 = rs.getString(3);
            String k4 = rs.getString(4);
            String k5 = rs.getString(5);
            String k6 = rs.getString(6);
            String k7 = rs.getString(7);
            String k[]={k1,k2,k3,k4,k5,k6,k7};
            data.addRow(k);
            }
            gtable.setModel(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }

    }
    
    public void pilih_laporan(){
        try {
         if (tablebox.getSelectedItem().equals("All")) {
           try {
            File file = new File("src\\appkasir\\LaporanGudang.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
//            
         if (tablebox.getSelectedItem().equals("Makanan")) {
           try {
            File file = new File("src\\appkasir\\LaporanGudangMakanan.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
            
        if (tablebox.getSelectedItem().equals("Minuman")) {
           try {
            File file = new File("src\\appkasir\\LaporanGudangMinuman.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
        
        if (tablebox.getSelectedItem().equals("Pakaian")) {
           try {
            File file = new File("src\\appkasir\\LaporanGudangPakaian.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
//        
        if (tablebox.getSelectedItem().equals("Alat Tulis")) {
           try {
            File file = new File("src\\appkasir\\LaporanGudangATK.jrxml");
            jasdes = JRXmlLoader.load(file);
            param.clear();
            jasrep = JasperCompileManager.compileReport(jasdes);
            jaspri = JasperFillManager.fillReport(jasrep, param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e) {
            e.printStackTrace();
           }
       }
//        
//        
//            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        kdbarang = new javax.swing.JTextField();
        kbox = new javax.swing.JComboBox<>();
        namabarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hargaj = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hargab = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        stok = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gtable = new javax.swing.JTable();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        combocari = new javax.swing.JComboBox<>();
        Search = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tablebox = new javax.swing.JComboBox<>();
        cetak = new javax.swing.JButton();
        chartna = new javax.swing.JButton();
        userid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pegawai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 51));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLIKASI GUDANG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel8.setText("Kode Barang");

        jLabel9.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel9.setText("Kategori");

        kdbarang.setEditable(false);
        kdbarang.setBackground(new java.awt.Color(204, 255, 204));
        kdbarang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        kbox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));
        kbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kboxItemStateChanged(evt);
            }
        });
        kbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kboxActionPerformed(evt);
            }
        });

        namabarang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        namabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabarangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel3.setText("Harga Jual");

        hargaj.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel4.setText("Harga Beli");

        hargab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel5.setText("Stok Barang");

        stok.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        gtable.setBackground(new java.awt.Color(255, 255, 153));
        gtable.setModel(new javax.swing.table.DefaultTableModel(
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
        gtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gtable);

        simpan.setBackground(new java.awt.Color(0, 255, 255));
        simpan.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(204, 255, 204));
        edit.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(255, 153, 153));
        hapus.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 51, 0));
        jButton4.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jButton4.setText("LOGOUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 19)); // NOI18N
        jLabel6.setText("Pencarian :");

        combocari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -", "ID_Barang", "Nama_Barang" }));
        combocari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocariActionPerformed(evt);
            }
        });

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        jLabel7.setText("Laporan");

        tablebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -", "All", "Makanan", "Minuman", "Pakaian", "Alat Tulis" }));

        cetak.setBackground(new java.awt.Color(204, 204, 255));
        cetak.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        chartna.setBackground(new java.awt.Color(255, 204, 153));
        chartna.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        chartna.setText("Chart");
        chartna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartnaActionPerformed(evt);
            }
        });

        userid.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jLabel10.setText("ID User");

        jLabel11.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jLabel11.setText("Pegawai");

        pegawai.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(namabarang)
                                    .addComponent(kbox, 0, 141, Short.MAX_VALUE)
                                    .addComponent(hargaj)
                                    .addComponent(hargab)
                                    .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kdbarang))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(cetak)
                                        .addGap(18, 18, 18)
                                        .addComponent(chartna))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(combocari, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(Search)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tablebox, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edit)
                                .addGap(18, 18, 18)
                                .addComponent(hapus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pegawai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(combocari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tablebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chartna)
                            .addComponent(cetak)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(kdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hargaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hargab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(edit)
                    .addComponent(hapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        if (namabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Barang Tidak Boleh Kosong");
        }
        if (hargaj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Harga Jual tidak boleh kosong");
        }
        if (hargab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Harga Beli Tidak Boleh Kosong");
        }
        if (stok.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Stok Tidak Boleh Kosong");
        }else{
            int pilih = JOptionPane.showConfirmDialog(null,"Apakah Data Sudah Benar?"+"Simpan?","Simpan Data",JOptionPane.YES_NO_OPTION);
            if (pilih == JOptionPane.YES_OPTION) {
                inputdata();
                load_data();
                bersih();
                AutoID();
            }
            else if(pilih == JOptionPane.NO_OPTION){
                bersih();
            }
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
          int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin merubah?","ubah data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
      try {
          edit();
          JOptionPane.showMessageDialog(null, "Ubah Data Berhasil");
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Gagal");
      }
        }
        else{
            bersih();
        }
        load_data();
        bersih();
        AutoID();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
     int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin menghapus?","hapus data",JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
      try {
          koneksi.con.createStatement().executeUpdate("DELETE FROM tbl_barang WHERE kd_barang='"+kdbarang.getText()+"'");
          JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
      } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Gagal");
      }
        }
        else{
            System.exit(0);
        }
        load_data();
        bersih();
        AutoID();
    }//GEN-LAST:event_hapusActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Loginn a = new Loginn();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
     pilih_laporan();
    }//GEN-LAST:event_cetakActionPerformed

    private void chartnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartnaActionPerformed
    String sql = "SELECT tbl_barang.kd_barang, tbl_barang.nama_barang, tbl_barang.kategori, tbl_barang.harga_jual, tbl_barang.harga_beli, tbl_barang.stok, tbl_barang.NAMA_USER FROM tbl_barang;";
        try {
            st = koneksi.con.createStatement();
            rs = st.executeQuery(sql);
            DefaultCategoryDataset objaa = new DefaultCategoryDataset();
            while (rs.next()) {                
                objaa.setValue(Integer.parseInt(rs.getString("stok")), rs.getString("kategori"), rs.getString("nama_barang"));
            }
            JFreeChart chart = ChartFactory.createBarChart3D("Data Barang", null, null, objaa);
CategoryPlot obja = chart.getCategoryPlot();
obja.setRangeGridlinePaint(Color.BLACK);
ChartFrame frame = new ChartFrame("Grafik Barang", chart);
frame.setSize(400,400);
frame.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_chartnaActionPerformed

    private void namabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namabarangActionPerformed

    private void gtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gtableMouseClicked
 int cok = gtable.getSelectedRow();
 String a = gtable.getValueAt(cok, 0).toString();
 String b = gtable.getValueAt(cok, 1).toString();
 String c = gtable.getValueAt(cok, 2).toString();
 String d = gtable.getValueAt(cok, 3).toString();
 String e = gtable.getValueAt(cok, 4).toString();
 String f = gtable.getValueAt(cok, 5).toString();
 String g = gtable.getValueAt(cok, 6).toString();
 kdbarang.setText(a);
 kbox.setSelectedItem(c);
 namabarang.setText(b);
 hargaj.setText(d);
 hargab.setText(e);
 stok.setText(f);
//kdbarang.setText(data.getValueAt(gtable.getSelectedRow(),0).toString());
//kbox.setSelectedItem(data.getValueAt(gtable.getSelectedRow(),1).toString());
//namabarang.setText(data.getValueAt(gtable.getSelectedRow(),2).toString());
//hargaj.setText(data.getValueAt(gtable.getSelectedRow(),3).toString());
//hargab.setText(data.getValueAt(gtable.getSelectedRow(),4).toString());
//stok.setText(data.getValueAt(gtable.getSelectedRow(),5).toString());
//userid.setText(data.getValueAt(gtable.getSelectedRow(),6).toString());
    }//GEN-LAST:event_gtableMouseClicked

    private void kboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kboxItemStateChanged
        AutoID();
    }//GEN-LAST:event_kboxItemStateChanged

    private void kboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kboxActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_SearchKeyReleased

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void combocariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocariActionPerformed
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_combocariActionPerformed

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
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fbarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Search;
    private javax.swing.JButton cetak;
    private javax.swing.JButton chartna;
    private javax.swing.JComboBox<String> combocari;
    private javax.swing.JButton edit;
    private javax.swing.JTable gtable;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField hargab;
    private javax.swing.JTextField hargaj;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kbox;
    private javax.swing.JTextField kdbarang;
    private javax.swing.JTextField namabarang;
    private javax.swing.JTextField pegawai;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stok;
    private javax.swing.JComboBox<String> tablebox;
    private javax.swing.JTextField userid;
    // End of variables declaration//GEN-END:variables
}
