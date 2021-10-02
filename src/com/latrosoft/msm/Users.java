package com.latrosoft.msm;



import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
public class Users extends javax.swing.JInternalFrame {

    /**
     * Creates new form User1
     */
    
    
    
    
   Connection con;
   PreparedStatement pst;
   ResultSet rs;
   DefaultTableModel d;
   DBHelper db;
   
   String path= null;
   byte[] userimage = null;

   
    public Users() throws ClassNotFoundException, SQLException {
        initComponents();
        
        this.db=new DBHelper();
        autoID();
        UserLoad();
        editbtn.setEnabled(false); 
        deletebtn.setEnabled(false);
    }

  
    public void autoID()
    {
        try {
             rs = db.getData("select MAX(id) from users");
            rs.getString("MAX(id)");
            if(rs.getString("MAX(id)") == null)
            {
                txtid.setText("U001");
            }
            else
            {
               long id = Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                txtid.setText("U" + String.format("%03d", id)); 
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
      public void UserLoad() throws ClassNotFoundException{
        
        try {
            
            int c;
            pst = db.setData("select * from users ");
            rs= pst.executeQuery();
            
           ResultSetMetaData rsd = rs.getMetaData();
           c= rsd.getColumnCount();
           
           d= (DefaultTableModel)table.getModel();
           d.setRowCount(0);
           
           while(rs.next()){
               
               
               Vector v =new Vector();
               for(int i=1; i<=c ; i++)
               {
                   v.add(rs.getString("id"));
                   v.add(rs.getString("username"));
                   v.add(rs.getString("password"));
                   v.add(rs.getString("gender"));
                   v.add(rs.getString("dob"));
                   v.add(rs.getString("emailid"));
                   v.add(rs.getString("aadhaar"));
                   v.add(rs.getString("doj"));
                   v.add(rs.getString("number"));
                   v.add(rs.getString("position"));
                   v.add(rs.getString("salary"));
                   v.add(rs.getString("usertype"));
                   v.add(rs.getString("address"));
                   v.add(rs.getBlob("image"));
                   
               }
               d.addRow(v);
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtuname = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtaadhaar = new javax.swing.JTextField();
        txtcheck = new javax.swing.JCheckBox();
        txtpass = new javax.swing.JPasswordField();
        txtphone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtutype = new javax.swing.JComboBox<>();
        savebtn = new javax.swing.JButton();
        browsebtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaddress = new javax.swing.JTextArea();
        txtgender = new javax.swing.JComboBox<>();
        txtposition = new javax.swing.JComboBox<>();
        txtsearch = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        clearbtn = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();
        txtsalary = new javax.swing.JTextField();
        txtdob = new com.toedter.calendar.JDateChooser();
        txtjoin = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("D.O.B");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email id");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Position");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gender");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("User name");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Date of Joined");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Aadhar number");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Salary");

        txtuname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtuname.setForeground(new java.awt.Color(102, 0, 102));
        txtuname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunameActionPerformed(evt);
            }
        });
        txtuname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtunameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtunameKeyTyped(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtemail.setForeground(new java.awt.Color(102, 0, 102));
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
        });

        txtaadhaar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtaadhaar.setForeground(new java.awt.Color(102, 0, 102));
        txtaadhaar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaadhaarActionPerformed(evt);
            }
        });
        txtaadhaar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtaadhaarKeyPressed(evt);
            }
        });

        txtcheck.setBackground(new java.awt.Color(102, 0, 102));
        txtcheck.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtcheck.setForeground(new java.awt.Color(255, 255, 255));
        txtcheck.setText("Show Password");
        txtcheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcheckActionPerformed(evt);
            }
        });

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpass.setForeground(new java.awt.Color(102, 0, 102));
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        txtpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpassKeyPressed(evt);
            }
        });

        txtphone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtphone.setForeground(new java.awt.Color(102, 0, 102));
        txtphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtphoneActionPerformed(evt);
            }
        });
        txtphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtphoneKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Address");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User type");

        txtutype.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtutype.setForeground(new java.awt.Color(102, 0, 102));
        txtutype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Worker" }));
        txtutype.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtutypeKeyPressed(evt);
            }
        });

        savebtn.setBackground(new java.awt.Color(0, 255, 0));
        savebtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        savebtn.setForeground(new java.awt.Color(255, 255, 255));
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        browsebtn.setBackground(new java.awt.Color(0, 153, 153));
        browsebtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        browsebtn.setForeground(new java.awt.Color(255, 255, 255));
        browsebtn.setText("Search Image");
        browsebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsebtnActionPerformed(evt);
            }
        });
        browsebtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                browsebtnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                browsebtnKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User name", "Password", "Gender", "D.O.B", "Email id", "Aadhaar number", "D.O.J", "Ph.no", "Position", "Salary", "User type", "Address", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ph.no");

        txtaddress.setColumns(20);
        txtaddress.setRows(5);
        txtaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtaddressKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtaddress);

        txtgender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtgender.setForeground(new java.awt.Color(102, 0, 102));
        txtgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        txtgender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgenderKeyPressed(evt);
            }
        });

        txtposition.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtposition.setForeground(new java.awt.Color(102, 0, 102));
        txtposition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "HR", "Sales Executive Officer", "Quality Checker", "Labour" }));
        txtposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpositionActionPerformed(evt);
            }
        });
        txtposition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpositionKeyPressed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsearch.setForeground(new java.awt.Color(102, 0, 102));
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchKeyPressed(evt);
            }
        });

        searchbtn.setBackground(new java.awt.Color(255, 51, 153));
        searchbtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        editbtn.setBackground(new java.awt.Color(255, 204, 0));
        editbtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        editbtn.setForeground(new java.awt.Color(255, 255, 255));
        editbtn.setText("Edit");
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });

        deletebtn.setBackground(new java.awt.Color(255, 51, 51));
        deletebtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        clearbtn.setBackground(new java.awt.Color(0, 153, 153));
        clearbtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clearbtn.setForeground(new java.awt.Color(255, 255, 255));
        clearbtn.setText("Clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        label.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                labelComponentAdded(evt);
            }
        });
        label.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                labelKeyTyped(evt);
            }
        });

        txtid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtid.setForeground(new java.awt.Color(255, 255, 255));
        txtid.setText("E0001");

        txtsalary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsalary.setForeground(new java.awt.Color(102, 0, 102));
        txtsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalaryActionPerformed(evt);
            }
        });
        txtsalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsalaryKeyPressed(evt);
            }
        });

        txtdob.setPreferredSize(new java.awt.Dimension(78, 25));
        txtdob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdobKeyPressed(evt);
            }
        });

        txtjoin.setPreferredSize(new java.awt.Dimension(78, 25));
        txtjoin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtjoinKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(browsebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtuname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtposition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel11))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtaadhaar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel6))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtsalary, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(editbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(txtid)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtjoin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtutype, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(192, 192, 192))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtuname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcheck)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtaadhaar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtjoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtutype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtposition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(savebtn)
                                .addComponent(editbtn)
                                .addComponent(deletebtn)
                                .addComponent(clearbtn))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(browsebtn)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
  
    
    private void txtdobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdobKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtdobKeyPressed

    private void labelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labelKeyTyped

    }//GEN-LAST:event_labelKeyTyped

    private void labelComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_labelComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelComponentAdded

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed

        savebtn.setEnabled(true);

        txtuname.setText("");
        txtpass.setText("");
        txtgender.setSelectedIndex(-1);
        txtdob.setDate(null);
        txtemail.setText("");
        txtaadhaar.setText("");
        txtjoin.setDate(null);
        txtphone.setText("");
        txtposition.setSelectedIndex(-1);
        txtsalary.setText("");
        txtutype.setSelectedIndex(-1);
        txtaddress.setText("");
        txtuname.setText("");
        label.setIcon(null);
        txtuname.requestFocus();
       
        //            editbtn.setEnabled(true);
        //            deletebtn.setEnabled(true);
    }//GEN-LAST:event_clearbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        try {
            String id = txtid.getText();
            String uname = txtuname.getText();
            String pass = txtpass.getText();
            String gender = txtgender.getSelectedItem().toString();
            String email = txtemail.getText();
            String aadhaar =txtaadhaar.getText();
            String number =txtphone.getText();

            String position = txtposition.getSelectedItem().toString();
            String salary = txtsalary.getText();
            String utype = txtutype.getSelectedItem().toString();
            String address = txtaddress.getText();

            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String birth = date.format(txtdob.getDate());
            String join = date.format(txtjoin.getDate());

           
            pst = db.setData(" delete from users where id=? ");

            pst.setString(1,id);
            pst.executeUpdate();

            txtuname.setText("");
            txtpass.setText("");
            txtgender.setSelectedIndex(-1);
            txtdob.setDate(null);
            txtemail.setText("");
            txtaadhaar.setText("");
            txtjoin.setDate(null);
            txtphone.setText("");
            txtposition.setSelectedIndex(-1);
            txtsalary.setText("");
            txtutype.setSelectedIndex(-1);
            txtaddress.setText("");
            txtuname.setText("");
            label.setIcon(null);
            txtuname.requestFocus();

            UserLoad();
            autoID();
            editbtn.setEnabled(true);
            JOptionPane.showMessageDialog(null,"User deleted");
            savebtn.setEnabled(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_deletebtnActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed

       
            String id = txtid.getText();
            String uname = txtuname.getText();
            String pass = txtpass.getText();
            String gender = txtgender.getSelectedItem().toString();
            String email = txtemail.getText();
            String aadhaar =txtaadhaar.getText();
            String number =txtphone.getText();

            String position = txtposition.getSelectedItem().toString();
            String salary = txtsalary.getText();
            String utype = txtutype.getSelectedItem().toString();
            String address = txtaddress.getText();
  
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String birth = date.format(txtdob.getDate());
            String join = date.format(txtjoin.getDate());
           
            
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }else if(birth.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill birth");
                return;
             }else if(join.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill join");
                return;
             }
             


        try {
           
            pst = db.setData("update users  set username = ?,password = ?,gender = ?,dob = ?,emailid = ?,aadhaar = ?,doj = ?,number = ?,position = ?,salary = ?,usertype = ?,address = ?,image = ? where id = ? ");

            pst.setString(1,uname);
            pst.setString(2,pass);
            pst.setString(3,gender);
            pst.setString(4,birth);
            pst.setString(5,email);
            pst.setString(6,aadhaar);
            pst.setString(7,join);
            pst.setString(8,number);
            pst.setString(9,position);
            pst.setString(10,salary);
            pst.setString(11,utype);
            pst.setString(12,address);
            pst.setBytes(13,userimage);
            pst.setString(14,id);
            pst.executeUpdate();

            txtuname.setText("");
            txtpass.setText("");
            txtgender.setSelectedIndex(-1);
            txtdob.setDate(null);
            txtemail.setText("");
            txtaadhaar.setText("");
            txtjoin.setDate(null);
            txtphone.setText("");
            txtposition.setSelectedIndex(-1);
            txtsalary.setText("");
            txtutype.setSelectedIndex(-1);
            txtaddress.setText("");
            txtuname.setText("");
            label.setIcon(null);
            txtuname.requestFocus();

            UserLoad();
            autoID();
            
            JOptionPane.showMessageDialog(null,"User updated");
            
            savebtn.setEnabled(true);
            clearbtn.setEnabled(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      editbtn.setEnabled(false);
      deletebtn.setEnabled(false);
     
    }//GEN-LAST:event_editbtnActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        String id = txtsearch.getText();

        try {
          
            pst = db.setData("select * from users where id = ?");
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next() == false){

                JOptionPane.showMessageDialog(this,"Record not found");

            }
            else{
                String id1 = rs.getString("id");
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                String gender = rs.getString("gender");
                String email = rs.getString("emailid");

                String aadhaar=rs.getString("aadhaar");
                String  number =rs.getString("number");
                String position = rs.getString("position");
                String salary = rs.getString("salary");
                String utype = rs.getString("usertype");
                String address = rs.getString("address");

                String birth = rs.getString("dob");
                String join = rs.getString("doj");
                Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
                Date dt2 = new SimpleDateFormat("yyyy-MM-dd").parse(join);
               


               
                Blob blob  =  rs.getBlob("image");
                byte[]imagebytes = blob.getBytes(1,(int)blob.length());
                userimage = blob.getBytes(1,(int)blob.length());
                ImageIcon image = new ImageIcon(imagebytes);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                    
   
                 
                txtid.setText(id1);
                txtuname.setText(uname.trim());
                txtpass.setText(pass.trim());
                txtgender.setSelectedItem(gender.trim());
                txtdob.setDate(dt1);
                txtjoin.setDate(dt2);
                txtemail.setText(email.trim());
                txtaadhaar.setText(aadhaar.trim());
                txtphone.setText(number.trim());
                txtposition.setSelectedItem(position.trim());
                txtsalary.setText(salary.trim());
                txtutype.setSelectedItem( utype.trim());
                txtaddress.setText(address.trim());
                label.setIcon(newImage);
                
                editbtn.setEnabled(true);
                deletebtn.setEnabled(true);
                clearbtn.setEnabled(false);
                savebtn.setEnabled(false);
                txtsearch.setText("");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_searchbtnActionPerformed

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){

        }
    }//GEN-LAST:event_txtsearchKeyPressed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtpositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpositionActionPerformed

    private void txtgenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgenderKeyPressed
 if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtgenderKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        savebtn.setEnabled(false);
        clearbtn.setEnabled(false);
        editbtn.setEnabled(true);
        deletebtn.setEnabled(true);
        d= (DefaultTableModel)table.getModel();
        int selectIndex= table.getSelectedRow();

        txtid.setText(d.getValueAt(selectIndex,0).toString());
        txtuname.setText( d.getValueAt(selectIndex,1).toString());
        txtpass.setText( d.getValueAt(selectIndex,2).toString());
        txtgender.setSelectedItem( d.getValueAt(selectIndex,3).toString());
       try {
           Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(d.getValueAt(selectIndex,4).toString());
           Date dt2 = new SimpleDateFormat("yyyy-MM-dd").parse(d.getValueAt(selectIndex,7).toString());
           txtdob.setDate(dt1);
           txtjoin.setDate(dt2);
       } catch (ParseException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        txtemail.setText( d.getValueAt(selectIndex,5).toString());
        txtaadhaar.setText( d.getValueAt(selectIndex,6).toString());
       
       
        txtphone.setText(d.getValueAt(selectIndex,8).toString());
        txtposition.setSelectedItem( d.getValueAt(selectIndex,9).toString());
        txtsalary.setText( d.getValueAt(selectIndex,10).toString());
        txtutype.setSelectedItem( d.getValueAt(selectIndex,11).toString());
        txtaddress.setText( d.getValueAt(selectIndex,12).toString());
            
        
        System.out.println(d.getValueAt(selectIndex,13));
        
         Blob blob;
       try {
           
        pst = db.setData("select * from users where id =?");
        pst.setString(1,d.getValueAt(selectIndex,0).toString());
        ResultSet rs = pst.executeQuery();
        rs.next();
        blob = rs.getBlob("image");
        byte[]imagebytes = blob.getBytes(1,(int)blob.length());
        userimage = blob.getBytes(1,(int)blob.length());
        ImageIcon image = new ImageIcon(imagebytes);
        Image im = image.getImage();
        Image myImg = im.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(myImg);
        
        
        label.setIcon(newImage) ;
       } catch (SQLException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       editbtn.setEnabled(true);
       deletebtn.setEnabled(true);
        

        
    }//GEN-LAST:event_tableMouseClicked

    private void browsebtnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_browsebtnKeyTyped

    }//GEN-LAST:event_browsebtnKeyTyped

    private void browsebtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_browsebtnKeyPressed

    }//GEN-LAST:event_browsebtnKeyPressed

    private void browsebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsebtnActionPerformed

        try {
            JFileChooser picchooser = new JFileChooser();
            picchooser.showOpenDialog(null);
            File pic = picchooser.getSelectedFile();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","png","jpg","jpeg","gif","webp");
            picchooser.addChoosableFileFilter(filter);
            path= pic.getAbsolutePath();
            BufferedImage img;
            img = ImageIO.read(picchooser.getSelectedFile());
            ImageIcon imageIcon = new ImageIcon(new
            ImageIcon(img).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

            label.setIcon(imageIcon);
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            for(int readNum; (readNum=fis.read(buff)) !=-1 ; )
            {
                baos.write(buff,0,readNum);
            }
            userimage=baos.toByteArray();

        } catch (IOException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_browsebtnActionPerformed


    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed

        
        
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_savebtnActionPerformed

    private void txtphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtphoneActionPerformed

    private void txtpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpassKeyPressed
       if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtpassKeyPressed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void txtcheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcheckActionPerformed
        if(txtcheck.isSelected()){
            txtpass.setEchoChar((char)0);
        }
        else{
            txtpass.setEchoChar('*');
        }
    }//GEN-LAST:event_txtcheckActionPerformed

    private void txtaadhaarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaadhaarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaadhaarActionPerformed

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
 
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NullPointerException e){
            }
 }
    }//GEN-LAST:event_txtemailKeyPressed

    private void txtunameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtunameKeyTyped
       
    }//GEN-LAST:event_txtunameKeyTyped

    private void txtunameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtunameKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 } 
    }//GEN-LAST:event_txtunameKeyPressed

    private void txtunameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunameActionPerformed
       
    }//GEN-LAST:event_txtunameActionPerformed

    private void txtsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalaryActionPerformed
        
    }//GEN-LAST:event_txtsalaryActionPerformed

    private void txtaadhaarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaadhaarKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtaadhaarKeyPressed

    private void txtjoinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjoinKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtjoinKeyPressed

    private void txtphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtphoneKeyPressed

    private void txtpositionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpositionKeyPressed
       if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtpositionKeyPressed

    private void txtutypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtutypeKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtutypeKeyPressed

    private void txtaddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaddressKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtaddressKeyPressed

    private void txtsalaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsalaryKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
     
         String id = txtid.getText();
                String uname = txtuname.getText();
                String pass = txtpass.getText();
                String gender = txtgender.getSelectedItem().toString();
                String email = txtemail.getText();
                String aadhaar =txtaadhaar.getText();
                String number =txtphone.getText();

                String position = txtposition.getSelectedItem().toString();
                String salary = txtsalary.getText();
                String utype = txtutype.getSelectedItem().toString();
                String address = txtaddress.getText();

                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String birth = date.format(txtdob.getDate());
                String join = date.format(txtjoin.getDate());

        
        
        
        
        
            if(uname.equals("")){
                 JOptionPane.showMessageDialog(this, "Fill User Field");
                 return;
            }
            else if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Password");
                return;
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(this, "Fill Gender");
                return;
            }else if (email.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(aadhaar.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill email");
                return;
             }else if(number.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill number");
                return;
             }else if(position.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill position");
                return;
             }else if(salary.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill salary");
                return;
             }else if(utype.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill utype");
                return;
             }else if(address.equals("")){
                    JOptionPane.showMessageDialog(this, "Fill address");
                return;
             }
             
            try {
               
                pst = db.setData("insert into users (id,username,password,gender,dob,emailid,aadhaar,doj,number, position,salary,usertype,address,image)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,gender);
                pst.setString(5,birth);
                pst.setString(6,email);
                pst.setString(7,aadhaar);
                pst.setString(8,join);
                pst.setString(9,number);
                pst.setString(10,position);
                pst.setString(11,salary);
                pst.setString(12,utype);
                pst.setString(13,address);
                pst.setBytes(14,userimage);
                pst.executeUpdate();

                txtuname.setText("");
                txtpass.setText("");
                txtgender.setSelectedIndex(-1);
                txtdob.setDate(null);
                txtemail.setText("");
                txtaadhaar.setText("");
                txtjoin.setDate(null);
                txtphone.setText("");
                txtposition.setSelectedIndex(-1);
                txtsalary.setText("");
                txtutype.setSelectedIndex(-1);
                txtaddress.setText("");

                label.setIcon(null);
                txtuname.requestFocus();
                UserLoad();
                autoID();
               
                JOptionPane.showMessageDialog(null,"User added");
                
              editbtn.setEnabled(false);
              deletebtn.setEnabled(false);  

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    }//GEN-LAST:event_txtsalaryKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browsebtn;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JLabel label;
    private javax.swing.JButton savebtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtaadhaar;
    private javax.swing.JTextArea txtaddress;
    private javax.swing.JCheckBox txtcheck;
    private com.toedter.calendar.JDateChooser txtdob;
    private javax.swing.JTextField txtemail;
    private javax.swing.JComboBox<String> txtgender;
    private javax.swing.JLabel txtid;
    private com.toedter.calendar.JDateChooser txtjoin;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtphone;
    private javax.swing.JComboBox<String> txtposition;
    private javax.swing.JTextField txtsalary;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtuname;
    private javax.swing.JComboBox<String> txtutype;
    // End of variables declaration//GEN-END:variables
}
