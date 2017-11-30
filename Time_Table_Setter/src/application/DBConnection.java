package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	private Statement st;  // Ư���� DB�� SQL ������ �����ϴ� ����� �����ϰ� ���ִ� ��ü
	private ResultSet rs;  // ����� ����� �޾ƿ��� ��ü
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
			System.out.println("�����ͺ��̽� ���� ���� : "+e.getMessage());
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
			System.out.println("�����ͺ��̽� �˻� ����  : "+e.getMessage());
		}
		return false;
	}
}
