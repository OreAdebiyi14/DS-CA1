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
    try {
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // Rollback on error
        }
        e.printStackTrace(); // Handle logging appropriately
    } finally {
        em.close(); // Ensure EntityManager is closed
    }
}

public Loan findLoan(Long loanId) {
    EntityManager em = emf.createEntityManager();
    Loan loan = null;
    try {
        loan = em.find(Loan.class, loanId);
    } catch (Exception e) {
        e.printStackTrace(); // Handle logging appropriately
    } finally {
        em.close(); // Ensure EntityManager is closed
    }
    return loan; // Return null if not found
}

public void updateLoan(Loan loan) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // Rollback on error
        }
        e.printStackTrace(); // Handle logging appropriately
    } finally {
        em.close(); // Ensure EntityManager is closed
    }
}

public boolean deleteLoan(Long loanId) {
    EntityManager em = emf.createEntityManager();
    boolean isDeleted = false;
    try {
        em.getTransaction().begin();
        Loan loan = em.find(Loan.class, loanId);
        if (loan != null) {
            em.remove(loan);
            isDeleted = true; // Loan found and deleted
        }
        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // Rollback on error
        }
        e.printStackTrace(); // Handle logging appropriately
    } finally {
        em.close(); // Ensure EntityManager is closed
    }
    return isDeleted; // Return deletion status
}

public List<Loan> getAllLoans() {
    EntityManager em = emf.createEntityManager();
    List<Loan> loans = null;
    try {
        em.getTransaction().begin();
        loans = em.createQuery("from Loan", Loan.class).getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // Rollback on error
        }
        e.printStackTrace(); // Handle logging appropriately
    } finally {
        em.close(); // Ensure EntityManager is closed
    }
    return loans; // Return the list of loans
}
}
