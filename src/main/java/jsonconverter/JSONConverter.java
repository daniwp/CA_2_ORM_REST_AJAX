package jsonconverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Address;
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
    public String getJsonFromPersonAllDetails(Person person) {

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
    public String getJsonFromPersonsAllDetails(List<Person> persons) {

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
    public String getJsonFromPersonContactInfo(Person person) {

        JsonObject json = new JsonObject();
        JsonArray phones = new JsonArray();

        json.addProperty("id", person.getId());
        json.addProperty("name", person.getFirstName() + " " + person.getLastName());
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

        return getGsonInstance().toJson(json);
    }

    @Override
    public String getJsonFromPersonsContactInfo(List<Person> persons) {

        List<JsonObject> jsonPersons = new ArrayList();

        for (Person p : persons) {

            JsonObject json = new JsonObject();
            JsonArray phones = new JsonArray();

            json.addProperty("id", p.getId());
            json.addProperty("name", p.getFirstName() + " " + p.getLastName());
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

            jsonPersons.add(json);
        }

        return getGsonInstance().toJson(jsonPersons);
    }

    @Override
    public Person getPersonFromJson(String content) {

        Person p = null;

        try {

            JsonObject json = new JsonParser().parse(content).getAsJsonObject();

            String fname = json.get("firstname").getAsString();
            String lname = json.get("lastname").getAsString();
            String email = json.get("email").getAsString();

            p = new Person(fname, lname, email);

            if (json.get("street") != null) {

                String street = json.get("street").getAsString();
                int zipcode = json.get("zipcode").getAsInt();
                String city = json.get("city").getAsString();

                p.setAddress(new Address(street, new CityInfo(zipcode, city)));
            }

            if (json.get("phones") != null) {

                for (JsonElement e : json.getAsJsonArray("phones")) {

                    String number = e.getAsJsonObject().get("number").getAsString();
                    String description = e.getAsJsonObject().get("description").getAsString();

                    p.addPhone(new Phone(number, description));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    /**
     *
     * Company Converter Methods
     *
     */
    @Override
    public Company getCompanyFromJson(String content) {

        Company c = null;

        try {

            JsonObject json = new JsonParser().parse(content).getAsJsonObject();

            String name = json.get("name").getAsString();
            String description = json.get("description").getAsString();
            String email = json.get("email").getAsString();
            String cvr = json.get("cvr").getAsString();
            int numEmployees = json.get("numEmployees").getAsInt();
            int marketValue = json.get("marketValue").getAsInt();

            c = new Company(name, description, cvr, numEmployees, marketValue, email);

            if (json.get("street") != null) {

                String street = json.get("street").getAsString();
                int zipcode = json.get("zipcode").getAsInt();
                String city = json.get("city").getAsString();

                c.setAddress(new Address(street, new CityInfo(zipcode, city)));
            }

            if (json.get("phones") != null) {

                for (JsonElement e : json.getAsJsonArray("phones")) {

                    String number = e.getAsJsonObject().get("number").getAsString();
                    String descr = e.getAsJsonObject().get("description").getAsString();

                    c.addPhone(new Phone(number, description));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public String getCompanyInfoASJson(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJsonFromCompanyAllDetails(Company company) {

        JsonObject json = new JsonObject();
        JsonArray phones = new JsonArray();

        json.addProperty("id", company.getId());
        json.addProperty("name", company.getName());
        json.addProperty("description", company.getDescription());
        json.addProperty("email", company.getEmail());
        json.addProperty("cvr", company.getCvr());
        json.addProperty("numEmployees", company.getNumEmployees());
        json.addProperty("marketValue", company.getMarketValue());

        if (!company.getPhones().isEmpty()) {

            for (Phone phone : company.getPhones()) {

                JsonObject jsonPhone = new JsonObject();
                jsonPhone.addProperty("number", phone.getNumber());
                jsonPhone.addProperty("description", phone.getDescription());

                phones.add(jsonPhone);
            }

            json.add("phones", phones);
        }

        if (company.getAddress() != null) {
            json.addProperty("street", company.getAddress().getStreet());

            if (company.getAddress().getCity() != null) {
                json.addProperty("zipcode", company.getAddress().getCity().getZipCode());
                json.addProperty("city", company.getAddress().getCity().getCity());

            }
        }

        return getGsonInstance().toJson(json);
    }

    @Override
    public String getJsonFromCompanyContactInfo(Company company) {

        JsonObject json = new JsonObject();
        JsonArray phones = new JsonArray();

        json.addProperty("id", company.getId());
        json.addProperty("name", company.getName());
        json.addProperty("email", company.getEmail());

        if (!company.getPhones().isEmpty()) {

            for (Phone phone : company.getPhones()) {

                JsonObject jsonPhone = new JsonObject();
                jsonPhone.addProperty("number", phone.getNumber());
                jsonPhone.addProperty("description", phone.getDescription());

                phones.add(jsonPhone);
            }

            json.add("phones", phones);
        }

        return getGsonInstance().toJson(json);
    }

    @Override
    public String getJsonFromCompaniesAllDetails(List<Company> companies) {

        List<JsonObject> jsonPersons = new ArrayList();

        for (Company c : companies) {

            JsonObject json = new JsonObject();
            JsonArray phones = new JsonArray();

            json.addProperty("id", c.getId());
            json.addProperty("name", c.getName());
            json.addProperty("description", c.getDescription());
            json.addProperty("email", c.getEmail());
            json.addProperty("cvr", c.getCvr());
            json.addProperty("numEmployees", c.getNumEmployees());
            json.addProperty("marketValue", c.getMarketValue());

            if (!c.getPhones().isEmpty()) {

                for (Phone phone : c.getPhones()) {

                    JsonObject jsonPhone = new JsonObject();
                    jsonPhone.addProperty("number", phone.getNumber());
                    jsonPhone.addProperty("description", phone.getDescription());

                    phones.add(jsonPhone);
                }

                json.add("phones", phones);
            }

            if (c.getAddress() != null) {
                json.addProperty("street", c.getAddress().getStreet());

                if (c.getAddress().getCity() != null) {
                    json.addProperty("zipcode", c.getAddress().getCity().getZipCode());
                    json.addProperty("city", c.getAddress().getCity().getCity());

                }
            }

            jsonPersons.add(json);

        }

        return getGsonInstance().toJson(jsonPersons);
    }

    @Override
    public String getJsonFromCompaniesContactInfo(List<Company> companies) {

        List<JsonObject> jsonCompanies = new ArrayList();

        for (Company c : companies) {

            JsonObject json = new JsonObject();
            JsonArray phones = new JsonArray();

            json.addProperty("id", c.getId());
            json.addProperty("name", c.getName());
            json.addProperty("email", c.getEmail());

            if (!c.getPhones().isEmpty()) {

                for (Phone phone : c.getPhones()) {

                    JsonObject jsonPhone = new JsonObject();
                    jsonPhone.addProperty("number", phone.getNumber());
                    jsonPhone.addProperty("description", phone.getDescription());

                    phones.add(jsonPhone);
                }

                json.add("phones", phones);
            }

            jsonCompanies.add(json);
        }

        return getGsonInstance().toJson(jsonCompanies);
    }

    @Override
    public String getJsonFromPersonAllDetailsIncHobbies(Person person) {
        JsonObject json = new JsonObject();
        JsonArray phones = new JsonArray();
        JsonArray hobbies = new JsonArray();

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

        if (!person.getHobbies().isEmpty()) {

            for (Hobby hobby : person.getHobbies()) {

                JsonObject jsonHobby = new JsonObject();
                jsonHobby.addProperty("name", hobby.getName());
                jsonHobby.addProperty("description", hobby.getDescription());

                hobbies.add(jsonHobby);
            }

            json.add("hobbies", hobbies);
        }

        return getGsonInstance().toJson(json);
    }
}
