package api;

import entities.Person;
import exceptions.CustomException;
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
    public Response getComplete(@PathParam("id") long id) throws CustomException {

        Person p = pFacade.getPerson(id);

        if (p == null) {
            throw new CustomException(404, "No person with that id was found");
        }

        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(p)).build();
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
    public Response getContact(@PathParam("id") long id) throws CustomException {

        Person p = pFacade.getPerson(id);

        if (p == null) {
            throw new CustomException(404, "No person with that id was found");
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
    public Response add(String content) throws CustomException {

        Person p = jCon.getPersonFromJson(content);

        if (p == null || p.getFirstName() == null || p.getLastName() == null || p.getEmail() == null) {
            throw new CustomException(400, "Bad request - Must at least contain a firstname, lastname and an email in JSON format");
        }

        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.addPerson(jCon.getPersonFromJson(content)))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) throws CustomException {

        Person p = pFacade.deletePerson(id);

        if (p == null) {
            throw new CustomException(404, "No person with that id was found");
        }

        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(p)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") long id, String content) throws CustomException {

        Person p = jCon.getPersonFromJson(content);

        if (p == null || p.getFirstName() == null || p.getLastName() == null || p.getEmail() == null) {
            throw new CustomException(400, "Bad request - Must at least contain a firstname, lastname and an email in JSON format");
        }

        if (pFacade.getPerson(id) == null) {
            throw new CustomException(404, "No person with that id was found");
        }

        return Response.ok().entity(jCon.getJsonFromPersonAllDetails(pFacade.editPerson(p, id))).build();
    }
}
