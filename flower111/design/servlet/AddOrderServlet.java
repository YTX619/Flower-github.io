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
        String userId = request.getParameter("userId");
        String flowerId = request.getParameter("flowerId");
        String orderNum = request.getParameter("orderNum");
        String orderNotice = request.getParameter("orderNotice");
        String orderStatus = request.getParameter("orderStatus");

        // create Order
        Order newOrder = new Order();
        try {
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
        boolean success = orderDAO.addOrder(newOrder);

        if (success) {
            HttpSession session = request.getSession(false);
            if (session!= null) {
                User user = (User) session.getAttribute("adminUser");
                if (user!= null) {
                    if ("admin".equals(user.getUser_flag())) {
                        // to admin.jsp
                        response.sendRedirect("admin.jsp");
                    } else {
                        // to user.jsp
                        response.sendRedirect("user.jsp");
                    }
                }
            }
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }
}