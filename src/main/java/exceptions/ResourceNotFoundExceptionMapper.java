package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import jsonconverter.JSONConverter;

/**
 *
 * @author Daniel
 */
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response.status(404).entity(JSONConverter.getGsonInstance().toJson(new PrettyException(404, "The service you requested does not exist."))).type("application/json").build();
    }
}
