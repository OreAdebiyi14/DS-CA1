import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Deposit;

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
            em.persist(deposit);
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

    public Deposit getDepositById(long deposit_id) 
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
			Deposit e = em.createQuery(
                "SELECT p FROM Deposit p WHERE p.deposit_id = :deposit_id", 
                Deposit.class)
	                .setParameter("deposit_id", deposit_id)
	                .getSingleResult();
            em.getTransaction().commit();
            em.close();
            return e;       
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
