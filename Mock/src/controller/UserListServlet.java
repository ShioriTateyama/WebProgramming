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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session =request.getSession(false);
		if(session== null ) {
			session =request.getSession(true);
			response.sendRedirect("/WEB-INF/index.jsp/");
			return;
		}
		// ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserList.jsp");
		dispatcher.forward(request, response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 検索処理全般
		request.setCharacterEncoding("UTF-8");
		String loginId =request.getParameter("loginId");
		String name=request.getParameter("name");
		String birthDateStart=request.getParameter("birthDateStart");
		String birthDateEnd=request.getParameter("birthDateEnd");

		UserDao userDao = new UserDao();
		List<User> result =userDao.findByUserSerch(loginId,name,birthDateStart,birthDateEnd);

		// リクエストスコープにresultをセット
				request.setAttribute("userList", result);

		// ユーザ一覧のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserList.jsp");
				dispatcher.forward(request, response);


	}


}
