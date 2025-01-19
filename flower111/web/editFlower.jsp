<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit Flowers</title>
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

<div id="editFlowerContainer">
    <h2>Edit Flowers</h2>
    <form action="EditFlowerServlet" method="post">
        <input type="hidden" name="flowerId" value="${flower.flower_id}">
        <div><span>Flower Name:</span><input type="text" name="flowerName" value="${flower.flower_name}"/></div>
        <div><span>Price:</span><input type="text" name="flowerPrice" value="${flower.flower_price}"/></div>
        <div><span>Description:</span><input type="text" name="flowerContent" value="${flower.flower_content}"/></div>
        <div><span>Stock:</span><input type="text" name="stock" value="${flower.stock}"/></div>
        <input type="submit" value="Save Changes">
    </form>
    <button type="button" onclick="window.location.href='flowerManagement.jsp'">Back to Flower Management</button>
</div>
</body>

</html>