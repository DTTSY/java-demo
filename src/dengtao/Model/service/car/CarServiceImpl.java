package dengtao.Model.service.car;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dengtao.Model.dao.Car.CarDao;
import dengtao.Model.dao.Car.CarDaoImpl;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Car;
import sun.security.timestamp.TSRequest;

public class CarServiceImpl implements CarService{
	private CarDao carDao; 
		
	public CarServiceImpl() {
		carDao = new CarDaoImpl();
	}

	@Override
	public List<Car> getAvailableCars() {
		Connection conn=null;
		List<Car> cars=null;
		
		try {
			conn=BaseDao.getConnection();
			cars=carDao.getCars(conn, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn, null, null);
		}
		if (cars==null) {
			return null;
		}
		return cars;
	}

	@Override
	public String getAvailableCarsToJSON() {
		// TODO Auto-generated method stub
		List<Car> cars = getAvailableCars();
		List<JSONObject> data = new ArrayList<>();
		for (Car car : cars) {
			JSONObject carsData = new JSONObject();
			carsData.put("label", car.getName()+"/"+car.getColor()+"/"+car.getType()+" ￥"+car.getPrice()+"/天" );
			carsData.put("value", car.getId());
			carsData.put("price", car.getPrice());
			data.add(carsData);
		}
		JSONObject rs = new JSONObject();
		
		if (JSON.toJSONString(data) != null) {
			rs.put("data", data);
			rs.put("ok", 1);
		}else {
			rs.put("ok", 0);
		}
		return JSON.toJSONString(rs);
	}

}
