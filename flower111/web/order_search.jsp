<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Order Search</title>
    <link rel="stylesheet" href="css/public.css">
</head>

<body>
<jsp:useBean id="user" scope="session" class="Dao.User"></jsp:useBean>
<form action="OrderServlet" method="post">
    <input type="text" name="keyword" placeholder="Search Order">
    <input type="submit" value="Search">
</form>

<div id="head">
    <!-- logo-->
    <div id="head_left">
        <a href="index.jsp"></a>
    </div>
    <div id="head_top">
        <%
            Object userObj = session.getAttribute("user");
            if (userObj!= null && userObj instanceof User && ((User) userObj).getUsername()!= null) {
        %>
        <a href="UserExitServlet"><span>[Logout]</span></a>
        <span>Welcome: <%= ((User) userObj).getUsername() %></span>
        <%
        } else {
        %>
        <a href="register.jsp"><span>[Register]</span></a>
        <a href="login.jsp"><span>[Login]</span></a>
        <%
            }
        %>
    </div>
    <!-- nav-->
    <div id="head_right">
        <ul>
            <li><a href="index.jsp">Homepage</a></li>
            <li><a href="MemberServlet">Member Management Center</a></li>
        </ul>
    </div>
</div>

<div id="body">
    <h2>Order Search</h2>
    <form action="OrderServlet?info=search" method="post">
        <label for="orderId">Order ID:</label><input type="text" name="orderId" id="orderId" placeholder="Please enter the order ID"><br>
        <label for="userId">User ID:</label><input type="text" name="userId" id="userId" placeholder="Please enter the user ID"><br>
        <label for="flowerId">Flower ID:</label><input type="text" name="flowerId" id="flowerId" placeholder="Please enter the flower ID"><br>
        <label for="orderStatus">Order Status:</label><input type="text" name="orderStatus" id="orderStatus" placeholder="Please enter the order status"><br>
        <input type="submit" value="Search">
    </form>
    <c:if test="${not empty orders}">
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Flower ID</th>
                <th>Order Quantity</th>
                <th>Order Note</th>
                <th>Order Status</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.order_id}</td>
                    <td>${order.user_id}</td>
                    <td>${order.flower_id}</td>
                    <td>${order.order_num}</td>
                    <td>${order.order_notice}</td>
                    <td>${order.order_status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div id="footer">
    <span>Flower Booking System</span>
</div>
</body>

</html>