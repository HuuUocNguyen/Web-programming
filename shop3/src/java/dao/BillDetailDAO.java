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
import model.BillDetail;

/**
 *
 * @author User
 */
public class BillDetailDAO {
    public void insertBillDetail(BillDetail bd) throws SQLException{
        Connection conn = DBConnect.getConnection();
        String sql = "insert into bill_detail value(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setLong(1, bd.getBillDetailID());
        ps.setLong(2, bd.getBillID());
        ps.setLong(3, bd.getProductID());
        ps.setDouble(4, bd.getPrice());
        ps.setInt(5, bd.getQuantity());
        ps.executeUpdate();
    }
    public static void main(String[] args) throws SQLException {
        //new BillDetailDAO().insertBillDetail(new BillDetail(0, 0, 0, 0, 0));
    }
}
