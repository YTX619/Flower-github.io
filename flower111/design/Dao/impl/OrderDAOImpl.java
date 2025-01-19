package Dao.impl;



import Dao.Flower;
import Dao.Order;
import Dao.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/flower?useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ytx20020619";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Order> searchOrders(String keyword) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM orderflower WHERE order_notice LIKE?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flower_id"),
                        rs.getInt("order_num"),
                        rs.getString("order_notice"),
                        rs.getString("order_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrders(int page, int pageSize) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM orderflower ";
            pstmt = conn.prepareStatement(sql);
            int offset = (page - 1) * pageSize;
            int limit = pageSize;
            if (offset < 0 || limit <= 0) {

                return new ArrayList<>();
            }
//            pstmt.setInt(1, offset);
//            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order=new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFlower_id(rs.getInt("flower_id"));
                order.setOrder_num(rs.getInt("order_num"));
                order.setOrder_notice(rs.getString("order_notice"));
                order.setOrder_status(rs.getString("order_status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }


    @Override
    public void deleteOrder(int orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "DELETE FROM orderflower WHERE order_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void saveOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO orderflower (user_id, flower_id, order_num, order_notice, order_status) VALUES (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getFlower_id());
            pstmt.setInt(3, order.getOrder_num());
            pstmt.setString(4, order.getOrder_notice());
            pstmt.setString(5, order.getOrder_status());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "UPDATE orderflower SET user_id =?, flower_id =?, order_num =?, order_notice =? ,order_status=? WHERE order_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getFlower_id());
            pstmt.setInt(3,order.getOrder_num());
            pstmt.setString(4, order.getOrder_notice());
            pstmt.setString(5,order.getOrder_status());
            pstmt.setInt(6,order.getOrder_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return true;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM orderflower WHERE user_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flower_id"),
                        rs.getInt("order_num"),
                        rs.getString("order_notice"),
                        rs.getString("order_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return orderList;
    }

    @Override
    public boolean addOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO orderflower (user_id, flower_id, order_num, order_notice, order_status) VALUES (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getFlower_id());
            pstmt.setInt(3, order.getOrder_num());
            pstmt.setString(4, order.getOrder_notice());
            pstmt.setString(5, order.getOrder_status());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }


    @Override
    public Order getOrderById(int orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM orderflower WHERE order_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flower_id"),
                        rs.getInt("order_num"),
                        rs.getString("order_notice"),
                        rs.getString("order_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return null;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs!= null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt!= null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}