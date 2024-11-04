import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import model.Student;


@Path("/student")
public class StudentDBCRUD {

    private static Map<String, Student> students = new HashMap<String, Student>();
	  private StudentDAO studentDAO = new StudentDAO();

    @POST
    @Path("/createstudent")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  // Accept both JSON and XML
    public Student createStudent(Student student) {
        studentDAO.persist(student);
        return student;
    }

    @GET
    @Path("/allstudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> listStudents() {
        return studentDAO.getAllStudents();
    }

    @PUT
    @Path("/check/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkStudent(@PathParam("id")Long id){
		StudentDAO dao = new StudentDAO();
		Student emp = dao.getStudentById(id);	
		return "Student : \n"+ emp +" exists";
    }


    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteEmployee(@PathParam("id")Long id){
		StudentDAO dao = new StudentDAO();
		Student emp = dao.getStudentById(id);
		dao.removeStudent(emp);	
		return "Student "+emp+" deleted";
    }

}
