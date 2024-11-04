
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Loan;

public class LoanDAO 
{
    protected EntityManagerFactory emf;

    public LoanDAO() 
    {
        emf = Persistence.createEntityManagerFactory("mydb"); 
    }

    public void saveLoan(Loan loan) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(loan);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close(); 
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

    public void deleteLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(loan));
			em.getTransaction().commit();
			em.close();
    }

    public Loan getLoanById(long loan_id) 
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
			Loan e = em.createQuery("SELECT p FROM Loan p WHERE p.loan_id = :loan_id", Loan.class)
	                .setParameter("loan_id", loan_id)
	                .getSingleResult();
            em.getTransaction().commit();
            em.close();
            return e;
    }


}
