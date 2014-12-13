package wanczy.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wanczy.entity.Teacher;

public class BaseDao {
	private String url = "jdbc:oracle:thin:@linkin:1521:orcl";
	private String username = "linkin";
	private String password = "linkin";

	public Connection getConnection() {
		Connection conn = null;
		try {
			// 加载MySql的驱动类
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return conn;
	}

	public List find(Object o) throws Exception {
		//获取类
		Class clazz = o.getClass();
		// 获取类的所有属性，返回Field数组
		Field[] fields = clazz.getDeclaredFields();
		
		StringBuffer sql = new StringBuffer("select ");
		for (Field field : fields) {
			sql.append(field.getName()+",");
		}
		sql = new StringBuffer( sql.substring(0,sql.length()-1));
		sql.append(" from	" + clazz.getSimpleName());
		sql.append(" where 1=1	");
		
		//再遍历一边添加where条件
		for (Field field : fields) {
			//如果属性有值
			 PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			 Method m = pd.getReadMethod();//获得读方法
			 
			 if(m.invoke(o)  != null){
					sql.append(" and " + field.getName()+" = " + m.invoke(o));
			 }
		}
		System.out.println(sql.toString());
		

		//获得数据库连接
		Connection conn = getConnection();
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		List resultList = new ArrayList();
		pstmt = conn.prepareStatement(sql.toString());
		resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
			Object instance = clazz.newInstance();
			
			for (Field field : fields) {
				if(field.getType().toString().equals("class java.lang.String")){
					 String val = resultSet.getString(field.getName());
					 PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
					 Method m = pd.getWriteMethod();//获得写方法
					 m.invoke(instance, val);
				}
			}
			resultList.add(instance);
		}
		return resultList;
	}
	
	
	
	public int insert(Object o) throws Exception {
		//获取类
		Class clazz = o.getClass();
		// 获取类的所有属性，返回Field数组
		Field[] fields = clazz.getDeclaredFields();
		
		StringBuffer sql = new StringBuffer("insert into " + clazz.getSimpleName());
		sql.append(" ( ");
		for (Field field : fields) {
			//如果属性有值
			 PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			 Method m = pd.getReadMethod();//获得读方法
			 
			 if(m.invoke(o)  != null){
					sql.append(field.getName()+" ,");
			 }
		}
		sql = new StringBuffer( sql.substring(0,sql.length()-1));
		sql.append(" ) ");
		System.out.println(sql.toString());
		sql.append(" values ");
		//再遍历一边添加values
		sql.append(" ( ");
		for (Field field : fields) {
			//如果属性有值
			 PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			 Method m = pd.getReadMethod();//获得读方法
			 
			 if(m.invoke(o)  != null){
					sql.append( (String)m.invoke(o) + " ,");
			 }
		}
		sql = new StringBuffer( sql.substring(0,sql.length()-1));
		sql.append(" ) ");
		System.out.println(sql.toString());
		

		//获得数据库连接
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(sql.toString());
		int result = pstmt.executeUpdate();
		return result;
	}

	public static void main(String[] args) throws Exception {
		BaseDao bd = new BaseDao();
		Teacher t = new Teacher("1","2","3");
		bd.find(t);
	}
}
