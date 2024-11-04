import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Loan;

@Path("/loans")
public class LoanDBCRUD {
    //private static Map<String, Loan> loans = new HashMap<String, Loan>();
	private LoanDAO loanDAO = new LoanDAO();

    // Create a new loan
    @POST
    @Path("/createLoan")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  // Accept both JSON and XML
    public Loan createLoan(Loan loan) {
        loanDAO.saveLoan(loan);
        return loan;
    }

    // Delete a loan by ID
    @DELETE
    @Path("/delete/{loan_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteLoan(@PathParam("loan_id")int loanId){
		LoanDAO dao = new LoanDAO();
		Loan emp = dao.getLoanById(loanId);
		dao.deleteLoan(emp);	
		return "Loan "+ emp + " was deleted";
    }

    
}