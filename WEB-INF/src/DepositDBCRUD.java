
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Deposit;

import java.util.List;

@Path("/deposits")
public class DepositDBCRUD {

    //private static Map<String, Deposit> deposits = new HashMap<String, Deposit>();
    private DepositDAO depositDAO = new DepositDAO();

    // Create a new deposit
    @POST
    @Path("/depositLoan")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  // Accept both JSON and XML
    public String createDeposit(Deposit deposit) {
        depositDAO.saveDeposit(deposit);
        
        return "Deposit was created \n" + deposit;
    }

    @DELETE
    @Path("/delete/{deposit_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteDeposit(@PathParam("deposit_id")long deposit_id){
		DepositDAO dao = new DepositDAO();
		Deposit emp = dao.getDepositById(deposit_id);
		dao.deleteDeposit(emp);	
		return "Deposit "+ emp +" deleted";
    }
    
    // Get all deposits
    @GET
    @Path("/alldeposits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposit> listDeposits() {
        return depositDAO.getAllDeposits();
    }

    // @GET
    // @Path("/studentdeposit/{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public List<Student> getDepositsByStudent(@PathParam("id") Long id) {
    //     return depositDAO.getDepositsByStudent(id);
    // }

}
