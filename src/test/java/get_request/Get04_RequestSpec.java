package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get04_RequestSpec extends JsonPlaceHolderBaseUrl {         //yeni ayarlamalar ile aynı get03
    //page object model olusturduk   (JsonPlaceHolderBaseUrl   clası olusturduk)

    @Test
    public void get() {

        //  i)  Url belirlenir
        //                 String url="https://jsonplaceholder.typicode.com/todos/23";

        spec.pathParams("first","todos",
                "second",23);


        //  ii) Beklenen data belirlenir

        //  iii) Request gönderilip Response alınır
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //  iv)) Doğrulamalar yapılır

        // Body de hard assertion yapacak isek her doğrulamada farklı body metodları kullanılır

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

        // Soft Assertion için tüm body sorgulamaları tek bir body metodu içinde yapılır
        response
                .then()
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        ,"completed",equalTo(false)
                        ,"userId",equalTo(2));


    }
}
