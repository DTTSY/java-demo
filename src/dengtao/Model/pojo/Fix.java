package dengtao.Model.pojo;

import java.util.Date;

public class Fix {
	private Integer id;
	private Date fixDate;
	private String F_case;
	private Integer carId;
	private Float price;
	private String fixedDate;

	public Fix() {
		super();
	}

	public Fix(Integer id, Date fixDate, String f_case, Integer carId, Float price, String fixedDate) {
		super();
		this.id = id;
		this.fixDate = fixDate;
		F_case = f_case;
		this.carId = carId;
		this.price = price;
		this.fixedDate = fixedDate;
	}

	public String getFixedDate() {
		return fixedDate;
	}

	public void setFixedDate(String fixedDate) {
		this.fixedDate = fixedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFixDate() {
		return fixDate;
	}

	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}

	public String getF_case() {
		return F_case;
	}

	public void setF_case(String f_case) {
		F_case = f_case;
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

	@Override
	public String toString() {
		return "Fix [id=" + id + ", fixDate=" + fixDate + ", F_case=" + F_case + ", carId=" + carId + ", price=" + price
				+ "]";
	}

}
