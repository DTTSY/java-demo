package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import dengtao.Model.pojo.User;
import dengtao.Model.service.user.UserService;
import dengtao.Model.service.user.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do login!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		boolean authority = Boolean.parseBoolean(request.getParameter("authority")) ;
		UserService userService = new UserServiceImpl();
		User user = userService.login(name, psw);
		
		JSONObject data = new JSONObject();
		System.out.println(request.getParameter("authority"));
		System.out.println(authority);
		System.out.println(user);
		if(user!=null && user.getAuthority().equals(authority)) {
			//保存登陆用户的信息
			request.getSession().setAttribute("USER_SESSION",user);
			if(authority)
				data.put("url", "jsp/Admin.jsp");
			else 
				data.put("url", "jsp/UserMain.jsp");
		}else {
			data.put("url", "null");
		}
		response.getWriter().write(data.toString());
		System.out.println(data.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
