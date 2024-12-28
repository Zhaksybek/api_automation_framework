package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponses;

public class ApiRunner {

    // This is getter method for CustomResponses. To be able to read value of our CustomResponses
    @Getter
    private static CustomResponses customResponses;

    public static CustomResponses runGET(String path)  {

      String url = Config.getProperty("basURI")   + path;

        Response response = RestAssured.given()
                .auth().oauth2(  CashwiseAuthorization.getToken() )
                .get(url);

        ObjectMapper mapper = new ObjectMapper();

        try {
             customResponses = mapper.readValue(response.asString(), CustomResponses.class );
        } catch (JsonProcessingException e) {
            System.out.println("Maybe LIST response");
            throw new RuntimeException(e);
        }

        return customResponses;

    }


}
