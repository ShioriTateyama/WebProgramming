<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>ユーザ情報更新</title>
</head>
<body>
		<header class=header>

			<h4 class=r>${loginUser.name} さん
			<a href="/src/controller/LogoutServlet.java">ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ情報更新</h1>

			<div class="p">
			<span class=a>ログインID</span><span class=a>${loginUser.id}</span><br>
			<span class=a>パスワード</span><span class=a><input type="text" name="password"></span><br>
			<span class=a>パスワード（確認）</span><span class=a><input type="text" name="password2"></span><br>
			<span class=a>ユーザ名</span><span class=a><input type="text" name="name"></span><br>
			<span class=a>生年月日</span><span class=a><input type="date" name="birthDate"> </span><br>

			<input type="submit" value="更新">

			</div>

		<p><a href="/src/controll/UserListServlet.java">戻る</a></p>

</body>
</html>