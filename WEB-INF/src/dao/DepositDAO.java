package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Deposit;
import model.Student;


public class DepositDAO 
{
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca1DS");

    public void saveDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(deposit);
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

    public void updateDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(deposit);
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

    public boolean deleteDeposit(Long depositId) {
        EntityManager em = emf.createEntityManager();
        boolean isDeleted = false;
        try {
            em.getTransaction().begin();
            Deposit deposit = em.find(Deposit.class, depositId);
            if (deposit != null) {
                em.remove(deposit);
                isDeleted = true; // Deposit found and deleted
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
    
    public void associateStudentToDeposit(Long depositId, Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Deposit deposit = em.find(Deposit.class, depositId);
            Student student = em.find(Student.class, studentId);

            if (deposit != null && student != null) {
                deposit.setStudent(student);
                em.merge(deposit);
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
