package servlet;

import Dao.Flower;
import Dao.FlowerDAO;
import Dao.Order;
import Dao.OrderDAO;
import Dao.impl.FlowerDAOImpl;
import Dao.impl.OrderDAOImpl;
import service.FlowerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditOrderServlet")
public class EditOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get order by id
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // search order by id
        OrderDAO orderDAO = new OrderDAOImpl();
        Order order = orderDAO.getOrderById(orderId);

        // set order detai
        request.setAttribute("order", order);

        request.getRequestDispatcher("editOrder.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        String userId = request.getParameter("userId");
        String flowerId = request.getParameter("flowerId");
        String orderNum = request.getParameter("orderNum");
        String orderNotice= request.getParameter("orderNotice");
        String orderStatus= request.getParameter("orderStatus");

        Order newOrder = new Order();
        try {
            newOrder.setOrder_id(Integer.parseInt(orderId));
            newOrder.setUser_id(Integer.parseInt(userId));
            newOrder.setFlower_id(Integer.parseInt(flowerId));
            newOrder.setOrder_num(Integer.parseInt(orderNum));
            newOrder.setOrder_notice(orderNotice);
            newOrder.setOrder_status(orderStatus);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
            return;
        }

        OrderDAO orderDAO = new OrderDAOImpl();
        boolean success = orderDAO.updateOrder(newOrder);

        if (success) {

            request.setAttribute("message", "success");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }
}