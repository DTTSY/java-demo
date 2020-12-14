package dengtao.Model.dao.Car;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Car;

public interface CarMapper {
	public List<Car> getCars();
	public int addCar(Map<String, Object> info);
	public int deleteCar(int id);
	public int modifyCar(Map<String, Object> info);
}
