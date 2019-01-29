<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>ユーザ情報更新</title>
</head>
<body>
		<header class=header>

			<h4 class=r>${loginUser.name} さん
			<a href="LogoutServlet" class=r>ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ情報更新</h1>
		<form action="UpdateUserServlet" method="post">

			<div class="p">
			<span class=a>ログインID</span><span class=a>${user.loginId} </span><br>
			<input type="hidden" value="${loginUser.id}" name=loginId>
			<span class=a>パスワード</span><span class=a><input type="text" name="password"></span><br>
			<span class=a>パスワード（確認）</span><span class=a><input type="text" name="password2"></span><br>
			<span class=a>ユーザ名</span><span class=a><input value="${user.name}" type="text" name="name"></span><br>
			<span class=a>生年月日</span><span class=a><input value="${user.birthDate}" type="date" name="birthDate"> </span><br>

			<input type="submit" value="更新">

			</div>

		<p><a href="UserListServlet">戻る</a></p>
</form>
</body>
</html>