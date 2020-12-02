package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeaseCar
 */
@WebServlet("/jsp/LeaseCar.do")
public class LeaseCar extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do LeaseCar.do!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println(request.getParameter("city"));
		System.out.println(request.getParameter("region"));
		System.out.println(request.getParameter("rentDays"));
		System.out.println(request.getParameter("car"));
		System.out.println(request.getParameter("date"));
		System.out.println(request.getParameter("total"));
		
		response.getWriter().write("{\"ok\": 1}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
