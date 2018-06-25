package net.c0nan.agic.integration;

import com.google.common.base.Strings;
import com.jayway.restassured.RestAssured;
import net.c0nan.agic.BaseIntegrationTest;
import net.c0nan.agic.models.error.validation.ToDoItemValidationError;
import net.c0nan.agic.models.result.BalanceTestResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;
import static net.c0nan.agic.utils.TaskTestUtil.BASIC_WORKING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskControllerIntegrationTest extends BaseIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @Before
    public void setup() {
        RestAssured.port = randomServerPort;
    }

    @Test
    public void testValidateBracketsViaREST() {
        String url = "/tasks/validateBrackets";
        BalanceTestResult result = given()
                .queryParam("input", BASIC_WORKING)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(BalanceTestResult.class);

        assertTrue(result.isBalanced());
    }

    @Test
    public void testSizeValidation() {
        String value = Strings.padStart("", 55, '{');
        value = Strings.padEnd(value, 110, '}');

        String url = "/tasks/validateBrackets";
        ToDoItemValidationError result = given()
                .queryParam("input", value)
                .when()
                .get(url)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().as(ToDoItemValidationError.class);

        assertEquals(result.getDetails().get(0).getMsg(), "Must be between 1 and 100 chars long");
    }

}
