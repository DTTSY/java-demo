package dengtao.Model.service.user;

import dengtao.Model.pojo.User;

public interface UserService {
	public User login(String name, String psw);
	public boolean signUp(String name, String psw);
	public String getUserToJson();
}
