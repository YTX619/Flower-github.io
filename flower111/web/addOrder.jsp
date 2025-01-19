<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Add Order</title>
    <link rel="stylesheet" href="css/admin.css"> <!-- Link to the custom admin page stylesheet -->
</head>

<body>
<jsp:useBean id="adminUser" scope="session" class="Dao.User"></jsp:useBean>
<%

    if (adminUser == null) {
        // If adminUser is null, redirect to the adminLogin.jsp page
        response.sendRedirect("adminLogin.jsp");
        return; // Add return here to avoid further page code execution since it has already redirected
    }
%>

<div id="addOrderContainer">
    <h2>Add Order</h2>
    <form action="AddOrderServlet" method="post">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br>
        <label for="flowerId">Flower ID:</label>
        <input type="text" id="flowerId" name="flowerId" required><br>
        <label for="orderNum">Order Quantity:</label>
        <input type="text" id="orderNum" name="orderNum" required><br>
        <label for="orderNotice">Order Note:</label>
        <input type="text" id="orderNotice" name="orderNotice" required><br>
        <label for="orderStatus">Order Status:</label>
        <input type="text" id="orderStatus" name="orderStatus" required><br>
        <input type="submit" value="Add Order">
    </form>
    <button type="button" onclick="window.location.href='orderManagement.jsp'">Back to Order Management</button>
</div>
</body>

</html>