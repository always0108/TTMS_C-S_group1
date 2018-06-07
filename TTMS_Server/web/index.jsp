<%--
  Created by IntelliJ IDEA.
  User: limeng
  Date: 18-5-22
  Time: 下午5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>添加剧目</h1>
  <form action="/rest/schedule/add " method="post">
    <input type="text" name="studio_id">
    <input type="text" name="play_id">
    <input type="text" name="schedule_time">
    <input type="text" name="schedule_ticket_price">
    <input type="submit">
  </form>
  </body>
</html>
