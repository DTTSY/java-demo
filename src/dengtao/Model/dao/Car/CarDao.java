package dengtao.Model.dao.Car;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Car;

public interface CarDao {
	//jdbc
	public List<Car> getCars(Connection conn, String status);
	public List<Car> getCars(Connection conn);
	
	//mybatis
}
