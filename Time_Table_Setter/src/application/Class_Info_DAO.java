package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import application.model.Class_Info;

public class Class_Info_DAO {
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/timetabledb";
	Connection conn;
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	Vector<String> items = null;
	String sql;
	
	public Vector<String> getItems(){
		return items;
	}
	
	public ArrayList<Class_Info> getAll(){
		connectDB();
		sql = "select * from classinfo";
		
		ArrayList<Class_Info> datas = new ArrayList<Class_Info>();
		
		items = new Vector<String>();
		items.add("ÀüÃ¼");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Class_Info c = new Class_Info();
				c.setClassNum(rs.getString("classnum"));
				c.setGrade(rs.getInt("grade"));
				c.setSubdiv(rs.getString("subdiv"));
				c.setClassName(rs.getString("classname"));
				c.setProfessor(rs.getString("professor"));
				c.setCredit(rs.getInt("credit"));
				c.setDayInfo(rs.getString("classtime"));
				datas.add(c);
				items.add(String.valueOf(rs.getString("classnum")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			closeDB();
		}
		return datas;
	}
	
	
	
	
	public void connectDB() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "root", "root");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
}
