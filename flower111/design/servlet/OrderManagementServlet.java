package servlet;

import Dao.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrderManagementServlet")
public class OrderManagementServlet extends HttpServlet {
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

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getAllOrders(page, pageSize);



        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if ("addOrder".equals(operation)) {
            handleAddOrder(request, response);
        } else {
            request.setAttribute("errorMessage", "error");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }

    private void handleAddOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int flowerId = Integer.parseInt(request.getParameter("flower_id"));
        int orderNum = Integer.parseInt(request.getParameter("order_num"));
        String orderNotice = request.getParameter("order_notice");
        String orderStatus = request.getParameter("order_status");

        Order newOrder = new Order();
        newOrder.setUser_id(userId);
        newOrder.setFlower_id(flowerId);
        newOrder.setOrder_num(orderNum);
        newOrder.setOrder_notice(orderNotice);
        newOrder.setOrder_status(orderStatus);

        OrderService orderService = new OrderService();
        boolean success = orderService.addOrder(newOrder);

        if (success) {
            response.sendRedirect("OrderManagementServlet");
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }
}