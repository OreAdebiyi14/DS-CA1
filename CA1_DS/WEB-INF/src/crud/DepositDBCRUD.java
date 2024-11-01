package crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.DepositDAO;
import model.Deposit;

@Path("/deposits")
public class DepositDBCRUD {
    private DepositDAO depositDAO = new DepositDAO();

    // Create a new deposit
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDeposit(Deposit deposit, @QueryParam("studentId") Long studentId) {
        depositDAO.saveDeposit(deposit);
        depositDAO.associateStudentToDeposit(deposit.getDepositId(), studentId); // Associate student
        return "Deposit added for student ID: " + studentId;
    }

    // Delete a deposit by ID
    @DELETE
    @Path("/{depositId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDeposit(@PathParam("depositId") Long depositId) {
        depositDAO.deleteDeposit(depositId);
        return "Deposit with ID " + depositId + " deleted.";
    }
}
