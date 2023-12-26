package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Post03 extends HerokuAppBaseUrl {

      /*
Given
1) https://restful-booker.herokuapp.com/booking
2) {
    "firstname": "John",
    "lastname": "Doe",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
     }
  }
When
I send POST Request to the Url
Then
Status code is 200
And response body should be like {
                                   "bookingid": 5315,
                                   "booking": {
                                       "firstname": "John",
                                       "lastname": "Doe",
                                       "totalprice": 11111,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-09",
                                           "checkout": "2021-09-21"
                                       }
                                   }
                                }
*/

    @Test
    public void post() {

        //url olustur
        spec.pathParam("first","booking");

        //beklenen data olustur

       // {
       //     "firstname": "John",
       //         "lastname": "Doe",
       //         "totalprice": 11111,
       //         "depositpaid": true,
       //         "bookingdates": {
       //     "checkin": "2021-09-09",
       //             "checkout": "2021-09-21"
       // }



        Map<String,String> bookings = bookingDatesMapper("2021-09-09","2021-09-21");
        Map<String,Object> payLoad = herokuAppMapper("John","Doe",11111,true,bookings,null);
        System.out.println("payLoad = " + payLoad);
        // Request--- Response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
        // Doğrulamalar yapılır:
        Map<String, Object> actualData =  response.as(HashMap.class);
        JsonPath json = response.jsonPath();

        assertEquals(200,response.statusCode());
        //assertEquals(payLoad.get("firstname"),json.getString("booking.firstname"));
        //assertEquals(bookings.get("checkin"),json.getString("booking.bookingdates.checkin"));

        assertEquals(payLoad.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(payLoad.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(payLoad.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(payLoad.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookings.get("checkin"),(((Map)((Map)actualData.get("booking")).get("bookingdates")) ).get("checkin"));
        assertEquals(bookings.get("checkout"),(((Map)((Map)actualData.get("booking")).get("bookingdates"))) .get("checkout")                  ) ;



    }
}
