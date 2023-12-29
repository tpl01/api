package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C03_UpdatecreateBooking extends HerokuAppBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id

    And
     {
    "firstname" : "Nazar",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "kahve"
}
    When
        Send put request

    Then
        Status code is 200

    And
        Body:
{
    "firstname": "Nazar",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "kahve"
}
     */

    @Test
    public void updateCreateBooking() {
        spec.pathParams("first","booking","second",C01_CreateBooking.bookingId);

        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01",
                "2019-01-01");

        BookingPojo payLoad=new BookingPojo("Nazar","Can",111,true,bookingDatesPojo,"kahve");

        Response response=given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getAdditionalneeds());



    }
}
