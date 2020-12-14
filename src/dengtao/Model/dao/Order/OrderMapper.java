package dengtao.Model.dao.Order;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Order;

public interface OrderMapper {
	public List<Order> getOrders();
	public int addOrder(Map<String, Object> info);
	public int modifyOrder(Map<String, Object> info);
	public int deleteOrder(int id);
}
