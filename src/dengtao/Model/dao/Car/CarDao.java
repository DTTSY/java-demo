package dengtao.Model.dao.Car;

import java.sql.Connection;
import java.util.List;

import dengtao.Model.pojo.Car;

public interface CarDao {
	public List<Car> getCars(Connection conn, int status);
	
	public List<Car> getCars(Connection conn);
}
