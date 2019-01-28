<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>ユーザ新規登録</title>
</head>
<body>
		<header class=header>

			<h4 class=r>ユーザ名 さん
			<a href="index.html">ログアウト</a>
			</h4>
		</header>

		<h1 class=p>ユーザ新規登録</h1>

			<div class="p">
			ログインID：<input type="text" name="loginId"><br>
			パスワード：<input type="text" name="password"><br>
			パスワード（確認）：<input type="text" name="password2"><br>
			ユーザ名：<input type="text" name="name"><br>
			生年月日：<input type="date" name="birthDate"> <br>
			<input type="hidden" value="<%=System.currentTimeMills() %>" name="createDate"></input>
			<input type="hidden" value="<%=System.currentTimeMills() %>" name="upDateDate"></input>
			
			

			<button type="submit" value="登録">登録</button>

		</div>

		<c:if test="${errorMsg}" >
	    <div class="p"><span style="color: #F00;">
		  ${errorMsg}
		</span></div>
	</c:if>

		<p><a href="index.html">戻る</a></p>

</body>
</html>