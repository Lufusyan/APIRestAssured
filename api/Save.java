package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Save {
    @Test
    public void testSuccessfulSubscriberCreation() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"79110000000\",\"name\":\"Иван\",\"balance\":100,\"tariffId\":12}")
                .when()
                .post("/save")
                .then()
                .statusCode(200)
                .body(equalTo("Новый абонент успешно создан"));
    }

    @Test
    public void testMissingNameField() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"79110000000\",\"balance\":100,\"tariffId\":12}")
                .when()
                .post("/save")
                .then()
                .statusCode(422)
                .body(equalTo("Ошибка валидации"));
    }
}
