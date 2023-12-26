package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get11_NestedMap extends HerokuAppBaseUrl {

      /*
        Given
            https://restful-booker.herokuapp.com/booking/51
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get() {

        //url kurma
        spec.pathParams("first","booking","second",51);

        //beklenen data
        //nested yapilarda data olusturulurken en icten baslanir

        Map<String,String> bookingDateMap=new HashMap<>();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDateMap);
        expectedData.put("additionalneeds","Breakfast");

        //request gönder response al
        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Ödev: body içerisinde Hamcrest ile assertion ları yapın
        response
                .then()
                .statusCode(200)
                .body("firstname",equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin",equalTo(bookingDateMap.get("checkin")));

        //doğrulama yap
        Map<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData,actualData);   //teker teker yapalım


        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));



    }


}
