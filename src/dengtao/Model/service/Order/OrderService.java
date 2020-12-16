package dengtao.Model.service.Order;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Order;
import dengtao.Model.pojo.User;

public interface OrderService {
	public String leaseCar(Object orderDate, Object carId, Object price, Object city, User user);
	public List<Order> getOrdersByUser(String userName);
	public String getOrdersJson(List<Order> orders);
	
	//mybatis
	public String addOrder(Map<String, Object> info);
	public String getOrdersJson();
	public String modifyOrder(Map<String, Object> info);
	public String deleteOrder(int id);
	public String getMyorders(String name);
}
