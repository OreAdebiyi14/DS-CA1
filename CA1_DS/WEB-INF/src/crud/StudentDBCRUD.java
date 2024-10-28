package crud;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.StudentDAO;
import model.Student;

@Path("/students")
public class StudentDBCRUD 
{
   private StudentDAO studentDAO = new StudentDAO();

   @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") Long studentId) {
        return studentDAO.findStudent(studentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createStudent(Student student) {
        studentDAO.saveStudent(student);
        return "Student added: " + student.getName();
    }

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") int studentId, Student student) {
        student.setId(studentId); 
        studentDAO.updateStudent(student);
        return student;
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent(@PathParam("studentId") Long studentId) {
        studentDAO.deleteStudent(studentId);
        return "Student with ID " + studentId + " deleted.";
    }
   
    
}
