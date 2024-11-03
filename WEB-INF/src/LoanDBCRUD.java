package crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.LoanDAO;
import model.Loan;

@Path("/loans")
public class LoanDBCRUD {
    private LoanDAO loanDAO = new LoanDAO();

    // Create a new loan
    @POST
    @PATH("/createLoan")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Change to JSON response
    public Response createLoan(Loan loan) {
        loanDAO.saveLoan(loan);
        return Response.status(Response.Status.CREATED) // 201 Created
                .entity("Loan added with ID: " + loan.getLoanId())
                .build();
    }

    // Delete a loan by ID
    @DELETE
    @Path("/{loanId}")
    @Produces(MediaType.APPLICATION_JSON) // Change to JSON response
    public Response deleteLoan(@PathParam("loanId") Long loanId) {
        boolean isDeleted = loanDAO.deleteLoan(loanId);
        if (isDeleted) {
            return Response.ok("Loan with ID " + loanId + " deleted.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND) // 404 Not Found
                    .entity("Loan with ID " + loanId + " not found.")
                    .build();
        }
    }
}