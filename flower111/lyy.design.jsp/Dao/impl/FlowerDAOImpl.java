package Dao.impl;


import Dao.Flower;
import Dao.FlowerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl implements FlowerDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/flower?useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveFlower(Flower flower) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO flower (flower_name, flower_price, flower_content, stock) VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, flower.getFlower_name());
            pstmt.setDouble(2, flower.getFlower_price());
            pstmt.setString(3, flower.getFlower_content());
            pstmt.setInt(4, flower.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public boolean updateFlower(Flower flower) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "UPDATE flower SET flower_name =?, flower_price =?, flower_content =?, stock =? WHERE flower_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, flower.getFlower_name());
            pstmt.setDouble(2, flower.getFlower_price());
            pstmt.setString(3, flower.getFlower_content());
            pstmt.setInt(4, flower.getStock());
            pstmt.setInt(5, flower.getFlower_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return true;
    }

    @Override
    public void deleteFlower(int flowerId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "DELETE FROM flower WHERE flower_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, flowerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public Flower getFlowerById(int flowerId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM flower WHERE flower_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, flowerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Flower(
                        rs.getInt("flower_id"),
                        rs.getString("flower_name"),
                        rs.getDouble("flower_price"),
                        rs.getString("flower_content"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return null;
    }

    @Override
    public List<Flower> getAllFlowers(int page, int pageSize) {
        List<Flower> flowerList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM flower ";
            pstmt = conn.prepareStatement(sql);
            int offset = (page - 1) * pageSize;
            int limit = pageSize;
            if (offset < 0 || limit <= 0) {
                // 处理无效参数的情况，例如返回空列表或者抛出异常
                return new ArrayList<>();
            }
//            pstmt.setInt(1, offset);
//            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Flower flower=new Flower();
                flower.setFlower_id(rs.getInt("flower_id"));
                flower.setFlower_name(rs.getString("flower_name"));
                flower.setFlower_price(rs.getDouble("flower_price"));
                flower.setFlower_content(rs.getString("flower_content"));
                flower.setStock(rs.getInt("stock"));
                flowerList.add(flower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowerList;
    }

    @Override
    public int getTotalFlowers() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT COUNT(*) FROM flower";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return 0;
    }

    @Override
    public boolean addFlower(Flower flower) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO flower (flower_name, flower_price, flower_content, stock) VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, flower.getFlower_name());
            pstmt.setDouble(2, flower.getFlower_price());
            pstmt.setString(3, flower.getFlower_content());
            pstmt.setInt(4, flower.getStock());
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
    public List<Flower> searchFlowers(String keyword) {
        List<Flower> flowerList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            // 使用LIKE关键字进行模糊查询，%keyword%表示在字段中包含keyword的记录
            String sql = "SELECT * FROM flower WHERE flower_name LIKE? OR flower_content LIKE?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                flowerList.add(new Flower(
                        rs.getInt("flower_id"),
                        rs.getString("flower_name"),
                        rs.getDouble("flower_price"),
                        rs.getString("flower_content"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return flowerList;
    }

    // 获取数据库连接
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // 关闭数据库连接资源
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