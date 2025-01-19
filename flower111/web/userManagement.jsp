<%@ page import="Dao.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <link rel="stylesheet" href="css/admin.css">
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

<div id="userManagementContainer">
    <h2>User Management</h2>

    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Real Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Operation</th>
        </tr>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");
            if (userList!= null) {
                for (User user : userList) {
        %>
        <tr>
            <td><%= user.getUser_id() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getRealname() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getSex() %></td>
            <td>
                <a href="DeleteUserServlet?userId=<%= user.getUser_id() %>" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <button type="button" onclick="window.location.href='addUser.jsp'">Add New User</button>
    <button type="button" onclick="window.location.href='admin.jsp'">Back</button>
</div>

</body>

</html>