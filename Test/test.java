import dengtao.Model.service.car.CarService;
import dengtao.Model.service.car.CarServiceImpl;

public class test {
	public static void main(String[] args) {
		CarService carService = new CarServiceImpl();
		System.out.println(carService.getAvailableCarsToJSON());
	}
}
