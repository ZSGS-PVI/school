package schoolbookpannel.dto;

public class Student {
	private int rollNo;
	private String name;
	private String DOB;
	private String mobileno;
	private String email;
	private String password;
	private int id;

	public Student(int rollNo, String name, String dOB, String mobileno, String email) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.DOB = dOB;
		this.mobileno = mobileno;
		this.email = email;

	}
	public Student(int id,String name,String email,String password) {
		this.id=id;
		this.name = name;
		this.email = email;
		this.password=password;
	}

	public Student(String email2, String password2) {
		this.name=name;
		this.email=email;
	}
	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void onSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", mobileno=" + mobileno + ", DOB=" + DOB + ", email="
				+ email + "]";
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
