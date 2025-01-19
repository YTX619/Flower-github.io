package Dao.impl;



import Dao.Address;
import Dao.AddressDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/flower?useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ytx20020619";

    @Override
    public void saveAddress(Address address) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO address (user_id, address, tel, mobile, notice) VALUES (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, address.getUser_id());
            pstmt.setString(2, address.getAddress());
            pstmt.setString(3, address.getTel());
            pstmt.setString(4, address.getMobile());
            pstmt.setString(5, address.getNotice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void updateAddress(Address address) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "UPDATE address SET address =?, tel =?, mobile =?, notice =? WHERE address_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, address.getAddress());
            pstmt.setString(2, address.getTel());
            pstmt.setString(3, address.getMobile());
            pstmt.setString(4, address.getNotice());
            pstmt.setInt(5, address.getAddress_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void deleteAddress(int addressId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "DELETE FROM address WHERE address_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, addressId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public List<Address> getAddressesByUserId(int userId) {
        List<Address> addressList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM address WHERE user_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                addressList.add(new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("address"),
                        rs.getString("tel"),
                        rs.getString("mobile"),
                        rs.getString("notice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return addressList;
    }

    @Override
    public Address getAddressById(int addressId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM address WHERE address_id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, addressId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("address"),
                        rs.getString("tel"),
                        rs.getString("mobile"),
                        rs.getString("notice")
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
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}