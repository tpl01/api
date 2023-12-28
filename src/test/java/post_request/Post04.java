package post_request;
import  com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post04 extends JsonPlaceHolderBaseUrl {
     /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post() {
        //url olustur
        spec.pathParam("first","todos");

        //beklenen data
        JsonPlaceHolderPojo payLoad=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(payLoad);

        //request gönder response al
        Response response=given(spec).body(payLoad).when().get("{first}/{second}");
        response.prettyPrint();

        //dogrulama yap
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());




     //  NOT:
     //  @JsonIgnoreProperties(ignoreUnknown = true) annotasyonu, JSON verisindeki bilinmeyen alanları yoksaymak için kullanılır.
     //  Bu annotasyon, Java sınıfındaki alanlarla eşleşmeyen veya bilinmeyen JSON alanlarını görmezden gelir.

    }
}
