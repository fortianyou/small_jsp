package wanczy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			// 加载MySql的驱动类
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@linkin:1521:orcl";
		String username = "linkin";
		String password = "linkin";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}

		Statement stmt = con.createStatement();
		String sql = "select * from student";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet resultSet = null;
		resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
			String id = resultSet.getString("sid");
			System.out.println(id);
		}

	}

}
