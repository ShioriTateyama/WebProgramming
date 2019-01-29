<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>ユーザ情報参照</title>
</head>
<body>
		<header class=header>

			<h4 class=r>${loginUser.name}さん
			<a href="LogoutServlet" class=r>ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ情報参照</h1>
		<form action="ReferenceServlet" method="post">

			<div class="p">
			<span class=a>ログインID</span><span class=a>${user.loginId}</span><br>
			<span class=a>ユーザ名</span><span class=a>${user.name}</span><br>
			<span class=a>生年月日</span><span class=a>${user.birthDate}</span><br>
			<span class=a>登録日時</span><span class=a>${user.createDate}</span><br>
			<span class=a>更新日時</span><span class=a>${user.updateDate}</span><br>

			</div>



		<p><a href="UserListServlet">戻る</a></p>
</form>
</body>
</html>