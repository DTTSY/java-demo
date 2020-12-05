package dengtao.Model.pojo;

public class Order {
	private Integer id;
	private String orderDate;
	private String user;
	private Integer carId;
	private Float price;
	private String city;
	private Boolean status; 
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
