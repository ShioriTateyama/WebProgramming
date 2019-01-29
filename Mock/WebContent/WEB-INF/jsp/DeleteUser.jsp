<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>ユーザ削除確認</title>
</head>
<body>
		<header class=header>

			<h4 class=r>${loginUser.name}さん さん
			<a href="LogoutServlet" class=r>ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ削除確認</h1>
			<form action="Delete" method="post">
			<div class="p">
			ログインID : id0001<br>
			を本当に削除してもよろしいでしょうか。<br>

			<input type="submit" value="キャンセル" name="cancel">
			<span class=k><input type="submit" value="OK" name="ok"></span>
			<input type="hidden" value="${loginUser.loginId}" name="loginId">



			</div>



		<p><a href="UserListServlet">戻る</a></p>
		</form>
</body>
</html>