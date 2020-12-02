package dengtao.Model.service.user;

import java.sql.Connection;

import dengtao.Model.dao.User.UserDao;
import dengtao.Model.dao.User.UserDaoImpl;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.User;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public User login(String name, String psw) {
		Connection connection=null;
		User user=null;
		
		try {
			connection=BaseDao.getConnection();
			user=userDao.getUser(connection, name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(connection, null, null);
		}
		if (user==null || !user.getPsw().equals(psw)) {
			return null;
		}
		return user;
	}

	@Override
	public boolean signUp(String name, String psw) {
		Connection connection=null;
		boolean rs = false;
		
		try {
			connection=BaseDao.getConnection();
			rs=userDao.addUser(connection, name, psw);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(connection, null, null);
		}
		return rs;
	}
}
