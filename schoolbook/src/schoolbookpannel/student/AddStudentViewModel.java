package schoolbookpannel.student;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import schoolbookpannel.SchoolBookPannel;
import schoolbookpannel.dto.Student;
import schoolbookpannel.repository.SchoolBookPannelRepository;

public class AddStudentViewModel {

	private AddStudent addStudent;

	public AddStudentViewModel(AddStudent addStudent) {
		this.addStudent = addStudent;
	}
	
	public boolean loginCount() {
		SchoolBookPannelRepository.getInstanse().loginChech();
		return true;
	}
	public boolean login(Student student) {
		SchoolBookPannelRepository.getInstanse().loginAdmin(student);
		return true;
	}

public boolean addAdmin(Student student) throws SQLException {
	SchoolBookPannelRepository.getInstanse().signupAdmin(student);
	//this.addStudent.onSuccess();
		return true;
	}

public class viewstuinfo {
    private SchoolBookPannelRepository repository;

    public void AddStudentViewModel(SchoolBookPannelRepository repository) {
        this.repository = repository;
    }

    public void viewStudentById(int idToView) throws SQLException {
        Student student = repository.getStudentById(idToView);
        if (student != null) {
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            // Print other details as needed
        } else {
            System.out.println("Student with ID " + idToView + " not found.");
        }
    }
}

//public boolean isValidID(int rollNo) {
//	return SchoolBookPannelRepository.getInstanse().IdCheck(rollNo);
//}


// 1.add student details

	public AddStudentViewModel(SchoolBookPannel schoolBookPannel) {
		// TODO Auto-generated constructor stub
	}

	public void validate(Student student) {

		SchoolBookPannelRepository.getInstanse().insertStudent(student);
		

	}

// 2.view student details

	public void viewUsers() {
		List<Student> students = SchoolBookPannelRepository.getInstanse().getStudents();

		if (students.isEmpty()) {
			addStudent.notifications("No students found.");
			
		} else {
			addStudent.notifications("List of students:");
			for (Student student : students) {

				this.addStudent.studentlist(student);

			}
		}
	}
	public List<Student> list() {
		List<Student> students = SchoolBookPannelRepository.getInstanse().getStudents();
		return students;
	}

//3.Update the student details
	public void update(int idToUpdate) {

		List<Student> students = SchoolBookPannelRepository.getInstanse().getStudents();

		}

// 4.Delete the student Details
	public void delete(int idTODelete) {
		try {
			SchoolBookPannelRepository.getInstanse().deleteStudent(idTODelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean found = false;
//
//		Iterator<Student> iterator = students.iterator();
//		while (iterator.hasNext()) {
//			Student student = iterator.next();
//			if (student.getRollNo() == idTODelete) {
//				iterator.remove();
//				found = true;
//				System.out.println("Student deleted successfully");
//				break;
//			}
//		}
//
//		if (!found) {
//			System.out.println("Student not found.");
//		}
//
//		SchoolBookPannelRepository.getInstanse().setStudents(students);
	}
	


	// student name validation
	public void isValidName(String name) {

		if (name.matches("^[a-zA-Z]+$")) {
		}

		else {
			addStudent.notifications("only characters are allowed!");

			addStudent.addStudentInfo();
		}

	}

	// student DOB validation
	public void isValidDateOfBirth(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		try {
			Date date = dateFormat.parse(dateStr);

			Date currentDate = new Date();

			if (!date.after(currentDate) && date.before(currentDate)) {

			}
		} catch (ParseException e) {
			// ParseException will be thrown if the date format is invalid
			addStudent.notifications("Invalid date of birth!");
		
			addStudent.addStudentInfo();

		}
	}

	// student Mobile number validation

	public void isValidMobileNo(String number) {

		if (number != null && !number.isEmpty()) {

			if (number.matches("^[0-9]{10}$")) {

			} else {
				addStudent.notifications("Invalid mobile number! Only 10 digits are allowed.");
				
				addStudent.addStudentInfo();
			}
		} else {
			addStudent.notifications("Mobile number cannot be empty or null!");
			addStudent.addStudentInfo();
		}
	}

	// student Email validation
	public void isValidEmail(String name) {

		if (name != null && !name.isEmpty()) {

			if (name.matches(
					"^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

			}

			else {
				addStudent.notifications("Invalid Email Id!");
				addStudent.addStudentInfo();
			}

		} else {
			addStudent.notifications("Email Id cannot be empty or null!");
			addStudent.addStudentInfo();
		}

	}




}
