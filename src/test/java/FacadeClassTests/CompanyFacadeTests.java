
package FacadeClassTests;

import entities.Company;
import facade.CompanyFacade;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author John
 */
public class CompanyFacadeTests {
    CompanyFacade facade = CompanyFacade.getInstance();
    
    @Test
    public void testAddCompany() {
        String cvr = "0001231";
        Company c = new Company("Testname", "Test Description", cvr, 5, 1000, "test@test.dk");
        
        facade.addCompany(c);
        
        assertEquals(c, facade.getCompanyOnCvr(cvr));
    }
    
    @Test
    public void testGetCompanies() {
        List<Company> cList = facade.getCompanies();
        assertTrue(cList.size() > 1);
        
    }
}
