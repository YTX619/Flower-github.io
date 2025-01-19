package servlet;

import Dao.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String user_flag=request.getParameter("user_flag");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealname(realname);
        user.setSex(sex);
        user.setEmail(email);
        user.setUser_flag(user_flag);

        UserService userService = new UserService();
        if (userService.register(user)) {
            response.getWriter().println("<script LANGUAGE='javascript'>alert('注册成功！');self.location='login.jsp'</script>");
        } else {
            response.getWriter().println("<script LANGUAGE='javascript'>alert('该用户已存在');self.location='register.jsp'</script>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}