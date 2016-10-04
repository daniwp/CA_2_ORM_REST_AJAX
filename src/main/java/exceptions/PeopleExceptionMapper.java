package exceptions;

import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import jsonconverter.JSONConverter;

/**
 *
 * @author Daniel
 */
@Provider
public class PeopleExceptionMapper implements ExceptionMapper<PeopleException> {

    @Override
    public Response toResponse(PeopleException e) {
        return Response.status(e.getCode()).entity(JSONConverter.getGsonInstance().toJson(new PrettyException(e.getCode(), e.getMessage()))).type("application/json").build();
    }
}
