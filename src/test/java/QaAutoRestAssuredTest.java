import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class QaAutoRestAssuredTest extends BaseTest {


    @Test
    public void testRestAssuredExample() {
        RestAssured.baseURI = "https://qauto.forstudy.space";
        RestAssured.basePath = "/api/cars/brands";
        given()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("data[0].id", equalTo(1))
                .body("data[0].title", equalTo("Audi"));
    }

}
