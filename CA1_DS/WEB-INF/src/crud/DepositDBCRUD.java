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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.DepositDAO;
import model.Deposit;

public class DepositDBCRUD {
    private DepositDAO depositDAO = new DepositDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposit> getAllDeposits() {
        return depositDAO.getAllDeposits();
    }

    @GET
    @Path("/{depositId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Deposit getDeposit(@PathParam("depositId") Long depositId) {
        return depositDAO.findDeposit(depositId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDeposit(Deposit deposit, @QueryParam("studentId") Long studentId) {
        depositDAO.saveDeposit(deposit);
        depositDAO.associateStudentToDeposit(deposit.getDepositId(), studentId); // Associate student
        return "Deposit added for student ID: " + studentId;
    }

    @PUT
    @Path("/{depositId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Deposit updateDeposit(@PathParam("depositId") Long depositId, Deposit deposit, @QueryParam("studentId") Long studentId) {
        deposit.setDepositId(depositId);
        depositDAO.updateDeposit(deposit);
        depositDAO.associateStudentToDeposit(depositId, studentId); // Associate student
        return deposit;
    }

    @DELETE
    @Path("/{depositId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDeposit(@PathParam("depositId") Long depositId) {
        depositDAO.deleteDeposit(depositId);
        return "Deposit with ID " + depositId + " deleted.";
    }

}
