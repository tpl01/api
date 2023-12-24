package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setup() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseUrl).build();
    }
}
