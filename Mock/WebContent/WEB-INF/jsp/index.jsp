<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<h1 class=p>ログイン画面</h1>
		<form action="/UserManegment/LoginServlet" method="post">
		<div class="p">
			ログインID：<input type="text" name="loginId"><br>
			パスワード：<input type="password" name="password"><br>


			<input type="submit" value="ログイン">
</div>

	<c:if test="${errorMsg != null}" >
	    <div class="p"><span style="color: #F00;">
		  ${errorMsg}
		</span></div>
	</c:if>


	</form>
</body>
</html>