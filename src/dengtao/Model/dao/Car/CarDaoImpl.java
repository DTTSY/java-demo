package dengtao.Model.dao.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.regexp.internal.recompile;

import dengtao.Model.dao.util.BaseDao;
import dengtao.Model.pojo.Car;
import dengtao.Model.pojo.User;

public class CarDaoImpl implements CarDao{

	@Override
	public List<Car> getCars(Connection conn, int status) {
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Car> cars=new ArrayList<>();
		Car car=null;
		
		String sql="SELECT * FROM `Car` WHERE `status`= ?";
		Object[] params = {status};
		
		if (conn!=null) {
			try {
				rs = BaseDao.executeQuery(conn, sql, params, pstm, rs);
				if (rs.next()) {
					car=new Car();
					car.setId(rs.getInt("id"));
					car.setName(rs.getString("name"));
					car.setColor(rs.getString("color"));
					car.setPrice(rs.getFloat("price"));
					car.setType(rs.getString("type"));
					car.setStatus(rs.getInt("status"));
					cars.add(car);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				BaseDao.close(null, pstm, rs);
			}
		}
		
		return cars;
	}

}
