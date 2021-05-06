package tqsua.p1_rest_assured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TodoTests {
    @Test
    public void givenUrl_receiveStatusCode200() {
        get("https://jsonplaceholder.typicode.com/todos").then().assertThat().statusCode(200);
    }

    @Test
    public void givenATodoEntry_checkItsTitle() {
        get("https://jsonplaceholder.typicode.com/todos/4").then().assertThat().statusCode(200)
                .and().body("id", equalTo(4))
                .and().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void givenAllTodos_checkIfCertainIdsExist() {
        get("https://jsonplaceholder.typicode.com/todos").then().assertThat().statusCode(200)
                .and().body("id", hasItems(198, 199));
    }
}
