package dengtao.Model.service.car;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import dengtao.Model.dao.Car.CarDao;
import dengtao.Model.dao.Car.CarDaoImpl;
import dengtao.Model.dao.Car.CarMapper;
import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Car;
import sun.net.www.content.text.plain;
import sun.security.timestamp.TSRequest;

public class CarServiceImpl implements CarService {
	private CarDao carDao;

	public CarServiceImpl() {
		carDao = new CarDaoImpl();
	}

	@Override
	public List<Car> getAvailableCars() {
		Connection conn = null;
		List<Car> cars = null;

		try {
			conn = BaseDao.getConnection();
			cars = carDao.getCars(conn, "可租");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, null, null);
		}
		if (cars == null) {
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
			carsData.put("label",
					car.getName() + "/" + car.getColor() + "/" + car.getType() + " ￥" + car.getPrice() + "/天");
			carsData.put("value", car.getId());
			carsData.put("price", car.getPrice());
			data.add(carsData);
		}
		JSONObject rs = new JSONObject();

		if (JSON.toJSONString(data) != null) {
			rs.put("data", data);
			rs.put("ok", 1);
		} else {
			rs.put("ok", 0);
		}
		return JSON.toJSONString(rs);
	}

	@Override
	public String getCarsToJSON() {
		Connection conn = null;
		List<Car> cars = null;

		try {
			conn = BaseDao.getConnection();
			cars = carDao.getCars(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, null, null);
		}

		if (cars == null) {
			return null;
		}

		JSONObject col = new JSONObject(true);
		col.put("车辆编号", "车辆编号");
		col.put("车辆名称", "车辆名称");
		col.put("车辆颜色", "车辆颜色");
		col.put("车辆类型", "车辆类型");
		col.put("车辆计价", "车辆计价");
		col.put("车辆状态", "车辆状态");
		JSONObject rs = new JSONObject(true);
		rs.put("col", col);
		rs.put("list", cars);
		return JSON.toJSONString(rs);
	}

	@Override
	public String addCar(Map<String, Object> info) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		CarMapper carMapper = session.getMapper(CarMapper.class);

		JSONObject okJsonObject = new JSONObject();
		try {
			
			carMapper.addCar(info);
			okJsonObject.put("ok", 1);
		} catch (Exception e) {
			// TODO: handle exception
			okJsonObject.put("ok", 0);
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String deleteCar(int id) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		CarMapper carMapper = session.getMapper(CarMapper.class);
		
		JSONObject okJsonObject = new JSONObject();
		try {
			carMapper.deleteCar(id);
			okJsonObject.put("ok", 1);
		} catch (Exception e) {
			// TODO: handle exception
			okJsonObject.put("ok", 0);
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}

		return okJsonObject.toJSONString();
	}

	@Override
	public String modifyCar(Map<String, Object> info) {
		// TODO Auto-generated method stub
		SqlSession session = MybatisUtils.getSqlSession();
		CarMapper carMapper = session.getMapper(CarMapper.class);
		
		JSONObject okJsonObject = new JSONObject();
		try {
			carMapper.modifyCar(info);
			okJsonObject.put("ok", 1);
		} catch (Exception e) {
			// TODO: handle exception
			okJsonObject.put("ok", 0);
			e.printStackTrace();	
		}finally {
			session.commit();
			session.close();
		}
		
		return okJsonObject.toJSONString();
	}

}
