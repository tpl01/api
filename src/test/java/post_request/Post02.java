package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static test_data.JsonPlaceHoldertestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {

    //Post01 deki task map olusturmayı metod icinde hallederek geliştirildiii
    @Test
    public void postMap() {
        // Url Oluşturulacak
        spec.pathParam("first", "todos");

        // Beklenen Datayı oluştur

        Map<String,Object> payLoad=jsonPlaceHolderMapper(55,"Tidy your room",false);

        // Request Gönder Response al
        Response response = given(spec).body(payLoad).when().post("{first}");
        // Serialization: Java objesini Json Objesine dönüştürme işlemine denir

        response.prettyPrint();
        // Serialization, Serializer denen JAckson Databind, Gson , Yasson gibi dependencyleri pom a yüklemek ile halledilir

        //doğrulama yapılır
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));

    }
}
