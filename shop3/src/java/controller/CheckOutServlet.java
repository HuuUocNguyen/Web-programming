/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDAO;
import dao.BillDetailDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BillDetail;
import model.Cart;
import model.Item;
import model.Users;
import tools.SendMail;

/**
 *
 * @author User
 */
public class CheckOutServlet extends HttpServlet {

    private BillDAO billDAO = new BillDAO();
    private BillDetailDAO billDetailDAO = new BillDetailDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String payment = request.getParameter("payment");
        String address = request.getParameter("address");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Users users = (Users) session.getAttribute("user");
        try {
            long ID = new Date().getTime();
            Bill bill = new Bill();
            bill.setBillID(ID);
            bill.setAddress(address);
            bill.setPayment(payment);
            bill.setUserID(users.getUserID());
            bill.setDate(new Timestamp(new Date().getTime()));
            bill.setTotal(cart.totalCart());
            billDAO.insertBill(bill);
            for(Map.Entry<Long, Item> list:cart.getCartItems().entrySet()){
                billDetailDAO.insertBillDetail(new BillDetail(ID, ID, list.getValue().getProduct().getProductID(), list.getValue().getProduct().getProductPrice(), list.getValue().getQuantity()));
            }
            SendMail sm = new SendMail();
            sm.sendMail(users.getUserEmail(), "Huu Uoc", "Hello, "+users.getUserEmail()+"\nTotal: "+cart.totalCart());
            cart = new Cart();
            session.setAttribute("cart", cart);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/shop2/index.jsp");
        
    }

}
