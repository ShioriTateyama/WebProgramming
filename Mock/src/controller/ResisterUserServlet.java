package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;


@WebServlet("/ResisterUser")
public class ResisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session =request.getSession(false);
		if(session== null ) {
			session =request.getSession(true);
			response.sendRedirect("/WEB-INF/jsp/index.jsp/");
			return;
		}
				/*if(request.getSession().getAttribute("loginUser")==null) {
					response.sendRedirect("/WEB-INF/index.jsp/");
					return;

				}*/
				//Resister.jspにforward
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ResisterUser.jsp");
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
		String birthDate =request.getParameter("birthDate");
		String createDate=request.getParameter("creatDate");
		String updateDate=request.getParameter("updateDate");

		// リクエストパラメータの入力項目を引数に渡して、resisterUserのメソッドを実行
		UserDao userDao = new UserDao();
		userDao.findSameloginId(loginId);



		if(!(password.equals(password2))){

			request.setAttribute("error", "入力された情報は正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ResisterUser.jsp");
			dispatcher.forward(request, response);
			return;

		}if(loginId==null||password==null||password2==null||name==null||birthDate==null) {

			request.setAttribute("error", "入力された情報は正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ResisterUser.jsp");
			dispatcher.forward(request, response);
			return;
		}if(userDao.findSameloginId(loginId)) {
			request.setAttribute("error", "入力された情報は正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ResisterUser.jsp");
			dispatcher.forward(request, response);
			return;


		}




			userDao.resisterUser(loginId,password,name,birthDate,createDate,updateDate);




			// ユーザ一覧のservletにredirect
			response.sendRedirect("UserListServlet");
	}
}
