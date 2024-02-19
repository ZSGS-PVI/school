package schoolbookpannel.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class preparedstatement {
	public static void main(String[] args) {
		   String url = "jdbc:mysql://localhost:3306/schoolManagementconsole";
	        String username = "Kalpana";
	        String password = "Dev@2277";
	

	try(Connection connection=DriverManager.getConnection(url,username,password)){
		String insertQuery="INSERT INTO Students(rollno,name,DOB,mobileno,email) VALUES(?,?,?,?,?)";
		try(PreparedStatement preparedStatement=connection.prepareStatement(insertQuery)){
//			preparedStatement.setInt(1,);
//			preparedStatement.setString(2,);
//			preparedStatement.setString(3,);
//			preparedStatement.setString(4,);
//			preparedStatement.setString(5,);
			
			int rowsAffected=preparedStatement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("student inserted successfully");
			}
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	
	}
	
}

