<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Page</title>
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
    <!-- nav-->
    <div id="head_right">
        <ul>
            <li><a href="index.jsp">Homepage</a></li>
            <li><a href="MemberServlet">Member Management Center</a></li>
        </ul>
    </div>
</div>

<div id="body">
    <h2>User Page</h2>
    <p>Welcome, ${user.username}! You can view order information here.</p>
    <button type="button" onclick="window.location.href='OrderManagementServlet'">Order Information</button>
</div>

<!-- foot-->
<div id="footer">
    <span>Flower Booking System</span>
</div>
</body>

</html>