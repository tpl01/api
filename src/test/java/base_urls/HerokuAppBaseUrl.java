package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setup() {
        String baseUrl = "https://restful-booker.herokuapp.com";

        spec = new RequestSpecBuilder().setBaseUri(baseUrl).build();
    }
}
