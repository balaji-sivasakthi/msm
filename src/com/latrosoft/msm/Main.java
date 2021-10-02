package com.latrosoft.msm;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends javax.swing.JFrame {

   
    public Main() {
        initComponents();
        jMenuItem3.setEnabled(false);
    }

    public void CusReport()
    {
        CustomerReport cusreport=new CustomerReport();
        cusreport.setVisible(true);
        jDesktopPane1.add(cusreport);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Master = new javax.swing.JMenu();
        Business_info = new javax.swing.JMenuItem();
        Add_stock = new javax.swing.JMenuItem();
        Add_items = new javax.swing.JMenuItem();
        Add_vendors = new javax.swing.JMenuItem();
        Add_users = new javax.swing.JMenuItem();
        Sales = new javax.swing.JMenu();
        Sale = new javax.swing.JMenuItem();
        Return_item = new javax.swing.JMenu();
        Return = new javax.swing.JMenuItem();
        Report = new javax.swing.JMenu();
        Stock_report = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        Analytics = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1362, 712));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1362, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );

        Master.setText("Master");
        Master.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasterActionPerformed(evt);
            }
        });

        Business_info.setText("Business info");
        Business_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Business_infoActionPerformed(evt);
            }
        });
        Master.add(Business_info);

        Add_stock.setText("Purchase");
        Add_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_stockActionPerformed(evt);
            }
        });
        Master.add(Add_stock);

        Add_items.setText("Items");
        Add_items.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_itemsActionPerformed(evt);
            }
        });
        Master.add(Add_items);

        Add_vendors.setText("Add vendors");
        Add_vendors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_vendorsActionPerformed(evt);
            }
        });
        Master.add(Add_vendors);

        Add_users.setText("Add users");
        Add_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_usersActionPerformed(evt);
            }
        });
        Master.add(Add_users);

        jMenuBar1.add(Master);

        Sales.setText("Sales");
        Sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesActionPerformed(evt);
            }
        });

        Sale.setText("Sale");
        Sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleActionPerformed(evt);
            }
        });
        Sales.add(Sale);

        jMenuBar1.add(Sales);

        Return_item.setText("Return item");
        Return_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Return_itemActionPerformed(evt);
            }
        });

        Return.setText("Return");
        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        Return_item.add(Return);

        jMenuBar1.add(Return_item);

        Report.setText("Report");
        Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportActionPerformed(evt);
            }
        });

        Stock_report.setText("Report");
        Stock_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stock_reportActionPerformed(evt);
            }
        });
        Report.add(Stock_report);

        jMenuItem2.setText("Customer Report");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Report.add(jMenuItem2);

        jMenuBar1.add(Report);

        Analytics.setText("Analytics");
        Analytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalyticsActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Analytics");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Analytics.add(jMenuItem3);

        jMenuBar1.add(Analytics);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Stock_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stock_reportActionPerformed
       Report streport = new  Report();
       this.add( streport);
       streport.setVisible(true);
        jDesktopPane1.add(streport);
        
         if(role != "Admin"){
                 this.add(streport);
                 streport.setVisible(false);
         }
    }//GEN-LAST:event_Stock_reportActionPerformed

    private void Business_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Business_infoActionPerformed
        try {
            Businessinfo binfo = new  Businessinfo();
            this.add(binfo);
            binfo.setVisible(true);
            jDesktopPane1.add(binfo);
            
                 if(role != "Admin"){
                 this.add(binfo);
                 binfo.setVisible(false);
               
          } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Business_infoActionPerformed

    private void Add_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_stockActionPerformed
        try {
            Purchase st = new Purchase();
            this.add(st);
            st.setVisible(true);
            jDesktopPane1.add(st);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Add_stockActionPerformed

    private void MasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasterActionPerformed
     
    }//GEN-LAST:event_MasterActionPerformed

    private void Add_itemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_itemsActionPerformed
        try {
            Items it = new Items();
            this.add(it);
            it.setVisible(true);
            jDesktopPane1.add(it);
           
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Add_itemsActionPerformed

    private void Add_vendorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_vendorsActionPerformed
       
        try {
             Vendors vn=new Vendors();
            
              vn.setVisible(true);
                this.add(vn);
                jDesktopPane1.add(vn);
     
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }//GEN-LAST:event_Add_vendorsActionPerformed

    private void Add_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_usersActionPerformed
  try {

            Users ur = new   Users();
            this.add(ur);
            ur.setVisible(true); 
            jDesktopPane1.add(ur);
          
          if(role != "Admin"){
                 this.add(ur);
                 ur.setVisible(false);
               
          } 
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }//GEN-LAST:event_Add_usersActionPerformed
    String role;
     public Main(String user){
     
        initComponents();
       
         this. role = user;
       
    }
    private void ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportActionPerformed
       
    }//GEN-LAST:event_ReportActionPerformed

    private void SalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesActionPerformed
         
    }//GEN-LAST:event_SalesActionPerformed

    private void Return_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Return_itemActionPerformed
       
    }//GEN-LAST:event_Return_itemActionPerformed

    private void AnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalyticsActionPerformed
     
    }//GEN-LAST:event_AnalyticsActionPerformed

    private void SaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleActionPerformed
        try {
            Sales sl = new  Sales();
            this.add( sl);
            sl.setVisible(true);
             jDesktopPane1.add(sl);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaleActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
      try {

      Returnitems ri = new Returnitems();
      this.add( ri);
      ri.setVisible(true);
       jDesktopPane1.add(ri);

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_ReturnActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Analytics al = new Analytics();
            this.add( al);
            al.setVisible(true);
             jDesktopPane1.add(al);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       this.CusReport();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

     public static void Main(String args[]){
     
     
     }
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Add_items;
    private javax.swing.JMenuItem Add_stock;
    private javax.swing.JMenuItem Add_users;
    private javax.swing.JMenuItem Add_vendors;
    private javax.swing.JMenu Analytics;
    private javax.swing.JMenuItem Business_info;
    private javax.swing.JMenu Master;
    private javax.swing.JMenu Report;
    private javax.swing.JMenuItem Return;
    private javax.swing.JMenu Return_item;
    private javax.swing.JMenuItem Sale;
    private javax.swing.JMenu Sales;
    private javax.swing.JMenuItem Stock_report;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables

}
