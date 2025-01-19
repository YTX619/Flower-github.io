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
        // 获取当前页码，默认为1，如果前端有传递页码参数则进行更新
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam!= null &&!pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // 如果参数格式转换失败，保持默认页码1
            }
        }

        // 每页显示的用户数量，可根据实际需求调整
        int pageSize = 10;

        // 调用UserService获取用户列表数据
        UserService userService = new UserService();
        List<User> userList = userService.getAllUsers(page, pageSize);

        // 获取用户总数，用于分页相关功能展示（比如计算总页数等）
        int totalUsers = userService.getTotalUsers();

        // 将用户列表数据和用户总数设置到请求作用域，以便在userManagement.jsp页面中获取使用
        request.setAttribute("userList", userList);
        request.setAttribute("totalUsers", totalUsers);

        // 转发请求到userManagement.jsp页面进行展示
        request.getRequestDispatcher("userManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单提交的操作类型，这里可以根据实际情况扩展更多操作类型判断
        String operation = request.getParameter("operation");
        if ("addUser".equals(operation)) {
            // 如果是添加用户操作，调用添加用户逻辑处理
            handleAddUser(request, response);
        } else if ("searchUser".equals(operation)) {
            // 如果是搜索用户操作，调用搜索用户逻辑处理
            handleSearchUser(request, response);
        }else {
            // 对于其他未识别的操作或者没有指定操作类型的情况，可以进行相应处理，比如提示错误信息等
            request.setAttribute("errorMessage", "不支持的操作类型");
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
            // 如果添加成功，重定向到当前UserManagementServlet（刷新页面，同时可以获取最新的用户列表数据展示）
            // 也可以添加提示信息到请求作用域，用于在页面显示添加成功的提示，这里重定向方式刷新页面暂不设置提示信息
            response.sendRedirect("UserManagementServlet");
        } else {
            // 如果添加失败，添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "用户添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("userManagement.jsp").forward(request, response);
        }
    }
}