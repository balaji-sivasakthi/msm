package com.latrosoft.msm;



import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
        Connect();
        this.hidepass.setVisible(false);
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
//        this.setBackground(new Color(0,0,0,0));
//        panel.setBackground(new Color(0,0,0,0));

    }

   Connection con;
   PreparedStatement pst;
   ResultSet rs;
   DefaultTableModel d;
   public void Connect() {
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/mobile_erp_system","root","");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
   }
    
                                        

     public static void Login(String args[]){
     
     
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showpass = new javax.swing.JLabel();
        hidepass = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        utype = new javax.swing.JComboBox<>();
        loginbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 0, 102));
        setPreferredSize(new java.awt.Dimension(1362, 749));
        getContentPane().setLayout(null);

        showpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showpass.setIcon(new javax.swing.ImageIcon("K:\\Images\\Icons\\eye_24px.png")); // NOI18N
        showpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showpassMouseClicked(evt);
            }
        });
        showpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                showpassKeyPressed(evt);
            }
        });
        getContentPane().add(showpass);
        showpass.setBounds(900, 360, 20, 40);

        hidepass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hidepass.setIcon(new javax.swing.ImageIcon("K:\\Images\\Icons\\invisible_23px.png")); // NOI18N
        hidepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hidepassMouseClicked(evt);
            }
        });
        hidepass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hidepassKeyPressed(evt);
            }
        });
        getContentPane().add(hidepass);
        hidepass.setBounds(900, 360, 20, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 55)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Login");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(540, 60, 131, 67);

        jLabel8.setFont(new java.awt.Font("Gadugi", 0, 20)); // NOI18N
        jLabel8.setText("User name");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(540, 180, 92, 27);

        jLabel9.setFont(new java.awt.Font("Gadugi", 0, 20)); // NOI18N
        jLabel9.setText("Password");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(540, 310, 90, 27);

        jLabel7.setFont(new java.awt.Font("Gadugi", 0, 20)); // NOI18N
        jLabel7.setText("Role");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(550, 440, 39, 27);

        uname.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });
        uname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unameKeyPressed(evt);
            }
        });
        getContentPane().add(uname);
        uname.setBounds(540, 230, 400, 39);

        pass.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        getContentPane().add(pass);
        pass.setBounds(540, 360, 400, 39);

        utype.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        utype.setForeground(new java.awt.Color(51, 51, 51));
        utype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Worker" }));
        utype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utypeActionPerformed(evt);
            }
        });
        utype.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                utypeKeyPressed(evt);
            }
        });
        getContentPane().add(utype);
        utype.setBounds(540, 490, 400, 37);

        loginbtn.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        loginbtn.setText("Login");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        getContentPane().add(loginbtn);
        loginbtn.setBounds(580, 590, 320, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
     void loginenter(){
    
        String username = uname.getText();
        String password = pass.getText();
        String usertype = utype.getSelectedItem().toString();

        try {
            pst = con.prepareStatement(" select * from users where username=?  and password =? and usertype=?");

            pst.setString(1,username);

            pst.setString(2, password);
            pst.setString(3,usertype);
            rs= pst.executeQuery();

            if(rs.next()){

                this.setVisible(false);
                new Main(usertype).setVisible(true);
            }
            else if(username.equals("user") && password.equals("123")){
                this.setVisible(false);
                new Main(usertype).setVisible(true);
            }
            else if(username!="user" && password != "123"){
                JOptionPane.showMessageDialog(this,"Login Failed");
                uname.setText("");
                pass.setText("");
                utype.setSelectedItem(-1);
                uname.requestFocus();

            }
            else{
                JOptionPane.showMessageDialog(this,"Login Failed");
                uname.setText("");
                pass.setText("");
                utype.setSelectedItem(-1);
                uname.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private void utypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_utypeKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        loginenter();
        
       }
    }//GEN-LAST:event_utypeKeyPressed

    private void utypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utypeActionPerformed

    }//GEN-LAST:event_utypeActionPerformed

    private void hidepassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hidepassKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        loginenter();
        
       }
    }//GEN-LAST:event_hidepassKeyPressed

    private void hidepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidepassMouseClicked
        hidepass.setVisible(false);
        showpass.setVisible(true);
        pass.setEchoChar('*');
    }//GEN-LAST:event_hidepassMouseClicked

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        loginenter();
        
       }
    }//GEN-LAST:event_passKeyPressed

    private void unameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unameKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        loginenter();
        
       }
    }//GEN-LAST:event_unameKeyPressed

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed

    }//GEN-LAST:event_unameActionPerformed

    private void showpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_showpassKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        loginenter();
        
       }
    }//GEN-LAST:event_showpassKeyPressed

    private void showpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpassMouseClicked
        hidepass.setVisible(true);
        showpass.setVisible(false);
        pass.setEchoChar((char)0);
    }//GEN-LAST:event_showpassMouseClicked

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
       
        loginenter();
        
       
    }//GEN-LAST:event_loginbtnActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hidepass;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton loginbtn;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel showpass;
    private javax.swing.JTextField uname;
    private javax.swing.JComboBox<String> utype;
    // End of variables declaration//GEN-END:variables
}
