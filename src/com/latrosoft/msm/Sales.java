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
import java.util.Date;
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
public class Sales extends javax.swing.JInternalFrame {
    DBHelper db;
    /**
     * Creates new form StockReport
     */
    public Sales() throws SQLException ,ClassNotFoundException{
          this.db= new DBHelper();
        initComponents();
        Connect();
        autoID();
       
        showDate();
        showTime();
        
    }
     public static void Sales(String args[]){
     
     
     }
       Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    DefaultTableModel df;
    ResultSet rs;

    
    
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
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void BarCode() {

        try {
            String pcode = txtitemid.getText();
            pst = con.prepareStatement("select * from item where itemid = ?");
            pst.setString(1, pcode);
            rs = pst.executeQuery();

            if (rs.next() == false) {
                JOptionPane.showMessageDialog(this, "Item Not Found");

                txtitemid.setText("");
            } else {
                String iname = rs.getString("itemname");
                String brand =rs.getString("brand");
                String model=rs.getString("model");
                String rate = rs.getString("itemrate");
                String discount=rs.getString("discount");
             
                

                txtiname.setText(iname.trim());
                txtbrand.setText(brand.trim());
                txtmodel.setText(model.trim());
                txtrate.setText(rate.trim());
                txtdiscount.setText(discount.trim());
                
                txtcount.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void autoID() throws SQLException,ClassNotFoundException
    {
     
             
            ResultSet rs;
            rs = db.getData("select MAX(salesid) from sales");
            
            rs.getString("MAX(salesid)");
            
            if(rs.getString("MAX(salesid)") == null)
            {
                txtid.setText("SL001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(salesid)").substring(2,rs.getString("MAX(salesid)").length()));
                id++;
                txtid.setText("SL" + String.format("%03d", id));
        
            }
    }
       
    

     public void Sales() {
          
        
         
        try {
            String iid=txtitemid.getText();
            pst=con.prepareStatement("select * from item where itemid=?");
            pst.setString(1, iid);
            rs=pst.executeQuery();
            
            while(rs.next())
            {
                int currentqty;
                currentqty=rs.getInt("stock");
                
             
              int Rate=Integer.parseInt(txtrate.getText());
              int Discount=Integer.parseInt(txtdiscount.getText());
              int StockCount=Integer.parseInt(txtcount.getText());
              int MidRate= Rate *Discount/100;
              int FinalRate=Rate-MidRate;
              int tot=StockCount * FinalRate;
              
              if(StockCount >=  currentqty)
              {
                  JOptionPane.showMessageDialog(this,"Quantity  not enough");
                  txtitemid.setText("");
                  txtiname.setText("");
                  txtcusname.setText("");
                  txtbrand.setText("");
                  txtmodel.setText("");
                  txtimei1.setText("");
                  txtimei2.setText("");
                  txtrate.setText("");
                  txtdiscount.setText("");
                  txtcount.setText("");
                  
              }
              else
              {
                     
         
        df = (DefaultTableModel) jTable1.getModel();
        df.addRow(new Object[]{
            txtitemid.getText().toString(),
            txtiname.getText().toString(),
            txtcusname.getText().toString(),
            txtbrand.getText().toString(),
            txtmodel.getText().toString(),
            txtimei1.getText().toString(),
            txtimei2.getText().toString(),
            txtrate.getText().toString(),
            txtdiscount.getText().toString(),
             txtcount.getText().toString(),
             
            tot
                
                
                

        }
        );
        
        int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 10).toString());
        }
        txtcost.setText(String.valueOf(sum));

        txtitemid.setText("");
         txtiname.setText("");
  
        txtbrand.setText("");
        txtmodel.setText("");
         txtimei1.setText("");
        txtimei2.setText("");
        txtrate.setText("");
        txtdiscount.setText("");
        txtcount.setText("");
               
        }
                  
              }

            }
            
     
       catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }
    
     
    public void add() throws ClassNotFoundException {

        try {

         
        /*    int subtotal=Integer.parseInt(txtprice.getText().trim());
            int payment = Integer.parseInt(txtpay.getText().trim());
            int balance = Integer.parseInt(txtbal.getText().trim());  */
  
           

            
            pst =con.prepareStatement("insert into sales(salesid,cusname,subtotal,payment,balance,date,time)values(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
             String iname;
                 String custname=txtcusname.getText();
                   
            String salesid=txtid.getText();
            long subtotal = Long.parseLong(txtcost.getText());
            long payment = Long.parseLong( txtpay.getText());
            long balance = Long.parseLong(txtbal.getText());
            String date = txtdate.getText();
            String time=txttime1.getText();
            int n=jTable1.getRowCount();
      
              
              
            pst.setString(1,salesid);
           
            pst.setString(2,custname);
            pst.setLong(3, subtotal);
            pst.setLong(4, payment);
            pst.setLong(5, balance);
            pst.setString(6, date);
            pst.setString(7, time);
            

          
            pst.executeUpdate();

          

           

           

            
            pst1 =db.setData("insert into salesitem(salesid,itmid,itemname,customername,brand,model,imei1,imei2,rate,discount,stockcount,total,date,time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            String  itmid;
            String lastid=txtid.getText();
            String itemname;
            String brand;
            String model;
            long imei1;
            long imei2;
            String cusname;
            long rate;
            int discount;
            long  stockcount;
            
     
               
            long Total = 0;

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                 itmid = (String) jTable1.getValueAt(i, 0);
                itemname=(String) jTable1.getValueAt(i, 1);
                cusname=(String)  jTable1.getValueAt(i,2);
                brand =(String) jTable1.getValueAt(i,3);
                model=(String)   jTable1.getValueAt(i, 4);
                imei1=Long.parseLong(jTable1.getValueAt(i,5).toString());
                imei2=Long.parseLong(jTable1.getValueAt(i,6).toString());
                rate=Long.parseLong(jTable1.getValueAt(i, 7).toString()) ;
                discount=Integer.parseInt( jTable1.getValueAt(i, 8).toString());
                stockcount=Long.parseLong(jTable1.getValueAt(i, 9).toString()) ;
                Total=Long.parseLong( jTable1.getValueAt(i, 10).toString());
               
                pst1.setString(1, lastid);
                pst1.setString(2, itmid);
                pst1.setString(3,itemname);
                pst1.setString(4, cusname);
                pst1.setString(5, brand);
                pst1.setString(6,model);
                pst1.setLong(7,imei1);
                pst1.setLong(8, imei2);
                pst1.setLong(9, rate);
                pst1.setInt(10,discount);
                pst1.setLong(11,stockcount);
                pst1.setLong(12,Total);
                pst1.setString(13, date);
                pst1.setString(14, time);
              
                pst1.executeUpdate();

            }

        
            pst2 = db.setData("update item set stock=stock- ? where itemid=?");

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                   itmid = (String) jTable1.getValueAt(i, 0);

                stockcount =Long.parseLong(jTable1.getValueAt(i, 9).toString()); 

                pst2.setLong(1, stockcount);

                pst2.setString(2, itmid);
                pst2.executeUpdate();
                

            }
            JOptionPane.showMessageDialog(this, "Sales Completed");
            DefaultTableModel df=(DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            
                 txtitemid.setText("");
                  txtiname.setText("");
                  txtcusname.setText("");
                  txtbrand.setText("");
                  txtmodel.setText("");
                  txtimei1.setText("");
                  txtimei2.setText("");
                  txtrate.setText("");
                  txtdiscount.setText("");
                  txtcount.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* catch(NumberFormatException nfe)
        {
         System.out.println("Invalid Format");
         JOptionPane.showMessageDialog(this,"Invalid Format");
        }
        */

    }
                

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txttime = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        txttime1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtitemid = new javax.swing.JTextField();
        txtiname = new javax.swing.JTextField();
        txtrate = new javax.swing.JTextField();
        txtcount = new javax.swing.JTextField();
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
        jLabel7 = new javax.swing.JLabel();
        txtbrand = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdiscount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtimei1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtimei2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtcusname = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtid = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 0, 102));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Sales Report");

        txttime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdate.setText("Date");

        txttime1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txttime1.setText("Time");

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Item Code");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Item Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Count");

        txtitemid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtitemid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtitemidKeyPressed(evt);
            }
        });

        txtiname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtrate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
                "Item  Code", "Item Name", "CustomerName", "Brand", "Model", "IMEI 1", "IMEI 2", "Rate", "Discount", "Stock Count", "Total"
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

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Brand");

        txtbrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Model");

        txtmodel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Discount");

        txtdiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiscountActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IMEI 1");

        txtimei1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtimei1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtimei1KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("IMEI 2");

        txtimei2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtimei2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtimei2KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CustomerName");

        txtcusname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcusname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcusnameKeyPressed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtitemid, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtiname, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(422, 422, 422))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(73, 73, 73)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtimei1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtimei2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(21, 21, 21)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtitemid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcusname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtiname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel6))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtimei1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtimei2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(25, 25, 25)
                        .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtbal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("SALES");

        txtid.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtid.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtdate)
                .addGap(60, 60, 60)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(478, 478, 478)
                .addComponent(txttime1)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txttime)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttime1)
                    .addComponent(txtdate)
                    .addComponent(txtid))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 328, Short.MAX_VALUE)
                    .addComponent(txttime)
                    .addGap(0, 329, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtitemidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitemidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BarCode();
            txtcusname.requestFocus();
        }
    }//GEN-LAST:event_txtitemidKeyPressed

    private void txtrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrateActionPerformed

    private void txtcountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Sales();

        txtitemid.setText("");
        txtiname.setText("");
        txtrate.setText("");
        txtcount.setText("");
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
            int payment=Integer.parseInt(txtpay.getText());
            int subtotal=Integer.parseInt(txtcost.getText());
            int balance = payment-subtotal;
            
            txtbal.setText(String.valueOf(balance));
            
            add();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      df=(DefaultTableModel)jTable1.getModel();
     
       int SelectedRowIndex =jTable1.getSelectedRow();
       df.removeRow(SelectedRowIndex);
     
     //  df.setRowCount(i);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      /* df = (DefaultTableModel) jTable1.getModel();

        int selected = jTable1.getSelectedRow();

        
        txtitemid.setText(df.getValueAt(selected, 0).toString());
        txtiname.setText(df.getValueAt(selected, 1).toString());
        txtcusname.setText(df.getValueAt(selected, 2).toString());
        txtbrand.setText(df.getValueAt(selected, 3).toString());
        txtmodel.setText(df.getValueAt(selected, 4).toString());
        txtimei1.setText(df.getValueAt(selected, 5).toString());
        txtimei2.setText(df.getValueAt(selected, 6).toString());
        txtrate.setText(df.getValueAt(selected,7).toString());
        txtdiscount.setText(df.getValueAt(selected,8).toString());
        txtcount.setText(df.getValueAt(selected, 9).toString());
        

        jButton2.setEnabled(false);
*/
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiscountActionPerformed

    private void txtimei1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimei1KeyPressed
     if(evt.getKeyCode()==KeyEvent.VK_ENTER)
     {
         txtimei2.requestFocus();
     }
    }//GEN-LAST:event_txtimei1KeyPressed

    private void txtimei2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimei2KeyPressed
      if(evt.getKeyCode()==KeyEvent.VK_ENTER)
      {
          txtcount.requestFocus();
      }
    }//GEN-LAST:event_txtimei2KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      txtitemid.setText("");
        txtiname.setText("");
        txtbrand.setText("");
        txtmodel.setText("");
        txtimei1.setText("");
        txtimei2.setText("");
        txtrate.setText("");
        txtcount.setText("");
        txtdiscount.setText("");
        txtcost.setText("");
        txtpay.setText("");
        txtbal.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtcountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcountKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtrate.requestFocus();
        }
    }//GEN-LAST:event_txtcountKeyPressed

    private void txtrateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrateMouseClicked
     
    }//GEN-LAST:event_txtrateMouseClicked

    private void txtrateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrateKeyPressed
     if(evt.getKeyCode()==KeyEvent.VK_ENTER)
     {
         txtdiscount.requestFocus();
     }
    }//GEN-LAST:event_txtrateKeyPressed

    private void txtcusnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusnameKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER)
    {
        txtimei1.requestFocus();
    }
    }//GEN-LAST:event_txtcusnameKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtcost;
    private javax.swing.JTextField txtcount;
    private javax.swing.JTextField txtcusname;
    private javax.swing.JLabel txtdate;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JLabel txtid;
    private javax.swing.JTextField txtimei1;
    private javax.swing.JTextField txtimei2;
    private javax.swing.JTextField txtiname;
    private javax.swing.JTextField txtitemid;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtrate;
    private javax.swing.JLabel txttime;
    private javax.swing.JLabel txttime1;
    // End of variables declaration//GEN-END:variables
}
