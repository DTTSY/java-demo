package dengtao.Model.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class User implements Serializable{
	@JSONField(name="用户名", ordinal = 1)
	private String name;
	
	@JSONField(name="密码", ordinal = 2)
	private String psw;
	
	@JSONField(name="订单编号", ordinal = 3)
	private Integer orderId;
	
	@JSONField(name="权限", ordinal = 4)
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
