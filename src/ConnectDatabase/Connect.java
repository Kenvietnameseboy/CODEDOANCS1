package ConnectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" + "databaseName=QUANLITHUCHIHOCPHI;" + "user=sa;"
					+ "password=123456789;" + "encrypt=true;" + "trustServerCertificate=true;" + "loginTimeout=30;");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		
//		//sửa
//		
//		try {
//			PreparedStatement pstm2 = c.prepareStatement("UPDATE Books SET BookTitle = ? where BookID = ? ");
//			pstm2.setString(1, "Giày");
//			
//			pstm2.setInt(2, 3);
//			
//			pstm2.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// xóa
//		
//		try {
//			PreparedStatement pstm2 = c.prepareStatement("Delete Books where BookID = ? ");
//			pstm2.setInt(1, 2);
//			
//			pstm2.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
		// thêm mới

//				try {
//					PreparedStatement pstm1 = c.prepareStatement("INSERT INTO Books(BookID,BookTitle) VALUES (?,?)");
//					pstm1.setInt(1, 5);
//					pstm1.setString(2, "Mũ");
//					
//					pstm1.executeUpdate();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		try {
//			PreparedStatement pstm = c.prepareStatement("Select CourseName from Courses");
//			ResultSet rs = pstm.executeQuery();
//
//			while (rs.next()) {
//				System.out.println("id : " + rs.getInt("BookID"));
//				System.out.println("name : " + rs.getString("BookTitle"));
//				System.out.println("status:" + rs.getBoolean("CopyRight"));
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			// Bước 1 kết nối csdl
			Connection connection = Connect.getConnection();
			System.out.println(connection);
			// Bước 2 tạo đối tượng stament
			Statement st = connection.createStatement();
			// Bước 3 thực thi một câu lệnh SQL
//			String sql = "";
//			st.executeUpdate(sql);
			// Bước 4 xử lý kết quả

			// Bước 5 ngắt kết nối
			Connect.closeConnection(connection);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
