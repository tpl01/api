package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C01_CreateBooking extends HerokuAppBaseUrl {

            /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
    "firstname" : "Veli",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Koy Kahvaltısı"
}
     When
        Send post request

     Then
        Status code is 200

     And
        Body:
      {
    "bookingid": 1046,
    "booking": {
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
}

     */

    public static int bookingId;

    @Test
    public void createBookingTest() {

      spec.pathParam("first","booking");

        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo payLoad=new BookingPojo("Veli","Can",111,true,bookingDates,"Koy Kahvaltısı");

        Response response=given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuRootPojo actualData=response.as(HerokuRootPojo.class);   //kök şablon

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

       bookingId= response.jsonPath().getInt("bookingid");

    }
}
