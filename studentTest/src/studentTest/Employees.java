package studentTest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Employees implements Serializable{
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commisionPct;
	private int managerId;
	private int departmentId;
	
	// 생성자 오버로딩 
	public Employees(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobId, double salary, double commisionPct, int managerId, int departmentId) {
	}
	public Employees(int employeeId, String firstName, double salary) {
	}


	// getter, setter 
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommisionPct() {
		return commisionPct;
	}
	public void setCommisionPct(double commisionPct) {
		this.commisionPct = commisionPct;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerID(int managerId) {
		this.managerId = managerId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	// 객체 비교
	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
	@Override
	public boolean equals(Object obj) {
//		Employees e = null;
		if (!(obj instanceof Employees)) {
			return false;
		}	
		return this.employeeId == ((Employees)obj).employeeId;
	}
	// toString 
	@Override 
	public String toString() {
		return "[ employeeId = " + employeeId + ", firstName = " + firstName 
				+ ", salary = " + salary + "]";
 	}
	
}
