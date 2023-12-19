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


    }
}
