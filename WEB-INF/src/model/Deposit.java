package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "deposit")
@Entity
public class Deposit 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    private String depositDate;
    private BigDecimal amount;
    
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Deposit()
    {

    }

    public Deposit(Long depositId, String depositDate, BigDecimal amount, Loan loan, Student student) {
        this.depositId = depositId;
        this.depositDate = depositDate;
        this.amount = amount;
        this.loan = loan;
        this.student = student;
    }


    @XmlElement
    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    @XmlElement
    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    @XmlElement
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlElement
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @XmlElement
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
}
