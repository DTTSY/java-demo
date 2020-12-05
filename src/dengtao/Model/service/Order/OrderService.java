package dengtao.Model.service.Order;

import java.util.List;

import dengtao.Model.pojo.Order;
import dengtao.Model.pojo.User;

public interface OrderService {
	public String leaseCar(Object orderDate, Object carId, Object price, Object city, User user);
	public List<Order> getOrdersByUser(String userName);
	public List<Order> getOrders();
	public String getOrdersJson(List<Order> orders);
}
