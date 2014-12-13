package wanczy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wanczy.entity.Wanczy_Student;

public class StudentDao extends BaseDao {
	
	public List findAll(){
		Connection conn = getConnection();
		ResultSet resultSet = null;
		String sql = "select * from wanczy_student";
		PreparedStatement pstmt = null;
		List resultList = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String pwd = resultSet.getString("pwd");
				Wanczy_Student s = new Wanczy_Student(id, name, pwd);
				resultList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	public Wanczy_Student findById(){
		return null;
	}
	public boolean add(){
		return false;
	}
	
	
}
