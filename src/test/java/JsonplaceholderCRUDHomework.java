import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderCRUDHomework {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String COMMENTS = "comments";
    private static Faker faker;
    private String fakeName;
    private String fakeEmail;
    private String fakeBody;


    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();

    }
    @BeforeEach
    public void beforeEach(){
        fakeName = faker.funnyName().name();
        fakeEmail = faker.internet().emailAddress();
        fakeBody = faker.superhero().name();

        JSONObject comment = new JSONObject();
        comment.put("name", fakeName);
        comment.put("email", fakeEmail);
        comment.put("body", fakeBody);

    }

    @Test
    public void JsonplaceholderPostNewComment(){

        JSONObject comment = new JSONObject();
        comment.put("name", fakeName);
        comment.put("email", fakeEmail);
        comment.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(comment.toString())
                .when()
                .post(BASE_URL + "/" + COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeEmail, json.get("email"));

    }
    @Test
    public void JsonplaceholderPutNewComment(){

        JSONObject newComment = new JSONObject();
        newComment.put("name", fakeName);
        newComment.put("email", fakeEmail);
        newComment.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(newComment.toString())
                .when()
                .put(BASE_URL + "/" + COMMENTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeBody, json.get("body"));

    }
    @Test
    public void JsonplaceholderPatchNewBodyToComment(){

        JSONObject newComment1 = new JSONObject();
        newComment1.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(newComment1.toString())
                .when()
                .patch(BASE_URL + "/" + COMMENTS + "/12")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals(fakeBody, json.get("body"));

    }
    @Test
    public void JsonplaceholderDeleteComment(){

        Response response = given()
                .when()
                .delete(BASE_URL + "/" + COMMENTS + "/25")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

}
