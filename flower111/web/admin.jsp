<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="css/public.css">
</head>

<body>
<jsp:useBean id="user" scope="session" class="Dao.User"></jsp:useBean>

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

</div>

<div id="body">
    <h2>Admin Page</h2>
    <p>Here you can perform operations such as user management, flower management, and order management.</p>
    <button type="button" onclick="window.location.href='UserManagementServlet'">User Management</button>
    <button type="button" onclick="window.location.href='FlowerManagementServlet'">Flower Management</button>
    <button type="button" onclick="window.location.href='OrderManagementServlet'">Order Management</button>
</div>

<!-- foot-->
<div id="footer">
    <span>Flower Booking System</span>
</div>
</body>

</html>