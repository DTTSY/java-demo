package dengtao.Model.service.car;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dengtao.Model.dao.Car.CarDao;
import dengtao.Model.dao.Car.CarDaoImpl;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Car;
import sun.net.www.content.text.plain;
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

	@Override
	public String getCarsToJSON() {
		Connection conn=null;
		List<Car> cars=null;
		List<JSONObject> data = new ArrayList<>();
		try {
			conn=BaseDao.getConnection();
			cars=carDao.getCars(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(conn, null, null);
		}
		
		if (cars==null) {
			return null;
		}
		
		for (Car car : cars) {
			JSONObject carData= new JSONObject();
			carData.put("id", car.getId());
			carData.put("name", car.getName());
			carData.put("color", car.getColor());
			carData.put("type", car.getType());
			carData.put("price", car.getPrice());
//			String string = null;
			carData.put("status", car.getStatus()==0?"可借":"已借");	
			data.add(carData);
		}
		JSONObject clo= new JSONObject(true);
		clo.put("id", "车辆编号");
		clo.put("name", "车辆名称");
		clo.put("color", "车辆颜色");
		clo.put("type", "车辆类型");
		clo.put("price", "车辆计价");
		clo.put("status","租借状态" );
		JSONObject rs= new JSONObject();
		rs.put("col", clo);
		rs.put("list", data);
		
		return JSON.toJSONString(rs);
	}

}
