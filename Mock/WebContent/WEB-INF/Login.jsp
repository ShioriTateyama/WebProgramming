<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1 class=p>ログイン画面</h1>
		<form action="/UserManegment/LoginServlet" method="post">
		<div class="p">
			ログインID：<input type="text" id="loginId"><br>
			パスワード：<input type="text" id="password"><br>


			<input type="submit" value="ログイン">

		</div>


	</form>
</body>
</html>