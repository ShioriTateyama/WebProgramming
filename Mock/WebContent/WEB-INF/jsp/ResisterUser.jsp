<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>ユーザ新規登録</title>
</head>
<body>
		<header class=header>

			<h4 class=r>ユーザ名 さん
			<a href="LogoutServlet" class=r>ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ新規登録</h1>
		<c:if test="${error}" >
	    <div class="p"><span style="color: #F00;">
		  ${error}
		</span></div>
	</c:if>
			<form action="ResisterUser" method="post">
			<div class="p">
			ログインID：<input type="text" name="loginId"><br>
			パスワード：<input type="text" name="password"><br>
			パスワード（確認）：<input type="text" name="password2"><br>
			ユーザ名：<input type="text" name="name"><br>
			生年月日：<input type="date" name="birthDate"> <br>



			<button type="submit" value="登録">登録</button>

		</div>



		<p><a href="UserListServlet">戻る</a></p>
</form>
</body>
</html>