package com.latrosoft.msm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 

public class Returnitems extends javax.swing.JInternalFrame {
    DBHelper db;
 
    public Returnitems() throws SQLException ,ClassNotFoundException{
          this.db= new DBHelper();
        initComponents();
        Connect();
        DefaultTableModel d;
        showDate();
        showTime();
        UserLoad();
        
        txtimei1.requestFocus();
       
        
    }

    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4;
    PreparedStatement pst5;
    PreparedStatement pst6;
    PreparedStatement pst7;
    PreparedStatement pst8;
    DefaultTableModel d;
    ResultSet rs;
    ResultSet rs1;
    
    
    void showDate() {
        Date dt = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        txtdate.setText(s.format(dt));

    }

   void showTime() {
        javax.swing.Timer t1 = new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss ");
                txttime1.setText(s.format(dt));

            }
        });
        t1.start();
    }


    public void Connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mobile_erp_system", "root", "");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Returnitems.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void Imei() {

        try {
            String imei1 = txtimei1.getText();
            pst = con.prepareStatement("select * from salesitem where imei1 =?");
            pst.setString(1, imei1);
            rs = pst.executeQuery();

           
           if ( rs.next()== false ) {
              
                 System.out.println("Item not found");
            } else {
                String salesid =rs.getString("salesid");
                String itemid =rs.getString("itmid");
                String iname = rs.getString("itemname");
                String cusname =rs.getString("customername");
                String brand =rs.getString("brand");
                String model = rs.getString("model"); 
                String imei2 = rs.getString("imei2"); 
                String count =rs.getString("stockcount");
                String itemrate = rs.getString("rate");
                String discount =rs.getString("discount");
                String total =rs.getString("total");
                
                txtsalesid.setText(salesid.trim());
                txtitemid.setText(itemid.trim());
                txtiname1.setText(iname.trim());
                txtcusname.setText(cusname.trim());
                txtbrand.setText(brand.trim());
                txtmodel.setText(model.trim());  
                txtimei2.setText(imei2.trim()); 
                txtcount.setText(count.trim());
                txtrate.setText(itemrate.trim());
                txtdiscount.setText(discount.trim());
                txttotalcost.setText(total.trim());   
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(Returnitems.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    

       
     public void UserLoad() throws ClassNotFoundException{
        
        try {
            
            int c;
            pst = db.setData("select * from returnitem ");
            rs= pst.executeQuery();
            
           ResultSetMetaData rsd = rs.getMetaData();
           c= rsd.getColumnCount();
           
           d= (DefaultTableModel)table.getModel();
           d.setRowCount(0);
           
           while(rs.next()){
               
               
               Vector v =new Vector();
               for(int i=1; i<=c ; i++)
               {
                   v.add(rs.getString("imei"));
                   v.add(rs.getString("salesid"));
                   v.add(rs.getString("itemid"));
                   v.add(rs.getString("itemname"));
                   v.add(rs.getString("customername"));
                   v.add(rs.getString("brand"));
                   v.add(rs.getString("model"));
                   v.add(rs.getString("imei2"));
                   v.add(rs.getString("stockcount"));
                   v.add(rs.getString("rate"));
                   v.add(rs.getString("discount"));
                   v.add(rs.getString("total"));
                   v.add(rs.getString("date"));
                   v.add(rs.getString("time"));
                  
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

        txttime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtimei2 = new javax.swing.JTextField();
        txtrate = new javax.swing.JTextField();
        txtcount = new javax.swing.JTextField();
        txttotalcost = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        addbtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtbrand = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdiscount = new javax.swing.JTextField();
        txtimei1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtcusname = new javax.swing.JTextField();
        clearbtn = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        txtitemid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtsalesid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtiname1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        txttime1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 51));
        setClosable(true);
        setForeground(new java.awt.Color(0, 0, 51));
        setMaximizable(true);
        setTitle("Return items");
        setPreferredSize(new java.awt.Dimension(1370, 672));

        txttime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Item Imei2");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Count");

        txtimei2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtimei2.setForeground(new java.awt.Color(0, 0, 51));

        txtrate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtrate.setForeground(new java.awt.Color(0, 0, 51));
        txtrate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrateMouseClicked(evt);
            }
        });
        txtrate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrateActionPerformed(evt);
            }
        });
        txtrate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrateKeyPressed(evt);
            }
        });

        txtcount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcount.setForeground(new java.awt.Color(0, 0, 51));
        txtcount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcountActionPerformed(evt);
            }
        });
        txtcount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcountKeyPressed(evt);
            }
        });

        txttotalcost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttotalcost.setForeground(new java.awt.Color(0, 0, 51));
        txttotalcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalcostActionPerformed(evt);
            }
        });
        txttotalcost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttotalcostKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TotalCost");

        addbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addbtn.setForeground(new java.awt.Color(0, 0, 51));
        addbtn.setText("Add");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 51));
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Brand");

        txtbrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbrand.setForeground(new java.awt.Color(0, 0, 51));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Model");

        txtmodel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtmodel.setForeground(new java.awt.Color(0, 0, 51));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Discount");

        txtdiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdiscount.setForeground(new java.awt.Color(0, 0, 51));
        txtdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiscountActionPerformed(evt);
            }
        });

        txtimei1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtimei1.setForeground(new java.awt.Color(0, 0, 51));
        txtimei1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtimei1KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CustomerName");

        txtcusname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcusname.setForeground(new java.awt.Color(0, 0, 51));
        txtcusname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcusnameActionPerformed(evt);
            }
        });
        txtcusname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcusnameKeyPressed(evt);
            }
        });

        clearbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearbtn.setForeground(new java.awt.Color(0, 0, 51));
        clearbtn.setText("Clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        searchbtn.setText("Search IMEI");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        txtitemid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtitemid.setForeground(new java.awt.Color(0, 0, 51));
        txtitemid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtitemidKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Item Id");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IMEI", "SalesId", "Itmid", "Itemname", "Customername", "Brand", "Model", "IMEI2", "Count", "Rate", "Discount", "Total", "Date", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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

        txtsalesid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsalesid.setForeground(new java.awt.Color(0, 0, 51));
        txtsalesid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsalesidKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sales Id");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Item Name");

        txtiname1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtiname1.setForeground(new java.awt.Color(0, 0, 51));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("RETURN ITEMS");

        txtdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.setText("Date");

        txttime1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txttime1.setForeground(new java.awt.Color(255, 255, 255));
        txttime1.setText("Time");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(564, 564, 564)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtdate)
                .addGap(70, 70, 70)
                .addComponent(txttime1)
                .addGap(71, 71, 71))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(searchbtn)
                                .addGap(18, 18, 18)
                                .addComponent(txtimei1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsalesid, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(txtitemid, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtiname1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3)
                            .addComponent(txtimei2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotalcost, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(465, 465, 465)
                        .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtdate)
                    .addComponent(txttime1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtimei1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchbtn)
                            .addComponent(txtsalesid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtitemid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtiname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtimei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txttotalcost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txttime)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 359, Short.MAX_VALUE)
                    .addComponent(txttime)
                    .addGap(0, 359, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsalesidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsalesidKeyPressed

    }//GEN-LAST:event_txtsalesidKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void txtitemidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitemidKeyPressed

    }//GEN-LAST:event_txtitemidKeyPressed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        Imei();

    }//GEN-LAST:event_searchbtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
        txtimei1.setText("");
        txtsalesid.setText("");
        txtitemid.setText("");
        txtiname1.setText("");
        txtcusname.setText("");
        txtbrand.setText("");
        txtmodel.setText("");
        txtimei2.setText("");
        txtcount.setText("");
        txtrate.setText("");
        txtdiscount.setText("");
        txttotalcost.setText("");
        txtimei1.requestFocus();
    }//GEN-LAST:event_clearbtnActionPerformed

    private void txtcusnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusnameKeyPressed

    }//GEN-LAST:event_txtcusnameKeyPressed

    private void txtcusnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcusnameActionPerformed

    }//GEN-LAST:event_txtcusnameActionPerformed

    private void txtimei1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimei1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            Imei();

        }
    }//GEN-LAST:event_txtimei1KeyPressed

    private void txtdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiscountActionPerformed

    }//GEN-LAST:event_txtdiscountActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed

        String imei = txtimei1.getText();
        String salesid = txtsalesid.getText();
        String itemid = txtitemid.getText();
        String itemname = txtiname1.getText();
        String customername = txtcusname.getText();
        String brand = txtbrand.getText();
        String model = txtmodel.getText();
        String imei2 =  txtimei2.getText();
        String count = txtcount.getText();
        String rate = txtrate.getText();
        String discount = txtdiscount.getText();
        String total = txttotalcost.getText();
        String date = txtdate.getText();
        String time = txttime1.getText();

        if(imei .equals("")){
            JOptionPane.showMessageDialog(null,"Item not found");

        }
        try {

            pst = db.setData("insert into returnitem (imei,salesid,itemid,itemname,customername,brand,model,imei2,stockcount,rate,discount, total,date,time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1,imei);
            pst.setString(2,salesid);
            pst.setString(3,itemid);
            pst.setString(4,itemname);
            pst.setString(5,customername);
            pst.setString(6,brand);
            pst.setString(7,model);
            pst.setString(8,imei2);
            pst.setString(9,count);
            pst.setString(10,rate);
            pst.setString(11,discount);
            pst.setString(12,total);
            pst.setString(13,date);
            pst.setString(14,time);
            pst.executeUpdate();

            String imei1 = txtimei1.getText();
            pst1 = db.setData(" delete from salesitem where imei1= ? ");
            pst1.setString(1,imei1);
            pst1.executeUpdate();

            String salesid1 = txtsalesid.getText();
            String totalcost =  txttotalcost.getText();

            pst2 = con.prepareStatement("select * from sales where salesid =?");
            pst2.setString(1, salesid1);
            rs = pst2.executeQuery();

            while(rs.next())
            {
                pst3 = db.setData("update sales set subtotal = subtotal - ? ,payment = payment - ? where salesid =?");

                pst3.setString(1, totalcost);
                pst3.setString(2, totalcost);
                pst3.setString(3, salesid1);
                pst3.executeUpdate();

            }

            pst4 = con.prepareStatement("select * from sales where salesid =?");
            pst4.setString(1, salesid1);
            rs = pst4.executeQuery();

            while(rs.next())
            {
                int subtotal;
                subtotal =rs.getInt("subtotal");
                if(subtotal == 0){
                    pst5 = db.setData(" delete from sales where salesid = ? ");
                    pst5.setString(1,salesid1);
                    pst5.executeUpdate();

                }
            }
            String count1 = txtcount.getText();
            String itemid1 =  txtitemid.getText();
            pst6 = db.setData("update item set stock = stock - ? where itemid =?");

            pst6.setString(1,count1);
            pst6.setString(2,itemid1);
            pst6.executeUpdate();

            txtimei1.setText("");
            txtsalesid.setText("");
            txtitemid.setText("");
            txtiname1.setText("");
            txtcusname.setText("");
            txtbrand.setText("");
            txtmodel.setText("");
            txtimei2.setText("");
            txtcount.setText("");
            txtrate.setText("");
            txtdiscount.setText("");
            txttotalcost.setText("");

            clearbtn.setEnabled(true);

            addbtn.setEnabled(true);

            txtimei1.requestFocus();
            UserLoad();
            JOptionPane.showMessageDialog(this,"Item added");
            JOptionPane.showMessageDialog(this,"Item deleted in Sales");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_addbtnActionPerformed

    private void txttotalcostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalcostKeyTyped

    }//GEN-LAST:event_txttotalcostKeyTyped

    private void txttotalcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalcostActionPerformed

    }//GEN-LAST:event_txttotalcostActionPerformed

    private void txtcountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcountKeyPressed

    }//GEN-LAST:event_txtcountKeyPressed

    private void txtcountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcountActionPerformed

    }//GEN-LAST:event_txtcountActionPerformed

    private void txtrateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrateKeyPressed

    }//GEN-LAST:event_txtrateKeyPressed

    private void txtrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrateActionPerformed

    }//GEN-LAST:event_txtrateActionPerformed

    private void txtrateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrateMouseClicked

    }//GEN-LAST:event_txtrateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtcount;
    private javax.swing.JTextField txtcusname;
    private javax.swing.JLabel txtdate;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JTextField txtimei1;
    private javax.swing.JTextField txtimei2;
    private javax.swing.JTextField txtiname1;
    private javax.swing.JTextField txtitemid;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtrate;
    private javax.swing.JTextField txtsalesid;
    private javax.swing.JLabel txttime;
    private javax.swing.JLabel txttime1;
    private javax.swing.JTextField txttotalcost;
    // End of variables declaration//GEN-END:variables
}
