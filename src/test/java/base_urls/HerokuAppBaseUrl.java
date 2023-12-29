package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){
        String baseUrl ="https://restful-booker.herokuapp.com";
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Cookie","token=71cac8d479ae990")
                .setBaseUri(baseUrl)
                .build();
    }
}
