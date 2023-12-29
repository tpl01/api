package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C05_DeleteBooking extends HerokuAppBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send delete request
    Then
        Status code is 200
    And
        Body should be : "Created"
     */

    @Test
    public void deleteBookingTest() {
        spec.pathParams("first", "booking", "second", C01_CreateBooking.bookingId);

        String expectedData = "Created";

        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, actualData);

    }
}