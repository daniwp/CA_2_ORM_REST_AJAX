package api;

import facade.CompanyFacade;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonconverter.JSONConverter;
import static org.glassfish.jersey.uri.UriComponent.Type.PATH;

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
        return Response.ok().entity(jCon.getJsonFromCompaniesAllInfo(cFacade.getCompanies())).build();
    }
    
}
