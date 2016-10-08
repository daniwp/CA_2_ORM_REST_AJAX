package testerpackage;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

/**
 *
 * @author John
 */
public class PersonIntegrationTest {

    private String baseUrl = "http://localhost:8084/CA2REST/api/person";

    public PersonIntegrationTest() {
    }

    @Test
    public void VerifyAllPersons() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/complete")
                .then()
                .statusCode(200)
                //Checking for dummy-data in several json objects
                .body("[0]", hasKey("firstname"))
                .body("[0]", hasKey("lastname"))
                .body("[0]", hasKey("email"));

    }

    @Test
    public void VerifyPersonById() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .get(baseUrl + "/complete/{id}")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("David"))
                .body("email", equalTo("dwp@dwp.com")); //email of person with id=1;
    }

    @Test
    public void VerifyContacts() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/contactinfo")
                .then()
                .statusCode(200)
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("email"));
    }

    @Test
    public void VerifyContactById() {
        int id = 1;
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(baseUrl + "/contactinfo/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void VerifyContactByIdException() {
        int id = 999;
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(baseUrl + "/contactinfo/{id}")
                .then()
                .statusCode(404)
                .body("message", equalTo("No person with that id was found"));
    }

}
