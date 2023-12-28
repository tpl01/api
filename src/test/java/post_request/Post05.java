package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.HerokuAppPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;

public class Post05 extends HerokuAppBaseUrl {
         /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": ,bookingDates
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post(){
        spec.pathParam("first","booking");

        BookingPojo bookingDates= new BookingPojo("2021-09-21","2021-12-21");
        HerokuAppPojo payLoad = new HerokuAppPojo("Ali","Can",999,true,bookingDates,"Breakfast");

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuRootPojo actualData =response.as(HerokuRootPojo.class);

        System.out.println("actualData.getBookingid() = " + actualData.getBookingid());
        System.out.println("actualData.getBooking().getFirstname() = " + actualData.getBooking().getFirstname());

    }

}
