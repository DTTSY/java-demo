package dengtao.Model.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private String name;
	private String psw;
	private Integer orderId;
	private Boolean authority;
	
	public User() {
		super();
	}
	
	public User(String name, String psw, Integer orderId, Boolean authority) {
		super();
		this.name = name;
		this.psw = psw;
		this.orderId = orderId;
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Boolean getAuthority() {
		return authority;
	}
	public void setAuthority(Boolean authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", psw=" + psw + ", orderId=" + orderId + ", authority=" + authority + "]";
	}
	
}
