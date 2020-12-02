package dengtao.Model.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author dengtao 创建和关闭数据库连接
 */
public class BaseDao {
//    private static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// MSSQLSERVER数据库引擎
//    private static final String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Experiment";// 数据源 ！！！
//    private static final String Name = "sa";
//    private static final String Pwd = "1234";

	private static final String driverName = "com.mysql.jdbc.Driver";// MySQL数据库引擎
	private static final String dbURL = "jdbc:mysql://140.143.12.227:3306/Experiment";// 数据源 ！！！
	private static final String Name = "DengTao";
	private static final String Pwd = "7C8fKaMif2mKbMet";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbURL, Name, Pwd);
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
		}
		return connection;
	}

	public static ResultSet executeQuery(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement,ResultSet resultSet) throws SQLException{
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		
		for (int i = 0; i < params.length; i++) {
			preparedStatement.setObject(i+1, params[i]);
		}
		//预编译的sql可直接执行
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	public static int executeUpdate(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement) throws SQLException{
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		
		for (int i = 0; i < params.length; i++) {
			preparedStatement.setObject(i+1, params[i]);
		}
		
		int updatRows = preparedStatement.executeUpdate();
		return updatRows;
	}
	
	public  static boolean close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
		boolean flag = true;
		if (resultSet!=null) {
			try {
				resultSet.close();
				resultSet=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		}
		
		if (preparedStatement!=null) {
			try {
				preparedStatement.close();
				preparedStatement=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		}
		
		if (connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
		}
		
		return flag;
	}
        
}