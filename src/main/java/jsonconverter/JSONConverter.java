package jsonconverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class JSONConverter implements IJSONConverter {

    private static JSONConverter instance = null;
    private static Gson gson = null;

    public static JSONConverter getClassInstance() {
        if (instance == null) {
            instance = new JSONConverter();
        }
        return instance;
    }

    public static Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        }
        return gson;
    }

    @Override
    public String getJsonFromInteger(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getZipsToJson(List<CityInfo> cities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Person Converter Methods
     */
    @Override
    public String getJsonFromPerson(Person person) {

        JsonObject json = new JsonObject();
        JsonArray phones = new JsonArray();

        json.addProperty("firstname", person.getFirstName());
        json.addProperty("lastname", person.getLastName());
        json.addProperty("email", person.getEmail());

        if (!person.getPhones().isEmpty()) {

            for (Phone phone : person.getPhones()) {

                JsonObject jsonPhone = new JsonObject();
                jsonPhone.addProperty("number", phone.getNumber());
                jsonPhone.addProperty("description", phone.getDescription());

                phones.add(jsonPhone);
            }

            json.add("phones", phones);
        }

        if (person.getAddress() != null) {
            json.addProperty("street", person.getAddress().getStreet());

            if (person.getAddress().getCity() != null) {
                json.addProperty("zipcode", person.getAddress().getCity().getZipCode());
                json.addProperty("city", person.getAddress().getCity().getCity());

            }
        }

        return getGsonInstance().toJson(json);

    }

    @Override
    public String getJsonFromPersons(List<Person> persons) {

        List<JsonObject> jsonPersons = new ArrayList();

        for (Person p : persons) {

            JsonObject json = new JsonObject();
            JsonArray phones = new JsonArray();

            json.addProperty("firstname", p.getFirstName());
            json.addProperty("lastname", p.getLastName());
            json.addProperty("email", p.getEmail());

            if (!p.getPhones().isEmpty()) {

                for (Phone phone : p.getPhones()) {

                    JsonObject jsonPhone = new JsonObject();
                    jsonPhone.addProperty("number", phone.getNumber());
                    jsonPhone.addProperty("description", phone.getDescription());

                    phones.add(jsonPhone);
                }

                json.add("phones", phones);
            }

            if (p.getAddress() != null) {
                json.addProperty("street", p.getAddress().getStreet());

                if (p.getAddress().getCity() != null) {
                    json.addProperty("zipcode", p.getAddress().getCity().getZipCode());
                    json.addProperty("city", p.getAddress().getCity().getCity());

                }
            }

            jsonPersons.add(json);

        }

        return getGsonInstance().toJson(jsonPersons);

    }

    @Override
    public Person getPersonFromJson(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPersonInfoAsJson(String phoneNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Company Converter Methods
     */
    @Override
    public String getJsonFromCompany(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJsonFromCompanies(List<Company> companies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company getCompanyFromJson(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCompanyInfoASJson(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
