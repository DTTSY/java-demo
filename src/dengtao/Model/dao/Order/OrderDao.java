package dengtao.Model.dao.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Order;

public interface OrderDao {
	//jdbc
	public int addOrder(Connection conn, Object[] params);
	public List<Order> getOrders(Connection conn, String userName);
	public List<Order> getOrders(Connection conn);
	//mybatis
	public List<Order> getOrders();
	public int addOrder(Map<String, Object> info);
}
