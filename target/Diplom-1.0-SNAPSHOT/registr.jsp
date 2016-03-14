<%@ page import="classes.MailService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Перенаправление на почту</title>
  <link href="css/fEn.css" type="text/css" rel="stylesheet"/>
  <script src="js/jquery.js"></script>
  <script charset="windows-1251" src="js/index.js"></script>
</head>
<body>
  <div>
    <%
      MailService par = new MailService();
      par.selectDomain(request.getParameter("mail"));
    %>
    <p>Для завершения регистрации перейдите по ссылке отправленной вам на почту</p>
    <a href="<%=par.getUrl()%>" class="buttEnter">Войти в <%=par.getName()%></a>
  </div>
</body>
</html>
