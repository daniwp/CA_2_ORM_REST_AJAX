package jsonconverter;

import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IJSONConverter {
    
    public String getCompanyInfoASJson(Company company);
    
    public String getJsonFromInteger(int number);
    public String getZipsToJson(List<CityInfo> cities);
    
    public String getJsonFromPersonAllDetailsIncHobbies(Person person);
    public String getJsonFromPersonAllDetails(Person person);
    public String getJsonFromPersonsAllDetails(List<Person> persons);
    public String getJsonFromPersonContactInfo(Person person);
    public String getJsonFromPersonsContactInfo(List<Person> persons);
    public Person getPersonFromJson(String content);
    
    public String getJsonFromCompanyAllDetails(Company company);
    public String getJsonFromCompanyContactInfo(Company company);
    public String getJsonFromCompaniesAllDetails(List<Company> companies);
    public String getJsonFromCompaniesContactInfo(List<Company> companies);
    public Company getCompanyFromJson(String content);
    
}
