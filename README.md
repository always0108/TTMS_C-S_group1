## 项目概述

TTMS全名为票务管理系统，采用经典的C/S架构，用Java语言来开发，Server与Client间采用HTTP协议进行交互，数据库采用MySQL 8.0.11。

### Server

采用Spring MVC + Spring + Mybatis经典架构。根据Client的请求，进行处理，返回封装过的JSON客户端解析使用。

### Client

采用JavaFX来开发GUI，利用httpclient来发送各种请求，HTTP是无状态的协议，利用Session和Cookie来解决。

## 所需技能

1. Java语言基础，MySQL数据库的基本使用
2. JavaFX基础，能开发基本的页面
3. 了解httpclient，并且能够用其发送get及post请求
4. 熟悉web服务器开发，掌握Spring框架、会使用Spring MVC开发，并且熟悉Mybatis

