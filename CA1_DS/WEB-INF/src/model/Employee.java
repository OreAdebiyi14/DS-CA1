package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String employeeId;
	private String employeeName;
	private String job;
	
	public Employee(String employeeId, String employeeName, String job) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.job = job;
	}
	
	public Employee() {
		
	}
	
	@XmlElement
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@XmlElement
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@XmlElement
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
}
