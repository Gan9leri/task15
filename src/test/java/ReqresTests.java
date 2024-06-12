import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("API тесты")
@Tag("api")
public class ReqresTests extends TestBase {

    @DisplayName("Positive Тест получения данных пользователя")
    @Tag("api_getSingleUser")
    @Test
    void getSingleUser(){
        given().log().all().
                get("users/2").
                then().log().all().
                statusCode(200).
                body("data.first_name", is("Janet"));
    }

    @DisplayName("Positive Тест получения списка пользователей")
    @Tag("apy_getListUsers")
    @Test
    void getListUsers(){
        given().log().all().
                get("users?page=2").
                then().log().all().
                statusCode(200).
                body("total", is(12));
    }

    @DisplayName("Negative Тест получения данных пользователя")
    @Tag("api_getSingleUserNotFound")
    @Test
    void getSingleUserNotFound(){
        given().log().all().
                get("users/23").
                then().log().all().
                statusCode(404);
    }

    @DisplayName("Positive Тест получения списка материалов")
    @Tag("api_getListResource")
    @Test
    void getListResource(){
        given().log().all().
                get("unknown").
                then().log().all().
                statusCode(200).
                body("per_page", is(6));
    }

    @DisplayName("Positive Тест получения данных материала")
    @Tag("api_getSingleResource")
    @Test
    void getSingleResource(){
        given().log().all().
                get("unknown/2").
                then().log().all().
                statusCode(200).
                body("data.name", is("fuchsia rose"));
    }

    @DisplayName("Negative Тест получения данных материала")
    @Tag("tag_getSingleResourceNotFount")
    @Test
    void getSingleResourceNotFound(){
        given().log().all().
                get("unknown/23").
                then().log().all().
                statusCode(404);
    }
}