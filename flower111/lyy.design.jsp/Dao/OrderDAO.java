package Dao;

import java.util.List;

public interface OrderDAO {
    // 保存订单
    void saveOrder(Order order);

    // 更新订单状态
    boolean updateOrder(Order order);

    // 根据用户ID获取订单列表
    List<Order> getOrdersByUserId(int userId);

    void deleteOrder(int orderId);

    // 根据订单ID获取订单
    Order getOrderById(int orderId);
    public List<Order> getAllOrders(int page, int pageSize) ;
    boolean addOrder(Order order);
    List<Order> searchOrders(String keyword);
}