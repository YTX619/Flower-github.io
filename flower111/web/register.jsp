<%@ page import="Dao.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" href="css/public.css">
</head>

<body>
<jsp:useBean id="user" scope="session" class="Dao.User"></jsp:useBean>

<div id="head">
    <!-- logo-->
    <div id="head_left">
        <a href="index.jsp"><</a>
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
    <h2>User Registration</h2>
    <form name="registerForm" action="RegisterServlet" method="post" onsubmit="return validateRegister()">
        <div><span class="name">Username:</span><input type="text" name="username" placeholder="Please enter username"/></div>
        <div><span class="name">Password:</span><input type="password" name="password" placeholder="Please enter password"/></div>
        <div><span class="name">Confirm Password:</span><input type="password" name="repassword" placeholder="Please re-enter password"/></div>
        <div><span class="name">Email:</span><input type="email" name="email" placeholder="Please enter email"/></div>
        <div><span class="name">Phone:</span><input type="text" name="phone" placeholder="Please enter phone number"/></div>
        <div><span class="name">Real Name:</span><input type="text" name="realname" placeholder="Please enter real name"/></div>
        <div><span class="name">User Flag:</span><select name="user_flag">
            <option value="Regular User">Regular User</option>
            <option value="admin">Administrator</option>
        </select></div>
        <div><input type="submit" value="Register"/>
            <input type="reset" value="Reset"/></div>
    </form>
</div>

<!-- foot-->
<div id="footer">
    <span>Flower Booking System</span>
</div>

<script type="text/javascript">
    window.addEventListener('load', function() {
        // Registration form validation function
        function validateRegister() {
            var username = document.registerForm.username.value;
            var password = document.registerForm.password.value;
            var repassword = document.registerForm.repassword.value;
            var email = document.registerForm.email.value;
            var phone = document.registerForm.phone.value;
            var realname = document.registerForm.realname.value;

            if (username == "") {
                alert('Username cannot be empty!');
                return false;
            }
            if (password == "") {
                alert('Password cannot be empty!');
                return false;
            }
            if (repassword!= password) {
                alert('The two entered passwords do not match!');
                return false;
            }
            if (email == "") {
                alert('Email cannot be empty!');
                return false;
            }
            if (phone == "") {
                alert('Phone number cannot be empty!');
                return false;
            }
            if (realname == "") {
                alert('Real name cannot be empty!');
                return false;
            }
            return true;
        }
    });
</script>
</body>

</html>