package servlet;

import Dao.User;
import service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        User user = userService.login(username, password);

        if (user!= null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            // 根据user_flag字段的值来判断是管理员还是普通用户，进而重定向到不同页面
            if ("admin".equals(user.getUser_flag())) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("user.jsp");
            }
        } else {
            response.getWriter().println("<script LANGUAGE='javascript'>alert('用户名或密码错误！');self.location='login.jsp'</script>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captchaInput = request.getParameter("captcha");


        // 从会话中获取生成的验证码
        String captchaInSession = (String) request.getSession().getAttribute("captcha");

        if (captchaInSession!= null && captchaInSession.equalsIgnoreCase(captchaInput)) {
            UserService userService = new UserService();
            User user = userService.login(username, password);

            if (user!= null) {
                HttpSession session = request.getSession(true);

                session.setAttribute("adminUser", user);
                if ("admin".equals(user.getUser_flag())) {
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("user.jsp");
                }
            } else {
                response.getWriter().println("<script LANGUAGE='javascript'>alert('用户名或密码错误！');self.location='login.jsp'</script>");
            }
        } else {
            response.getWriter().println("<script LANGUAGE='javascript'>alert('验证码错误！');self.location='login.jsp'</script>");
        }
    }
}