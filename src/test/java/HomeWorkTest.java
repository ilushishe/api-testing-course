import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.concurrent.TimeUnit;



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
    public void lesson2Ex6() {
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
            System.out.println("\n Redirect url is: " +  redirectUrl);
        }
    }

    @Test
    public void lesson2Ex7() {
        int redirectCounter = 0;
        String url = "https://playground.learnqa.ru/api/long_redirect";
        while (true) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

            url = response.getHeader("Location");

            if (response.statusCode()==200) {
                break;
            } else if (url == null) {
                break;
            }

            redirectCounter++;
        }

        System.out.println("Redicrect count is: " + redirectCounter);
    }

    @Test
    public void lesson2Ex8() throws InterruptedException {
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.get("token");
        int timeOut = response.get("seconds");

        TimeUnit.SECONDS.sleep(timeOut);
        response = RestAssured
                .given()
                .queryParam("token", token)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response.get("status");
        String result = response.get("result");

        if (status.equals("Job is ready") && result!=null) {
            System.out.println("Test is passed \nStatus:" + status + "\nResult: " + result);
        } else {
            System.out.println("Test FAILED");
            response.prettyPrint();
        }
    }

    @Test
    public void lesson2Ex9() {

    }
}
