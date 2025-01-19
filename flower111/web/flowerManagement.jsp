<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Flower Management</title>
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

<div id="flowerManagementContainer">
    <h2>Flower Management</h2>
    <table border="1">
        <tr>
            <th>Flower ID</th>
            <th>Flower Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Stock</th>
            <th>Operation</th>
        </tr>
        <c:forEach var="flower" items="${flowerList}">
            <tr>
                <td>${flower.flower_id}</td>
                <td>${flower.flower_name}</td>
                <td>${flower.flower_price}</td>
                <td>${flower.flower_content}</td>
                <td>${flower.stock}</td>
                <td>
                    <a href="DeleteFlowerServlet?flowerId=${flower.flower_id}" onclick="return confirm('Are you sure you want to delete this flower?')">Delete</a>
                    <a href="EditFlowerServlet?flowerId=${flower.flower_id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button type="button" onclick="window.location.href='addFlower.jsp'">Add Fresh Flowers</button>
    <button type="button" onclick="window.location.href='admin.jsp'">Back</button>
    <button type="button" onclick="window.location.href='flowerList.jsp'">Search</button>

</div>

</body>

</html>