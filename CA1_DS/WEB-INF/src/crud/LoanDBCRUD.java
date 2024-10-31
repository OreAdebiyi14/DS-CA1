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

import dao.LoanDAO;
import model.Loan;
import model.Student;

@Path("/loans")
public class LoanDBCRUD {
    private LoanDAO loanDAO = new LoanDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @GET
    @Path("/{loanId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Loan getLoan(@PathParam("loanId") Long loanId) {
        return loanDAO.findLoan(loanId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLoan(Loan loan) {
        loanDAO.saveLoan(loan);
        return "Loan added: " + Student.getName();
    }

    @PUT
    @Path("/{loanId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Loan updateLoan(@PathParam("loanId") Long loanId, Loan loan) {
        loan.setLoanId(loanId); // Assuming Loan has a setLoanId method
        loanDAO.updateLoan(loan);
        return loan;
    }

    @DELETE
    @Path("/{loanId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLoan(@PathParam("loanId") Long loanId) {
        loanDAO.deleteLoan(loanId);
        return "Loan with ID " + loanId + " deleted.";
    }
    
}
