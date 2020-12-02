package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/jsp/GetData.do")
public class GetData extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do GetData!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		CarService carService=new CarServiceImpl();
		String rs = null;
		
		String dataName = request.getParameter("dataName");
		switch (dataName) {
		case "availableCars": {
			rs = carService.getAvailableCarsToJSON();
			break;
		}
		default:
			break;
		}
//		System.out.println(rs);
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
