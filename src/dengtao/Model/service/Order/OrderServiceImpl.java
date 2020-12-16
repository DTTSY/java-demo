package dengtao.Model.service.Order;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import dengtao.Model.dao.Order.OrderDao;
import dengtao.Model.dao.Order.OrderDaoImpl;
import dengtao.Model.dao.Order.OrderMapper;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Order;
import dengtao.Model.pojo.User;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;

	public OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
	}

	@Override
	public String leaseCar(Object orderDate, Object carId, Object price, Object city, User user) {
		Connection conn = null;
		Object[] params = { orderDate, user.getName(), carId, price, city };
		int rs = 0;
		String json = null;
		try {
			conn = BaseDao.getConnection();
			rs = orderDao.addOrder(conn, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, null, null);
		}

		if (rs != 0) {
			json = "{\"ok\": 1}";
		} else {
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
			conn = BaseDao.getConnection();
			if (userName != null) {
				orders = orderDao.getOrders(conn, userName);
			} else {
				orders = orderDao.getOrders(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, null, null);
		}
		if (orders == null) {
			return null;
		}
		return orders;
	}

	@Override
	public String getOrdersJson(List<Order> orders) {
		// TODO Auto-generated method stub
//		List<Order> orders = getOrdersByUser(userName);

		List<JSONObject> data = new ArrayList<>();
		for (Order item : orders) {
			JSONObject orderData = new JSONObject();
			orderData.put("id", item.getId());
			orderData.put("orderDate", item.getOrderDate());
			orderData.put("user", item.getUser());
			orderData.put("carId", item.getCarId());
			orderData.put("price", item.getPrice());
			orderData.put("city", item.getCity());
			data.add(orderData);
		}

		JSONObject clo = new JSONObject(true);
		clo.put("id", "订单编号");
		clo.put("orderDate", "取车时间");
		clo.put("user", "租车用户");
		clo.put("carId", "车辆ID");
		clo.put("price", "租车费用");
		clo.put("city", "取车地点");
		JSONObject rs = new JSONObject();
		rs.put("col", clo);
		rs.put("list", data);

		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}

	@Override
	public String getOrdersJson() {
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);
		List<Order> orders = orderMapper.getOrders();
		session.close();
		return orderListToJSON(orders);
	}

	@Override
	public String addOrder(Map<String, Object> info) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);

		JSONObject okJsonObject = new JSONObject();
		try {
			orderMapper.addOrder(info);
			okJsonObject.put("ok", 1);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			okJsonObject.put("ok", 0);
		} finally {
			session.close();
		}

		return okJsonObject.toJSONString();
	}

	@Override
	public String modifyOrder(Map<String, Object> info) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);

		JSONObject okJsonObject = new JSONObject();

		try {
			orderMapper.modifyOrder(info);
			okJsonObject.put("ok", 1);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			okJsonObject.put("ok", 0);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String deleteOrder(int id) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);

		JSONObject okJsonObject = new JSONObject();

		try {
			orderMapper.deleteOrder(id);
			okJsonObject.put("ok", 1);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			okJsonObject.put("ok", 0);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String getMyorders(String name) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);
		List<Order> orders = orderMapper.getMyOrders(name);
		session.close();
		return orderListToJSON(orders);
	}
	
	private String orderListToJSON(List<Order> orders) {
		JSONObject rs = new JSONObject(true);
		JSONObject col = new JSONObject(true);
		col.put("订单编号", "订单编号");
		col.put("车辆编号", "车辆编号");
		col.put("日租金", "日租金");
		col.put("租车用户", "租车用户");
		col.put("起始日期", "起始日期");
		col.put("还车日期", "还车日期");
		col.put("取车地点", "取车地点");
		col.put("租车天数", "租车天数");
		col.put("租车费用", "租车费用");
		rs.put("col", col);
		rs.put("list", orders);
		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}
}


