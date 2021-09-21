package com.latrosoft.msm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hari
 */
public class Purchase extends javax.swing.JInternalFrame {

    /**
     * Creates new form Business_info
     */
    public Purchase() throws SQLException, ClassNotFoundException {
          this.db= new DBHelper();
        initComponents();
        Connect();
        Vendor();
        autoID();

        showDate();
        showTime();
    }
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    DefaultTableModel df;
    ResultSet rs;
    DBHelper db;

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
                txttime.setText(s.format(dt));

            }
        });
        t1.start();
    }

    public void Connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mobile_erp_system", "root", "");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Vendor() throws ClassNotFoundException {
        try {
          //  pst = con.prepareStatement("select Distinct name from vendor");
            //rs = pst.executeQuery();
               rs=db.getData("select Distinct name from vendor");
            txtvendor.removeAllItems();

            while (rs.next()) {
                txtvendor.addItem(rs.getString("name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
  public void autoID() throws SQLException,ClassNotFoundException
    {
     
            
            ResultSet rs;
            rs = db.getData("select MAX(purid) from purchase");
            
            rs.getString("MAX(purid)");
            
            if(rs.getString("MAX(purid)") == null)
            {
                txtid.setText("PU001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(purid)").substring(2,rs.getString("MAX(purid)").length()));
                id++;
                txtid.setText("PU" + String.format("%03d", id));
        
            }
   
       
    }
    public void render() {

        try {
            String itemid = txtitemid.getText();
            pst = con.prepareStatement("select * from item where itemid = ?");
            pst.setString(1, itemid);
            rs = pst.executeQuery();

            if (rs.next() == false) {
                JOptionPane.showMessageDialog(this, "Item Not Found");

                txtitemid.setText("");
            txtname.setText("");
            txtbrand.setText("");
            txtmodel.setText("");
            txtcost.setText("");
            txtpay.setText("");
            txtbal.setText("");
            } else {
               String itemname=rs.getString("itemname");
               String brand=rs.getString("brand");
               String model=rs.getString("model");
              

                txtname.setText(itemname.trim());
                txtbrand.setText(brand.trim());
                txtmodel.setText(model.trim());
              
                txtmodel.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TempAdd() {

        int Rate=Integer.parseInt(txtrate.getText());
        int Discount=Integer.parseInt(txtdiscount.getText());
        int StockCount=Integer.parseInt(txtscount.getText());
        int MidRate= Rate *Discount/100;
        int FinalRate=Rate-MidRate;
          int tot=StockCount * FinalRate;
      df = (DefaultTableModel) jTable1.getModel();
        df.addRow(new Object[]{
            txtitemid.getText(),
            txtname.getText(),
            txtbrand.getText(),
            txtmodel.getText(),
            txtrate.getText(),
            txtdiscount.getText(),
            txtscount.getText(),
            tot

        }
        );
        int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 7).toString());
        }
        txtcost.setText(String.valueOf(sum));

        txtitemid.setText("");
        txtname.setText("");
        txtbrand.setText("");
        txtmodel.setText("");
        txtdiscount.setText("");
        txtrate.setText("");
        txtscount.setText("");
    }
    public void delete()
    {
        df=(DefaultTableModel)jTable1.getModel();
     
       int SelectedRowIndex =jTable1.getSelectedRow();
       df.removeRow(SelectedRowIndex);
    }

    public void add() throws ClassNotFoundException {

        try {
            
           
            pst =  con.prepareStatement("insert into purchase(purid,vendorname,subtotal,payment,balance,date,time)values(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
          DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
           // String date = dt.format(now);                                    //
             String date = txtdate.getText();
            String purchaseid=txtid.getText();
           String venname=txtvendor.getSelectedItem().toString();
            long subtotal = Long.parseLong(txtcost.getText());
            long payment = Long.parseLong(txtpay.getText());
            long balance = Long.parseLong(txtbal.getText());
            String time = txttime.getText();
          
           
                  
           
             pst=db.setData("insert into purchase(purid,vendorname,subtotal,payment,balance,date,time)values(?,?,?,?,?,?,?)");
              
            pst.setString(1, purchaseid);
            pst.setString(2, venname);
            
            pst.setLong(3, subtotal);
            pst.setLong(4, payment);
            pst.setLong(5, balance);
            pst.setString(6, date); 
            pst.setString(7, time);
            
            pst.executeUpdate();

            

           
            pst1 =db.setData("insert into purchaseitem(purid,itmid,itemname,brand,model,rate,discount,stockcount,total,date,time)values(?,?,?,?,?,?,?,?,?,?,?)");
           String lastid=txtid.getText();
            String itemID;
            String itemname;
             String brand;
             String model;
            
             long rate;
             int discount;
             int stockcount;
              long total = 0;

            for (int i = 0; i < jTable1.getRowCount(); i++) {
              
                itemID = (String) jTable1.getValueAt(i, 0);
                itemname=(String) jTable1.getValueAt(i, 1);
                brand =(String) jTable1.getValueAt(i,2).toString();
                model=(String)   jTable1.getValueAt(i, 3).toString();
                rate= Long.parseLong(jTable1.getValueAt(i, 4).toString());
                discount=Integer.parseInt(  jTable1.getValueAt(i, 5).toString() );
                stockcount=Integer.parseInt(jTable1.getValueAt(i, 6).toString())  ;
               
                total = Long.parseLong(jTable1.getValueAt(i, 7).toString());

               // pst1.setInt(1, lastid);
                pst1.setString(1,lastid);
                pst1.setString(2, itemID);
               
                pst1.setString(3 ,itemname);
                pst1.setString(4, brand);
                pst1.setString(5, model);
                pst1.setLong(6,rate);
                pst1.setInt(7, discount);
                pst1.setInt(8, stockcount);
                pst1.setLong(9, total);
                pst1.setString(10, date);
                pst1.setString(11, time);
                pst1.executeUpdate();

            }

     
            pst2 = db.setData("update item set stock=stock+ ? where itemid=?");

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                itemID = (String) jTable1.getValueAt(i, 0);

                stockcount =Integer.parseInt( jTable1.getValueAt(i, 6).toString());

                pst2.setInt(1, stockcount);

                pst2.setString(2, itemID);
                pst2.executeUpdate();

            }
            JOptionPane.showMessageDialog(this, "Purchase Completed");
            DefaultTableModel df=(DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            txtitemid.setText("");
            txtname.setText("");
            txtbrand.setText("");
            txtmodel.setText("");
            txtcost.setText("");
            txtpay.setText("");
            txtbal.setText("");
       
            
        } catch (SQLException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Stocks(String args[]) {

    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        txtvendor = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtitemid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtbrand = new javax.swing.JTextField();
        txtmodel = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtcost = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        txtbal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtrate = new javax.swing.JTextField();
        txtdiscount = new javax.swing.JTextField();
        txtscount = new javax.swing.JTextField();
        txtdate = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 0, 102));
        setClosable(true);
        setForeground(new java.awt.Color(255, 255, 255));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Add Stock");
        setPreferredSize(new java.awt.Dimension(1366, 690));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Vendor");

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rate");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Item Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Brand");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Model");

        txtitemid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtitemid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtitemidActionPerformed(evt);
            }
        });
        txtitemid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtitemidKeyPressed(evt);
            }
        });

        txtname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtbrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbrandActionPerformed(evt);
            }
        });

        txtmodel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodelActionPerformed(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Id", "Item Name", "Brand", "Model", "Rate", "Discount", "Stock Count", "Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Payment");

        txtcost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcostActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TotalCost");

        txtpay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayActionPerformed(evt);
            }
        });

        txtbal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbalActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Balance");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Item ID");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Discount");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Stock Count");

        txtrate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        txtdiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiscountActionPerformed(evt);
            }
        });
        txtdiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdiscountKeyPressed(evt);
            }
        });

        txtscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtscountActionPerformed(evt);
            }
        });
        txtscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtscountKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtitemid, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(196, 196, 196)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtscount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpay)
                                    .addComponent(txtbal)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtitemid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel2))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtscount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(txtbal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(133, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        txtdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdate.setText("Date");

        txttime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txttime.setForeground(new java.awt.Color(51, 51, 51));
        txttime.setText("Time");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("PURCHASE");

        txtid.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtid.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(txtdate)
                .addGap(52, 52, 52)
                .addComponent(txtid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188)
                .addComponent(txttime)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(610, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(613, 613, 613)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdate)
                    .addComponent(txttime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel8)
                    .addContainerGap(648, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtitemidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitemidKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          render();
          txtrate.requestFocus();
        }
    }//GEN-LAST:event_txtitemidKeyPressed

    private void txtbrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbrandActionPerformed

    private void txtmodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmodelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TempAdd();

        txtitemid.setText("");
        txtname.setText("");
        txtbrand.setText("");
        txtmodel.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcostActionPerformed

    private void txtpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayActionPerformed

    private void txtbalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int payment = Integer.parseInt(txtpay.getText());
            int subtotal = Integer.parseInt(txtcost.getText());
            int balance = subtotal - payment;
            
            txtbal.setText(String.valueOf(balance));
            
            add();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtitemidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtitemidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtitemidActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          df = (DefaultTableModel) jTable1.getModel();

        int selected = jTable1.getSelectedRow();

        
        txtitemid.setText(df.getValueAt(selected, 0).toString());
        txtname.setText(df.getValueAt(selected, 1).toString());
        txtbrand.setText(df.getValueAt(selected, 2).toString());
        txtmodel.setText(df.getValueAt(selected, 3).toString());
        txtcost.setText(df.getValueAt(selected, 4).toString());

        jButton2.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtitemid.setText("");
        txtname.setText("");
        txtbrand.setText("");
        txtmodel.setText("");
        txtcost.setText("");
        txtpay.setText("");
        txtbal.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     delete();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrateActionPerformed

    private void txtrateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrateKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtdiscount.requestFocus();
        }
    }//GEN-LAST:event_txtrateKeyPressed

    private void txtdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiscountActionPerformed

    private void txtdiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiscountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtscount.requestFocus();
        }
    }//GEN-LAST:event_txtdiscountKeyPressed

    private void txtscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtscountActionPerformed

    private void txtscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtscountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtscountKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtcost;
    private javax.swing.JLabel txtdate;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JLabel txtid;
    private javax.swing.JTextField txtitemid;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtrate;
    private javax.swing.JTextField txtscount;
    private javax.swing.JLabel txttime;
    private javax.swing.JComboBox<String> txtvendor;
    // End of variables declaration//GEN-END:variables

    private Vector makeData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
