package servlet;

import Dao.User;
import service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam!= null &&!pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
            }
        }

        int pageSize = 10;

        UserService userService = new UserService();
        List<User> userList = userService.getAllUsers(page, pageSize);

        int totalUsers = userService.getTotalUsers();

        request.setAttribute("userList", userList);
        request.setAttribute("totalUsers", totalUsers);

        request.getRequestDispatcher("userManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if ("addUser".equals(operation)) {
            handleAddUser(request, response);
        } else if ("searchUser".equals(operation)) {
            handleSearchUser(request, response);
        }else {
            request.setAttribute("errorMessage", "error");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        }
    }
    private void handleSearchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        UserService userService = new UserService();
        List<User> userList = userService.searchUsers(keyword);
        request.setAttribute("userList", userList);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("userManagement.jsp").forward(request, response);
    }

    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        // use UserService
        UserService userService = new UserService();
        boolean success = userService.addUser(newUser);

        if (success) {
            response.sendRedirect("UserManagementServlet");
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        }
    }
}