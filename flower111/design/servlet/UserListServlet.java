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

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = 1;
        int pageSize = 10;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        UserService userService = new UserService();

        if (keyword!= null &&!keyword.isEmpty()) {
            List<User> userList =userService.searchUsers(keyword);
            request.setAttribute("userList", userList);
            request.setAttribute("keyword", keyword);
        } else {
            List<User> userList = userService.getAllUsers(page, pageSize);
            int totalUsers = userService.getTotalUsers();
            int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

            request.setAttribute("userList",userList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        }

        request.getRequestDispatcher("userManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}