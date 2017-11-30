package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	private Statement st;  // 특정한 DB의 SQL 문장을 실행하는 방법을 가능하게 해주는 객체
	private ResultSet rs;  // 실행된 결과를 받아오는 객체
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/Tutorial";
	private final String USER ="root";
	private final String PW = "root";
	
	public DBConnection() {
		try 
		{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PW);
			st = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 연결 오류 : "+e.getMessage());
		}
	}
	
	public boolean isAdmin(String adminID, String adminPassword) {
		try
		{
			String SQL = "SELECT * FROM ADMIN WHERE adminID = '" + adminID + "' and adminPassword = '" + adminPassword + "'";
			rs = st.executeQuery(SQL);
			if(rs.next())
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류  : "+e.getMessage());
		}
		return false;
	}
}
