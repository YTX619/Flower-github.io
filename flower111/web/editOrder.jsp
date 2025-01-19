<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
    <link rel="stylesheet" href="css/admin.css">
</head>

<body>
<jsp:useBean id="adminUser" scope="session" class="Dao.User"></jsp:useBean>
<%

    if (adminUser == null) {
        // If adminUser is null, redirect to the adminLogin.jsp page
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>

<div id="editOrderContainer">
    <h2>Edit Order</h2>
    <form action="EditOrderServlet" method="post">
        <input type="hidden" name="orderId" value="${order.order_id}">
        <div><span>User ID:</span><input type="text" name="userId" value="${order.user_id}"/></div>
        <div><span>Flower ID:</span><input type="text" name="flowerId" value="${order.flower_id}"/></div>
        <div><span>Order Quantity:</span><input type="text" name="orderNum" value="${order.order_num}"/></div>
        <div><span>Note:</span><input type="text" name="orderNotice" value="${order.order_notice}"/></div>
        <div><span>Status:</span><input type="text" name="orderStatus" value="${order.order_status}"/></div>
        <input type="submit" value="Save Changes">
    </form>
    <button type="button" onclick="window.location.href='orderManagement.jsp'">Back to Order Management</button>
</div>
</body>

</html>