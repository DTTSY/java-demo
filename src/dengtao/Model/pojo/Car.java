package dengtao.Model.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Car {
	@JSONField(name="车辆编号", ordinal = 1)
	private Integer id;
	
	@JSONField(name="车辆名称", ordinal = 2)
	private String name;
	
	@JSONField(name="车辆颜色", ordinal = 3)
	private String color;
	
	@JSONField(name="车辆类型", ordinal = 4)
	private String type;
	
	@JSONField(name="车辆计价", ordinal = 5)
	private Float price;
	
	@JSONField(name="车辆状态", ordinal = 6)
	private String status;
	
	public Car() {
		super();
	}
	
	public Car(Integer id, String name, String color, String type, Float price, String status) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.type = type;
		this.price = price;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", color=" + color + ", type=" + type + ", price=" + price
				+ ", status=" + status + "]";
	}
	
	
	
}
