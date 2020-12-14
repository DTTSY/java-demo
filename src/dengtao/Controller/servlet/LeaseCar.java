package dengtao.Controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
		
		String rs = null;
		User user = (User)request.getSession().getAttribute("USER_SESSION");
		System.out.println(user.getName());
		
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("beginDate", request.getParameter("beginDate"));
		orderMap.put("endDate", request.getParameter("endDate"));
		orderMap.put("user", user.getName());
		orderMap.put("carId", request.getParameter("carId"));
		orderMap.put("price", request.getParameter("price"));
		orderMap.put("total", request.getParameter("total"));
		orderMap.put("city", request.getParameter("city"));
		orderMap.put("rentDays", request.getParameter("rentDays"));
		OrderService orderService=new OrderServiceImpl();
		rs = orderService.addOrder(orderMap);
		
		response.getWriter().write(rs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
