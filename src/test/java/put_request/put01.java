package put_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHoldertestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class put01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 55,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 55,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put() {

        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //expected data
        Map<String, Object> payLoad = JsonPlaceHoldertestData.jsonPlaceHolderMapper(55, "Wash the dishes", false);

        //Request gönder response alınacak
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        //doğrulama yapılır
        Map<String,Object> actualData=response.as(HashMap.class);
        // Desirialization : Json Objesini Java Objesine dönüştürmek

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));



    }
}
