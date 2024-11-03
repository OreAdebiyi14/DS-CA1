import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Student;

public class StudentDAO {
   
    protected EntityManagerFactory emf;

    public StudentDAO() {
        emf = Persistence.createEntityManagerFactory("mydb"); 
    }

    public void persist(Student student) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

        public void updateStudent(Student student) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            em.close();
        }

        //need
        public void deleteStudent(Long studentId) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
            em.close();
        }
        
        //need
        public List<Student> getAllStudents() {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Student> student = new ArrayList<Student>();
			student = em.createQuery("from Student", Student.class).getResultList();
			em.getTransaction().commit();
			em.close();
			return student;
		}

}
