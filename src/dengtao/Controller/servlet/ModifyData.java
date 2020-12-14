package dengtao.Controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.pojo.Car;
import dengtao.Model.service.Order.OrderService;
import dengtao.Model.service.Order.OrderServiceImpl;
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

/**
 * Servlet implementation class ModifyData
 */
@WebServlet("/jsp/ModifyData.do")
public class ModifyData extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do ModifyData!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String rs = null;
		Map<String, Object> DataMap = new HashMap<>();
		String modifyType = request.getParameter("modifyType");

		switch (modifyType) {
		case "modifyCar": {
			DataMap.put("oldId", Integer.parseInt(request.getParameter("oldId")));
			DataMap.put("newId", Integer.parseInt(request.getParameter("newId")));
			DataMap.put("name", request.getParameter("name"));
			DataMap.put("color", request.getParameter("color"));
			DataMap.put("type", request.getParameter("type"));
			DataMap.put("price", Float.parseFloat(request.getParameter("price")));
			DataMap.put("status", request.getParameter("status"));
			CarService carService=new CarServiceImpl();
			rs = carService.modifyCar(DataMap);
			break;
		}
		case "modifyOrder":{
			DataMap.put("oldId", Integer.parseInt(request.getParameter("oldId")));
			DataMap.put("newId", Integer.parseInt(request.getParameter("newId")));
			DataMap.put("carId", Integer.parseInt(request.getParameter("carId")));
			DataMap.put("price", Float.parseFloat(request.getParameter("price")));
			DataMap.put("user", request.getParameter("user"));
			DataMap.put("orderDate", request.getParameter("orderDate"));
			DataMap.put("repayDate", request.getParameter("repayDate"));
			DataMap.put("city", request.getParameter("city"));
			DataMap.put("days", request.getParameter("days"));
			DataMap.put("total", request.getParameter("total"));
			OrderService orderService=new OrderServiceImpl();
			rs = orderService.modifyOrder(DataMap);
			break;
		}
		default:
			break;
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
