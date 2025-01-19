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
        // 从前端表单获取用户输入的各项信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");

        // 创建一个新的User对象，用于封装要添加的用户信息
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRealname(realname);
        newUser.setSex(sex);
        newUser.setEmail(email);

        // 调用UserService的方法来处理添加用户的业务逻辑
        UserService userService = new UserService();
        boolean success = userService.addUser(newUser);

        if (success) {
            // 如果添加成功，重定向到用户管理页面，并添加提示信息到请求作用域，用于在页面显示添加成功的提示
            request.setAttribute("message", "用户添加成功！");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        } else {
            // 如果添加失败，重定向到用户管理页面，并添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "用户添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        }
    }
}