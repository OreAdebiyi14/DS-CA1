
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Deposit;
import model.Loan;

import java.util.List;

@Path("/deposits")
public class DepositDBCRUD {
    private DepositDAO depositDAO = new DepositDAO();

    // Create a new deposit
    @POST
    @Path("/depositLoan")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  // Accept both JSON and XML
    public Deposit createDeposit(Deposit deposit) {
        System.out.println("Received Deposit Object: " + deposit);
        depositDAO.saveDeposit(deposit);
        return deposit;
    }

    // // Delete a deposit by ID
    // @DELETE
    // @Path("/deletedeposit/{deposit_id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String deleteDeposit(@PathParam("deposit_id")long deposit_id){
	// 	DepositDAO dao = new DepositDAO();
    //     Deposit deposit = dao.getDepositById(deposit_id);
    //     if (deposit != null) {
    //         dao.deleteDeposit(deposit);
    //     }
	// 	Deposit emp = dao.getDepositById(deposit_id);
	// 	dao.deleteDeposit(emp);	
	// 	return "Deposit "+emp+" deleted";
    // }

    @DELETE
    @Path("/delete/{deposit_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteLoan(@PathParam("deposit_id")long deposit_id){
		DepositDAO dao = new DepositDAO();
		Deposit emp = dao.getDepositById(deposit_id);
		dao.deleteDeposit(emp);	
		return "Loan "+emp+" deleted";
    }

    // Get all deposits
    @GET
    @Path("/alldeposits")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeposits() {
        List<Deposit> deposits = depositDAO.getAllDeposits(); // Implement this in your DAO
        return Response.ok(deposits).build();
    }

    // @GET
    // @Path("/deposit/{depositId}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public List<Deposit> getDepositsByStudent(@PathParam("studentId") Long studentId) {
    //     return depositDAO.getDepositsByStudent(studentId);
    // }

}
