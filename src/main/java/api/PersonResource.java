package api;

import facade.PersonFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonconverter.JSONConverter;

/**
 * REST Web Service
 *
 * @author Daniel
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComplete(@PathParam("id") long id) {
        return Response.ok().entity(JSONConverter.getClassInstance().getJsonFromPersonAllDetails(PersonFacade.getInstance().getPerson(id))).build();
    }
    
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComplete() {
        return Response.ok().entity(JSONConverter.getClassInstance().getJsonFromPersonsAllDetails(PersonFacade.getInstance().getPersons())).build();
    }
    
    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact(@PathParam("id") long id) {
        return Response.ok().entity(JSONConverter.getClassInstance().getJsonFromPersonContactInfo(PersonFacade.getInstance().getPerson(id))).build();
    }
    
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContact() {
        return Response.ok().entity(JSONConverter.getClassInstance().getJsonFromPersonsContactInfo(PersonFacade.getInstance().getPersons())).build();
    }
}
