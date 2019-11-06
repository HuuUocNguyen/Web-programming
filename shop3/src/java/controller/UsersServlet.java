package controller;

import dao.UsersDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import tools.MD5;

public class UsersServlet extends HttpServlet {

    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // command: chỉ huy
        // đăng ký rồi chuyển sang index.jsp sau đó lưu lại session tài khoản để hiển thị
        /*
        String command = request.getParameter("command");
        String url = "";
        switch (command) {
            case "insert":
                Users users = new Users(); // neu khai bao ra ngoai thi se khong luu duoc CSDL
                users.setUserID(new java.util.Date().getTime());
                users.setUserEmail(request.getParameter("email"));
                users.setUserPass(request.getParameter(MD5.encryption("pass")));
                users.setUserRole(false);
                usersDAO.insertUser(users);
                HttpSession session = request.getSession();
                session.setAttribute("user", users);
                url = "/index.jsp";
                break;
            
        }
         
        
        // RequestDispatcher là một interface, việc thực hiện xác định một đối tượng có thể gửi yêu cầu tới bất kỳ tài nguyên nào (như HTML, Image, JSP, Servlet) trên server.
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
         */
        String command = request.getParameter("command");
        String url = "";
        Users users = new Users();
        HttpSession session = request.getSession();
        switch (command) {
            case "insert":
                users.setUserID(new java.util.Date().getTime());
                users.setUserEmail(request.getParameter("email"));
                //users.setUserPass(request.getParameter(MD5.encryption("pass")));
                // neu viet nhu o tren thi request se ma hoa md5 "pass" chu khong phai ma hoa mat khau
                // va khi ma hoa thi se tra ve null, ma o trong csdl thiet ke ban dau la not null
                String pass = request.getParameter("pass");
                String encryptPass = MD5.encryption(pass);
                users.setUserPass(encryptPass);
                users.setUserRole(false);
                boolean checkUser = usersDAO.insertUser(users);
                if (checkUser) {
                    session.setAttribute("user", users);
                    url = "/index.jsp";
                }
                
                else {
                    url = "/register.jsp";
                }
                break;
            case "login":
                String p = request.getParameter("pass");
                String e = MD5.encryption(p);
                Users loginUser = usersDAO.login(request.getParameter("email"), e);
                if(loginUser != null){
                    session.setAttribute("user", users);
                    url = "/index.jsp";
                }
                else{
                    session.setAttribute("error", "Error email or password!");
                    url = "/login.jsp";
                }
                break;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
