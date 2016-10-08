package JsonConverterTests;

import com.google.gson.Gson;
import org.junit.*;
import entities.Person;
import jsonconverter.JSONConverter;
import static jsonconverter.JSONConverter.getClassInstance;
import static junit.framework.TestCase.*;
import com.google.gson.JsonObject;
import entities.Company;
import java.util.ArrayList;
import java.util.List;
import static jsonconverter.JSONConverter.getGsonInstance;

public class JsonConverterTest {

    protected JSONConverter converter = getClassInstance();
    protected Gson gson = getGsonInstance();
    protected Person p1 = new Person("Hans", "Christian", "hans@christian.dk");
    protected Person p2 = new Person("Peter", "FraLeasy", "peter@leasy.dk");
    protected Company c1 = new Company("TestCompany", "Test Description", "1234567890", 4, 100000, "test@email.com");

    @Test
    public void testGetJsonFromPersonAllDetails() {
        JsonObject obj = new JsonObject();
        String json = converter.getJsonFromPersonAllDetails(p1);

        obj.addProperty("firstname", p1.getFirstName());
        obj.addProperty("lastname", p1.getLastName());
        obj.addProperty("email", p1.getEmail());

        assertEquals(gson.toJson(obj), json);

    }

    @Test
    public void testGetJsonFromPersonsAlLDetails() {
        List<Person> plist = new ArrayList<Person>();
        List<JsonObject> jsonPersons = new ArrayList();
        plist.add(p1);
        plist.add(p2);
        String json = converter.getJsonFromPersonsAllDetails(plist);

        for (Person p : plist) {
            JsonObject obj = new JsonObject();
            obj.addProperty("firstname", p.getFirstName());
            obj.addProperty("lastname", p.getLastName());
            obj.addProperty("email", p.getEmail());

            jsonPersons.add(obj);
        }

        assertEquals(gson.toJson(jsonPersons), json);

    }

    @Test
    public void testGetPersonFromJson() {
        String json = converter.getJsonFromPersonAllDetails(p1);
        Person newPerson = converter.getPersonFromJson(json);

        assertEquals(p1, newPerson);

    }

    //Testing both JsonFromCompany and CompanyFromJson 
    @Test
    public void testgetCompanyFromJson() {
        String json = converter.getJsonFromCompanyAllDetails(c1);
        Company newCompany = converter.getCompanyFromJson(json);

        assertEquals(c1, newCompany);
    }



}
