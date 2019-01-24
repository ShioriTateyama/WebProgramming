import java.util.List;

import dao.UserDao;
import model.User;

public class SelectUser {
	public static void main(String[] args) {


		UserDao userDao = new UserDao();
		List<User>userList=userDao.findAll();


		for (User users:userList) {
			System.out.println("ID:"+users.getId());
			System.out.println("ログインID"+users.getLoginId());
			System.out.println("名前"+users.getName());
			System.out.println("生年月日"+users.getBirthDate());
			System.out.println("パスワード"+users.getPassword());
			System.out.println("作成日時"+users.getCreateDate());
			System.out.println("更新日時"+users.getUpdateDate());
		}
	}
}
