/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appkasir;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JFileChooser;
//import.javax.swing.filechooser.FileFilter;

/**
 *
 * @author USER
 */
public class AppUser extends javax.swing.JFrame {
DefaultTableModel data;
    /**
     * Creates new form AppUser
     */
     Statement st;
     ResultSet rs;
     Koneksi koneksi;
     JasperReport jasrep;
     JasperPrint jaspri;
     JasperDesign jasdep;
     Map Param = new HashMap();
     
    public AppUser() {
        koneksi = new Koneksi();
        initComponents();
        load_data();
        IDotomatis();
        level();
        setLocationRelativeTo(this);
    }
        private String validatePath(String invalidPath){
        String validPath;
        validPath = invalidPath.replace('\\','/');
        return validPath;
        }
        
        private void load_data(){
        Object header[]={"ID USER","LEVEL","NAMA USER","JENIS KELAMIN","TANGGAL LAHIR","NO HANDPHONE","USERNAME","PASSWORD"};
        data = new DefaultTableModel(null,header);
        
        String sql = "SELECT tbl_user.ID_USER, tbl_level.LEVEL, tbl_user.NAMA_USER, tbl_user.JK,"
                + "tbl_user.TANGGAL_LAHIR, tbl_user.NOPE, tbl_user.USERNAME,"+"tbl_user.PASSWORD "
                + "FROM tbl_user INNER JOIN tbl_level ON tbl_user.ID_LEVEL=tbl_level.ID_LEVEL;";
        
        try {
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
                String k8 = rs.getString(8);
                String k[]={k1,k2,k3,k4,k5,k6,k7,k8};
                data.addRow(k);
            }
            TUser.setModel(data);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
        public void level(){
        try {
            st = koneksi.con.createStatement();
            String query = "SELECT * FROM tbl_level";
            rs = st.executeQuery(query);
            while(rs.next()){
                clevel.addItem(rs.getString("LEVEL"));
            }
        }catch (SQLException e){
        }
        }
        public void bersih(){
            iduser.setText("");
            nama.setText("");
            nope.setText("");
            us.setText("");
            pw.setText("");
            jDateChooser1.setDate(null);
            clevel.setSelectedItem("PILIH");
            jkl.setSelected(false);
            jkp.setSelected(false);
        }
        
        private void edit(){
            
        try {
            String jk="";
            if(jkl.isSelected()){
                jk=jkl.getText();
            }else{
                jk=jkp.getText();
            }
            
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dformat.format(jDateChooser1.getDate());
            
            st = koneksi.con.createStatement();
            String id="";
            String ab = "SELECT ID_LEVEL FROM tbl_level WHERE tbl_level='"+clevel.getSelectedItem()+"'";
            rs = st.executeQuery(ab);
            if (rs.next()) {
                id = rs.getString("ID_LEVEL");
            }
            koneksi.con.createStatement().executeUpdate("UPDATE tbl_user SET ID_LEVEL='"+id+"',"
            + "NAMA_USER='"+nama.getText()+"',JK='"+jk+"',"
            + "TANGGAL_LAHIR='"+date+"',NOPE='"+nope.getText()+"',USERNAME='"+us.getText()+"',"
            + "PASSWORD='"+pw.getText()+"' WHERE ID_USER='"+iduser.getText()+"'");
        }catch (Exception e) {
        }
    }
        
        public void input_data(){
            try{
                String jk="";
                if(jkl.isSelected()){
                    jk=jkl.getText();
                }else{
                    jk=jkp.getText();
                }
                
                SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dformat.format(jDateChooser1.getDate());
                
                st = koneksi.con.createStatement();
                String id = "";
                String query = "SELECT ID_LEVEL FROM tbl_level WHERE LEVEL='"+clevel.getSelectedItem()+"'";
                rs = st.executeQuery(query);
                if(rs.next()){
                    id = rs.getString("ID_LEVEL");
                }
                
                String sql = "INSERT INTO tbl_user values('"+iduser.getText()
                        +"','"+id
                        +"','"+nama.getText()
                        +"','"+jk
                        +"','"+date
                        +"','"+nope.getText()
                        +"','"+us.getText()
                        +"','"+pw.getText()
                        +"')";
                st.execute(sql);
                JOptionPane.showMessageDialog(null, "Data Barang Berhasil Dimasukkan");
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        private void IDotomatis(){
        try {
            st = koneksi.con.createStatement();
            String sql = "SELECT * FROM tbl_user order by ID_USER desc";
            rs = st.executeQuery(sql);
            if(rs.next()){
                    String ID = rs.getString("ID_USER").substring(4);
                    String NO = ""+(Integer.parseInt(ID)+1);
                    String Nol="";
                    if (NO.length()==1){
                        Nol="000";
                    }
                    else if(NO.length()==2){
                        Nol="00";
                    }
                    iduser.setText("USER"+Nol+NO);
                }
            else{
                iduser.setText("USER0001");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
        
//        private void edit(){
//        try {
//            String jk="";
//            if(jkl.isSelected()){
//                jk=jkl.getText();
//            }else{
//                jk=jkp.getText();
//            }
//            
//            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
//            String date = dformat.format(jDateChooser1.getDate());
//            
//            st = koneksi.con.createStatement();
//            String id="";
//            String ab = "SELECT ID_LEVEL FROM tbl_level WHERE tbl_level='"+clevel.getSelectedItem()+"'";
//            rs = st.executeQuery(ab);
//            if (rs.next()) {
//                id = rs.getString("ID_LEVEL");
//            }
//            koneksi.con.createStatement().executeUpdate("UPDATE tbl_user SET ID_LEVEL='"+id+"',"
//            + "NAMA_USER='"+nama.getText()+"',JK='"+jk+"',"
//            + "TANGGAL_LAHIR='"+date+"',NOPE='"+nope.getText()+"',USERNAME='"+us.getText()+"',"
//            + "PASSWORD='"+pw.getText()+"' WHERE ID_USER='"+iduser.getText()+"'");
//        }catch (Exception e) {
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this codeq. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iduser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        clevel = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jkp = new javax.swing.JRadioButton();
        jkl = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        nope = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        us = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TUser = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        pw = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel1.setText("DATA USER APLIKASI KASIR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel2.setText("ID USER");

        iduser.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iduserActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("FORM INPUT DATA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel4.setText("LEVEL");

        clevel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel5.setText("NAMA USER");

        nama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel6.setText("TANGGAL LAHIR");

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel7.setText("JENIS KELAMIN");

        jkp.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jkp.setText("P");
        jkp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkpActionPerformed(evt);
            }
        });

        jkl.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jkl.setText("L");
        jkl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jklActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel8.setText("NO HP");

        nope.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        nope.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nopeKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel9.setText("USERNAME");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel10.setText("PASSWORD");

        us.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });

        TUser.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        TUser.setModel(new javax.swing.table.DefaultTableModel(
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
        TUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TUser);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel11.setText("DATA USER");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("IMPORT ...");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jButton1.setText("BROWSE");

        jButton2.setText("IMPORT");

        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        jButton4.setText("REPORT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        pw.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(337, 337, 337))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addGap(110, 110, 110)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clevel, 0, 164, Short.MAX_VALUE)
                            .addComponent(iduser)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(78, 78, 78)
                                    .addComponent(nama))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jkl)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                            .addComponent(jkp))
                                        .addComponent(nope)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                        .addComponent(us)
                                        .addComponent(pw))))
                            .addComponent(jLabel8))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(370, 370, 370))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(clevel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jkp, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jkl, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(nope)))
                                .addGap(34, 34, 34)
                                .addComponent(us, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(pw, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan)
                            .addComponent(jButton4)
                            .addComponent(edit)
                            .addComponent(hapus))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jkpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkpActionPerformed

    private void jklActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jklActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jklActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        if (nama.getText().equals("")){
            JOptionPane.showMessageDialog(null, "nama tidak boleh kosong");
        }
        if (pw.getText().equals("")){
            JOptionPane.showMessageDialog(null, "password tidak boleh kosong");
        }
        if (nope.getText().equals("")){
            JOptionPane.showMessageDialog(null, "no hp tidak boleh kosong");
        }
        if (us.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong");
        }else {
            int pilih = JOptionPane.showConfirmDialog(null, "Apakah data sudah benar?"+"Simpan", "Simpan Data",
                    JOptionPane.YES_NO_OPTION);
            if (pilih == JOptionPane.YES_OPTION) {
                input_data();
                load_data();
                IDotomatis();
            }
            else if (pilih == JOptionPane.NO_OPTION){
            }
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void nopeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nopeKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if((Character.isAlphabetic(enter))){
        evt.consume();
        JOptionPane.showMessageDialog(null, "Data tidak valid");
        }
    }//GEN-LAST:event_nopeKeyTyped

    private void iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iduserActionPerformed
        // TODO add your handling code here:
        IDotomatis();
    }//GEN-LAST:event_iduserActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        int pilih = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin merubah?","ubah data",
                JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
            try {
                edit();
                JOptionPane.showMessageDialog(null, "ubah data berhasil");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "gagal");
            }
        }
        else{
            bersih();
        }
        load_data();
        bersih();
        IDotomatis();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        int pilih = JOptionPane.showConfirmDialog(null, "apakah anda yakin ingin menghapus?","hapus data",
                JOptionPane.YES_NO_OPTION);
        if(pilih == JOptionPane.YES_OPTION){
            try {
                koneksi.con.createStatement().executeUpdate("DELETE FROM tbl_user WHERE ID_USER='"+iduser.getText()
                        +"'");
                JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
        else {
            System.exit(0);
        }
        load_data();
        bersih();
        IDotomatis();
    }//GEN-LAST:event_hapusActionPerformed

    private void TUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TUserMouseClicked
        // TODO add your handling code here:
        try{
            int cok = TUser.getSelectedRow();
            String a = TUser.getValueAt(cok, 0).toString();
            String b = TUser.getValueAt(cok, 1).toString();
            String c = TUser.getValueAt(cok, 2).toString();
            String d = TUser.getValueAt(cok, 3).toString();
            Date e = new SimpleDateFormat("yyyy-MM-dd").parse((String)data.getValueAt(cok, 4));
            String f = TUser.getValueAt(cok, 5).toString();
            String g = TUser.getValueAt(cok, 6).toString();
            String h = TUser.getValueAt(cok, 7).toString();
            
            iduser.setText(a);
            clevel.setSelectedItem(b);
            nama.setText(c);
            jDateChooser1.setDate(e);
            nope.setText(f);
            us.setText(g);
            pw.setText(h);
            
                    if ("L".equals(d)){
                        jkl.setSelected(true);
                        jkp.setSelected(false);
                    }
                    else {
                        jkl.setSelected(false);
                        jkp.setSelected(true);
                    }
                    simpan.setEnabled(true);
                    edit.setEnabled(true);
                    hapus.setEnabled(true);
        }
                    catch(ParseException e){
                    }
    }//GEN-LAST:event_TUserMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            File file = new File("src\\jdbc\\LaporanFuser.jrxml");
            jasdep = JRXmlLoader.load(file);
            Param.clear();
            jasrep = JasperCompileManager.compileReport(jasdep);
            jaspri = JasperFillManager.fillReport(jasrep, Param, koneksi.con);
            JasperViewer.viewReport(jaspri,false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(AppUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TUser;
    private javax.swing.JComboBox<String> clevel;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JRadioButton jkl;
    private javax.swing.JRadioButton jkp;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nope;
    private javax.swing.JPasswordField pw;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField us;
    // End of variables declaration//GEN-END:variables
}
