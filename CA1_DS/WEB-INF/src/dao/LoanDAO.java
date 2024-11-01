package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Loan;

public class LoanDAO 
{
protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca1DS");

    public void saveLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    // public Loan findLoan(Long loanId) {
    //     EntityManager em = emf.createEntityManager();
    //     Loan loan = em.find(Loan.class, loanId);
    //     em.close();
    //     return loan;
    // }

    // public void updateLoan(Loan loan) {
    //     EntityManager em = emf.createEntityManager();
    //     em.getTransaction().begin();
    //     em.merge(loan);
    //     em.getTransaction().commit();
    //     em.close();
    // }

    public void deleteLoan(Long loanId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Loan loan = em.find(Loan.class, loanId);
        if (loan != null) {
            em.remove(loan);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Loan> loans = em.createQuery("from Loan", Loan.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return loans;
    }
    
}
