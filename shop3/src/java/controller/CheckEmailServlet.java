package controller;

import dao.UsersDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckEmailServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // đã tồn tại email đánh dấu x - ko để trùng
        if (usersDAO.checkEmail(request.getParameter("username"))) {
            response.getWriter().write("<img src=\"img/not-available.png\" />");
        }
        // khác thì cho đăng ký
        else {
            response.getWriter().write("<img src=\"img/available.png\" />");
        }
    }
}
