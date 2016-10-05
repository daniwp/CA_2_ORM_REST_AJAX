package api;

import facade.CompanyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
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
    
    
    
}
