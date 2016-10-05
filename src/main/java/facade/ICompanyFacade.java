package facade;

import entities.Address;
import entities.Company;
import entities.Phone;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ICompanyFacade {

    Company addCompany(Company company);

    Company getCompany(String cvr);
    
    Company getCompany(long id);

    List<Company> getCompanies();

    Company addPhoneToCompany(Phone phone, long id);

    Company addAddressToCompany(Address address, long id);

    Company editCompany(Company company, long id);

    Company deleteCompany(long id);

}
