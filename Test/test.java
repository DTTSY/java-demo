import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dengtao.Model.dao.Car.CarMapper;
import dengtao.Model.dao.Fix.FixMapper;

import dengtao.Model.dao.Order.OrderMapper;
import dengtao.Model.dao.User.UserDao;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Car;
import dengtao.Model.pojo.Fix;
import dengtao.Model.pojo.Order;
import dengtao.Model.pojo.User;
import dengtao.Model.service.Order.OrderService;
import dengtao.Model.service.Order.OrderServiceImpl;
import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;
import dengtao.Model.service.fix.FixService;
import dengtao.Model.service.fix.FixServiceImpl;


public class test {
	public static void main(String[] args) {
//		testcar();
//		testorder();
//		CarService carService=new CarServiceImpl();
//		System.out.println(carService.getCarsToJSON());
//		OrderService orderService=new OrderServiceImpl();
//		System.out.println(orderService.getOrdersJson());
//		testUser();
//		testfix();
		FixService fixService=new FixServiceImpl();
//		System.out.println(fixService.getFixs());
//		CarService carService=new CarServiceImpl();
		Map<String, Object> orderMap=new HashMap<>();
		orderMap.put("begingDate", "2020-01-01");
		orderMap.put("endDate", "2020-12-21");
		System.out.println(fixService.getFixByDate(orderMap));
//		carMap.put("name", "t78est1");
//		carMap.put("color", "t45est1");
//		carMap.put("type", "te45st1");
//		carMap.put("price", Float.parseFloat("250"));
//		carMap.put("status", "test0");
//		carMap.put("oldId", 23);
	}
	
	public static void testcar() {
		//获得SqlSession
		SqlSession sqlSession=MybatisUtils.getSqlSession();
		
		//getMapper 执行sql
		CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
		List<Car> cars = carMapper.getCars();
		
		for (Car car : cars) {
			System.out.println(car);
		}
		
		//close
		sqlSession.close();
		
	}
	public static void testorder() {
		//获得SqlSession
		SqlSession sqlSession=MybatisUtils.getSqlSession();
		
		//getMapper 执行sql
		SqlSession session = MybatisUtils.getSqlSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);
		List<Order> orders=orderMapper.getOrders();
		
		for (Order order : orders) {
			System.out.println(order);
		}
		//close
		sqlSession.close();
		
	}
	
	public static void testUser() {
		//获得SqlSession
		SqlSession sqlSession=MybatisUtils.getSqlSession();
		
		//getMapper 执行sql
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> orders = userDao.getUsers();
		User user1 =userDao.getUserByName("admin2");
		System.out.println(user1);
		System.out.println("*************************");
		for (User user : orders) {
			System.out.println(user);
		}
		//close
		sqlSession.close();
		
	}
	
	public static void testfix() {
		//获得SqlSession
		SqlSession sqlSession=MybatisUtils.getSqlSession();
		
		//getMapper 执行sql
		FixMapper fixdao= sqlSession.getMapper(FixMapper.class);
		List<Fix> fixs=fixdao.getFixes();
		for (Fix fix : fixs) {
			System.out.println(fix);
		}
		//close
		sqlSession.close();
		
	}
}
