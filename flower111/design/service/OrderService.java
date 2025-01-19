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

    // add order
    public boolean addOrder(Order order) {
        orderDAO.saveOrder(order);
        return true;
    }

    public List<Order> getAllOrders(int page, int pageSize) {
        return orderDAO.getAllOrders(page, pageSize);
    }

    //update order
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }
    // get orders
    public List<Order> getOrdersByUserId(int userId) {
        return orderDAO.getOrdersByUserId(userId);
    }
    public List<Order> searchOrders(String keyword) {
        return orderDAO.searchOrders(keyword);
    }

    //get order by id
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }
    public List<Order> searchFlowers(String keyword) {
        return orderDAO.searchOrders(keyword);
    }
}