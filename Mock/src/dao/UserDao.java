package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;


public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE id not in('1') ";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_Id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("up_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }
    public List<User> findByUserSerch(String loginId, String name, String birthDateStart,String birthDateEnd) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            String sql = "SELECT * FROM user WHERE id not in('1') ";

            if(!loginId.isEmpty()) {
            	sql += " AND login_id = '" + loginId + "'";
            }if(!name.isEmpty()) {
            	sql +="AND name LIKE '%"+name+"%'  ";
            }if(!birthDateStart.isEmpty()) {
            	sql +="AND birth_date > ' "+birthDateStart+"' ";
            }if(!birthDateEnd.isEmpty()) {
            	sql +="AND birth_date < ' "+birthDateEnd+"' ";
            }



//            // SELECT文を準備
//            String sql = "SELECT login_id,name,birth_date FROM user WHERE login_id = ? and name LIKE %?% and birth_date BETWEEN ? AND ?";
//
//             // SELECTを実行し、結果表を取得
//            PreparedStatement pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, loginId);
//            pStmt.setString(2, name);
//            pStmt.setString(3,birthDateStart);
//            pStmt.setString(4, birthDateEnd);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginIdData = rs.getString("login_Id");
                String nameData = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("up_date");
                User user = new User(id, loginIdData, nameData, birthDate, password, createDate, updateDate);

                userList.add(user);
            }



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
		return userList;
    }

    public User referUser(String id) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?;";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);


            ResultSet rs = pStmt.executeQuery();

            rs.next();//nextmethodの戻り値は新しい現在の行が有効である場合はtrue、行がそれ以上存在しない場合はfalse
            int idData = rs.getInt("id");
            String loginIdData = rs.getString("login_Id");
            String nameData = rs.getString("name");
            Date birthDateData = rs.getDate("birth_date");
            String password = rs.getString("password");
            String createDateData = rs.getString("create_date");
            String updateDateData = rs.getString("up_date");

            User user = new User(idData, loginIdData, nameData, birthDateData, password, createDateData,updateDateData);


            return user;


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
		return null;

    }

    public void resisterUser(String loginId, String password,String name,  String birthDate, String cteateDate,String updateDate) {
    Connection conn=null;
	try {
		//connectDB
		conn=DBManager.getConnection();


		//insert文
		String sql ="INSERT INTO user(login_Id,password,name,birth_date,create_date,up_date)VALUES(?,?,?,?,now(),now())";
		//インサート実行
		 // SELECTを実行し、結果表を取得
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, loginId);
        pStmt.setString(2, password);
        pStmt.setString(3, name);
        pStmt.setString(4, birthDate);


        //暗号化
        String sourse="password";
        Charset charset =StandardCharsets.UTF_8;
        String algorithm="MD5";
        byte[] bytes =MessageDigest.getInstance(algorithm).digest(sourse.getBytes(charset));
        String code=DatatypeConverter.printHexBinary(bytes);
        System.out.println(code);



		pStmt.executeUpdate();


	 } catch (SQLException | NoSuchAlgorithmException e) {
         e.printStackTrace();
     } finally {
         // データベース切断
         if (conn != null) {
             try {
                 conn.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
    }
    public boolean findSameloginId(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT login_id FROM user WHERE login_id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);

            ResultSet rs = pStmt.executeQuery();

            return rs.next();//nextmethodの戻り値は新しい現在の行が有効である場合はtrue、行がそれ以上存在しない場合はfalse


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
		return true;
    }


    public void updateUser(String id,String password,String name,  String birthDate) {
        Connection conn=null;
    	try {
    		//connectDB
    		conn=DBManager.getConnection();

    		//update文
    		String sql ="UPDATE user SET password=?,name=? , birth_date=? WHERE id=?;";
    		//インサート実行
    		 // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, password);
            pStmt.setString(2, name);
            pStmt.setString(3, birthDate);
            pStmt.setString(4, id);

          //暗号化
            String sourse="password";
            Charset charset =StandardCharsets.UTF_8;
            String algorithm="MD5";
            byte[] bytes =MessageDigest.getInstance(algorithm).digest(sourse.getBytes(charset));
            String code=DatatypeConverter.printHexBinary(bytes);
            System.out.println(code);


    		pStmt.executeUpdate();



    	}catch(SQLException | NoSuchAlgorithmException e)	{
    		e.printStackTrace();
    	}finally {
    		// データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
    	}

    }

    public void updateUserWithoutPassword(String id, String name,  String birthDate) {
        Connection conn=null;
    	try {
    		//connectDB
    		conn=DBManager.getConnection();

    		//update文
    		String sql ="UPDATE user SET name=?,birth_date =? WHERE id=?";
    		//インサート実行
    		 // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);


            pStmt.setString(1, name);
            pStmt.setString(2, birthDate);
            pStmt.setString(3, id);




    		pStmt.executeUpdate();


    	}catch(SQLException e)	{
    		e.printStackTrace();
    	}finally {
    		// データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
    	}

    }
		public List<User> delete(String id) {
	        Connection conn = null;
	        List<User> userList = new ArrayList<User>();

	        try {
	            // データベースへ接続
	            conn = DBManager.getConnection();

	            // delete文を準備

	            String sql = "DELETE FROM user WHERE id =? ";

	             // SELECTを実行し、結果表を取得
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1,id);

	            pStmt.executeUpdate();





	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        }
	        return userList;
	    }
    }