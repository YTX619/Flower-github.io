package service;

import Dao.Order;
import Dao.OrderDAO;
import Dao.impl.OrderDAOImpl;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;


    public OrderService() {
        orderDAO = new OrderDAOImpl();
    }

    // 添加订单
    public boolean addOrder(Order order) {
        orderDAO.saveOrder(order);
        return true;
    }

    public List<Order> getAllOrders(int page, int pageSize) {
        return orderDAO.getAllOrders(page, pageSize);// 这里暂时假设获取所有订单，实际可能需要根据用户权限等获取相应订单
    }

    // 更新订单状态
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }
    // 根据用户ID获取订单列表
    public List<Order> getOrdersByUserId(int userId) {
        return orderDAO.getOrdersByUserId(userId);
    }
    public List<Order> searchOrders(String keyword) {
        return orderDAO.searchOrders(keyword);
    }

    // 根据订单ID获取订单
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }
    public List<Order> searchFlowers(String keyword) {
        return orderDAO.searchOrders(keyword);
    }
}