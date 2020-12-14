package dengtao.Model.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Order {
	@JSONField(name="订单编号", ordinal = 1)
	private Integer id;
	
	@JSONField(name="车辆编号", ordinal = 2)
	private Integer carId;
	
	@JSONField(name="日租金", ordinal = 3)
	private Float price;
	
	@JSONField(name="租车用户", ordinal = 4)
	private String user;
	
	@JSONField(name="起始日期", ordinal = 5)
	private String orderDate;
	
	@JSONField(name="还车日期", ordinal = 6)
	private String repayDate;
	
	@JSONField(name="取车地点", ordinal = 7)
	private String city;
	
	@JSONField(name="租车天数", ordinal = 8)
	private Integer days;
	
	@JSONField(name="租车费用", ordinal = 9)
	private Float total;

	public Order() {
		super();
	}

	public Order(Integer id, String orderDate, String user, Integer carId, Float price, String city, String repayDate,
			Float total, Integer days) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.user = user;
		this.carId = carId;
		this.price = price;
		this.city = city;
		this.repayDate = repayDate;
		this.total = total;
		this.days = days;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", user=" + user + ", carId=" + carId + ", price="
				+ price + ", city=" + city + ", repayDate=" + repayDate + ", total=" + total + ", days=" + days + "]";
	}



}
