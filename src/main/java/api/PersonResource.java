package api;

import entities.Person;
import exceptions.PeopleException;
import facade.PersonFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    JSONConverter jCon = JSONConverter.getClassInstance();
    PersonFacade pFacade = PersonFacade.getInstance();

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
        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.getPerson(id))).build();
    }

    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComplete() {
        return Response.ok().entity(jCon.getJsonFromPersonsAllDetails(pFacade.getPersons())).build();
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact(@PathParam("id") long id) throws PeopleException {
        
        Person p = pFacade.getPerson(id);
        
        if (p == null) {
            throw new PeopleException(404, "No person with that id was found");
        }
        
        return Response.ok().entity(jCon.getJsonFromPersonContactInfo(p)).build();
    }

    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContact() {
        return Response.ok().entity(jCon.getJsonFromPersonsContactInfo(pFacade.getPersons())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(String content) {
        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.addPerson(jCon.getPersonFromJson(content)))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.deletePerson(id))).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") long id, String content) {
        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.editPerson(jCon.getPersonFromJson(content), id))).build();
    }
}
