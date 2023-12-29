package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C06_GetDeleteBooking extends HerokuAppBaseUrl {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/:id
   When
       Send get request
   Then
       Status code is 404
   And
       Body is "Not Found"
    */
    @Test
    public void confirmDeleteTest(){
        spec.pathParams("first","booking","second",C01_CreateBooking.bookingId);

        String expectedData = "Not Found";

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,actualData);
    }

}


