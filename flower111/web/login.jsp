<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="css/public.css">
</head>

<body>
<!-- Check if the user has already logged in, if so, redirect directly to the homepage -->


<div class="index_main">
    <div id="head">
        <!-- logo-->
        <div id="head_left">
            <a href="index.jsp"></a>
        </div>
        <div id="head_top">
            <a href="register.jsp"><span>[Register]</span></a>
            <a href="login.jsp"><span>[Login]</span></a>
        </div>
        <!-- nav-->
        <div id="head_right">
            <ul>
                <li><a href="index.jsp">Homepage</a></li>
                <li><a href="MemberServlet">Member Management Center</a></li>
            </ul>
        </div>
    </div>
    <div class="indx_main_body">
        <div class="index_header">
            <div class="index_tab_nav">
                <div class="name" id="signupTab">Register</div>
                <div class="name" id="signinTab">Login</div>
            </div>
            <div class="signin" id="signinForm" style="display: block;">
                <form name="signinForm" action="LoginServlet" method="post" onsubmit="return validateSignIn()">
                    <div><span class="name">Username:</span><input type="text" name="username" placeholder="Please enter user ID or username" id="username2"/></div>
                    <div><span class="name">Password:</span><input type="password" name="password" placeholder="Please enter the password" id="password3"/></div>
                    <img src="CaptchaServlet" alt="Captcha" id="captchaImg">
                    <input type="text" name="captcha" placeholder="Please enter the captcha">
                    <div><span class="name1"></span><input type="submit" value="Login"/>
                        <input type="reset" value="Reset"/>
                </form>
            </div>
        </div>
    </div>
    <div id="footer">
        <span>Flower Booking System</span>
    </div>

    <script type="text/javascript">
        // Code to be executed after the page is loaded
        window.addEventListener('load', function() {
            // Switch login and registration tabs
            document.getElementById('signupTab').addEventListener('click', function() {
                document.getElementById('signinForm').style.display = 'none';
                document.getElementById('signupForm').style.display = 'block';
            });
            document.getElementById('signinTab').addEventListener('click', function() {
                document.getElementById('signupForm').style.display = 'none';
                document.getElementById('signinForm').style.display = 'block';
            });

            // Login form validation function
            function validateSignIn() {
                var username = document.getElementById('username2').value;
                var password = document.getElementById('password3').value;
                if (username == "") {
                    alert('Username cannot be empty!');
                    return false;
                }
                if (password == "") {
                    alert('Password cannot be empty!');
                    return false;
                }
                return true;
            }
        });
    </script>
</body>

</html>