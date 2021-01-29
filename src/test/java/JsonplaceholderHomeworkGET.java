import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderHomeworkGET {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String COMMENTS = "comments";
    @Test
    public void JsonplaceholderReadAllComments(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        //System.out.println(response.asString());

        JsonPath json = response.jsonPath();
        List<String> Ids = json.getList("Id");

        assertEquals(500, Ids.size());


    }
    @Test
    public void JsonplaceholderReadOneComment(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + COMMENTS + "/20")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals("molestias expedita iste aliquid voluptates", json.get("name"));
        assertEquals("Mariana_Orn@preston.org", json.get("email"));

    }
    @Test
    public void JsonplaceholderGetOneCommentWithPathVariable(){
        Response response = given()
                .pathParam("id", "12")
                .when()
                .get(BASE_URL + "/" + COMMENTS + "/" + "{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals("Oswald.Vandervort@leanne.org",json.get("email"));
        assertEquals("modi ut eos dolores illum nam dolor", json.get("name"));

        // test sprawdzający pole"postId" nie przechodzi pomimo wpisywania liczby zarówno jako string i jako int.
        // Otrzymuję taki komunikat: .both method assertEquals(float,java.lang.Float) in org.junit.jupiter.api.Assertions and method assertEquals(double,java.lang.Double) in org.junit.jupiter.api.Assertions match

    }
    @Test
    public void JsonplaceholderGetOneCommentWithQueryParam(){
        Response response = given()
                .queryParam("postId", "2")
                .when()
                .get(BASE_URL + "/" + COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        // System.out.println(response.asString());

        JsonPath json = response.jsonPath();
        List<String> ids = json.getList("id");

        assertEquals(5,ids.size());

    }
}

