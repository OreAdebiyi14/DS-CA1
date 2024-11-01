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
        em.getTransaction().begin();
        em.persist(deposit);
        em.getTransaction().commit();
        em.close();
    }

    // public Deposit findDeposit(Long depositId) {
    //     EntityManager em = emf.createEntityManager();
    //     Deposit deposit = em.find(Deposit.class, depositId);
    //     em.close();
    //     return deposit;
    // }

    // public void updateDeposit(Deposit deposit) {
    //     EntityManager em = emf.createEntityManager();
    //     em.getTransaction().begin();
    //     em.merge(deposit);
    //     em.getTransaction().commit();
    //     em.close();
    // }

    public void deleteDeposit(Long depositId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Deposit deposit = em.find(Deposit.class, depositId);
        if (deposit != null) {
            em.remove(deposit);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Deposit> getAllDeposits() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Deposit> deposits = em.createQuery("from Deposit", Deposit.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return deposits;
    }
    
    public void associateStudentToDeposit(Long depositId, Long studentId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Deposit deposit = em.find(Deposit.class, depositId);
        Student student = em.find(Student.class, studentId);
        
        if (deposit != null && student != null) {
            deposit.setStudent(student);
            em.merge(deposit);
        }
        
        em.getTransaction().commit();
        em.close();
    }
}
