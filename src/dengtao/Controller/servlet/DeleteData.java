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
 * Servlet implementation class DeleteData
 */
@WebServlet("/jsp/DeleteData.do")
public class DeleteData extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do DeleteData!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String rs = null;
		String DeleteType = request.getParameter("DeleteType");
		switch (DeleteType) {
		case "deleteCar":{
			CarService carService= new CarServiceImpl();
			System.out.println(request.getParameter("id"));
			rs = carService.deleteCar(Integer.parseInt(request.getParameter("id")));
			break;
		}
		default:
			break;
		}
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
