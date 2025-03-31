# Spring MVC 示例项目

这是一个基础的Spring MVC示例项目，用于展示Spring MVC框架的基本概念和项目结构。

## 项目结构

```
SpringMVCDemo/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── springmvc/
│       │               ├── controller/    # 控制器层
│       │               ├── service/       # 服务层
│       │               ├── model/         # 数据模型层
│       │               └── config/        # 配置类
│       └── webapp/
│           └── WEB-INF/
│               ├── views/                # JSP视图文件
│               ├── web.xml              # Web应用配置文件
│               └── spring-mvc.xml       # Spring MVC配置文件
└── pom.xml                            # Maven配置文件
```

## 主要组件说明

1. **Controller层**：处理HTTP请求，调用Service层处理业务逻辑
2. **Service层**：实现业务逻辑
3. **Model层**：定义数据模型
4. **View层**：使用JSP实现视图展示

## 配置说明

- `web.xml`：配置Spring MVC的前端控制器和字符编码过滤器
- `spring-mvc.xml`：配置组件扫描、视图解析器等Spring MVC相关配置
- `pom.xml`：配置项目依赖，包括Spring MVC、Servlet API、JSTL等

## 运行说明

1. 确保已安装Java 11和Maven
2. 在项目根目录执行：`mvn clean package`
3. 将生成的WAR包部署到Tomcat等Web服务器中
4. 访问：`http://localhost:8080/SpringMVCDemo/`

## 示例功能

- 首页：展示Spring MVC项目结构说明
- 关于页：展示Spring MVC框架介绍

## 学习要点

1. Spring MVC的基本配置
2. 控制器的编写方式
3. 视图解析器的配置
4. 静态资源的处理
5. 数据绑定和模型传递 