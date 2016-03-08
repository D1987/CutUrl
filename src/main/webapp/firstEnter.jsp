<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Приветствие</title>
    <link href="css/fEn.css" type="text/css" rel="stylesheet"/>
  <script src="js/jquery.js"></script>
  <script src="js/index.js"></script>
</head>
<body>
    <form class="form1" method="post" action="http://localhost:81/userCabinet.jsp" id="formRegistr">
      <input type="hidden" name="mail"   value="${param.mail}" id="eEmail">
      <input type="hidden" name="login"  value="${param.login}" id="eLogin">
      <input type="hidden" name="password"  value="${param.hash}" id="ePassword">
      <input type="hidden" name="idU"  value="" id="idUserReg">
      <button type="button" class="buttEnter" onclick="pervuyVhod()">Спасибо за регистрацию!</button>
    </form>
</body>
</html>
