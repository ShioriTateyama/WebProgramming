<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>ユーザ削除確認</title>
</head>
<body>
		<header class=header>

			<h4 class=r>${loginUser.name}さん さん
			<a href="/src/controller/LogoutServlet">ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ削除確認</h1>
			<div class="p">
			ログインID : id0001<br>
			を本当に削除してもよろしいでしょうか。<br>

			<input type="submit" value="キャンセル">
			<span class=k><input type="submit" value="OK"></span>



			</div>



		<p><a href="/src/controller/UserListServlet">戻る</a></p>

</body>
</html>