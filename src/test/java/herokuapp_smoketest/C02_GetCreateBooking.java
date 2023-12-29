package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class C02_GetCreateBooking extends HerokuAppBaseUrl {
      /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send get request
    Then
        Status code is 200
    And
        Body:
            {
    "firstname": "Veli",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Koy Kahvaltısı"
}
     */

    @Test
    public void getCreatedBookingtest() {

        spec.pathParams("first","booking","second",C01_CreateBooking.bookingId);


        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Veli","Can",111,true,bookingDates,"Koy Kahvaltısı");

        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

    }
}
