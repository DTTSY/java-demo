package dengtao.Model.dao.User;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User getUser(Connection conn, String userId) {
		PreparedStatement pstm = null;
		ResultSet rs=null;
		User user=null;

		String sql="SELECT * FROM `User` WHERE `name`=?";
		Object[] params = {userId};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				if (rs.next()) {
					user=new User();
					user.setName(rs.getString("name"));
					user.setPsw(rs.getString("psw"));
					user.setOrderId(rs.getInt("orderId"));
					user.setAuthority(rs.getInt("Authority")==0);
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
	public boolean addUser(Connection conn, String name, String psw) {
		PreparedStatement pstm = null;
		int rs;
		
		String sql="INSERT INTO `User`(`name`,`psw`) VALUES(?,?)";
		Object[] params = {name, psw};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeUpdate(conn, sql, params, pstm);
				if(rs!=0)
					return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, null);
			}
		}
		return false;
	}
	

}
