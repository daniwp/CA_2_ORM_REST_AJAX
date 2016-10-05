package api;

import entities.Company;
import exceptions.CustomException;
import facade.CompanyFacade;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonconverter.JSONConverter;

/**
 * REST Web Service
 *
 * @author danie
 */
@Path("company")
public class CompanyResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
    }

    private JSONConverter jCon = JSONConverter.getClassInstance();
    private CompanyFacade cFacade = CompanyFacade.getInstance();

    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(jCon.getJsonFromCompaniesAllDetails(cFacade.getCompanies())).build();
    }

    @GET
    @Path("/complete/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("cvr") String cvr) throws CustomException {

        Company c = cFacade.getCompany(cvr);

        if (c == null) {
            throw new CustomException(404, "No company with that cvr was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(c)).build();
    }

}
