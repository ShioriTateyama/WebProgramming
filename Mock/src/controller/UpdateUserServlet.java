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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
				HttpSession session =request.getSession(false);
				if(session== null ) {
					session =request.getSession(true);
					response.sendRedirect("/WEB-INF/index.jsp/");
					return;
				}

				String id = request.getParameter("id");

				// 確認用：idをコンソールに出力
				System.out.println(id);


				// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
				UserDao userDao = new UserDao();
				User user = userDao.referUser(id);


				// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
				request.setAttribute("user", user);

				//UpdateUser.jspにforward
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateUser.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");

				// リクエストパラメータの入力項目を取得
				String loginId=request.getParameter("loginId");
				String password =request.getParameter("password");
				String password2 =request.getParameter("password2");
				String name=request.getParameter("name");
				String birthDate=request.getParameter("birthDate");

				//失敗の時
				if(password!=password2||name==null||birthDate==null) {

					//インスタンスをリクエストスコープに保存
					request.setAttribute("errorMsg", "入力された内容は正しくありません。" );
					//UpdateUser.jspにforward
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateUser.jsp");
					dispatcher.forward(request, response);
					return ;


				}
				// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				UserDao userDao = new UserDao();
				userDao.updateUser(loginId, password,name,birthDate);

				if(password==null&&password2==null) {
					userDao.updateUserWithoutPassword(loginId,name,birthDate);
				}


				// ユーザ一覧のサーブレットにリダイレクト
				response.sendRedirect("UserListServlet");
	}

}
