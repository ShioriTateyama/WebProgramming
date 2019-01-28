package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;



@WebServlet("/ResisterUser")
public class ResisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインセッションがある場合、ユーザ一覧画面にリダイレクトさせる
				if(request.getSession().getAttribute("loginUser")!=null) {
					response.sendRedirect("/WEB-INF/UserList.jsp/");

				}
				//Resister.jspにforward
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Resister.jsp");
				dispatcher.forward(request, response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId =request.getParameter("loginId");
		String password =request.getParameter("password");
		String password2 =request.getParameter("password2");
		String name =request.getParameter("name");
		String birhDate =request.getParameter("birthDate");
		String createDate=request.getParameter("creatDate");
		String updateDate=request.getParameter("updateDate");

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				UserDao userDao = new UserDao();
				User user = userDao.findByLoginInfo(loginId, password);

				if(user ==null) {
					//インスタンスをリクエストスコープに保存
					request.setAttribute("errorMsg", "ログインに失敗しました。" );

					// ログインjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
					dispatcher.forward(request, response);
					return;
				}

request.setAttribute("errorMsg", "入力された内容は正しくありません" );
	}

}
