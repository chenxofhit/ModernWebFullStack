<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC 示例</title>
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
        <h1>${who},${message}</h1>
        <div class="nav">
            <a href="<c:url value='/'/>">首页</a>
            <a href="<c:url value='/about'/>">关于</a>
        </div>
        <div class="content">
            <h2>Spring MVC 项目结构说明</h2>
            <ul>
                <li>Controller层：处理请求和响应</li>
                <li>Service层：处理业务逻辑</li>
                <li>Model层：数据模型</li>
                <li>View层：视图展示</li>
            </ul>
        </div>
    </div>
</body>
</html> 