<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
<title>ユーザー一覧</title>

</head>
<body>
	<header class=header>

				<h4 class=r>${loginUser.name}さん
				<a  href="LogoutServlet" class=r>ログアウト</a>
				</h4>
			</header>

	<h1 class=p>ユーザー一覧</h1>
<form action="UserListServlet" method="post">

		<div class="text-right">
        <a href="ResisterUser">新規登録</a>

      	</div>
	<div class="p">
			ログインID：<input type="text" name="loginId"><br>
			ユーザ名：<input type="text" name="name"><br>
			生年月日：<input type="date" name="dateStart">  〜  <input type="date" name="dateEnd"><br>
			<button type="submit" value="検索">検索</button><br>
			<hr size="ピクセル値">
			<table style="margin:auto;" border="1">
				 <thead>
				<tr >
					<th>ログインID</th>
					<th>ユーザ名</th>
					<th>生年月日</th>
					<th>  </th>
				</tr>
				 </thead>

					<tbody>

                 <c:forEach var="user" items="${userList}" >
                   <tr>
                     <td>${user.loginId}</td>
                     <td>${user.name}</td>
                     <td>${user.birthDate}</td>
                     <!-- TODO 未実装；ログインボタンの表示制御を行う -->
                     <td>
                       <a href="ReferenceServlet?id=${user.id}"><input type="hidden" name="id">詳細</a>
                       <c:if test="${loginUser.loginId=='admin'||user.loginId==loginUser.loginId}">
                       <a href="UpdateUserServlet?id=${user.id}">更新</a></c:if>
                       <c:if test="${loginUser.loginId=='admin'}">
                       <a href ="Delete?id=${user.id}">削除</a>
                     </c:if></td>
                   </tr>
                 </c:forEach>

			</table>

			</div>
			</form>
	</body>

</html>