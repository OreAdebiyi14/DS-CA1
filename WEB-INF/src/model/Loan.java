package model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loan")
@Entity
public class Loan 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;
    private BigDecimal amount;
    private double interestRate;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Deposit> deposits;

    public Loan()
    {

    }

    public Loan(Long loan_id, BigDecimal amount, double interestRate, Student student, List<Deposit> deposits) {
        this.loan_id = loan_id;
        this.amount = amount;
        this.interestRate = interestRate;
        this.student = student;
        this.deposits = deposits;
    }

    @XmlElement
    public Long getLoanId() {
        return loan_id;
    }

    public void setLoanId(Long loan_id) {
        this.loan_id = loan_id;
    }

    @XmlElement
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlElement
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlElement
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
    
    
}
