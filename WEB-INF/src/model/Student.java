package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@Entity
public class Student 
{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String phoneNumber;
    private String address;
    private String studentNumber;
    private String programmeCode;

    public Student()
    {

    }

    public Student(Long id, String name, String phoneNumber, String address, String studentNumber, String programmeCode) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.studentNumber = studentNumber;
        this.programmeCode = programmeCode;
    }
    
    @XmlElement
    public Long getId() {
            return id;
    }

    public void setId(Long id2) {
        this.id = id2;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @XmlElement
    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    
}
