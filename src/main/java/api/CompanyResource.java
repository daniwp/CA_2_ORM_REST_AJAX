package api;

import entities.Company;
import exceptions.CustomException;
import facade.CompanyFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public Response getAllComplete() throws CustomException {
        List<Company> companies = cFacade.getCompanies();

        if (companies.isEmpty()) {
            throw new CustomException(404, "No companies has been added yet");
        }

        return Response.ok().entity(jCon.getJsonFromCompaniesAllDetails(companies)).build();
    }

    @GET
    @Path("/complete/cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompleteOnCvr(@PathParam("cvr") String cvr) throws CustomException {

        Company c = cFacade.getCompanyOnCvr(cvr);

        if (c == null) {
            throw new CustomException(404, "No company with that cvr was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(c)).build();
    }

    @GET
    @Path("/complete/phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompleteOnPhone(@PathParam("phone") String phone) throws CustomException {

        Company c = cFacade.getCompanyOnPhone(phone);

        if (c == null) {
            throw new CustomException(404, "No company with that number was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(c)).build();
    }

    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContact() throws CustomException {
        List<Company> companies = cFacade.getCompanies();

        if (companies.isEmpty()) {
            throw new CustomException(404, "No companies has been added yet");
        }

        return Response.ok().entity(jCon.getJsonFromCompaniesContactInfo(companies)).build();
    }

    @GET
    @Path("/contactinfo/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact(@PathParam("cvr") String cvr) throws CustomException {

        Company c = cFacade.getCompanyOnCvr(cvr);

        if (c == null) {
            throw new CustomException(404, "No company with that cvr was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyContactInfo(c)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(String content) throws CustomException {

        Company c = jCon.getCompanyFromJson(content);

        if (c == null || c.getName() == null || c.getDescription() == null || c.getCvr() == null || c.getNumEmployees() == 0 || c.getMarketValue() == 0 || c.getEmail() == null) {
            throw new CustomException(400, "Bad request - Must at least contain a name, description, email, cvr, numEmployees and marketValue in JSON format");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(cFacade.addCompany(jCon.getCompanyFromJson(content)))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) throws CustomException {

        Company c = cFacade.deleteCompany(id);

        if (c == null) {
            throw new CustomException(404, "No company with that id was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(c)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") long id, String content) throws CustomException {

        Company c = jCon.getCompanyFromJson(content);

        if (c == null || c.getName() == null || c.getDescription() == null || c.getCvr() == null || c.getNumEmployees() == 0 || c.getMarketValue() == 0 || c.getEmail() == null) {
            throw new CustomException(400, "Bad request - Must at least contain a name, description, email, cvr, numEmployees and marketValue in JSON format");
        }

        if (cFacade.getCompanyOnId(id) == null) {
            throw new CustomException(404, "No company with that id was found");
        }

        return Response.ok().entity(jCon.getJsonFromCompanyAllDetails(cFacade.editCompany(jCon.getCompanyFromJson(content), id))).build();
    }
}
