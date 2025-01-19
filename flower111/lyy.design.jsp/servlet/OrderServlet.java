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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int flowerId = Integer.parseInt(request.getParameter("flowerId"));
        int orderNum = Integer.parseInt(request.getParameter("orderNum"));
        String orderNotice = request.getParameter("orderNotice");

        Order order = new Order();
        order.setUser_id(userId);
        order.setFlower_id(flowerId);
        order.setOrder_num(orderNum);
        order.setOrder_notice(orderNotice);
        order.setOrder_status("未付款");

        OrderService orderService = new OrderService();
        orderService.addOrder(order);

        try {
            response.sendRedirect("orderList.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取查询条件
        String keyword = request.getParameter("keyword");
        // 调用OrderService的searchOrders方法进行模糊查询
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.searchOrders(keyword);
        request.setAttribute("orders", orderList);
        request.getRequestDispatcher("order_search.jsp").forward(request, response);
    }
}