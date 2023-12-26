package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;
import test_data.JsonPlaceHoldertestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get10 extends JsonPlaceHolderBaseUrl {
      /*
Given
https://jsonplaceholder.typicode.com/todos/2
    When
        I send GET Request to the URL
    Then
        Status code is 200
    And
        "completed" is false
    And
        "userId" is 1
    And     "title" is "quis ut nam facilis et officia qui"
    And     header "Via" is "1.1 vegur"
    And     header "Server" is "cloudflare"
    And     body is like
        {
        "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false
        }
 */

    @Test
    public void get() {
        //url alınır
        spec.pathParams("first","todos","second",2);

        //beklenen data kurulur
        Map<String,Object> expectedData= JsonPlaceHoldertestData.
                jsonPlaceHolderMapper(1,"quis ut nam facilis et officia qui",false);
        //request gönder response alınır
       Response response= given(spec).when().get("{first}/{second}");
       response.prettyPrint();

       //doğrulama yapmak
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals("quis ut nam facilis et officia qui",actualData.get("title"));
        assertEquals(1,actualData.get("id"));
        assertEquals(false,actualData.get("completed"));




    }

    @Test
    public void get11b(){
        // Url kurulur:
        spec.pathParams("first","booking"
                ,"second", 99);

        // Beklenen data oluşturulur:
        // Nested yapılarda beklenen data oluşturulurken en iç yapıdan başlanır başlanır.

        Map<String,String> bookingDateMap =   HerokuAppTestData.bookingDatesMapper("2018-01-01","2019-01-01");
        System.out.println("bookingDateMap = " + bookingDateMap);

        Map<String ,Object> expectedData = HerokuAppTestData.herokuAppMapper("John","Smith",
                111,true,bookingDateMap,
                "Breakfast");
        System.out.println("expectedData = " + expectedData);

        // Request gönderip Response alınır:
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Doğrulamalar yapılır:

        // Ödev: body içerisinde Hamcrest ile assertion ları yapın
        response
                .then()
                .statusCode(200)
                .body("firstname",equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin",equalTo(bookingDateMap.get("checkin")));

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }
}

