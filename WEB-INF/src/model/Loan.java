package model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "loan")
@Entity
public class Loan 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    private BigDecimal amount;
    private BigDecimal interestRate;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    @XmlTransient
    private List<Deposit> deposits;

    public Loan()
    {}

    public Loan(Long loan_id, BigDecimal amount, BigDecimal interestRate, Student student, List<Deposit> deposits) {
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
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
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

    @XmlTransient
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }  
}
