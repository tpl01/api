package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get02 {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
 */

    @Test
    public void test01() {
        //   i)  Url belirlenir
        String url = "https://restful-booker.herokuapp.com/booking/0";

        //   ii) Beklenen data belirlenir


        //   iii) Request gönderilip Response alınır
        Response response = given().when().get(url);
        response.prettyPrint();

        //   iv)) Doğrulamalar yapılır

        response.then()
                .statusLine("HTTP/1.1 404 Not Found")
                .statusCode(404)
                .body(containsString("Not Found"))
                .body(not(containsString("TechProEd")));

        //eski usul junitten assert yapma ile
        String responseStr=response.toString();
        assertTrue(responseStr.contains("Not Found"));
        assertFalse(responseStr.contains("TechProEd"));



    }
}
