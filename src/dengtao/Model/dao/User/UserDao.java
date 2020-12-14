package dengtao.Model.dao.User;

import java.sql.Connection;
import java.util.List;
import dengtao.Model.pojo.User;

public interface UserDao {
	//jdbc
	public User getUserByName(Connection conn, String userName);
	public  int addUser(Connection conn, String name, String psw);
	public List<User> getUsers(Connection conn);
	
	//mybatis
	public List<User> getUsers();
	public User getUserByName(String userName);
}
