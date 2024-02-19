package schoolbookpannel.repository;

import java.io.FileWriter;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;



import schoolbookpannel.dto.Student;
import schoolbookpannel.student.AddStudent;

public class SchoolBookPannelRepository {
	
	

	private AddStudent addStudent;

	public void AddStudentViewModel(AddStudent addStudent) {
		this.addStudent = addStudent;
	}

	private static SchoolBookPannelRepository repository;
	 private static final String url = "jdbc:mysql://localhost:3306/schoolManagementconsole";
	    private static final String username = "kalpana";
	    private static final String password = "Dev@2277";
		private List<Student> studentList;

	

	private SchoolBookPannelRepository() {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	}

	public static SchoolBookPannelRepository getInstanse() {
		if (repository == null) {
			repository = new SchoolBookPannelRepository();
		}
		return repository;
	}
	
	public boolean loginChech() {
	    try (Connection connection = DriverManager.getConnection(url, username, password)) {
	        String query = "SELECT COUNT(*) FROM AdminSignup"; // Replace 'AdminTable' with your table name
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0; // If count is greater than 0, admin exists
	            }
	        }
	    }catch(SQLException e) {
			System.out.println("SQL Error: "+e.getMessage());
			System.out.println("SQL Error: "+e.getSQLState());
			System.out.println("SQL Error: "+e.getErrorCode());
			 e.printStackTrace();
		        return false;
		}
	    return false; // Default return if any exception occurs or admin doesn't exist
	}
	

	
	
	
	public boolean signupAdmin(Student student)  {
	    boolean isSuccess = false;
		try(Connection connection=DriverManager.getConnection(url,username,password)){
			String addadmin="INSERT INTO AdminSignup(id,name,email,password) VALUES(?,?,?,?)";
			try(PreparedStatement preparedStstement=connection.prepareStatement(addadmin)){
				 preparedStstement.setInt(1, student.getId());
				 preparedStstement.setString(2, student.getName());
				 preparedStstement.setString(3, student.getEmail());
				 preparedStstement.setString(4, student.getPassword());
				
					
					int rowsAffected= preparedStstement.executeUpdate();
					if(rowsAffected>0) {
					
						
						 isSuccess = true;
					}
			}
		}
		catch(SQLException e) {
			addStudent.notifications("SQL Error: "+e.getMessage());
			addStudent.notifications("SQL Error: "+e.getSQLState());
			addStudent.notifications("SQL Error: "+e.getErrorCode());
			 e.printStackTrace();
			 return false; 
		}
		 return false;
	}
	
	public boolean loginAdmin(Student student) {
		
		try (Connection connection=DriverManager.getConnection(url,username,password)){
			String query="SELECT * FROM AdminSignup WHERE  email=? AND password=?";
			try(PreparedStatement preparedStatement=connection.prepareCall(query)){
				preparedStatement.setString(1,student.getEmail());
				preparedStatement.setString(2,student.getPassword());
				ResultSet resultSet=preparedStatement.executeQuery();
				return resultSet.next();
				
			}
		}
		catch(SQLException e) {
			addStudent.notifications("SQL Error: "+e.getMessage());
			addStudent.notifications("SQL Error: "+e.getSQLState());
			addStudent.notifications("SQL Error: "+e.getErrorCode());
			 e.printStackTrace();
		        return false;
		}
	}

	// 1.add
	public void insertStudent(Student Student) {
		try(Connection connection=DriverManager.getConnection(url,username,password)){
			String insertQuery="INSERT INTO Students(rollno,name,DOB,mobileno,email) VALUES(?,?,?,?,?)";
			try(PreparedStatement preparedStatement=connection.prepareStatement(insertQuery)){
				preparedStatement.setInt(1,Student.getRollNo());
				preparedStatement.setString(2,Student.getName());
				preparedStatement.setString(3,Student.getDOB());
				preparedStatement.setString(4,Student.getMobileno());
				preparedStatement.setString(5,Student.getEmail());
				
				int rowsAffected=preparedStatement.executeUpdate();
				if(rowsAffected>0) {
					//addStudent.notifications("student inserted successfully");
					System.out.println("student inserted successfully...");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		//studentList.add(Student);
	
		//json();
		
	}

	// 2.view
	public List<Student> getStudents() {
		 List<Student> studentList = new ArrayList<Student>();
		 try(Connection connection=DriverManager.getConnection(url,username,password)){
			 String selectQuery="SELECT * FROM Students";
			 try(Statement statement=connection.createStatement();
					 ResultSet resultSet=statement.executeQuery(selectQuery)){
				 while(resultSet.next()) {
					 Student student=new Student(
						 resultSet.getInt("rollNo"),
							 resultSet.getString("name"),
							 resultSet.getString("DOB"),
							 resultSet.getString("mobileno"),
							 resultSet.getString("email")
							 );
//					 System.out.println(student.getName());
//						System.out.println(student.getRollNo());
//						System.out.println(student.getDOB());
//						System.out.println(student.getMobileno());
//						System.out.println(student.getEmail());
					 studentList.add(student);
				 }
			 }
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		
		return studentList;
	}

	 public void updateStudent(Student student) {
		    try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    	String updateQuery = "UPDATE Students SET name = ?, DOB = ?, mobileno = ?, email = ? WHERE rollNo = ?";
		    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
		    	    preparedStatement.setString(1, student.getName());
		    	    preparedStatement.setString(2, student.getDOB());
		    	    preparedStatement.setString(3, student.getMobileno());
		    	    preparedStatement.setString(4, student.getEmail());
		    	    preparedStatement.setInt(5, student.getRollNo());
		    	    
		    	    // Remaining code remains the same


	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                   // addStudent.onSuccess();
	                	System.out.println("update successfully..");
	                } else {
	                	//addStudent.notifications("No student found with Roll No: " + student.getRollNo());
	                	System.out.println("something get mistake in the update");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 

		public void setStudents(List<Student> updatedStudents) {
			this.studentList = updatedStudents;

		}
		public void deleteStudent(int rollNo) throws SQLException {
			try(Connection connection=DriverManager.getConnection(url,username,password)){
				String deleteQuery="DELETE FROM Students WHERE rollNo=?";
				try(PreparedStatement preparedStatement=connection.prepareStatement(deleteQuery)){
					preparedStatement.setInt(1, rollNo);
					int rowsAffected=preparedStatement.executeUpdate();
					if(rowsAffected>0) {
						//addStudent.notifications("Student deleted successfully");
						System.out.println("Student deleted successfully");
					}
					else {
						//addStudent.notifications("No student found with Roll No: "+rollNo);
						System.out.println("Student deleted not successfully");
					}
				}
			}
			catch (SQLException e) {
	            e.printStackTrace();
	        }
		}

		public Student getStudentById(int idToView) {
			// TODO Auto-generated method stub
			return null;
		}

		
}
