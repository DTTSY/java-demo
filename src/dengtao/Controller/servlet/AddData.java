package dengtao.Controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.service.Order.OrderService;
import dengtao.Model.service.Order.OrderServiceImpl;
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/jsp/AddData.do")
public class AddData extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do AddData.do!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String rs = null;
		Map<String, Object> DataMap = new HashMap<>();
		
		String AddType = request.getParameter("AddType");
		switch (AddType) {
		case "addCar": {
			CarService carService = new CarServiceImpl();
			
			DataMap.put("id", request.getParameter("id"));
			DataMap.put("name", request.getParameter("name"));
			DataMap.put("color", request.getParameter("color"));
			DataMap.put("type", request.getParameter("type"));
			DataMap.put("price", request.getParameter("price"));
			rs = carService.addCar(DataMap);
			break;
		}
		case "addOrder":{
			OrderService orderService = new OrderServiceImpl();
			DataMap.put("id", request.getParameter("id"));
			rs=orderService.addOrder(DataMap);
			break;
		}
		default:
			break;
		}
		System.out.println(rs);
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
