package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ChangeTariff {
    @Test
    public void testSuccessfulTariffChange() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"79110000000\",\"tariffId\":12}")
                .when()
                .patch("/changeTariff")
                .then()
                .statusCode(200)
                .body(equalTo("Тариф абонента успешно обновлен"));
    }

    @Test
    public void testInvalidTariffId() {
        RestAssured.baseURI = "http://localhost:8080/api/v1/CRM";

        given()
                .contentType(ContentType.JSON)
                .body("{\"msisdn\":\"79110000000\",\"tariffId\":999}")
                .when()
                .patch("/changeTariff")
                .then()
                .statusCode(422)
                .body(equalTo("Ошибка валидации"));
    }
}
