package crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.LoanDAO;
import model.Loan;

@Path("/loans")
public class LoanDBCRUD {
    private LoanDAO loanDAO = new LoanDAO();

    // Create a new loan
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLoan(Loan loan) {
        loanDAO.saveLoan(loan);
        return "Loan added: " + loan.getLoanId(); // Use loan.getLoanId() to return the ID of the added loan
    }

    // Delete a loan by ID
    @DELETE
    @Path("/{loanId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLoan(@PathParam("loanId") Long loanId) {
        loanDAO.deleteLoan(loanId);
        return "Loan with ID " + loanId + " deleted.";
    }
}

