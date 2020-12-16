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
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;
import dengtao.Model.service.fix.FixService;
import dengtao.Model.service.fix.FixServiceImpl;

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
		switch (dataName) {
		case "availableCars": {
			CarService carService = new CarServiceImpl();
			rs = carService.getAvailableCarsToJSON();
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
			Object tempObject = request.getSession().getAttribute("USER_SESSION");
			
			if(tempObject != null) {
				User user = (User)tempObject;
				OrderService orderService = new OrderServiceImpl();
				rs = orderService.getOrdersJson(orderService.getOrdersByUser(user.getName()));
			}else {
				rs = null;
			}
			break;
		}
		default:{
			rs = "null";
			break;
			}
		}
		System.out.println("DataName: " + dataName);
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
