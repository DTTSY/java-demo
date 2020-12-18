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
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

/**
 * Servlet implementation class LeaseCar
 */
@WebServlet("/jsp/LeaseCar.do")
public class LeaseCar extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do LeaseCar.do!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String rs = null;
		Map<String, Object> orderMap = new HashMap<>();
		Map<String, Object> carMap = new HashMap<>();
		String action = request.getParameter("action");
		CarService carService = new CarServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		User user = (User) request.getSession().getAttribute("USER_SESSION");
		
		switch (action) {
		case "lease": {
			orderMap.put("beginDate", request.getParameter("beginDate"));
			orderMap.put("user", user.getName());
			orderMap.put("carId", request.getParameter("carId"));
			orderMap.put("price", request.getParameter("price"));		
			orderMap.put("city", request.getParameter("city"));
			carMap.put("carId", request.getParameter("carId"));
			carMap.put("status", "租出未还");
			
			carService.modifyCar(carMap);
			rs = orderService.addOrder(orderMap);
			break;
		}
		case "repay":{
			orderMap.put("id", request.getParameter("id"));
			orderMap.put("total", request.getParameter("total"));
			orderMap.put("repayDate", request.getParameter("repayDate"));
			orderMap.put("days", request.getParameter("days"));
			carMap.put("carId", request.getParameter("carId"));
			carMap.put("status", "可租");
			carService.modifyCar(carMap);
			rs = orderService.modifyOrder(orderMap);
			break;
		}
		default:{
			rs = "null";
			break;
		}
			
		}

		response.getWriter().write(rs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
