package heroku_smoke_test;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C02CreateBooking  extends HerokuAppBaseUrl {

     /*
    Test Case:  Booking oluşturma
    Given
        https://restful-booker.herokuapp.com/booking
    And
        body: {
                "firstname" : "Ali",
                "lastname" : "Can",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Kahvalti"
}
    When
        user send Post request
    Then
        validates Status code is 200
    And
        body is like
                        {
                        "bookingid": 1,
                        "booking": {
                            "firstname": "Ali",
                            "lastname": "Can",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Kahvalti"
                        }
                        }

     */

    @Test
    public void bookingolustutTest() {

        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates= new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo payLoad = new BookingPojo("Ali","Can",111,true,bookingDates,"Kahvalti");

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuRootPojo actualData = response.as(HerokuRootPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());



    }
}
