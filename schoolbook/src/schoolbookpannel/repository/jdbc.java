package schoolbookpannel.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import schoolbookpannel.dto.Student;

public class jdbc {
	
	
	
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/schoolManagementconsole";
        String username = "Kalpana";
        String password = "Dev@2277";
    
        // INSERT operation
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO studentInfo(rollno,name,DOB,mobileno,email) VALUES(?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            	
            	Scanner in=new Scanner(System.in);
            	System.out.println("Enter the rollno,name,dob,mobileno,email");
            	int rollno=in.nextInt();
            	String name=in.next();
            	String DOB=in.next();
            	String mobileno=in.next();
            	String email=in.next();
                preparedStatement.setInt(1,rollno);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, DOB);
                preparedStatement.setString(4, mobileno);
                preparedStatement.setString(5,email);
           

                	 int rowsAffected = preparedStatement.executeUpdate();

                     if (rowsAffected > 0) {
                         System.out.println("Student inserted successfully");

                         // Display inserted student details
                         displayStudentDetails(connection, rollno);
            }
                     
                    
                     // Get the number of columns in the ResultSet
//                     int columnCount = metaData.getColumnCount();
//                     System.out.println("Number of columns: " + columnCount);
//
//                     // Print column names and types
//                     for (int i = 1; i <= columnCount; i++) {
//                         System.out.println("Column Name: " + metaData.getColumnName(i));
//                         System.out.println("Column Type: " + metaData.getColumnTypeName(i));
//                         System.out.println("----------");
//                     }
            }
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
	}
	




    private static void displayStudentDetails(Connection connection, int rollNo) throws SQLException {
        String selectQuery = "SELECT * FROM studentInfo WHERE rollno=?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, rollNo);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int rollno = resultSet.getInt("rollno");
                    String name = resultSet.getString("name");
                    String DOB = resultSet.getString("DOB");
                    String mobileno = resultSet.getString("mobileno");
                    String email = resultSet.getString("email");

                    System.out.print("\nRollNo:" + rollno + "\n Name: " + name + "\n DOB:" + DOB +
                            "\n MobileNo:" + mobileno + "\n Email: " + email);
                }
            }
        }
    }
}



