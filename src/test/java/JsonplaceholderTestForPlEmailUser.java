import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderTestForPlEmailUser {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";

    @Test
    public void JsonplaceholderCheckForPLUsers(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        //System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        emails.stream()
                .filter(email->email.endsWith(".pl"))
                .forEach(System.out::println);


    }
}

