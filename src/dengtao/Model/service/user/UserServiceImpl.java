package dengtao.Model.service.user;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
			user=userDao.getUserByName(connection, name);
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
		int rs = 0;
		
		try {
			connection=BaseDao.getConnection();
			rs=userDao.addUser(connection, name, psw);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(connection, null, null);
		}
		return rs!=0;
	}

	@Override
	public String getUserToJson() {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<User> users= null;
		
		try {
			conn = BaseDao.getConnection();
			users = userDao.getUsers(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn, null, null);
		}
		List<JSONObject> data = new ArrayList<>();
		
		for (User user : users) {
			JSONObject userData = new JSONObject(true);
			userData.put("name", user.getName());
			userData.put("psw", user.getPsw());
			userData.put("authority", user.getAuthority()?"普通用户":"管理员");
			data.add(userData);
		}
		
		JSONObject clo= new JSONObject(true);
		clo.put("name", "用户名");
		clo.put("psw", "用户密码");
		clo.put("authority", "用户权限");
		JSONObject rs= new JSONObject();
		rs.put("col", clo);
		rs.put("list", data);
		
		return JSON.toJSONString(rs);
	}

}
