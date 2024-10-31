package crud;

import java.util.ArrayList;
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

    private static Map<Integer, Student> students = new HashMap<>(); // Use Integer for ID
    private static int currentId = 1; // ID tracker for new students

    static {
        // Initial test student
        Student student1 = new Student();
        student1.setId(currentId++);
        student1.setStudentNumber("123445");
        student1.setPhoneNumber("089222200"); // Ensure phone number is a String
        student1.setAddress("333 fake address");
        student1.setProgrammeCode("tu988");
        students.put(student1.getId(), student1);
    }

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok("Service is running").build();
    }

    @POST
    @Path("/createStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        student.setId(currentId++); // Assign a new ID
        students.put(student.getId(), student); // Save student to map
        return Response.status(201).entity(student).build(); // Return created student
    }

    @GET
    @Path("/json/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values()); // Return all students
    }

    @PUT
    @Path("/update/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("studentId") int studentId, Student student) {
        if (!students.containsKey(studentId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }
        student.setId(studentId); 
        students.put(studentId, student); // Update student in map
        return Response.ok(student).build();
    }

    @DELETE
    @Path("/delete/{studentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudent(@PathParam("studentId") int studentId) {
        if (!students.containsKey(studentId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }
        students.remove(studentId); // Remove student from map
        return Response.ok("Student with ID " + studentId + " deleted.").build();
    }
}
