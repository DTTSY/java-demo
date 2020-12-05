package dengtao.Model.dao.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.regexp.internal.recompile;

import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Order;

public class OrderDaoImpl implements OrderDao{

	@Override
	public int addOrder(Connection conn, Object[] params) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		int rs = 0;
		
		String sql="INSERT INTO `Order`(`orderDate`,`user`, `carId`, `price`, `city`) VALUES(?,?,?,?,?)";
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeUpdate(conn, sql, params, pstm);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, null);
			}
		}
		return rs;
	}

	@Override
	public List<Order> getOrders(Connection conn, String userName) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Order> orders = new ArrayList<>();
		Order order=null;
		
		String sql="SELECT * FROM `Order` WHERE `user`= ?";
		Object[] params= {userName};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				
				while(rs.next()) {
					order=new Order();
					order.setId(rs.getInt("id"));
					order.setOrderDate(T_Dte(rs.getDate("orderDate")));
					order.setUser(rs.getString("user"));
					order.setPrice(rs.getFloat("price"));
					order.setCity(rs.getString("city"));
					order.setCarId(rs.getInt("carId"));
					order.setStatus(rs.getInt("status")!=0);
					orders.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, rs);
			}
		}
		return orders;
	}
	
	public String T_Dte(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

	@Override
	public List<Order> getOrders(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Order> orders = new ArrayList<>();
		Order order=null;
		
		String sql="SELECT * FROM `Order`";
		Object[] params= {};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				
				while(rs.next()) {
					order=new Order();
					order.setId(rs.getInt("id"));
					order.setOrderDate(T_Dte(rs.getDate("orderDate")));
					order.setUser(rs.getString("user"));
					order.setPrice(rs.getFloat("price"));
					order.setCity(rs.getString("city"));
					order.setCarId(rs.getInt("carId"));
					order.setStatus(rs.getInt("status")!=0);
					orders.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, rs);
			}
		}
		return orders;
	}
}
