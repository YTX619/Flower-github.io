<%@ page import="Dao.Order" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <link rel="stylesheet" href="css/admin.css">
</head>

<body>
<%
    Dao.User adminUser = (Dao.User) session.getAttribute("adminUser");
    boolean isAdmin = false;
    if (adminUser!= null && "admin".equals(adminUser.getUser_flag())) {
        isAdmin = true;
    }
%>
<%
    if (isAdmin) {
        // If it's an administrator, show the management return button and hide the user return button
%>
<div id="orderManagementContainer">
    <h2>Order Management</h2>
    <form action="OrderServlet" method="post">
        <input type="text" name="keyword" placeholder="Search Order">
        <input type="submit" value="Search">
    </form>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Flower ID</th>
            <th>Order Quantity</th>
            <th>Order Note</th>
            <th>Order Status</th>
            <th>Operation</th>
        </tr>
        <%
            List<Dao.Order> orderList = (List<Dao.Order>) request.getAttribute("orderList");
            if (orderList!= null) {
                for (Dao.Order order : orderList) {
        %>
        <tr>
            <td><%= order.getOrder_id() %></td>
            <td><%= order.getUser_id() %></td>
            <td><%= order.getFlower_id() %></td>
            <td><%= order.getOrder_num() %></td>
            <td><%= order.getOrder_notice() %></td>
            <td><%= order.getOrder_status() %></td>
            <td>
                <a href="DeleteOrderServlet?orderId=<%= order.getOrder_id() %>" onclick="return confirm('Are you sure you want to delete this order?')">Delete</a>
                <a href="EditOrderServlet?orderId=<%= order.getOrder_id() %>">Edit</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7">No order information found</td>
        </tr>
        <%
            }
        %>
    </table>
    <button type="button" onclick="window.location.href='addOrder.jsp'">Add Order</button>
    <button type="button" onclick="window.location.href='admin.jsp'">Management Return</button>
    <button type="button" onclick="window.location.href='orderList.jsp'">Search</button>
</div>
<%
} else {
    // If it's not an administrator (i.e., a regular user), show the user return button and hide the management return button
%>
<div id="orderManagementContainer">
    <h2>Order Management</h2>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Flower ID</th>
            <th>Order Quantity</th>
            <th>Order Note</th>
            <th>Order Status</th>
            <th>Operation</th>
        </tr>
        <%
            List<Dao.Order> orderList = (List<Dao.Order>) request.getAttribute("orderList");
            if (orderList!= null) {
                for (Dao.Order order : orderList) {
        %>
        <tr>
            <td><%= order.getOrder_id() %></td>
            <td><%= order.getUser_id() %></td>
            <td><%= order.getFlower_id() %></td>
            <td><%= order.getOrder_num() %></td>
            <td><%= order.getOrder_notice() %></td>
            <td><%= order.getOrder_status() %></td>
            <td>
                <a href="DeleteOrderServlet?orderId=<%= order.getOrder_id() %>" onclick="return confirm('Are you sure you want to delete this order?')">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7">No order information found</td>
        </tr>
        <%
            }
        %>
    </table>
    <button type="button" onclick="window.location.href='addOrder.jsp'">Add Order</button>
    <button type="button" onclick="window.location.href='user.jsp'">User Return</button>
    <button type="button" onclick="window.location.href='orderList.jsp'">Search</button>
</div>
<%
    }
%>

</body>

</html>