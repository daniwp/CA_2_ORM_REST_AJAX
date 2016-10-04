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
        JsonArray hobbies = new JsonArray();

        json.addProperty("id", person.getId());
        json.addProperty("firstname", person.getFirstName());
        json.addProperty("lastname", person.getLastName());
        json.addProperty("email", person.getEmail());

        if (person.getAddress() != null) {
            JsonObject jsonAddress = new JsonObject();
            jsonAddress.addProperty("street", person.getAddress().getStreet());

            if (person.getAddress().getCity() != null) {
                jsonAddress.addProperty("zipcode", person.getAddress().getCity().getZipCode());
                jsonAddress.addProperty("city", person.getAddress().getCity().getCity());

            }

            json.add("address", jsonAddress);
        }

        if (!person.getHobbies().isEmpty()) {

            for (Hobby hobby : person.getHobbies()) {

                JsonObject jsonHobby = new JsonObject();
                jsonHobby.addProperty("id", hobby.getId());
                jsonHobby.addProperty("name", hobby.getName());
                jsonHobby.addProperty("description", hobby.getDescription());

                hobbies.add(jsonHobby);
            }

            json.add("hobbies", hobbies);
        }

        return getGsonInstance().toJson(json);

    }

    @Override
    public String getJsonFromPersons(List<Person> persons) {

        List<JsonObject> jsonPersons = new ArrayList();

        for (Person p : persons) {

            JsonObject json = new JsonObject();
            JsonArray hobbies = new JsonArray();

            json.addProperty("id", p.getId());
            json.addProperty("firstname", p.getFirstName());
            json.addProperty("lastname", p.getLastName());
            json.addProperty("email", p.getEmail());

            if (p.getAddress() != null) {
                JsonObject jsonAddress = new JsonObject();
                jsonAddress.addProperty("street", p.getAddress().getStreet());

                if (p.getAddress().getCity() != null) {
                    jsonAddress.addProperty("zipcode", p.getAddress().getCity().getZipCode());
                    jsonAddress.addProperty("city", p.getAddress().getCity().getCity());

                }

                json.add("address", jsonAddress);
            }

            if (!p.getHobbies().isEmpty()) {

                for (Hobby hobby : p.getHobbies()) {

                    JsonObject jsonHobby = new JsonObject();
                    jsonHobby.addProperty("id", hobby.getId());
                    jsonHobby.addProperty("name", hobby.getName());
                    jsonHobby.addProperty("description", hobby.getDescription());

                    hobbies.add(jsonHobby);
                }

                json.add("hobbies", hobbies);
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
