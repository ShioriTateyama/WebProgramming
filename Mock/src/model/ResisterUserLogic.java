package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ResisterUserLogic {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			//connectDB
			conn=DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password");
			
			//insert文
			String sql ="INSERT INTO user(loginId,name,birth_date,password,creat_date,update_date)VALUES('loginId','name','birthDate','password','createDate','updateDate')";
			//インサート実行
			Statement stmt=conn.createStatement();
			int result=stmt.executeUpdate(sql);
			//追加された行数を出力
			System.out.println(result);
			stmt.close();
			
		}catch(SQLException e)	{
			e.printStackTrace();
		}finally {
		}
		}
	
}
