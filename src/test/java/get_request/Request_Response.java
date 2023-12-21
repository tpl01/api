package get_request;

import io.restassured.http.Header;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Request_Response {
 /*
        1)  Manuel testleri Postman kullanarak yapacağız
        2)  Otomasyon testi için Rest Library (Rest assured) kullanacağız
        3)  Test caseler yazılırken şu adımlar takip edilir
               a)  Önkoşullar iyi anlaşılmalı
               b) Test case yazılırken Gkerkin Dili kullanılır:
                  Gherkin Dili şu anahtar kelimeleri içerir:
                         i)     Given:  Ön şartlar için kullanılır --------> url , body .....
                         ii)    When: Aksiyon bildirir -------> get(), post(), .........
                         iii)   Then: Doğrulamalar yapılır
                         iV)    And: Tekrarlı koşulları bağlamakta kullanılınır.

        4)  Test otomasyonu yazılırken şu adımları izleyebiliriz:
            i)  Url belirlenir
            ii) Beklenen data belirlenir
            iii) Request gönderilip Response alınır
            iv)) Doğrulamalar yapılır

     */

    public static void main(String[] args) {
        // i)  Url belirlenir
        String url = "https://petstore.swagger.io/v2/pet/43";

        //ii) Beklenen data belirlenir ---> ilk testlerde atlayacağız

        // iii) Request gönderilip Response alınır
        Response response = given().when().get(url);
        // Response prettyPrint() mtd ile yazdırılır
        response.prettyPrint();

        //status code alma
        int statusCode=response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //status line nasıl
        System.out.println("statusLine() = " + response.statusLine());

        //content type nasıl ulaşıllır
        System.out.println("contentType() = " + response.contentType());

        //Headerlardan herhangibir degere ulasma

        String header=response.header("Server");
        System.out.println("header = " + header);

        System.out.println("***************************************");

        //headerlain hepsine ulasmak
        System.out.println("headers() = " + response.headers());

        //Response süresine ulaşma
        System.out.println("time() = " + response.time());


    }
}
