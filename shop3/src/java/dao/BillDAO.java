/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;

/**
 *
 * @author User
 */
public class BillDAO {
    public void insertBill(Bill bill){
        Connection conn = DBConnect.getConnection();
        String sql = "insert into bill value(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setLong(1, bill.getBillID());
            ps.setLong(2, bill.getUserID());
            ps.setDouble(3, bill.getTotal());
            ps.setString(4, bill.getPayment());
            ps.setString(5, bill.getAddress());
            ps.setTimestamp(6, bill.getDate());
            ps.executeUpdate();
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) {
        new BillDAO().insertBill(new Bill(0, 0, 0, "s", "s", new Timestamp(new Date().getTime())));
    }
}
