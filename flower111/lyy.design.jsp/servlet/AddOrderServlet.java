package servlet;



import Dao.Order;
import Dao.OrderDAO;
import Dao.User;
import Dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端表单（addOrder.jsp）获取订单相关信息
        String userId = request.getParameter("userId");
        String flowerId = request.getParameter("flowerId");
        String orderNum = request.getParameter("orderNum");
        String orderNotice = request.getParameter("orderNotice");
        String orderStatus = request.getParameter("orderStatus");

        // 创建一个Order对象，用于封装要添加的订单信息
        Order newOrder = new Order();
        try {
            newOrder.setUser_id(Integer.parseInt(userId));
            newOrder.setFlower_id(Integer.parseInt(flowerId));
            newOrder.setOrder_num(Integer.parseInt(orderNum));
            newOrder.setOrder_notice(orderNotice);
            newOrder.setOrder_status(orderStatus);
        } catch (NumberFormatException e) {
            // 如果参数转换整数出现异常，说明输入的数据格式有误，设置错误提示信息并转发到订单管理页面展示错误
            request.setAttribute("errorMessage", "订单信息输入格式有误，请检查输入的数字类型字段是否正确。");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
            return;
        }

        // 调用OrderDAO的方法来将订单信息插入数据库
        OrderDAO orderDAO = new OrderDAOImpl();
        boolean success = orderDAO.addOrder(newOrder);

        if (success) {
            // 获取当前登录用户的信息（假设用户信息存储在会话中）
            HttpSession session = request.getSession(false);
            if (session!= null) {
                User user = (User) session.getAttribute("adminUser");
                if (user!= null) {
                    if ("admin".equals(user.getUser_flag())) {
                        // 如果是管理员，重定向到admin.jsp
                        response.sendRedirect("admin.jsp");
                    } else {
                        // 如果是普通用户，重定向到user.jsp
                        response.sendRedirect("user.jsp");
                    }
                }
            }
        } else {
            // 如果添加失败，重定向到订单管理页面，并添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "订单添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }
}