package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReqresTest {

    @Test
    public void testPayEndpoint() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"79110000000\",\"money\":700}")
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body(equalTo("Баланс успешно обновлен"));
    }
    @Test
    public void testInvalidPhoneNumber() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"123456789\",\"money\":500}")
                .when()
                .post("/pay")
                .then()
                .statusCode(404)
                .body(equalTo("Не существует клиента с таким 'msisdn'"));
    }
}
