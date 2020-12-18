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
import dengtao.Model.service.fix.FixService;
import dengtao.Model.service.fix.FixServiceImpl;
import dengtao.Model.service.profit.profitService;
import dengtao.Model.service.profit.profitServiceImpl;
import sun.text.normalizer.Trie.DataManipulate;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/jsp/GetData.do")
public class GetData extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do GetData!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String rs = null;

		String dataName = request.getParameter("dataName");
		System.out.println("DataName: " + dataName);
		switch (dataName) {
		case "carOptions":{
			CarService carService=new CarServiceImpl();
			rs = carService.getAvailableCarsToJSON();
			break;
		}
		case "availableCars": {
			CarService carService = new CarServiceImpl();
			rs = carService.getCarsByStatus("可租");
			break;
		}
		case "fixingCars": {
			CarService carService = new CarServiceImpl();
			rs = carService.getCarsByStatus("维修");
			break;
		}
		case "UnderFixing":{
			FixService fixService=new FixServiceImpl();
			rs = fixService.getUnderFixing();
			break;
		}
		case "Orders": {
			OrderService orderService = new OrderServiceImpl();
			rs=orderService.getOrdersJson();
			break;
		}
		case "Cars": {
			CarService carService = new CarServiceImpl();
			rs = carService.getCarsToJSON();
			break;
		}
		case "Fixs": {
			FixService fixService=new FixServiceImpl();
			rs=fixService.getFixs();
			break;
		}
		case "myOrders": {
			int option = Integer.parseInt(request.getParameter("option"));
			User user = (User)request.getSession().getAttribute("USER_SESSION");
			OrderService orderService=new OrderServiceImpl();
			rs = orderService.getMyorders(user.getName(), option);
			break;
		}
		case "profit":{
			Map<String, Object> dateMap=new HashMap<>();
			dateMap.put("begingDate", request.getParameter("begingDate"));
			dateMap.put("endDate", request.getParameter("endDate"));
			profitService profitService=new profitServiceImpl();
			rs = profitService.getProfit(dateMap);
			break;
		}
		default:{
			rs = "null";
			break;
			}
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
