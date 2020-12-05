package dengtao.Model.service.Order;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.recompile;

import dengtao.Model.dao.Order.OrderDao;
import dengtao.Model.dao.Order.OrderDaoImpl;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Order;
import dengtao.Model.pojo.User;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;

	public OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
	}

	@Override
	public String leaseCar(Object orderDate, Object carId, Object price, Object city, User user) {
		Connection conn=null;
		Object[] params = {orderDate, user.getName(), carId, price, city};
		int rs = 0;
		String json=null;
		try {
			conn = BaseDao.getConnection();
			rs = orderDao.addOrder(conn, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn, null, null);
		}
		
		if (rs!=0){
			json = "{\"ok\": 1}";
		}else {
			json = "{\"ok\": 0}";
		}
		return json;
	}

	@Override
	public List<Order> getOrdersByUser(String userName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		List<Order> orders = null;
		
		try {
			conn=BaseDao.getConnection();
			if (userName!=null) {
				orders=orderDao.getOrders(conn, userName);
			}else {
				orders=orderDao.getOrders(conn);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn, null, null);
		}
		if (orders==null) {
			return null;
		}
		return orders;
	}

	@Override
	public String getOrdersJson(List<Order> orders) {
		// TODO Auto-generated method stub
//		List<Order> orders = getOrdersByUser(userName);
		
		List<JSONObject> data = new ArrayList<>();
		for(Order item: orders) {
			JSONObject orderData=new JSONObject();
			orderData.put("id", item.getId());
			orderData.put("orderDate", item.getOrderDate());
			orderData.put("user", item.getUser());
			orderData.put("carId", item.getCarId());
			orderData.put("price", item.getPrice());
			orderData.put("city", item.getCity());
			orderData.put("status", item.getStatus()?"已还":"未还");
			data.add(orderData);
		}
	
		JSONObject clo= new JSONObject(true);
		clo.put("id", "订单编号");
		clo.put("orderDate", "取车时间");
		clo.put("user", "租车用户");
		clo.put("carId", "车辆ID");
		clo.put("price", "租车费用");
		clo.put("city", "取车地点");
		clo.put("status","租借状态" );
		JSONObject rs=new JSONObject();
		rs.put("col", clo);
		rs.put("list", data);
		
		return JSON.toJSONString(rs);
	}

	@Override
	public List<Order> getOrders() {
		
		return getOrdersByUser(null);
	}

}
