package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09_GroovyLanguage extends JsonPlaceHolderBaseUrl {
    /*
Given
     https://jsonplaceholder.typicode.com/todos
When
   I send GET Request to the URL
Then
   1)Status code is 200
   2)Print all ids greater than 190 on the console
     Assert that there are 10 ids greater than 190
   3)Print all userIds whose ids are less than 5 on the console
     Assert that the number of userIds whose ids are less than 5 is 4
   4)Print all titles whose ids are less than 5
     Assert that "delectus aut autem" is one of the titles whose id is less than 5
*/
    @Test
    public void get(){
        // i)  Url belirlenir
        spec.pathParam("first","todos");

        // ii) Beklenen data belirlenir
        // iii) Request gönderilip Response alınır
        Response response = given(spec).when().get("{first}");

        // iv)) Doğrulamalar yapılır
        JsonPath json = response.jsonPath();
        response
                .then()
                .statusCode(200);
        //Print all ids greater than 190 on the console

        // 1. Yol: Loop kullanarak
        List<Integer> idsGreaterThan190 = new ArrayList<>();

        List<Integer> idList = json.getList("id");

        for (Integer w:idList){
            if(w>190){
                idsGreaterThan190.add(w);
            }
        }
        System.out.println(idsGreaterThan190);

        // 2. Yol : Groovy Language, kolleksiyonlarda şartlı sorgular ile uğraşıyorsak Groovy L. çok kullanışlıdır.
        //Print all ids greater than 190 on the console

        List<Integer> idsGreater =json.getList("findAll{it.id>190}.id");
        System.out.println(idsGreater);

        // Assert that there are 10 ids greater than 190
        assertEquals(10,idsGreater.size());

        //Print all userIds whose ids are less than 5 on the console
        List<Integer> idsLessThanFive =  json.getList("findAll{it.id<5}.userId");
        System.out.println(idsLessThanFive);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,idsLessThanFive.size());

        // Print all titles whose ids are less than 5
        List<String> titlesLessThanFive =  json.getList("findAll{it.id<5}.title");
        System.out.println(titlesLessThanFive);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titlesLessThanFive.contains("delectus aut autem"));

    }
}