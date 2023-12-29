package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerokuAppTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C04_PartiallyUpdateBooking extends HerokuAppBaseUrl {

      /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
    "firstname" : "Naz",
    "lastname" : "Canan",
    "additionalneeds" : "Çay"
}
    When
        Send  patch request
    Then
        Status code is 200
    And
       Body:
{
    "firstname": "Naz",
    "lastname": "Canan",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Çay"
}
     */

    @Test
    public void partiallyUpdateTest() {





        spec.pathParams("first","booking"
                ,"second",C01_CreateBooking.bookingId);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Naz","Canan",111,true,bookingDates,"Çay");

        Map<String,Object> payLoad = HerokuAppTestData.herokuAppMapper("Naz","Canan",null,null
                ,null,"Çay");

        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);




    }
}
