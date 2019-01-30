package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
				HttpSession session =request.getSession(false);
				if(session.getAttribute("loginUser")== null ) {
					session =request.getSession(true);
					response.sendRedirect("LoginServlet");
					return;
				}

				// URLからGETパラメータとしてIDを受け取る
				String id = request.getParameter("id");

				// 確認用：idをコンソールに出力
				System.out.println(id);


				// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
				UserDao userDao = new UserDao();
				User user = userDao.referUser(id);


				// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
				request.setAttribute("user", user);


				//DeleteUser.jspにforward
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/DeleteUser.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String cancel =request.getParameter("cancel");
		String id=request.getParameter("id");
		//cancelだった時＝nullではない
		if(cancel!=null) {

			// ユーザ一覧情報を取得
			UserDao userDao = new UserDao();
			List<User> userList = userDao.findAll();
			// リクエストスコープにユーザ一覧情報をセット
			request.setAttribute("userList", userList);

			// ユーザ一覧のサーブレットにリダイレクト

			response.sendRedirect("UserListServlet");
			return;
		}else {

			UserDao userDao = new UserDao();
			userDao.delete(id);
			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("UserListServlet");
		}
	}

}
