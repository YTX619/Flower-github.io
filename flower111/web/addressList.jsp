<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Address List</title>
    <link rel="stylesheet" href="css/public.css">
</head>

<body>
<jsp:useBean id="user" scope="session" class="Dao.User"></jsp:useBean>

<div id="head">
    <!-- logo-->
    <div id="head_left">
        <a href="index.jsp"><img src="images/2.jpg" width="225px" height="59px"></a>
    </div>
    <div id="head_top">
        <%
            Object userObj = session.getAttribute("user");
            if (userObj!= null && userObj instanceof User && ((User) userObj).getUsername()!= null) {
        %>
        <a href="UserExitServlet"><span>【Logout】</span></a>
        <span>Welcome: <%= ((User) userObj).getUsername() %></span>
        <%
        } else {
        %>
        <a href="register.jsp"><span>【Register】</span></a>
        <a href="login.jsp"><span>【Login】</span></a>
        <%
            }
        %>
    </div>
    <!-- nav-->
    <div id="head_right">
        <ul>
            <li><a href="index.jsp">Homepage</a></li>
            <li><a href="shopping.jsp">My Shopping Cart</a></li>
            <li><a href="MemberServlet">Member Management Center</a></li>
            <li><a href="order_search.jsp">Order Inquiry</a></li>
            <li><a href="about.jsp">About Us</a></li>
        </ul>
    </div>
</div>

<div id="body">
    <h2>Address List</h2>
    <table border="1">
        <tr>
            <th>Address ID</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Mobile</th>
            <th>Note</th>
            <th>Operation</th>
        </tr>
        <c:forEach items="${addressList}" var="address">
            <tr>
                <td>${address.address_id}</td>
                <td>${address.address}</td>
                <td>${address.tel}</td>
                <td>${address.mobile}</td>
                <td>${address.notice}</td>
                <td>
                    <a href="editAddress.jsp?addressId=${address.address_id}">Edit</a>
                    <a href="DeleteAddressServlet?addressId=${address.address_id}" onclick="return confirm('Are you sure you want to delete?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<!-- foot-->
<div id="footer">
    <span>Flower Booking System</span>
</div>
</body>

</html>