package dengtao.Model.dao.Order;

import java.sql.Connection;
import java.util.List;
import dengtao.Model.pojo.Order;

public interface OrderDao {
	public int addOrder(Connection conn, Object[] params);
	
	public List<Order> getOrders(Connection conn, String userName);
	
	public List<Order> getOrders(Connection conn);
	
}
