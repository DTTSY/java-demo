package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.pojo.User;
import dengtao.Model.service.Order.OrderService;
import dengtao.Model.service.Order.OrderServiceImpl;

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
		
		OrderService orderService=new OrderServiceImpl();
		orderService.leaseCar(request.getParameter("date"), request.getParameter("car"), request.getParameter("total"), request.getParameter("city")+"/"+request.getParameter("region"), (User)request.getSession().getAttribute("USER_SESSION"));
		
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
