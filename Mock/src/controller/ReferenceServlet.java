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
import model.User;

/**
 * Servlet implementation class ReferenceServlet
 */
@WebServlet("/ReferenceServlet")
public class ReferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReferenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
				HttpSession session =request.getSession(false);
				if(session== null ) {
					session =request.getSession(true);
					response.sendRedirect("/WEB-INF/jsp/index.jsp/");
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

				RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/Reference.jsp");
				dispatcher.forward(request, response);

	}

}
