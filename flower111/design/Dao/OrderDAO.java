package Dao;

import java.util.List;

public interface OrderDAO {

    void saveOrder(Order order);


    boolean updateOrder(Order order);


    List<Order> getOrdersByUserId(int userId);

    void deleteOrder(int orderId);


    Order getOrderById(int orderId);
    public List<Order> getAllOrders(int page, int pageSize) ;
    boolean addOrder(Order order);
    List<Order> searchOrders(String keyword);
}