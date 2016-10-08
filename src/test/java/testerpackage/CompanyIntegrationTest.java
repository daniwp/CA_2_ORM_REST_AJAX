//package testerpackage;
//
//import static io.restassured.RestAssured.*;
//import io.restassured.http.ContentType;
//import static org.hamcrest.Matchers.*;
//import org.junit.Test;
//
///**
// *
// * @author John
// */
//public class CompanyIntegrationTest {
//    private String baseUrl = "http://localhost:8084/CA2REST/api/company";
//    
//    public CompanyIntegrationTest() {
//        
//    }
//    //1st test
//    @Test
//    public void VerifyAllCompanies() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get(baseUrl + "/complete")
//                .then()
//                .statusCode(200)
//                //Checking for dummy-data in several json objects
//                .body("[0]", hasKey("name"))
//                .body("[0]", hasKey("description"))
//                .body("[0]", hasKey("email"))
//                .body("[0]", hasKey("cvr"))
//                .body("[0]", hasKey("numEmployees"))
//                .body("[0]", hasKey("marketValue"));
//    }
//    //2nd test
//    @Test
//    public void VerifySingleCvr() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("cvr","384729")
//                .when()
//                .get(baseUrl + "/complete/cvr/{cvr}")
//                .then()
//                .statusCode(200)
//                .body("name", equalTo("DWP Web"));
//    }
//    //3rd test
//    @Test
//    public void VerifySingleCvrException() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("cvr","999999999")
//                .when()
//                .get(baseUrl + "/complete/cvr/{cvr}")
//                .then()
//                .statusCode(404)
//                .body("message", equalTo("No company with that cvr was found"));
//    }
//    //4th test
//    @Test
//    public void UndefinedCompany() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get(baseUrl)
//                .then()
//                .statusCode(404)
//                .body("message", equalTo("The service you requested does not exist."));
//    }
//    //5th test
//    @Test
//    public void VerifyPhone() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .pathParam("phone", "93949192")
//                .get(baseUrl + "/complete/phone/{phone}")
//                .then()
//                .statusCode(200)
//                .body("name", equalTo("DWP Web"));
//    }
//    //6th test
//    @Test
//    public void VerifyContactInfo() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get(baseUrl + "/contactinfo")
//                .then()
//                .statusCode(200)
//                .body("id", hasItems(1,2,3));
//    }
//    //7th test
//    @Test
//    public void VerifyDeleteCompanyException() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("id", "999")
//                .when()
//                .delete(baseUrl + "/{id}")
//                .then()
//                .statusCode(404)
//                .body("message", equalTo("No company with that id was found"));
//    }
//    
//    
//    
//    
//    
//}
//
