package dengtao.Model.dao.User;

import java.sql.Connection;

import dengtao.Model.pojo.User;

public interface UserDao {
	public User getUser(Connection conn, String userId);

	public  boolean addUser(Connection conn, String name, String psw);

}
