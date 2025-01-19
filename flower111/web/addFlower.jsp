<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Add Flowers</title>
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

<div id="addFlowerContainer">
    <h2>Add Fresh Flowers</h2>
    <form action="AddFlowerServlet" method="post">
        <label for="flowerName">Flower Name:</label>
        <input type="text" id="flowerName" name="flowerName" required><br>
        <label for="flowerPrice">Price:</label>
        <input type="text" id="flowerPrice" name="flowerPrice" required><br>
        <label for="flowerContent">Description:</label>
        <input type="text" id="flowerContent" name="flowerContent" required><br>
        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" required><br>
        <input type="submit" value="Add Flowers">
    </form>
    <button type="button" onclick="window.location.href='flowerManagement.jsp'">Back to Flower Management</button>
</div>
</body>

</html>