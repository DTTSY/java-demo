package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import dengtao.Model.service.user.UserService;
import dengtao.Model.service.user.UserServiceImpl;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp.do")
public class SignUp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do SignUp!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");
		
		UserService userService=new UserServiceImpl();
		JSONObject data = new JSONObject();
		
		if (userService.signUp(name, psw)) {
			data.put("state", 1);
		}else {
			data.put("state", 0);
		}
		
		response.getWriter().write(data.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
