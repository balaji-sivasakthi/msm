/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latrosoft.msm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author balaji
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHelper bHelper = new DBHelper();
        ResultSet rs = bHelper.getData("select MAX(itemid) from item");
        System.err.println(rs.getString("MAX(itemid)"));
        
    }
}
