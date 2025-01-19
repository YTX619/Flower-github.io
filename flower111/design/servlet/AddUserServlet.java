package servlet;

import Dao.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRealname(realname);
        newUser.setSex(sex);
        newUser.setEmail(email);

        UserService userService = new UserService();
        boolean success = userService.addUser(newUser);

        if (success) {
            request.setAttribute("message", "success");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        }
    }
}