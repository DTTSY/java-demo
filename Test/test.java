import java.util.Date;

import dengtao.Model.service.Order.OrderService;
import dengtao.Model.service.Order.OrderServiceImpl;
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

public class test {
	public static void main(String[] args) {
		OrderService orderService=new OrderServiceImpl();
		System.out.println(orderService.getOrders());
	}
}
