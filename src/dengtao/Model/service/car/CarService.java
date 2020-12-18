package dengtao.Model.service.car;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Car;

public interface CarService {
	//jdbc
	public List<Car> getAvailableCars();
	public String getAvailableCarsToJSON();
	public String getCarsToJSON();
	//mybatis
	public String addCar(Map<String, Object> info);
	public String deleteCar(int id);
	public String modifyCar(Map<String, Object> info);
	public String getCarsByStatus(String status);
	
}
