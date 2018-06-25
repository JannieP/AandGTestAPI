package net.c0nan.agic.integration;

import com.jayway.restassured.RestAssured;
import net.c0nan.agic.BaseIntegrationTest;
import net.c0nan.agic.models.ToDoItem;
import net.c0nan.agic.models.error.notfound.ToDoItemNotFoundError;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.jayway.restassured.RestAssured.given;
import static net.c0nan.agic.utils.ToDoTestUtil.TEXT;
import static net.c0nan.agic.utils.ToDoTestUtil.TEXT2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TodoControllerIntegrationTest extends BaseIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    private Long id = 0L;

    @Before
    public void setup() {
        RestAssured.port = randomServerPort;
    }

    @Test
    public void testPostViaREST() {
        String url = "/todo";
        ToDoItem result = given()
                .body("{\"text\": \"" + TEXT + "\"}")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ToDoItem.class);

        id = result.getId();

        assertEquals(result.getText(), TEXT);
        assertTrue(result.getId() > 0);

    }

    @Test
    public void testGetViaREST() {

        testPostViaREST();

        String url = "/todo/" + id;
        ToDoItem result = given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ToDoItem.class);

        assertEquals(result.getId(), id);
        assertEquals(result.getText(), TEXT);
    }

    @Test
    public void testPatchViaREST() {

        testGetViaREST();

        String url = "/todo/" + id;
        ToDoItem result = given()
                .body("{\"text\": \"" + TEXT2 + "\"}")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ToDoItem.class);

        assertEquals(result.getId(), id);
        assertEquals(result.getText(), TEXT2);
    }

    @Test
    public void testGetNotFoundViaREST() {

        String url = "/todo/" + 12345L;
        ToDoItemNotFoundError result = given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract().as(ToDoItemNotFoundError.class);

        assertEquals(result.getName(), "NotFoundError");
        assertEquals(result.getDetails().get(0).getMessage(), "Item with id 12345 not found");
    }

}
