package dengtao.Model.dao.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Car;
import dengtao.Model.pojo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User getUserByName(Connection conn, String userName) {
		PreparedStatement pstm = null;
		ResultSet rs=null;
		User user=null;

		String sql="SELECT * FROM `User` WHERE `name`=?";
		Object[] params = {userName};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				if (rs.next()) {
					user=new User();
					user.setName(rs.getString("name"));
					user.setPsw(rs.getString("psw"));
					user.setAuthority(rs.getInt("Authority")==1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, rs);
			}
		}
		return user;
	}

	@Override
	public int addUser(Connection conn, String name, String psw) {
		PreparedStatement pstm = null;
		int rs = 0;
		
		String sql="INSERT INTO `User`(`name`,`psw`) VALUES(?,?)";
		Object[] params = {name, psw};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeUpdate(conn, sql, params, pstm);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, null);
			}
		}
		return rs;
	}

	@Override
	public List<User> getUsers(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<User> users = new ArrayList<>();
		User user=null;
		
		String sql="SELECT * FROM `User`";
		Object[] params = {};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				while(rs.next()) {
					user=new User();
					user.setName(rs.getString("name"));
					user.setPsw(rs.getString("psw"));
					user.setOrderId(rs.getInt("orderId"));
					user.setAuthority(rs.getInt("Authority")==0);
					users.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, rs);
			}
		}
		return users;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
