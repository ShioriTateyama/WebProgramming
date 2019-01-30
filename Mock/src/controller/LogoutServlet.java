package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Logout
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session =request.getSession(false);
		if(session.getAttribute("loginUser")== null ) {
			session =request.getSession(true);
			response.sendRedirect("LoginServlet");
			return;
		}

		// ログイン時に保存したセッション内のユーザ情報を削除

		session.removeAttribute("loginUser");

		// ログインのサーブレットにリダイレクト
		response.sendRedirect("LoginServlet");


		//sessionスコープを破棄

		//session.invalidate();

		//ログイン画面にフォワード
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		//dispatcher.forward(request, response);
	}




}
