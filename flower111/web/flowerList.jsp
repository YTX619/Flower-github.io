<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Flower List</title>
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
    <h2>Flower List</h2>
    <!-- Search form, submit to FlowerListServlet for fuzzy query -->
    <form action="FlowerListServlet" method="get">
        <input type="text" name="keyword" placeholder="Search Flowers">
        <input type="submit" value="Search">
    </form>
    <table border="1">
        <tr>
            <th>Flower ID</th>
            <th>Flower Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Stock</th>
            <th>Operation</th>
        </tr>
        <c:forEach items="${flowerList}" var="flower">
            <tr>
                <td>${flower.flower_id}</td>
                <td>${flower.flower_name}</td>
                <td>${flower.flower_price}</td>
                <td>${flower.flower_content}</td>
                <td>${flower.stock}</td>
                <td>
                    <a href="editFlower.jsp?flowerId=${flower.flower_id}">Edit</a>
                    <a href="DeleteFlowerServlet?flowerId=${flower.flower_id}" onclick="return confirm('Are you sure you want to delete?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- Pagination navigation -->
    <c:if test="${totalPages > 1}">
        <div id="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:if test="${i == currentPage}">
                    <span>${i}</span>
                </c:if>
                <c:if test="${i!= currentPage}">
                    <a href="FlowerListServlet?page=${i}&keyword=${keyword}">${i}</a>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</div>

<!-- foot-->
<div id="footer">
    <span>Flower Booking System</span>
</div>

</body>

</html>