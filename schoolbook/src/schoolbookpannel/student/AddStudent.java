package schoolbookpannel.student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import schoolbookpannel.SchoolBookPannel;
import schoolbookpannel.dto.Student;
import schoolbookpannel.repository.SchoolBookPannelRepository;

public class AddStudent {
	// private static final String String = null;
	private AddStudentViewModel addStudentviewmodel;

	public AddStudent(SchoolBookPannel schoolBookPannel) {
		addStudentviewmodel = new AddStudentViewModel(this);
	}

	public AddStudent() {
		// TODO Auto-generated constructor stub
	}

	Scanner in = new Scanner(System.in);

	public void addStudentInfo() {
		Scanner in = new Scanner(System.in);
		int rollNo = 0;
		String name;
		String DOB;
		String mobileno;
		String email;

		while (true) {
			System.out.println("Enter the rollNo  (type '0' to stop adding names):");
			rollNo = in.nextInt();
//			 if (addStudentviewmodel.isValidID(rollNo)) {
//		            System.out.println("ID is valid: " + rollNo);
//		            // Further processing
//		        } else {
//		            System.out.println("ID is not valid or does not exist in the database!");
//		            // Handle invalid ID
//		        }
			if (rollNo == 0) {
				break;
			}

			System.out.println("Enter  student name:");
			name = in.next();
			addStudentviewmodel.isValidName(name);

			System.out.println("Enter student DOB Like (\"yyyy-MM-dd\"): ");
			DOB = in.next();
			addStudentviewmodel.isValidDateOfBirth(DOB);
			System.out.println("Enter  student mobileno: ");
			mobileno = in.next();
			addStudentviewmodel.isValidMobileNo(mobileno);

			System.out.println("Enter student email  ");
			email = in.next();
			addStudentviewmodel.isValidEmail(email);

			Student student = new Student(rollNo, name, DOB, mobileno, email);
			addStudentviewmodel.validate(student);

		}
	}

	public void  signup(Student student) throws SQLException {
		  try {
		        if (addStudentviewmodel.addAdmin(student)) {
		            System.out.println("Sign up successful!");
		        } else {
		            System.out.println("Sign up failed. Please try again.");
		        }
		    } catch (SQLException e) {
		        System.out.println("Error during signup: " + e.getMessage());
		        e.printStackTrace(); // Print stack trace for detailed error info
		    }
	}

	public void viewStudent() {
		addStudentviewmodel.viewUsers();
	}

	public void updateStudent() {
	    System.out.println("Enter the Id to update:");
	    int idToUpdate = in.nextInt();
	    List<Student> list = addStudentviewmodel.list();

	    boolean found = false;
	    for (Student student : list) {
	        if (student.getRollNo() == idToUpdate) {
	            found = true;

	            System.out.println(" 1.Name\n 2.DOB \n 3.Mobileno\n 4.Email\n 5.Back");
	            int choice = in.nextInt();
	            in.nextLine(); // Consume newline character

	            switch (choice) {
	                case 1:
	                    System.out.println("Enter new name: ");
	                    String newName = in.nextLine();
	                    student.setName(newName);
	                    SchoolBookPannelRepository.getInstanse().updateStudent(student);
	                    break;
	                case 2:
	                    System.out.println("Enter new DOB: ");
	                    String newDOB = in.nextLine();
	                    student.setDOB(newDOB);
	                    SchoolBookPannelRepository.getInstanse().updateStudent(student);
	                    break;
	                case 3:
	                    System.out.println("Enter new MobileNo: ");
	                    String newMobileNo = in.nextLine();
	                    student.setMobileno(newMobileNo);
	                    SchoolBookPannelRepository.getInstanse().updateStudent(student);
	                    break;
	                case 4:
	                    System.out.println("Enter new email: ");
	                    String newEmail = in.nextLine();
	                    student.setEmail(newEmail);
	                    SchoolBookPannelRepository.getInstanse().updateStudent(student);
	                    break;
	                default:
	                    System.out.println("Invalid choice.");
	                    break;
	            }
	        }
	    }

	    if (!found) {
	        System.out.println("Student not found with ID: " + idToUpdate);
	    }

	    addStudentviewmodel.update(idToUpdate);
	}
	public void deleteStudent() {

		System.out.println("Enter the Id to delete:");
		int idTODelete = in.nextInt();
		

		addStudentviewmodel.delete(idTODelete);
	}

	public void onSuccess() {
		System.out.println("Inserted Successfully");

	}
	public void notifications(String string) {
		System.out.println(string);
	}

	public void showError(String errorMessage) {
		System.out.println(errorMessage);
	}

	public void studentlist(Student student) {
		System.out.println("---------------------------");
		System.out.println("ID: " + student.getRollNo());
		System.out.println("Name: " + student.getName());
		System.out.println("DOB: " + student.getDOB());
		System.out.println("Mobile No: " + student.getMobileno());
		System.out.println("Email: " + student.getEmail());
		System.out.println("---------------------------");
	}
	
//	public class viewStudentById {
//	    private AddStudentViewModel addStudentViewModel;
//	    private Scanner in;
//
//	   
//
//	    public void viewStudentById() throws SQLException {
//	        System.out.println("Enter the ID of the student you want to view:");
//	        int idToView = in.nextInt();
//	        in.nextLine(); // Consume newline character
//	        
//	        // Call method from AddStudentViewModel to view student by ID
//	        addStudentViewModel.viewstuinfo(idToView);
//	    }
//	}



	

}
