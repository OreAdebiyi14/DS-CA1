package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Student;

public class StudentDAO 
{
    //SHOULD HAVE:
    //1. saveStudent()
    //2. findStudent()
    //3. updateStudent()
    //4. deleteStudent()
    
    protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("ca1DS");

		public StudentDAO() {}
		 	
        public void saveStudent(Student student) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            em.close();
        }

        public Student findStudent(Long studentId) {
            EntityManager em = emf.createEntityManager();
            Student student = em.find(Student.class, studentId);
            em.close();
            return student;
        }

        public void updateStudent(Student student) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            em.close();
        }

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

        public List<Student> getAllStudents() {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Student> student = new ArrayList<Student>();
			student = em.createQuery("from Student", Student.class).getResultList();
			em.getTransaction().commit();
			em.close();
			return student;
		}
		
		public Student getStudentByName(String studentName) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Student e = em.createQuery("SELECT s FROM Student s WHERE s.studentName = :studentName", Student.class)
	                .setParameter("studentName", studentName)
	                .getSingleResult();
			em.getTransaction().commit();
			em.close();
			return e;
		}
}
