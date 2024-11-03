import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Deposit;
import model.Loan;


public class DepositDAO 
{
    protected EntityManagerFactory emf;

    public DepositDAO() 
    {
        emf = Persistence.createEntityManagerFactory("mydb"); 
    }

    public void saveDeposit(Deposit deposit) {
         EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Loan loan = em.find(Loan.class, deposit.getLoan().getLoanId());

            if (loan != null) {
                // Decrease the loan amount by the deposit amount
                BigDecimal updatedAmount = loan.getAmount().subtract(deposit.getAmount());
                loan.setAmount(updatedAmount);
                em.merge(loan);  // Persist the updated loan
            }
            em.persist(deposit);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                System.out.println("Transaction rolled back due to error.");
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Deposit findDeposit(Long depositId) {
        EntityManager em = emf.createEntityManager();
        Deposit deposit = null;
        try {
            deposit = em.find(Deposit.class, depositId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle logging appropriately
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
        return deposit; // Return null if not found
    }

    public void deleteDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(deposit));
			em.getTransaction().commit();
			em.close();
    }

    public List<Deposit> getAllDeposits() {
        EntityManager em = emf.createEntityManager();
        List<Deposit> deposits = null;
        try {
            em.getTransaction().begin();
            deposits = em.createQuery("from Deposit", Deposit.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace(); // Handle logging appropriately
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
        return deposits; // Return the list of deposits
    }

    public Deposit getDepositById(Long depositId) {
        EntityManager em = emf.createEntityManager();
        Deposit deposit = null;
        try {
            deposit = em.find(Deposit.class, depositId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle logging appropriately
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
        return deposit; // Return null if not found
    }

    //need to create path
    public List<Deposit> getDepositsByStudent(Long studentId) {
        EntityManager em = emf.createEntityManager();
        List<Deposit> deposits = null;
        try {
            em.getTransaction().begin();
            deposits = em.createQuery("SELECT d FROM Deposit d WHERE d.student.studentId = :studentId", Deposit.class)
                          .setParameter("studentId", studentId)
                          .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace(); // Handle logging appropriately
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
        return deposits; // Return the list of deposits
    }
    
}
