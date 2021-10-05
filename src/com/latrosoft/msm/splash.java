
package com.latrosoft.msm;

public class splash extends javax.swing.JFrame {

   
    public splash() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loadnum = new javax.swing.JLabel();
        loadbar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(721, 451));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Loading");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(110, 310, 70, 80);

        loadnum.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        loadnum.setForeground(new java.awt.Color(255, 255, 255));
        loadnum.setText("0");
        jPanel1.add(loadnum);
        loadnum.setBounds(180, 320, 50, 60);

        loadbar.setForeground(new java.awt.Color(204, 51, 0));
        jPanel1.add(loadbar);
        loadbar.setBounds(240, 350, 420, 5);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\LATRO.jpg")); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 721, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      
       splash sl = new splash();
       sl.setVisible(true);
       Login l = new Login();
       
     try{
         
       for(int i=0;i<=100;i++){
           
          
           sl.loadnum.setText(Integer.toString(i)+"%");
           sl.loadbar.setValue(i);
            Thread.sleep(50);
          
        if(i ==100){
           sl.setVisible(false);
           l.setVisible(true);
            
        }    
       }}  
     catch(Exception e){      
     }
       
       
     
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar loadbar;
    private javax.swing.JLabel loadnum;
    // End of variables declaration//GEN-END:variables
}
