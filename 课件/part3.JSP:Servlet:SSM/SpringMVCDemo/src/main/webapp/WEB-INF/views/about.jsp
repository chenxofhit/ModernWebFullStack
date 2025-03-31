<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>关于 - Spring MVC 示例</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
        }
        .nav {
            margin: 20px 0;
        }
        .nav a {
            margin-right: 15px;
            text-decoration: none;
            color: #0066cc;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>关于 Spring MVC</h1>
        <div class="nav">
            <a href="<c:url value='/'/>">首页</a>
            <a href="<c:url value='/about'/>">关于</a>
        </div>
        <div class="content">
            <h2>Spring MVC 框架介绍</h2>
            <p>Spring MVC 是 Spring 框架的一部分，是一个基于 MVC 设计模式的 Web 框架。</p>
            <h3>主要特点：</h3>
            <ul>
                <li>清晰的职责分离</li>
                <li>强大的数据绑定</li>
                <li>灵活的视图解析</li>
                <li>强大的验证机制</li>
            </ul>
        </div>
    </div>
</body>
</html> 