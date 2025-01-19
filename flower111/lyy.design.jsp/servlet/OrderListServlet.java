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

@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = 1;
        int pageSize = 10;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // 如果页码参数不是数字，使用默认值1
        }

        OrderService orderService = new OrderService();

        if (keyword!= null &&!keyword.isEmpty()) {
            // 执行模糊查询
            List<Order> orderList = orderService.searchOrders(keyword);
            request.setAttribute("orderList",orderList);
            request.setAttribute("keyword", keyword);
        }

        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}