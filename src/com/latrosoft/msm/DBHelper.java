/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latrosoft.msm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author balaji
 */
public class DBHelper {

    String dbUrl = "jdbc:mysql://localhost/mobile_erp_system";
    String username = "root";
    String password = "";
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement s;
    PreparedStatement pst1;
    PreparedStatement pst2;
    
    
    public DBHelper() throws ClassNotFoundException,SQLException{
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, username, password);
    
    }
    
    public ResultSet getData(String query) throws ClassNotFoundException,SQLException {

            if (con != null) {
                System.out.println("Db connected!");
            }
            
            s = con.createStatement();
            rs = s.executeQuery(query);
            rs.next();
            return rs;
            

    }
    
    public PreparedStatement setData(String query) throws SQLException,ClassNotFoundException{
            if (con != null) {
                System.out.println("Db connected!");
            }else{
                 System.out.println("Db Not connected!");
            
            }
            pst =  con.prepareStatement(query);
            return pst;
    
    }
     
}
