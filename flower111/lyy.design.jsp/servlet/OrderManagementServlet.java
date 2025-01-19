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

        // 每页显示的订单数量，可根据实际需求调整
        int pageSize = 10;

        // 调用OrderService获取订单列表数据
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getAllOrders(page, pageSize);// 这里暂时假设获取所有订单，实际可能需要根据用户权限等获取相应订单


        // 将订单列表数据和订单总数设置到请求属性中
        request.setAttribute("orderList", orderList);


        // 转发请求到orderManagement.jsp页面进行展示
        request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单提交的操作类型，这里可以根据实际情况扩展更多操作类型判断
        String operation = request.getParameter("operation");
        if ("addOrder".equals(operation)) {
            // 如果是添加订单操作，调用添加订单逻辑处理
            handleAddOrder(request, response);
        } else {
            // 对于其他未识别的操作或者没有指定操作类型的情况，可以进行相应处理，比如提示错误信息等
            request.setAttribute("errorMessage", "不支持的操作类型");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }

    private void handleAddOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端表单获取订单相关信息
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int flowerId = Integer.parseInt(request.getParameter("flower_id"));
        int orderNum = Integer.parseInt(request.getParameter("order_num"));
        String orderNotice = request.getParameter("order_notice");
        String orderStatus = request.getParameter("order_status");

        // 创建一个Order对象，用于封装要添加的订单信息
        Order newOrder = new Order();
        newOrder.setUser_id(userId);
        newOrder.setFlower_id(flowerId);
        newOrder.setOrder_num(orderNum);
        newOrder.setOrder_notice(orderNotice);
        newOrder.setOrder_status(orderStatus);

        // 调用OrderService的方法来处理添加订单的业务逻辑
        OrderService orderService = new OrderService();
        boolean success = orderService.addOrder(newOrder);

        if (success) {
            // 如果添加成功，重定向到当前OrderManagementServlet（刷新页面，同时可以获取最新的订单列表数据展示）
            // 也可以添加提示信息到请求作用域，用于在页面显示添加成功的提示，这里重定向方式刷新页面暂不设置提示信息
            response.sendRedirect("OrderManagementServlet");
        } else {
            // 如果添加失败，添加错误提示信息到请求作用域，用于在页面显示添加失败的提示
            request.setAttribute("errorMessage", "订单添加失败，请检查输入信息或联系管理员。");
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
        }
    }
}