
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Deposit;
import java.util.List;

@Path("/deposits")
public class DepositDBCRUD {
    private DepositDAO depositDAO = new DepositDAO();

    // Create a new deposit
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Change to JSON response
    public Response createDeposit(Deposit deposit, @QueryParam("studentId") Long studentId) {
        depositDAO.saveDeposit(deposit);
        depositDAO.associateStudentToDeposit(deposit.getDepositId(), studentId); // Associate student
        return Response.status(Response.Status.CREATED) // 201 Created
                .entity("Deposit added for student ID: " + studentId)
                .build();
    }

    // Delete a deposit by ID
    @DELETE
    @Path("/{depositId}")
    @Produces(MediaType.APPLICATION_JSON) // Change to JSON response
    public Response deleteDeposit(@PathParam("depositId") Long depositId) {
        boolean isDeleted = depositDAO.deleteDeposit(depositId);
        if (isDeleted) {
            return Response.ok("Deposit with ID " + depositId + " deleted.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Deposit with ID " + depositId + " not found.")
                    .build();
        }
    }

    // Get a deposit by ID
    @GET
    @Path("/{depositId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeposit(@PathParam("depositId") Long depositId) {
        Deposit deposit = depositDAO.getDepositById(depositId); // Implement this in your DAO
        if (deposit != null) {
            return Response.ok(deposit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Deposit with ID " + depositId + " not found.")
                    .build();
        }
    }

    // Get all deposits
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeposits() {
        List<Deposit> deposits = depositDAO.getAllDeposits(); // Implement this in your DAO
        return Response.ok(deposits).build();
    }

    // Get all deposits for a specific student
    @GET
    @Path("/student/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepositsByStudent(@PathParam("studentId") Long studentId) {
        List<Deposit> deposits = depositDAO.getDepositsByStudent(studentId); // Use the correct DAO method
        if (deposits == null || deposits.isEmpty()) { // Check for null and empty list
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No deposits found for student ID " + studentId)
                    .build();
        }
        return Response.ok(deposits).build();
    }

}
