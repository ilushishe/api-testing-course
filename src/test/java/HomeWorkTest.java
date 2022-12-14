import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class HomeWorkTest {

    @Test
    public void testHelloWorld() {
        System.out.println("Hello, world!");
    }

    @Test
    public void lesson1Ex3() {
        System.out.println("Hello from username");
    }

    @Test
    public void lesson1Ex4() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        response.getBody().print();
    }

    @Test
    public void lesson2Ex5() {
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        response.prettyPrint();

        String secondMessage = response.get("messages[1].message");
        if (secondMessage == null) {
            System.out.println("There is no second message");
        } else {
            System.out.println("\n Second message is: " +  secondMessage);

        }
    }

    @Test
    public  void lesson2Ex6() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String redirectUrl = response.getHeader("Location");
        if (redirectUrl == null) {
            System.out.println("There is no Location header");
        } else {
            System.out.println("\n Redicrect url is: " +  redirectUrl);

        }
    }
}
