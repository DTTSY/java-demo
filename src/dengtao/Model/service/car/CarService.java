package dengtao.Model.service.car;

import java.util.List;

import dengtao.Model.pojo.Car;

public interface CarService {
	public List<Car> getAvailableCars();
	public String getAvailableCarsToJSON();
}
